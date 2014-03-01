package samples.with.generics;

import java.io.Serializable;
import javax.annotation.Generated;

@Generated("PojoBuilder")
public class ContainerBuilder<T extends Item & Serializable> implements Cloneable {
    protected ContainerBuilder<T> self;

    protected T value$element$T;
    protected boolean isSet$element$T = false;

    public ContainerBuilder() {
        self = (ContainerBuilder<T>)this;
    }

    public ContainerBuilder<T> withElement(T aValue) {
        value$element$T = aValue;
        isSet$element$T = true;
        return self;
    }

    @Override
    public Object clone() {
        try {
            @SuppressWarnings("unchecked")
            ContainerBuilder<T> result = (ContainerBuilder<T>)super.clone();
            result.self = result;
            return result;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public ContainerBuilder<T> but() {
        return (ContainerBuilder<T>)clone();
    }

    public Container<T> build() {
        Container<T> result = new Container<T>();
        if (isSet$element$T) {
            result.setElement(value$element$T);
        }
        return result;
    }
}