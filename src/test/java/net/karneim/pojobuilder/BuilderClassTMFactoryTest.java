package net.karneim.pojobuilder;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import net.karneim.pojobuilder.codegen.BuilderClassTM;
import net.karneim.pojobuilder.codegen.FieldTM;
import net.karneim.pojobuilder.codegen.SetterTM;
import net.karneim.pojobuilder.model.FactoryM;
import net.karneim.pojobuilder.model.PropertyM;
import net.karneim.pojobuilder.model.TypeM;
import net.karneim.pojobuilder.model.TypeParameterM;

import org.junit.Test;

// TODO split this test into multiple classes, each testing a distinct feature
public class BuilderClassTMFactoryTest {

    BuilderClassTMFactory underTest = new BuilderClassTMFactory();

    @Test
    public void testAllwaysShouldContainGeneratedAnnotation() {
        // Given:
        underTest.setType(TypeM.get("MyPojoBuilder"));
        underTest.setSelfType(TypeM.get("MyPojoBuilder"));
        underTest.setPojoType(TypeM.get("MyPojo"));

        // When:
        BuilderClassTM act = underTest.build();

        // Then:
        assertThat(act.getGenerated()).isNotNull();
        assertThat(act.getGenerated().getName()).isEqualTo("PojoBuilder");
        assertThat(act.getImports()).hasSize(1);
        assertThat(stringsOf(act.getImports())).contains("javax.annotation.Generated");

    }

    @Test
    public void testSimpleName() {
        // Given:
        String simpleName = "MyPojoBuilder";
        TypeM type = TypeM.get(simpleName);
        underTest.setType(type);
        underTest.setSelfType(type);
        underTest.setPojoType(TypeM.get("MyPojo"));

        // When:
        BuilderClassTM act = underTest.build();

        // Then:
        assertThat(act.getName()).isEqualTo(simpleName);
    }

    @Test
    public void testClassnameWithGenerics() {
        // Given:
        String simpleName = "MyPojoBuilder";
        TypeM type = TypeM.get(simpleName);
        type.getTypeParameters().add(new TypeParameterM(TypeM.get("java.lang.String")));
        underTest.setType(type);
        underTest.setSelfType(type);
        underTest.setPojoType(TypeM.get("MyPojo"));

        // When:
        BuilderClassTM act = underTest.build();

        // Then:
        assertThat(act.getName()).isEqualTo("MyPojoBuilder<String>");
    }

    @Test
    public void testSelfField() {
        // Given:
        String simpleName = "MyPojoBuilder";
        TypeM type = TypeM.get(simpleName);
        underTest.setType(type);
        underTest.setSelfType(type);
        underTest.setPojoType(TypeM.get("MyPojo"));

        // When:
        BuilderClassTM act = underTest.build();

        // Then:
        assertThat(act.getSelfField().getType()).isEqualTo(simpleName);
    }

    @Test
    public void testSelfFieldWithGenerics() {
        // Given:
        String simpleName = "MyPojoBuilder";
        TypeM type = TypeM.get(simpleName);
        type.getTypeParameters().add(new TypeParameterM(TypeM.get("java.lang.String")));
        underTest.setType(type);
        underTest.setSelfType(type);
        underTest.setPojoType(TypeM.get("MyPojo"));

        // When:
        BuilderClassTM act = underTest.build();

        // Then:
        assertThat(act.getSelfField().getType()).isEqualTo("MyPojoBuilder<String>");
    }

    @Test
    public void testSimpleNameHasNoPackage() {
        // Given:
        String simpleName = "MyPojoBuilder";
        TypeM type = TypeM.get(simpleName);
        underTest.setType(type);
        underTest.setSelfType(type);
        underTest.setPojoType(TypeM.get("MyPojo"));

        // When:
        BuilderClassTM act = underTest.build();

        // Then:
        assertThat(act.getPackage()).isNull();
    }

