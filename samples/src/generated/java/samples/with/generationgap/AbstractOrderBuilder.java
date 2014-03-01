package samples.with.generationgap;

import java.util.List;
import javax.annotation.Generated;

@Generated("PojoBuilder")
public abstract class AbstractOrderBuilder<T extends Object> implements Cloneable {
    protected OrderBuilder<T> self;

    protected String value$customer$java$lang$String;
    protected boolean isSet$customer$java$lang$String = false;

    protected List<T> value$items$java$util$List;
    protected boolean isSet$items$java$util$List = false;

    public AbstractOrderBuilder() {
        self = (OrderBuilder<T>)this;
    }

    public OrderBuilder<T> withCustomer(String aValue) {
        value$customer$java$lang$String = aValue;
        isSet$customer$java$lang$String = true;
        return self;
    }

    public OrderBuilder<T> withItems(List<T> aValue) {
        value$items$java$util$List = aValue;
        isSet$items$java$util$List = true;
        return self;
    }

    @Override
    public Object clone() {
        try {
            @SuppressWarnings("unchecked")
            OrderBuilder<T> result = (OrderBuilder<T>)super.clone();
            result.self = result;
            return result;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public OrderBuilder<T> but() {
        return (OrderBuilder<T>)clone();
    }

    public Order<T> build() {
        Order<T> result = new Order<T>();
        if (isSet$customer$java$lang$String) {
            result.setCustomer(value$customer$java$lang$String);
        }
        if (isSet$items$java$util$List) {
            result.setItems(value$items$java$util$List);
        }
        return result;
    }
}