package net.karneim.pojobuilder;

import java.util.HashSet;
import java.util.Set;

import net.karneim.pojobuilder.model.TypeM;
import net.karneim.pojobuilder.model.TypeParameterM;

public class TypeMap {
    private String compilationUnit;
    private Set<String> visibleTypes;

    public TypeMap(String compilationUnit, Set<String> visibleTypes) {
        //log(visibleTypes.toArray());
        this.compilationUnit = compilationUnit;
        if ( visibleTypes == null) {
            throw new NullPointerException("visibleTypes must not be null!");
        }
        this.visibleTypes = visibleTypes;
    }

    private void log(Object[] array) {
        for (Object o : array) {
            System.out.println(o);
        }
    }

    public String getTypeString(TypeM type) {
        String result = getClassname(type);
        if (type.isGeneric()) {
            StringBuilder b = new StringBuilder();
            for (TypeParameterM param : type.getTypeParameters()) {
                if (b.length() > 0) {
                    b.append(", ");
                }
                b.append(getTypeString(param.getType()));
            }
            result += "<" + b.toString() + ">";
        }
        return result;
    }

    public String getTypeDeclarationString(TypeM type) {
        String result = getClassname(type);
        if (type.isGeneric()) {
            StringBuilder b = new StringBuilder();

            for (TypeParameterM param : type.getTypeParameters()) {
                if (b.length() > 0) {
                    b.append(", ");
                }
                b.append(getTypeString(param.getType()));
                if (param.isBounded()) {
                    b.append(" extends ");
                    StringBuilder b2 = new StringBuilder();
                    for (TypeM bound : param.getBounds()) {
                        if (b2.length() > 0) {
                            b2.append(" & ");
                        }
                        b2.append(getTypeDeclarationString(bound));
                    }
                    b.append(b2.toString());
                }
            }
            result += "<" + b.toString() + ">";
        }

        return result;
    }

    // TODO what to do with type params?
    public String getStaticMethodCall(TypeM classType, String methodName) {
        String namespace = getClassname(classType);
        return namespace + "." + methodName;
    }

    public String getClassname(TypeM type) {
        if ( type == null) {
            throw new NullPointerException("type must not be null!");
        }
        if ((compilationUnit != null && compilationUnit.equals(type.getQualifiedName())) || visibleTypes.contains(type.getQualifiedName())) {
            return type.getSimpleName();
        } else {
            return type.getQualifiedName();
        }
    }

    public Set<String> getImportSet() {
        Set<String> result = new HashSet<String>();
        for (String type : visibleTypes) {
            if (type != null && !isCompilationUnit(type) && !areInSamePackage(compilationUnit, type)
                    && !isInJavaLangPackage(type) && !isInDefaultPackage(type)) {
                result.add(type);
            }
        }
        return result;
    }

    private boolean isCompilationUnit(String type) {
        return compilationUnit != null && compilationUnit.equals(type);
    }

    private boolean isInDefaultPackage(String type) {
        return extractPackage(type) == null;
    }

    private boolean isInJavaLangPackage(String type) {
        return "java.lang".equals(extractPackage(type));
    }

    private boolean areInSamePackage(String type1, String type2) {
        return areEqual(extractPackage(type1), extractPackage(type2));
    }

    private boolean areEqual(Object objA, Object objB) {
        if (objA == null && objB == null) {
            return true;
        }
        return objA != null && objA.equals(objB);
    }

    public String getImportName(String qualifiedName) {
        if (extractPackage(qualifiedName) == null) {
            return null;
        }
        int idx = qualifiedName.indexOf('[');
        if (idx > -1) {
            return qualifiedName.substring(0, idx);
        } else {
            return qualifiedName;
        }
    }

    private static String extractPackage(String qualifiedName) {
        if (qualifiedName == null) {
            return null;
        }
        int idx = qualifiedName.lastIndexOf('.');
        if (idx == -1) {
            return null;
        } else {
            return qualifiedName.substring(0, idx);
        }
    }

    private static String extractSimpleName(String qualifiedName) {
        if (qualifiedName == null) {
            return null;
        }
        int idx = qualifiedName.lastIndexOf('.');
        if (idx == -1) {
            return qualifiedName;
        } else {
            return qualifiedName.substring(idx + 1);
        }
    }

}