    @Test
    public void testQualifiedName() {
        // Given:
        String packageName = "com.example";
        String simpleName = "MyPojoBuilder";
        String qualifiedName = packageName + "." + simpleName;
        TypeM type = TypeM.get(qualifiedName);
        underTest.setType(type);
        underTest.setSelfType(type);
        underTest.setPojoType(TypeM.get("com.example.MyPojo"));

        // When:
        BuilderClassTM act = underTest.build();

        // Then:
        assertThat(act.getName()).isEqualTo(simpleName);
        assertThat(act.getPackage().toString()).isEqualTo(packageName);
    }

    @Test
    public void testBuildMethod() {
        // Given:
        String simpleName = "MyPojoBuilder";
        TypeM type = TypeM.get(simpleName);
        underTest.setType(type);
        underTest.setSelfType(type);
        underTest.setPojoType(TypeM.get("MyPojo"));

        // When:
        BuilderClassTM act = underTest.build();

        // Then:
        assertThat(act.getName()).isEqualTo(simpleName);
        assertThat(act.getBuildMethod()).isNotNull();
        assertThat(act.getBuildMethod().getReturnType()).isEqualTo("MyPojo");
    }

    @Test
    public void testConstructor() {
        // Given:
        String qualifiedName = "com.example.MyPojoBuilder";
        TypeM type = TypeM.get(qualifiedName);
        underTest.setType(type);
        underTest.setSelfType(type);
        underTest.setPojoType(TypeM.get("com.example.MyPojo"));

        // When:
        BuilderClassTM act = underTest.build();

        // Then:
        assertThat(act.getConstructor()).isNotNull();
        assertThat(act.getConstructor().getName()).isEqualTo("MyPojoBuilder");
        assertThat(act.getConstructor().getSelfCast()).isEqualTo("MyPojoBuilder");
    }

    @Test
    public void testConstructorShouldUseGenericTypeInSelfCast() {
        // Given:
        String qualifiedName = "com.example.MyPojoBuilder";
        TypeM type = TypeM.get(qualifiedName);
        type.getTypeParameters().add(new TypeParameterM(TypeM.get("java.lang.String")));
        underTest.setType(type);
        underTest.setSelfType(type);
        underTest.setPojoType(TypeM.get("com.example.MyPojo"));

        // When:
        BuilderClassTM act = underTest.build();

        // Then:
        assertThat(act.getConstructor()).isNotNull();
        assertThat(act.getConstructor().getName()).isEqualTo("MyPojoBuilder");
        assertThat(act.getConstructor().getSelfCast()).isEqualTo("MyPojoBuilder<String>");
    }

    @Test
    public void testConstructorShouldUseGenericTypeInSelfCast_2() {
        // Given:
        String qualifiedName = "com.example.MyPojoBuilder";
        TypeM type = TypeM.get(qualifiedName);
        type.getTypeParameters().add(new TypeParameterM(TypeM.get("org.sample.Data")));
        underTest.setType(type);
        underTest.setSelfType(type);
        underTest.setPojoType(TypeM.get("com.example.MyPojo"));

        // When:
        BuilderClassTM act = underTest.build();

        // Then:
        assertThat(act.getConstructor()).isNotNull();
        assertThat(act.getConstructor().getName()).isEqualTo("MyPojoBuilder");
        assertThat(act.getConstructor().getSelfCast()).isEqualTo("MyPojoBuilder<Data>");
    }

    @Test
    public void testImportShouldContainGenericType() {
        // Given:
        String qualifiedName = "com.example.MyPojoBuilder";
        TypeM type = TypeM.get(qualifiedName);
        type.getTypeParameters().add(new TypeParameterM(TypeM.get("org.sample.Data")));
        underTest.setType(type);
        underTest.setSelfType(type);
        underTest.setPojoType(TypeM.get("com.example.MyPojo"));

        // When:
        BuilderClassTM act = underTest.build();

        // Then:
        assertThat(stringsOf(act.getImports())).contains("org.sample.Data");
    }

