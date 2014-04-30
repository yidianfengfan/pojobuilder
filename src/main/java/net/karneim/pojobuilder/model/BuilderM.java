package net.karneim.pojobuilder.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuilderM extends BaseBuilderM {
  private TypeM selfType;
  private FactoryM factory;
  private final Map<String, PropertyM> properties = new HashMap<String, PropertyM>();
  private boolean isImplementingCopyMethod;
  private List<TypeM> constructionExceptions = new ArrayList<TypeM>();

  public void setSelfType(TypeM selfType) {
    this.selfType = selfType;
  }

  public FactoryM getFactory() {
    return factory;
  }

  public void setFactory(FactoryM factory) {
    this.factory = factory;
  }

  public boolean isUsingFactory() {
    return factory != null;
  }

  public void setIsImplementingCopyMethod(boolean isImplementingCopyMethod) {
    this.isImplementingCopyMethod = isImplementingCopyMethod;
  }

  public boolean isImplementingCopyMethod() {
    return isImplementingCopyMethod;
  }

  public List<PropertyM> getProperties() {
    return new ArrayList<PropertyM>(properties.values());
  }

  public TypeM getSelfType() {
    return selfType;
  }

  public List<TypeM> getConstructionExceptions() {
    return constructionExceptions;
  }

  public PropertyM getOrCreateProperty(String propertyName, TypeM propertyType) {
    String fieldname = computeBuilderFieldname(propertyName, propertyType.getQualifiedName());
    PropertyM result = properties.get(fieldname);
    if (result == null) {
      result = new PropertyM(propertyName, fieldname, propertyType);
      properties.put(fieldname, result);
    }
    return result;
  }

  public PropertyM getProperty(String propertyName, TypeM propertyType) {
    String fieldname = computeBuilderFieldname(propertyName, propertyType.getQualifiedName());
    PropertyM result = properties.get(fieldname);
    return result;
  }

  private static String computeBuilderFieldname(String propertyName, String propertyType) {
    String typeString = propertyType.replaceAll("\\.", "\\$");
    typeString = typeString.replaceAll("\\[\\]", "\\$");
    return propertyName + "$" + typeString;
  }

}
