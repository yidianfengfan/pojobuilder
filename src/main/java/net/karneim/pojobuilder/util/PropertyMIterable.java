package net.karneim.pojobuilder.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.karneim.pojobuilder.model.PropertyM;

public class PropertyMIterable<T extends PropertyM, SELF extends PropertyMIterable<T, SELF>> extends
        DefaultFluentIterable<T, SELF> {

    private final Comparator<T> PARAMETER_POS_COMPARATOR = new Comparator<T>() {

        @Override
        public int compare(T o1, T o2) {
            if (o1.getParameterPos() == null) {
                if (o2.getParameterPos() == null) {
                    return 0;
                }
                return -1;
            }
            if (o2.getParameterPos() == null) {
                return 1;
            }
            return o1.getParameterPos().compareTo(o2.getParameterPos());
        }
    };

    public PropertyMIterable(List<T> elements) {
        super(elements);
    }

    public SELF filterMandatory() {
        return filterMandatory(true);
    }

    public SELF filterMandatory(boolean mandatory) {
        List<T> newElements = new ArrayList<T>();
        for (T el : elements) {
            if (el.isMandatoryParameter() == mandatory) {
                newElements.add(el);
            }
        }
        SELF result = clone(newElements);
        return result;
    }

    public SELF orderByParameterPos() {
        List<T> newElements = new ArrayList<T>(elements);
        Collections.sort(newElements, PARAMETER_POS_COMPARATOR);
        SELF result = clone(newElements);
        return result;
    }

    public SELF filterMutable(boolean mutable) {
        List<T> newElements = new ArrayList<T>();
        for (T el : elements) {
            boolean actual = (el.isAccessible() && el.isWritable() ) || el.getSetter() != null || el.getParameterPos() != null;
            if (actual == mutable) {
                newElements.add(el);
            }
        }
        SELF result = clone(newElements);
        return result;
    }

}
