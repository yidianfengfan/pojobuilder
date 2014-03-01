package samples.with.innerclasses;

import javax.annotation.Generated;

@Generated("PojoBuilder")
public class OrderBuilder implements Cloneable {
    protected OrderBuilder self;

    protected String value$customer$java$lang$String;
    protected boolean isSet$customer$java$lang$String = false;

    public OrderBuilder() {
        self = (OrderBuilder)this;
    }

    public OrderBuilder withCustomer(String aValue) {
        value$customer$java$lang$String = aValue;
        isSet$customer$java$lang$String = true;
        return self;
    }

    @Override
    public Object clone() {
        try {
            @SuppressWarnings("unchecked")
            OrderBuilder result = (OrderBuilder)super.clone();
            result.self = result;
            return result;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public OrderBuilder but() {
        return (OrderBuilder)clone();
    }

    public Order build() {
        Order result = new Order();
        if (isSet$customer$java$lang$String) {
            result.setCustomer(value$customer$java$lang$String);
        }
        return result;
    }
}