    @Test
    public void testImportShouldContainGenericType2() {
        // Given:
        TypeM comparableType = TypeM.get("my.custom.Comparable");
        comparableType.getTypeParameters().add(new TypeParameterM(TypeM.get("A")));
        String qualifiedName = "com.example.MyPojoBuilder";
        TypeM builderType = TypeM.get(qualifiedName);
        builderType.getTypeParameters().add(new TypeParameterM(TypeM.get("A")));
        builderType.getTypeParameters().add(new TypeParameterM(comparableType));

        underTest.setType(builderType);
        underTest.setSelfType(builderType);
        underTest.setPojoType(TypeM.get("com.example.MyPojo"));

        // When:
        BuilderClassTM act = underTest.build();

        // Then:
        assertThat(stringsOf(act.getImports())).contains("my.custom.Comparable");
        assertThat(stringsOf(act.getImports())).doesNotContain("A");
    }

    @Test
    public void testImportShouldContainGenericType3() {
        // Given:
        TypeM comparableType = TypeM.get("my.custom.Comparable");
        TypeM dataType = TypeM.get("my.custom.Data");
        comparableType.getTypeParameters().add(new TypeParameterM(dataType));
        String qualifiedName = "com.example.MyPojoBuilder";
        TypeM builderType = TypeM.get(qualifiedName);
        builderType.getTypeParameters().add(new TypeParameterM(dataType));
        builderType.getTypeParameters().add(new TypeParameterM(comparableType));

        underTest.setType(builderType);
        underTest.setSelfType(builderType);
        underTest.setPojoType(TypeM.get("com.example.MyPojo"));

        // When:
        BuilderClassTM act = underTest.build();

        // Then:
        assertThat(stringsOf(act.getImports())).contains("my.custom.Comparable", "my.custom.Data");
    }

    @Test
    public void testImportShouldContainGenericTypes() {
        // Given:
        String qualifiedName = "com.example.MyPojoBuilder";
        TypeM type = TypeM.get(qualifiedName);
        type.getTypeParameters().add(new TypeParameterM(TypeM.get("org.sample.Data")));
        type.getTypeParameters().add(new TypeParameterM(TypeM.get("org.sample.Data2")));
        underTest.setType(type);
        underTest.setSelfType(type);
        underTest.setPojoType(TypeM.get("com.example.MyPojo"));

        // When:
        BuilderClassTM act = underTest.build();

        // Then:
        assertThat(stringsOf(act.getImports())).contains("org.sample.Data", "org.sample.Data2");
    }

    @Test
    public void testImportShouldContainAllGenericTypes() {
        // Given:
        TypeM arg1 = TypeM.get("org.sample.Data");
        arg1.getTypeParameters().add(new TypeParameterM(TypeM.get("temp.util.MyType")));
        String qualifiedName = "com.example.MyPojoBuilder";
        TypeM type = TypeM.get(qualifiedName);
        type.getTypeParameters().add(new TypeParameterM(arg1));
        underTest.setType(type);
        underTest.setSelfType(type);
        underTest.setPojoType(TypeM.get("com.example.MyPojo"));

        // When:
        BuilderClassTM act = underTest.build();

        // Then:
        assertThat(stringsOf(act.getImports())).contains("org.sample.Data", "temp.util.MyType");
    }

    @Test
    public void testImportShouldNotContainAnyImportsFromOwnPackage() {
        // Given:
        TypeM type = TypeM.get("com.example.MyPojoBuilder");
        underTest.setType(type);
        underTest.setSelfType(type);
        underTest.setPojoType(TypeM.get("com.example.MyPojo"));

        // When:
        BuilderClassTM act = underTest.build();

        // Then:
        assertThat(stringsOf(act.getImports())).doesNotContain("com.example.MyPojoBuilder");
        assertThat(stringsOf(act.getImports())).doesNotContain("com.example.*");
    }

    @Test
    public void testImportShouldNotContainAnyImportsFromOwnPackage2() {
        // Given:
        TypeM type = TypeM.get("com.example.MyPojoBuilder");
        type.getTypeParameters().add(new TypeParameterM(TypeM.get("com.example.Data")));
        underTest.setType(type);
        underTest.setSelfType(type);
        underTest.setPojoType(TypeM.get("com.example.MyPojo"));

        // When:
        BuilderClassTM act = underTest.build();

        // Then:
        assertThat(stringsOf(act.getImports())).doesNotContain("com.example.MyPojoBuilder", "com.example.Data");
        assertThat(stringsOf(act.getImports())).doesNotContain("com.example.*");
    }

