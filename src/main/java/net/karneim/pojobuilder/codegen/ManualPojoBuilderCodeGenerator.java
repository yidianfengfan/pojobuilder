package net.karneim.pojobuilder.codegen;

import java.io.IOException;
import java.io.Writer;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroupFile;

/**
 * @since 3.0
 * @author karneim
 */
public class ManualPojoBuilderCodeGenerator {

    private final ManualBuilderClassTM model;

    public ManualPojoBuilderCodeGenerator(ManualBuilderClassTM model) {
        super();
        this.model = model;
    }

    public void generate(Writer writer) throws IOException {
        STGroupFile templateFile = new STGroupFile("net/karneim/pojobuilder/codegen/ManualPojoBuilder.stg");
        ST template = templateFile.getInstanceOf("classTemplate");
        template.add("manualBuilderClassTM", model);
        String text = template.render();
        writer.append(text);
    }
}
