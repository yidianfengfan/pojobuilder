package net.karneim.pojobuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import net.karneim.pojobuilder.codegen.ArgumentTM;
import net.karneim.pojobuilder.codegen.AssignmentTM;
import net.karneim.pojobuilder.codegen.BuildAssignmentTM;
import net.karneim.pojobuilder.codegen.BuildMethodTM;
import net.karneim.pojobuilder.codegen.BuilderClassTM;
import net.karneim.pojobuilder.codegen.ButMethodTM;
import net.karneim.pojobuilder.codegen.CloneMethodTM;
import net.karneim.pojobuilder.codegen.ConstructionTM;
import net.karneim.pojobuilder.codegen.ConstructorCallTM;
import net.karneim.pojobuilder.codegen.ConstructorTM;
import net.karneim.pojobuilder.codegen.CopyMethodTM;
import net.karneim.pojobuilder.codegen.FactoryCallTM;
import net.karneim.pojobuilder.codegen.FieldAccessorTM;
import net.karneim.pojobuilder.codegen.FieldTM;
import net.karneim.pojobuilder.codegen.GeneratedTM;
import net.karneim.pojobuilder.codegen.ImportTM;
import net.karneim.pojobuilder.codegen.InterfaceTM;
import net.karneim.pojobuilder.codegen.MethodAccessorTM;
import net.karneim.pojobuilder.codegen.PackageTM;
import net.karneim.pojobuilder.codegen.SelfFieldTM;
import net.karneim.pojobuilder.codegen.SetterCallTM;
import net.karneim.pojobuilder.codegen.SetterTM;
import net.karneim.pojobuilder.codegen.SuperclassTM;
import net.karneim.pojobuilder.model.FactoryM;
import net.karneim.pojobuilder.model.PropertyM;
import net.karneim.pojobuilder.model.TypeM;
import net.karneim.pojobuilder.util.PropertyMIterable;

public class BuilderClassTMFactory {
    private static final TypeM CLONEABLE = TypeM.get(Cloneable.class.getName());
    private static final TypeM JAVAX_ANNOTATON_GENERATED = TypeM.get(javax.annotation.Generated.class.getName());

    private TypeM type;
    private TypeM selfType;
    private TypeM superclass;
    private List<PropertyM> properties = new ArrayList<PropertyM>();
    private FactoryM factory;
    private TypeM pojoType;
    private List<TypeM> constructionExceptions = new ArrayList<TypeM>();
    private boolean abstractClass;
    private boolean generateCopyMethod;

    public void setType(TypeM type) {
        this.type = type;
    }

    public void setSelfType(TypeM selfType) {
        this.selfType = selfType;
    }

    public void setSuperclass(TypeM superclass) {
        this.superclass = superclass;
    }

    public void setPojoType(TypeM pojoType) {
        this.pojoType = pojoType;
    }

    public void addProperty(PropertyM p) {
        properties.add(p);
    }

    public void addConstructionException(TypeM exceptionType) {
        constructionExceptions.add(exceptionType);
    }

    public void setFactory(FactoryM factory) {
        this.factory = factory;
    }

    public void setAbstractClass(boolean abstractClass) {
        this.abstractClass = abstractClass;
    }

    public void setGenerateCopyMethod(boolean generateCopyMethod) {
        this.generateCopyMethod = generateCopyMethod;
    }