    @Test
    public void testShouldAddFieldForProperty() {
        // Given:
        underTest.setType(TypeM.get("MyPojoBuilder"));
        underTest.setSelfType(TypeM.get("MyPojoBuilder"));
        underTest.setPojoType(TypeM.get("com.example.MyPojo"));
        TypeM type = TypeM.get("java.lang.String");
        String name = "name";
        String fieldname = "name$java$lang$String";
        PropertyM p = new PropertyM(name, fieldname, type);
        p.setAccessible(true);
        p.setWritable(true);
        underTest.addProperty(p);

        // When:
        BuilderClassTM act = underTest.build();

        // Then:
        assertThat(act.getFields()).hasSize(1);
        FieldTM f1 = act.getFields().get(0);
        assertThat(f1.getName()).isEqualTo(fieldname);
        assertThat(f1.getType()).isEqualTo("String");
    }

    @Test
    public void testShouldAddFieldForEachProperty() {
        // Given:
        underTest.setType(TypeM.get("MyPojoBuilder"));
        underTest.setSelfType(TypeM.get("MyPojoBuilder"));
        underTest.setPojoType(TypeM.get("com.example.MyPojo"));
        PropertyM p1 = new PropertyM("name", "name$java$lang$String", TypeM.get("java.lang.String"));
        p1.setParameterPos(0);
        underTest.addProperty(p1);
        PropertyM p2 = new PropertyM("birthdate", "birthdate$java$util$Date", TypeM.get("java.util.Date"));
        p2.setParameterPos(1);
        underTest.addProperty(p2);

        // When:
        BuilderClassTM act = underTest.build();

        // Then:
        assertThat(act.getFields()).hasSize(2);
        FieldTM f1 = act.getFields().get(0);
        assertThat(f1.getName()).isEqualTo("name$java$lang$String");
        assertThat(f1.getType()).isEqualTo("String");
        FieldTM f2 = act.getFields().get(1);
        assertThat(f2.getName()).isEqualTo("birthdate$java$util$Date");
        assertThat(f2.getType()).isEqualTo("Date");
    }

    @Test
    public void testShouldAddSetterMethodForProperty() {
        // Given:
        TypeM type = TypeM.get("java.lang.String");
        String name = "name";
        String fieldname = "name$java$lang$String";
        underTest.setType(TypeM.get("MyPojoBuilder"));
        underTest.setSelfType(TypeM.get("MyPojoBuilder"));
        underTest.setPojoType(TypeM.get("com.example.MyPojo"));
        PropertyM p = new PropertyM(name, fieldname, type);
        p.setAccessible(true);
        p.setWritable(true);
        underTest.addProperty(p);

        // When:
        BuilderClassTM act = underTest.build();

        // Then:
        assertThat(act.getSetters()).hasSize(1);
        SetterTM setter = act.getSetters().get(0);
        assertThat(setter.getName()).isEqualTo("withName");
        assertThat(setter.getFieldname()).isEqualTo(fieldname);
        assertThat(setter.getType()).isEqualTo("String");
        assertThat(setter.getReturnType()).isEqualTo("MyPojoBuilder");
    }

    @Test
    public void testShouldAddSetterMethodForEachProperty() {
        // Given:
        underTest.setType(TypeM.get("MyPojoBuilder"));
        underTest.setSelfType(TypeM.get("MyPojoBuilder"));
        underTest.setPojoType(TypeM.get("com.example.MyPojo"));
        PropertyM p1 = new PropertyM("name", "name$java$lang$String", TypeM.get("java.lang.String"));
        p1.setParameterPos(0);
        underTest.addProperty(p1);
        PropertyM p2 = new PropertyM("birthdate", "birthdate$java$util$Date", TypeM.get("java.util.Date"));
        p2.setParameterPos(1);
        underTest.addProperty(p2);

        // When:
        BuilderClassTM act = underTest.build();

        // Then:
        assertThat(act.getSetters()).hasSize(2);
        SetterTM setter1 = act.getSetters().get(0);
        assertThat(setter1.getName()).isEqualTo("withName");
        assertThat(setter1.getFieldname()).isEqualTo("name$java$lang$String");
        assertThat(setter1.getType()).isEqualTo("String");
        assertThat(setter1.getReturnType()).isEqualTo("MyPojoBuilder");
        SetterTM setter2 = act.getSetters().get(1);
        assertThat(setter2.getName()).isEqualTo("withBirthdate");
        assertThat(setter2.getFieldname()).isEqualTo("birthdate$java$util$Date");
        assertThat(setter2.getType()).isEqualTo("Date");
        assertThat(setter2.getReturnType()).isEqualTo("MyPojoBuilder");
    }

