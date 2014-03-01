package net.karneim.pojobuilder;

import java.util.HashSet;
import java.util.Set;

import net.karneim.pojobuilder.model.TypeM;
import net.karneim.pojobuilder.model.TypeParameterM;

public class TypeSet {
	private final Set<TypeM> types = new HashSet<TypeM>();
	private final Set<String> imports = new HashSet<String>();
	private final TypeM compilationUnit;

	public TypeSet(TypeM compilationUnit) {
		if (compilationUnit == null) {
			throw new NullPointerException(
					"Type of compilation unit must not be null!");
		}
		this.compilationUnit = compilationUnit;
	}

	public void add(TypeM type) {
		types.add(type);
		if (mustBeQualified(type) && !isImported(type)) {
			addToImports(type);
		}
	}

	private boolean isImported(TypeM type) {
		return imports.contains(type.getClassName());
	}

	private void addToImports(TypeM type) {
		imports.add(type.getClassName());
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
		if (type == null) {
			throw new NullPointerException("type must not be null!");
		}
		if (!mustBeQualified(type) || isImported(type)) {
			return type.getSimpleName();
		}
		return type.getQualifiedName();
	}

	public Set<String> getImportSet() {
		return imports;
	}

	private boolean mustBeQualified(TypeM type) {
		return type != null && !isCompilationUnit(type)
				&& !areInSamePackage(compilationUnit, type)
				&& !isInJavaLangPackage(type) && !isInDefaultPackage(type);
	}

	private boolean isInJavaLangPackage(TypeM type) {
		return isInPackage(type, "java.lang");
	}

	private boolean isInPackage(TypeM type, String packageName) {
		return areEqual(type.getPackage(), packageName);
	}

	private boolean isCompilationUnit(TypeM type) {
		String importName = type.getClassName();
		String importName2 = compilationUnit.getClassName();
		return importName.equals(importName2);
	}

	private boolean isInDefaultPackage(TypeM type) {
		return type.getPackage() == null;
	}

}
