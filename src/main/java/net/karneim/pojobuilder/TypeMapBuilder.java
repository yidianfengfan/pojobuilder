package net.karneim.pojobuilder;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import net.karneim.pojobuilder.model.TypeM;

@SuppressWarnings("serial")
public class TypeMapBuilder extends HashSet<TypeM> {
    private TypeM compilationUnit;

    public TypeMapBuilder(TypeM compilationUnit) {
        if (compilationUnit == null) {
            throw new NullPointerException("Type of compilation unit must not be null!");
        }
        this.compilationUnit = compilationUnit;
    }

    public TypeMap build() {
        Map<String, String> visibleTypes = new HashMap<String, String>();
        if ( compilationUnit.getImportName() != null) {
            visibleTypes.put(compilationUnit.getSimpleName(), compilationUnit.getImportName());
        }
        for (TypeM type : this) {
            if (type != null && type.getImportName() != null && !visibleTypes.containsKey(type.getSimpleName()) && areInSamePackage(compilationUnit, type) ) {
                visibleTypes.put(type.getSimpleName(), type.getImportName());
                System.out.println(String.format("(1)put %s=%s", type.getSimpleName(), type.getImportName()));
            }
        }
        for (TypeM type : this) {
            if (type != null && type.getImportName() != null && !visibleTypes.containsKey(type.getSimpleName()) && "java.lang".equals(type.getPackage()) ) {
                visibleTypes.put(type.getSimpleName(), type.getImportName());
                System.out.println(String.format("(2)put %s=%s", type.getSimpleName(), type.getImportName()));
            }
        }
        for (TypeM type : this) {
            if (type != null && type.getImportName() != null && !visibleTypes.containsKey(type.getSimpleName()) ) {
                visibleTypes.put(type.getSimpleName(), type.getImportName());
                System.out.println(String.format("(3)put %s=%s", type.getSimpleName(), type.getImportName()));
            }
        }
        return new TypeMap(compilationUnit.getImportName(), new HashSet<String>(visibleTypes.values()));
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