    @Test
    public void testShouldAddConstructorArgumentInBuildMethodForPropertyInConstructor() {
        // Given:
        TypeM type = TypeM.get("java.lang.String");
        String name = "name";
        String fieldname = "name$java$lang$String";
        underTest.setType(TypeM.get("MyPojoBuilder"));
        underTest.setSelfType(TypeM.get("MyPojoBuilder"));
        underTest.setPojoType(TypeM.get("com.example.MyPojo"));
        PropertyM p = new PropertyM(name, fieldname, type);
        p.setParameterPos(0);
        underTest.addProperty(p);

        // When:
        BuilderClassTM act = underTest.build();

        // Then:
        assertThat(act.getBuildMethod()).isNotNull();
        assertThat(act.getBuildMethod().getConstruction().isConstructorCall()).isTrue();
        assertThat(act.getBuildMethod().getConstruction().getArguments()).hasSize(1);
        assertThat(act.getBuildMethod().getConstruction().getArguments().get(0).getName()).isEqualTo(fieldname);
    }

    @Test
    public void testShouldAddConstructorArgumentInBuildMethodForEachPropertyInConstructor() {
        // Given:
        underTest.setType(TypeM.get("MyPojoBuilder"));
        underTest.setSelfType(TypeM.get("MyPojoBuilder"));
        underTest.setPojoType(TypeM.get("com.example.MyPojo"));
        PropertyM p1 = new PropertyM("name", "name$java$lang$String", TypeM.get("java.lang.String"));
        p1.setParameterPos(0);
        underTest.addProperty(p1);
        PropertyM p2 = new PropertyM("birthdate", "birthdate$java$util$Date", TypeM.get("java.util.Date"));
        p2.setParameterPos(1);
        underTest.addProperty(p2);

        // When:
        BuilderClassTM act = underTest.build();

        // Then:
        assertThat(act.getBuildMethod()).isNotNull();
        assertThat(act.getBuildMethod().getConstruction().isConstructorCall()).isTrue();
        assertThat(act.getBuildMethod().getConstruction().getArguments()).hasSize(2);
        assertThat(act.getBuildMethod().getConstruction().getArguments().get(0).getName()).isEqualTo(
                "name$java$lang$String");
        assertThat(act.getBuildMethod().getConstruction().getArguments().get(1).getName()).isEqualTo(
                "birthdate$java$util$Date");
    }

    @Test
    public void testShouldAddFactoryCallArgumentInBuildMethodForPropertyInFactoryMethod() {
        // Given:
        TypeM type = TypeM.get("java.lang.String");
        String name = "name";
        String fieldname = "name$java$lang$String";
        underTest.setType(TypeM.get("MyPojoBuilder"));
        underTest.setSelfType(TypeM.get("MyPojoBuilder"));
        underTest.setPojoType(TypeM.get("com.example.MyPojo"));
        PropertyM p = new PropertyM(name, fieldname, type);
        p.setParameterPos(0);
        underTest.addProperty(p);
        underTest.setFactory(new FactoryM(TypeM.get("MyPojoFactory"), "createMyPojo"));

        // When:
        BuilderClassTM act = underTest.build();

        // Then:
        assertThat(act.getBuildMethod()).isNotNull();
        assertThat(act.getBuildMethod().getConstruction().isConstructorCall()).isFalse();
        assertThat(act.getBuildMethod().getConstruction().getArguments()).hasSize(1);
        assertThat(act.getBuildMethod().getConstruction().getArguments().get(0).getName()).isEqualTo(fieldname);
    }

