package samples.with.subclass;

import java.util.Date;
import javax.annotation.Generated;

@Generated("PojoBuilder")
public class DataBuilder implements Cloneable {
    protected DataBuilder self;

    protected Date value$end$java$util$Date; // mandatory construction parameter
    protected boolean isSet$end$java$util$Date = false;

    protected Date value$start$java$util$Date; // mandatory construction parameter
    protected boolean isSet$start$java$util$Date = false;

    public DataBuilder() {
        self = (DataBuilder)this;
    }

    public DataBuilder withEnd(Date aValue) {
        value$end$java$util$Date = aValue;
        isSet$end$java$util$Date = true;
        return self;
    }

    public DataBuilder withStart(Date aValue) {
        value$start$java$util$Date = aValue;
        isSet$start$java$util$Date = true;
        return self;
    }

    @Override
    public Object clone() {
        try {
            @SuppressWarnings("unchecked")
            DataBuilder result = (DataBuilder)super.clone();
            result.self = result;
            return result;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public DataBuilder but() {
        return (DataBuilder)clone();
    }

    public Data build() {
        Data result = new Data(value$start$java$util$Date, value$end$java$util$Date);
        return result;
    }
}