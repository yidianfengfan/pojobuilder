package net.karneim.pojobuilder.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PropertyM {

    private final String name;
    private final String fieldname;
    private final TypeM type;
    private String setter;
    private String getter;
    private boolean accessible;
    private Integer parameterPos;
    private boolean readable;
    private boolean writable;
    private List<TypeM> setterExceptions = new ArrayList<TypeM>();
    private List<TypeM> getterExceptions = new ArrayList<TypeM>();

    public PropertyM(String name, String fieldname, TypeM type) {
        super();
        this.name = name;
        this.fieldname = fieldname;
        this.type = type;
    }
    
    public List<TypeM> getGetterExceptions() {
        return getterExceptions;
    }

    public List<TypeM> getSetterExceptions() {
        return setterExceptions;
    }

    public boolean isAccessible() {
        return accessible;
    }

    public void setAccessible(boolean accessible) {
        this.accessible = accessible;
    }

    public boolean isReadable() {
        return readable;
    }

    public void setReadable(boolean readable) {
        this.readable = readable;
    }

    public boolean isWritable() {
        return writable;
    }

    public void setWritable(boolean writable) {
        this.writable = writable;
    }

    public Integer getParameterPos() {
        return parameterPos;
    }

    public void setParameterPos(Integer parameterPos) {
        this.parameterPos = parameterPos;
    }

    public String getSetter() {
        return setter;
    }

    public void setSetter(String setter) {
        this.setter = setter;
    }

    public boolean isHasSetter() {
        return setter != null;
    }

    public String getGetter() {
        return getter;
    }

    public boolean isHasGetter() {
        return getter != null;
    }

    public void setGetter(String getter) {
        this.getter = getter;
    }

    public String getName() {
        return name;
    }

    public String getFieldname() {
        return fieldname;
    }

    public String getNameUC() {
        if (name.length() <= 1) {
            return name.toUpperCase();
        } else {
            return name.toUpperCase().substring(0, 1) + name.substring(1);
        }
    }

    public TypeM getType() {
        return type;
    }

    public boolean isMandatoryParameter() {
        return getParameterPos() != null;
    }

    @Override
    public String toString() {
        return "PropertyM [name=" + name + ", fieldname=" + fieldname + ", type=" + type + ", setter=" + setter
                + ", getter=" + getter + ", accessible=" + accessible + ", parameterPos=" + parameterPos
                + ", readable=" + readable + ", writable=" + writable + ", setterExceptions=" + setterExceptions
                + ", getterExceptions=" + getterExceptions + "]";
    }

    public void exportImportTypes(Set<String> result) {
        type.exportImportTypes(result);
        for (TypeM exception : setterExceptions) {
            exception.exportImportTypes(result);
        }
        for (TypeM exception : getterExceptions) {
            exception.exportImportTypes(result);
        }
    }

}