    @Test
    public void testShouldAddFactoryCallArgumentInBuildMethodForEachPropertyInFactoryMethod() {
        // Given:
        underTest.setType(TypeM.get("MyPojoBuilder"));
        underTest.setSelfType(TypeM.get("MyPojoBuilder"));
        underTest.setPojoType(TypeM.get("com.example.MyPojo"));
        PropertyM p1 = new PropertyM("name", "name$java$lang$String", TypeM.get("java.lang.String"));
        p1.setParameterPos(0);
        underTest.addProperty(p1);
        PropertyM p2 = new PropertyM("birthdate", "birthdate$java$util$Date", TypeM.get("java.util.Date"));
        p2.setParameterPos(1);
        underTest.addProperty(p2);
        underTest.setFactory(new FactoryM(TypeM.get("MyPojoFactory"), "createMyPojo"));

        // When:
        BuilderClassTM act = underTest.build();

        // Then:
        assertThat(act.getBuildMethod()).isNotNull();
        assertThat(act.getBuildMethod().getConstruction().isConstructorCall()).isFalse();
        assertThat(act.getBuildMethod().getConstruction().getArguments()).hasSize(2);
        assertThat(act.getBuildMethod().getConstruction().getArguments().get(0).getName()).isEqualTo(
                "name$java$lang$String");
        assertThat(act.getBuildMethod().getConstruction().getArguments().get(1).getName()).isEqualTo(
                "birthdate$java$util$Date");
    }

    @Test
    public void testShouldAddAssignmentInBuildMethod() {
        // Given:
        TypeM type = TypeM.get("java.lang.String");
        String name = "name";
        String fieldname = "name$java$lang$String";
        underTest.setType(TypeM.get("MyPojoBuilder"));
        underTest.setSelfType(TypeM.get("MyPojoBuilder"));
        underTest.setPojoType(TypeM.get("com.example.MyPojo"));
        PropertyM p = new PropertyM(name, fieldname, type);
        p.setSetter(null); // no setter
        p.setAccessible(true);
        p.setWritable(true);
        underTest.addProperty(p);

        // When:
        BuilderClassTM act = underTest.build();

        // Then:
        assertThat(act.getBuildMethod()).isNotNull();
        assertThat(act.getBuildMethod().getConstruction().getArguments()).hasSize(0);
        assertThat(act.getBuildMethod().getSetterCalls()).hasSize(0);
        assertThat(act.getBuildMethod().getAssignments()).hasSize(1);
        assertThat(act.getBuildMethod().getAssignments().get(0).getFieldname()).isEqualTo(fieldname);
        assertThat(act.getBuildMethod().getAssignments().get(0).getPojoFieldname()).isEqualTo(name);
    }

    @Test
    public void testShouldAddSetterCallInBuildMethod() {
        // Given:
        TypeM type = TypeM.get("java.lang.String");
        String name = "name";
        String fieldname = "name$java$lang$String";
        underTest.setType(TypeM.get("MyPojoBuilder"));
        underTest.setSelfType(TypeM.get("MyPojoBuilder"));
        underTest.setPojoType(TypeM.get("com.example.MyPojo"));
        PropertyM p = new PropertyM(name, fieldname, type);
        p.setSetter("setName");
        underTest.addProperty(p);

        // When:
        BuilderClassTM act = underTest.build();

        // Then:
        assertThat(act.getBuildMethod()).isNotNull();
        assertThat(act.getBuildMethod().getConstruction().getArguments()).hasSize(0);
        assertThat(act.getBuildMethod().getSetterCalls()).hasSize(1);
        assertThat(act.getBuildMethod().getSetterCalls().get(0).getFieldname()).isEqualTo(fieldname);
        assertThat(act.getBuildMethod().getSetterCalls().get(0).getMethodName()).isEqualTo("setName");
    }

    // @Test
    // public void test() {
    // // Given:
    //
    // // When:
    // BuilderClassTM act = underTest.build();
    //
    // // Then:
    //
    // }

    // Helper methods
    private List<String> stringsOf(List<? extends Object> objects) {
        List<String> result = new ArrayList<String>();
        for (Object o : objects) {
            if (o == null) {
                result.add(null);
            } else {
                result.add(o.toString());
            }
        }
        return result;
    }

}
