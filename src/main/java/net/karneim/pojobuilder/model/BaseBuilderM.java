package net.karneim.pojobuilder.model;

import javax.annotation.Generated;

public class BaseBuilderM extends ClassM {
  private TypeM productType;

  public BaseBuilderM() {
    this.getAdditionalImports().add(TypeM.get(Generated.class.getName()));
  }

  public TypeM getPojoType() {
    return productType;
  }

  public void setPojoType(TypeM productType) {
    this.productType = productType;
  }

  @Override
  public String toString() {
    return "BaseBuilderM [pojoType=" + productType + ",type=" + getType() + ",superType=" + getSuperType()
        + ",abstract=" + isAbstract() + "]";
  }

}
