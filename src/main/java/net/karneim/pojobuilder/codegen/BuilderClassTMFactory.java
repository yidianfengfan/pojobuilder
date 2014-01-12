package net.karneim.pojobuilder.codegen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.karneim.pojobuilder.model.FactoryM;
import net.karneim.pojobuilder.model.PropertyM;
import net.karneim.pojobuilder.model.TypeM;
import net.karneim.pojobuilder.util.PropertyMIterable;

public class BuilderClassTMFactory {

    private TypeM type;
    private List<PropertyM> properties = new ArrayList<PropertyM>();
    private FactoryM factory;
    private TypeM pojoType;
    private boolean abstractClass;
    
    public void setType(TypeM type) {
        this.type = type;
    }

    public void setPojoType(TypeM pojoType) {
        this.pojoType = pojoType;
    }

    public void addProperty(PropertyM p) {
        properties.add(p);
    }

    public void setFactory(FactoryM factory) {
        this.factory = factory;
    }
    public void setAbstractClass(boolean abstractClass) {
        this.abstractClass = abstractClass;
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
        Set<String> importSet = new HashSet<String>();

        BuilderClassTM result = new BuilderClassTM();
        // set @Generated annotation
        result.setGenerated(new GeneratedTM("PojoBuilder"));
        importSet.add("javax.annotation.Generated");
        
        // set simple name
        result.setName(type.getGenericTypeSimpleNameWithBounds());
        result.setAbstractClass( abstractClass);
        
        // set self field
        result.setSelfField( new SelfFieldTM(type.getGenericTypeSimpleName()));
        // set package
        if (type.getPackage() != null) {
            result.setPackage(new PackageTM(type.getPackage()));
        }
        // set constructor
        result.setConstructor(new ConstructorTM(type.getSimpleName(), type.getGenericTypeSimpleName()));
        // add type to imports
        type.exportImportTypes(importSet);
        // add pojoType to imports
        pojoType.exportImportTypes(importSet);
        
        // process properties
        for (PropertyM p : properties) {
            // add property to imports
            p.getType().exportImportTypes(importSet);
            // add field for property
            FieldTM field = new FieldTM(p.getFieldname(), p.getType().getSimpleName());
            field.setMandatory(p.isMandatoryParameter());
            result.getFields().add(field);
            // add setter ("with"-methods)
            result.getSetters().add(
                    new SetterTM(getSetterNameFor(p.getName()), p.getFieldname(), p.getType()
                            .getGenericTypeSimpleName(), type.getGenericTypeSimpleName()));
        }
        // add build method
        BuildMethodTM buildMethod = new BuildMethodTM();
        buildMethod.setReturnType(pojoType.getGenericTypeSimpleName());
        ConstructionTM construction;
        if ( factory != null) {
            construction = new FactoryCallTM();
            construction.setMethodName(factory.getMethodName());
        } else {
            construction = new ConstructorCallTM();
            construction.setMethodName( pojoType.getGenericTypeSimpleName());
        }
        buildMethod.setConstruction(construction);
        result.setBuildMethod( buildMethod);
        for( PropertyM p: iterate(properties).filterMandatory().orderByParameterPos()) {
            // add property as constructor/factory argument
            result.getBuildMethod().getConstruction().getArguments().add( new ArgumentTM(p.getFieldname()));
        }
        for( PropertyM p: iterate(properties).filterMandatory(false)) {
            // add assignments of pojo properties
            if ( p.isHasSetter()) {
                result.getBuildMethod().getSetterCalls().add( new SetterCallTM(p.getSetter(), p.getFieldname()));
            } else if ( p.isAccessible()) {
                result.getBuildMethod().getAssignments().add( new BuildAssignmentTM(p.getFieldname(), p.getName()));    
            }
            
        }

        // make sure that the builder's own type is not going to be imported
        importSet.remove(type.getQualifiedName());
        // Make sure that no import of types from the builder's own package is
        // going to be imported
        Iterator<String> importIt = importSet.iterator();
        while (importIt.hasNext()) {
            String importArg = importIt.next();
            TypeM importType = TypeM.get(importArg);
            if (areInSamePackage(type, importType)) {
                importIt.remove();
            }
        }
        // collect imports
        List<String> importList = new ArrayList<String>(importSet);
        Collections.sort(importList);
        for (String typeName : importList) {
            result.getImports().add(new ImportTM(typeName));
        }
        return result;
    }

    public <T extends PropertyM, SELF extends PropertyMIterable<T, SELF>> PropertyMIterable<T, SELF> iterate(List<T> list) {
        return new PropertyMIterable<T,SELF>(list);
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

    private boolean areInSamePackage(TypeM typeA, TypeM typeB) {
        return areEqual(typeA.getPackage(), typeB.getPackage());
    }

    private boolean areEqual(Object objA, Object objB) {
        if (objA == null && objB == null) {
            return true;
        }
        return objA != null && objA.equals(objB);
    }

   

}
