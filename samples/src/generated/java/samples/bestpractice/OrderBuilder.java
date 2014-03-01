package samples.bestpractice;

import java.util.Date;
import java.util.List;
import javax.annotation.Generated;

@Generated("PojoBuilder")
public class OrderBuilder implements Cloneable {
    protected OrderBuilder self;

    protected Date value$date$java$util$Date;
    protected boolean isSet$date$java$util$Date = false;

    protected List<Item> value$items$java$util$List;
    protected boolean isSet$items$java$util$List = false;

    protected long value$id$long;
    protected boolean isSet$id$long = false;

    public OrderBuilder() {
        self = (OrderBuilder)this;
    }

    public OrderBuilder withDate(Date aValue) {
        value$date$java$util$Date = aValue;
        isSet$date$java$util$Date = true;
        return self;
    }

    public OrderBuilder withItems(List<Item> aValue) {
        value$items$java$util$List = aValue;
        isSet$items$java$util$List = true;
        return self;
    }

    public OrderBuilder withId(long aValue) {
        value$id$long = aValue;
        isSet$id$long = true;
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
        if (isSet$date$java$util$Date) {
            result.date = value$date$java$util$Date;
        }
        if (isSet$items$java$util$List) {
            result.items = value$items$java$util$List;
        }
        if (isSet$id$long) {
            result.id = value$id$long;
        }
        return result;
    }
}