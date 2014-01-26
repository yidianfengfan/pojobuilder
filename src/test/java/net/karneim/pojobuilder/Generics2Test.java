package net.karneim.pojobuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;

import net.karneim.pojobuilder.codegen.BuilderClassTM;
import net.karneim.pojobuilder.codegen.ImportTM;
import net.karneim.pojobuilder.codegen.PojoBuilderCodeGenerator;
import net.karneim.pojobuilder.model.BuilderM;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import testdata.factory.Contact;
import testdata.factory.PojoFactory;
import testdata.generics.Pair;
import testenv.AddToSourceTree;
import testenv.ProcessingEnvironmentRunner;

@RunWith(ProcessingEnvironmentRunner.class)
@AddToSourceTree({ TestBase.SRC_TESTDATA_DIR })
public class Generics2Test extends TestBase {

    private ProcessingEnvironment env;

    private GeneratePojoBuilderProcessor underTest;

    @Before
    public void setup() {
        env = ProcessingEnvironmentRunner.getProcessingEnvironment();
        underTest = new GeneratePojoBuilderProcessor(env);
    }

    @Test
    public void testPairWithGenerics() throws IOException {
        // Given:
        String pojoClassname = Pair.class.getCanonicalName();
        TypeElement pojoType = env.getElementUtils().getTypeElement(pojoClassname);

        // When:
        Output output = underTest.testProcess(pojoType);
        BuilderM builder = output.getBuilder();
        BuilderClassTM tm = underTest.generateTemplateModel(builder);

        // Then:
        assertEquals("type", "PairBuilder<A extends Comparable<A>, B extends Number>", builder.getType()
                .getGenericTypeSimpleNameWithBounds());

        assertThat( toStringList(tm.getImports())).containsOnly("java.lang.Comparable","java.lang.Number","javax.annotation.Generated" );
    }
    
    @Test
    public void testContactWithFactory() throws IOException {
        // Given:
        //String pojoClassname = Contact.class.getCanonicalName();
        //TypeElement pojoType = env.getElementUtils().getTypeElement(pojoClassname);
        String factoryClassname = PojoFactory.class.getCanonicalName();
        TypeElement factoryTypeElement = env.getElementUtils().getTypeElement(factoryClassname);
        List<ExecutableElement> methods = ElementFilter.methodsIn(env.getElementUtils().getAllMembers(factoryTypeElement));
        ExecutableElement factoryMethod = getFirstMethodByName("createContact", methods);
        
        // When:
        Output output = underTest.testProcess(factoryMethod);
        BuilderM builder = output.getBuilder();
        BuilderClassTM tm = underTest.generateTemplateModel(builder);

        PojoBuilderCodeGenerator newGenerator = new PojoBuilderCodeGenerator(tm);
        StringWriter writer = new StringWriter();
        newGenerator.generate(writer);
        writer.close();
        System.out.println(writer);
        
        // Then:
        assertEquals("type", "ContactBuilder", builder.getType()
                .getGenericTypeSimpleNameWithBounds());

        assertThat( toStringList(tm.getImports())).containsOnly("java.lang.String","javax.annotation.Generated" );
        assertThat(tm.getBuildMethod().getConstruction().getMethodName()).isEqualTo("PojoFactory.createContact");
    }
    
    public List<String> toStringList( List<ImportTM> imports) {
        List<String> result = new ArrayList<String>();
        for (ImportTM imp : imports) {
            result.add(imp.toString());
        }
        return result;
    }

}
