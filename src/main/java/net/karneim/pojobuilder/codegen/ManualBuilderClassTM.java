package net.karneim.pojobuilder.codegen;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@link ManualBuilderClassTM} is the template model of a manual modifiable pojo builder class.
 * 
 * @since 3.0
 * @author karneim
 */
public class ManualBuilderClassTM {
  private PackageTM _package;
  private List<ImportTM> imports = new ArrayList<ImportTM>();
  private GeneratedTM generated;
  private String name;

  private List<InterfaceTM> interfaces = new ArrayList<InterfaceTM>();
  private SuperclassTM superclass;
  private boolean abstractClass;

  private String pojoClassname;
  private String classname;

  public PackageTM getPackage() {
    return _package;
  }

  public void setPackage(PackageTM _package) {
    this._package = _package;
  }

  public List<ImportTM> getImports() {
    return imports;
  }

  public void setImports(List<ImportTM> imports) {
    this.imports = imports;
  }

  public GeneratedTM getGenerated() {
    return generated;
  }

  public void setGenerated(GeneratedTM generated) {
    this.generated = generated;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<InterfaceTM> getInterfaces() {
    return interfaces;
  }

  public void setInterfaces(List<InterfaceTM> interfaces) {
    this.interfaces = interfaces;
  }

  public SuperclassTM getSuperclass() {
    return superclass;
  }

  public void setSuperclass(SuperclassTM superclass) {
    this.superclass = superclass;
  }

  public void setAbstractClass(boolean abstractClass) {
    this.abstractClass = abstractClass;
  }

  public boolean isAbstractClass() {
    return abstractClass;
  }

  public String getPojoClassname() {
    return pojoClassname;
  }

  public void setPojoClassname(String pojoClassname) {
    this.pojoClassname = pojoClassname;
  }

  public void setClassname(String classname) {
    this.classname = classname;
  }

  public String getClassname() {
    return classname;
  }

}
