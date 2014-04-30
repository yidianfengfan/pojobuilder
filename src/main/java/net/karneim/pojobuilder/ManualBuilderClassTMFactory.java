package net.karneim.pojobuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import net.karneim.pojobuilder.codegen.GeneratedTM;
import net.karneim.pojobuilder.codegen.ImportTM;
import net.karneim.pojobuilder.codegen.ManualBuilderClassTM;
import net.karneim.pojobuilder.codegen.PackageTM;
import net.karneim.pojobuilder.codegen.SuperclassTM;
import net.karneim.pojobuilder.model.TypeM;

public class ManualBuilderClassTMFactory {
  private static final TypeM CLONEABLE = TypeM.get(Cloneable.class.getName());
  private static final TypeM JAVAX_ANNOTATON_GENERATED = TypeM.get(javax.annotation.Generated.class.getName());

  private TypeM type;
  private TypeM superclass;
  private TypeM pojoType;
  private boolean abstractClass;

  public void setType(TypeM type) {
    this.type = type;
  }

  public void setSuperclass(TypeM superclass) {
    this.superclass = superclass;
  }

  public void setPojoType(TypeM pojoType) {
    this.pojoType = pojoType;
  }

  public void setAbstractClass(boolean abstractClass) {
    this.abstractClass = abstractClass;
  }

  public ManualBuilderClassTM build() {
    if (type == null) {
      throw new IllegalStateException(String.format("Can't build template model! Value of 'type' must not be null."));
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

    // Create Template Model for the Builder

    ManualBuilderClassTM result = new ManualBuilderClassTM();
    // set @Generated annotation
    result.setGenerated(new GeneratedTM("PojoBuilder"));

    // set superclass
    if (superclass != null) {
      result.setSuperclass(new SuperclassTM(typeSet.getTypeString(superclass)));
    }

    result.setName(typeSet.getTypeDeclarationString(type));
    result.setClassname(typeSet.getClassname(type));
    result.setAbstractClass(abstractClass);

    result.setPojoClassname(typeSet.getClassname(pojoType));
    // set package
    if (type.getPackage() != null) {
      result.setPackage(new PackageTM(type.getPackage()));
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


}