    public BuilderClassTM build() {
        if (type == null) {
            throw new IllegalStateException(
                    String.format("Can't build template model! Value of 'type' must not be null."));
        }
        if (pojoType == null) {
            throw new IllegalStateException(
                    String.format("Can't build template model! Value of 'pojoType' must not be null."));
        }

        // collect all referenced types
        TypeSet typeSet = new TypeSet(type);
        JAVAX_ANNOTATON_GENERATED.addTo(typeSet);
        CLONEABLE.addTo(typeSet);
        if (superclass != null) {
            superclass.addTo(typeSet);
        }
        pojoType.addTo(typeSet);
        for (PropertyM prop : properties) {
            prop.addTo(typeSet);
        }
        for (TypeM ex : constructionExceptions) {
            ex.addTo(typeSet);
        }
        selfType.addTo(typeSet);
        if (factory != null) {
            factory.getOwnerType().addTo(typeSet);
        }
        
        // Create Template Model for the Builder

        BuilderClassTM result = new BuilderClassTM();
        // set @Generated annotation
        result.setGenerated(new GeneratedTM("PojoBuilder"));

        // set superclass
        if (superclass != null) {
            result.setSuperclass(new SuperclassTM(typeSet.getTypeString(superclass)));
        }

        // set simple name
        result.setName(typeSet.getTypeDeclarationString(type));
        result.setAbstractClass(abstractClass);

        // set self field
        result.setSelfField(new SelfFieldTM(typeSet.getTypeString(selfType)));
        // set package
        if (type.getPackage() != null) {
            result.setPackage(new PackageTM(type.getPackage()));
        }
        // set constructor
        result.setConstructor(new ConstructorTM(typeSet.getClassname(type), typeSet.getTypeString(selfType)));

        // process properties
        for (PropertyM p : iterate(properties).filterMutable(true)) {
            // add field for property
            FieldTM field = new FieldTM(p.getFieldname(), typeSet.getTypeString(p.getType()));
            field.setMandatory(p.isMandatoryParameter());
            result.getFields().add(field);
            // add setter ("with"-methods)
            result.getSetters().add(
                    new SetterTM(getSetterNameFor(p.getName()), p.getFieldname(), typeSet.getTypeString(p.getType()),
                            typeSet.getTypeString(selfType)));
        }
        // add build method
        BuildMethodTM buildMethod = new BuildMethodTM();
        buildMethod.setReturnType(typeSet.getTypeString(pojoType));
        ConstructionTM construction;
        if (factory != null) {
            TypeM factoryTypeM = factory.getOwnerType();
            construction = new FactoryCallTM();
            construction.setMethodName(typeSet.getStaticMethodCall(factoryTypeM, factory.getMethodName()));

        } else {
            construction = new ConstructorCallTM();
            construction.setMethodName(typeSet.getTypeString(pojoType));
        }
        buildMethod.setConstruction(construction);
        // declare build exceptions from construction
        for (TypeM exType : constructionExceptions) {
            buildMethod.getThrownExceptions().add(typeSet.getTypeString(exType));
        }

        result.setBuildMethod(buildMethod);
        for (PropertyM p : iterate(properties).filterMutable(true).filterMandatory().orderByParameterPos()) {
            // add property as constructor/factory argument
            result.getBuildMethod().getConstruction().getArguments().add(new ArgumentTM(p.getFieldname()));
            for (TypeM exTypeM : p.getSetterExceptions()) {
                buildMethod.getThrownExceptions().add(typeSet.getTypeString(exTypeM));
            }
        }
        for (PropertyM p : iterate(properties).filterMutable(true).filterMandatory(false)) {
            // add assignments of pojo properties
            if (p.isHasSetter()) {
                result.getBuildMethod().getSetterCalls().add(new SetterCallTM(p.getSetter(), p.getFieldname()));
                for (TypeM exTypeM : p.getSetterExceptions()) {
                    buildMethod.getThrownExceptions().add(typeSet.getTypeString(exTypeM));
                }
            } else if (p.isWritable()) {
                result.getBuildMethod().getAssignments().add(new BuildAssignmentTM(p.getFieldname(), p.getName()));
            }
        }

        // implement Cloneable
        result.getInterfaces().add(new InterfaceTM(typeSet.getTypeString(CLONEABLE)));

        // add clone method
        result.setCloneMethod(new CloneMethodTM(typeSet.getTypeString(selfType)));
        // add "but" method
        result.setButMethod(new ButMethodTM(typeSet.getTypeString(selfType)));

        // add "copy" method
        if (generateCopyMethod) {
            CopyMethodTM copyMethod = new CopyMethodTM(typeSet.getTypeString(selfType), typeSet.getTypeString(pojoType));
            result.setCopyMethod(copyMethod);
            for (PropertyM p : iterate(properties).filterMutable(true)) {
                if (p.isHasGetter()) {
                    copyMethod.getAssignments().add(
                            new AssignmentTM(getSetterNameFor(p.getName()), new MethodAccessorTM(p.getGetter())));
                } else if (p.isReadable()) {
                    copyMethod.getAssignments().add(
                            new AssignmentTM(getSetterNameFor(p.getName()), new FieldAccessorTM(p.getName())));
                }
            }
        }

        Set<String> importSet = typeSet.getImportSet();

        // collect imports
        List<String> importList = new ArrayList<String>(importSet);
        Collections.sort(importList);
        for (String typeName : importList) {
            result.getImports().add(new ImportTM(typeName));
        }
        return result;
    }

    public <T extends PropertyM, SELF extends PropertyMIterable<T, SELF>> PropertyMIterable<T, SELF> iterate(
            List<T> list) {
        return new PropertyMIterable<T, SELF>(list);
    }

    private String getSetterNameFor(String name) {
        // TODO prefix should be configurable
        String setterPrefix = "with";
        return setterPrefix.concat(firstCharUpperCase(name));
    }

    private String firstCharUpperCase(String text) {
        checkNotNull(text, "text==null");
        checkArgument(!text.isEmpty(), "text is empty");
        String result = text.substring(0, 1).toUpperCase().concat(text.substring(1));
        return result;
    }

    private void checkArgument(boolean condition, String message) {
        if (!condition) {
            throw new IllegalArgumentException(message);
        }
    }

    private void checkNotNull(Object obj, String message) {
        if (obj == null) {
            throw new NullPointerException(message);
        }
    }

   

}
