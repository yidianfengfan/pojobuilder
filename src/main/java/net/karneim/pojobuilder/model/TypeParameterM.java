package net.karneim.pojobuilder.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import net.karneim.pojobuilder.TypeMap;

public class TypeParameterM {
    private TypeM type;

    private List<TypeM> bounds = new ArrayList<TypeM>();

    /**
     * @param type
     */
    public TypeParameterM(TypeM type) {
        super();
        this.type = type;
    }

    public TypeM getType() {
        return type;
    }

    public String getName() {
        // TODO it may be better to remove this method an let the caller use
        // getType().getGenericTypeSimpleName() or any other methode instead
        return type.getGenericTypeSimpleName();
    }

    public boolean isBounded() {
        return bounds.isEmpty() == false;
    }

    public List<TypeM> getBounds() {
        return bounds;
    }

    public String getBoundsAsString() {
        StringBuilder b = new StringBuilder();
        for (TypeM bound : bounds) {
            if (b.length() > 0) {
                b.append(" & ");
            }
            b.append(bound.getGenericTypeSimpleName());
        }
        return b.toString();
    }

    public void addTo(Set<TypeM> typeSet) {
        if (isBounded()) {
            for (TypeM bound : bounds) {
                bound.addTo(typeSet);
            }
        }
        type.addTo(typeSet);
    }
    
    public void exportImportTypes(Set<String> result) {
        if (isBounded()) {
            for (TypeM bound : bounds) {
                bound.exportImportTypes(result);
            }
        }
        type.exportImportTypes(result);

    }

    @Override
    public String toString() {
        return "TypeParameterM[type=" + type + ",bounds=" + bounds + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bounds == null) ? 0 : bounds.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TypeParameterM other = (TypeParameterM) obj;
        if (bounds == null) {
            if (other.bounds != null)
                return false;
        } else if (!bounds.equals(other.bounds))
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        return true;
    }

    

}
