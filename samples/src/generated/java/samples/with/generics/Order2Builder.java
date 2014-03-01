package samples.with.generics;

import java.io.Serializable;
import java.util.List;
import javax.annotation.Generated;

@Generated("PojoBuilder")
public class Order2Builder<T extends Item & Serializable> implements Cloneable {
    protected Order2Builder<T> self;

    protected String value$customer$java$lang$String;
    protected boolean isSet$customer$java$lang$String = false;

    protected List<T> value$items$java$util$List;
    protected boolean isSet$items$java$util$List = false;

    public Order2Builder() {
        self = (Order2Builder<T>)this;
    }

    public Order2Builder<T> withCustomer(String aValue) {
        value$customer$java$lang$String = aValue;
        isSet$customer$java$lang$String = true;
        return self;
    }

    public Order2Builder<T> withItems(List<T> aValue) {
        value$items$java$util$List = aValue;
        isSet$items$java$util$List = true;
        return self;
    }

    @Override
    public Object clone() {
        try {
            @SuppressWarnings("unchecked")
            Order2Builder<T> result = (Order2Builder<T>)super.clone();
            result.self = result;
            return result;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public Order2Builder<T> but() {
        return (Order2Builder<T>)clone();
    }

    public Order2<T> build() {
        Order2<T> result = new Order2<T>();
        if (isSet$customer$java$lang$String) {
            result.setCustomer(value$customer$java$lang$String);
        }
        if (isSet$items$java$util$List) {
            result.setItems(value$items$java$util$List);
        }
        return result;
    }
}