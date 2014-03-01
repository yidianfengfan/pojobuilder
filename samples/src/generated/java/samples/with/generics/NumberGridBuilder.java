package samples.with.generics;

import java.util.Collection;
import java.util.List;
import javax.annotation.Generated;

@Generated("PojoBuilder")
public class NumberGridBuilder<E extends Number> implements Cloneable {
    protected NumberGridBuilder<E> self;

    protected List<Collection<E>> value$elements$java$util$List;
    protected boolean isSet$elements$java$util$List = false;

    public NumberGridBuilder() {
        self = (NumberGridBuilder<E>)this;
    }

    public NumberGridBuilder<E> withElements(List<Collection<E>> aValue) {
        value$elements$java$util$List = aValue;
        isSet$elements$java$util$List = true;
        return self;
    }

    @Override
    public Object clone() {
        try {
            @SuppressWarnings("unchecked")
            NumberGridBuilder<E> result = (NumberGridBuilder<E>)super.clone();
            result.self = result;
            return result;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public NumberGridBuilder<E> but() {
        return (NumberGridBuilder<E>)clone();
    }

    public NumberGrid<E> build() {
        NumberGrid<E> result = new NumberGrid<E>();
        if (isSet$elements$java$util$List) {
            result.setElements(value$elements$java$util$List);
        }
        return result;
    }
}