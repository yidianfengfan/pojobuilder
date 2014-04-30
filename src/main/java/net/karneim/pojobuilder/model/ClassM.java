package net.karneim.pojobuilder.model;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClassM {
  private TypeM type;
  private TypeM superType;
  private boolean abstractClass;
  private Date created;
  private List<TypeM> additionalImports = new ArrayList<TypeM>();

  public ClassM() {
    super();
  }

  public void setType(TypeM type) {
    this.type = type;
  }

  public void setSuperType(TypeM superType) {
    this.superType = superType;
  }

  public void setAbstract(boolean abstractClass) {
    this.abstractClass = abstractClass;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public Date getCreated() {
    return created;
  }

  public String getCreatedString() {
    return DateFormat.getDateInstance().format(created);
  }

  public TypeM getType() {
    return type;
  }

  public TypeM getSuperType() {
    return superType;
  }

  public boolean isAbstract() {
    return abstractClass;
  }

  public List<TypeM> getAdditionalImports() {
    return additionalImports;
  }

  @Override
  public String toString() {
    return "ClassM[type=" + type + ",superType=" + superType + ",created=" + created + "]";
  }

}
