package samples.with.subclass;

import java.util.Date;
import javax.annotation.Generated;

@Generated("PojoBuilder")
public class MyDataBuilder implements Cloneable {
    protected MyDataBuilder self;

    protected Date value$end$java$util$Date; // mandatory construction parameter
    protected boolean isSet$end$java$util$Date = false;

    protected String value$name$java$lang$String;
    protected boolean isSet$name$java$lang$String = false;

    protected Date value$start$java$util$Date; // mandatory construction parameter
    protected boolean isSet$start$java$util$Date = false;

    public MyDataBuilder() {
        self = (MyDataBuilder)this;
    }

    public MyDataBuilder withEnd(Date aValue) {
        value$end$java$util$Date = aValue;
        isSet$end$java$util$Date = true;
        return self;
    }

    public MyDataBuilder withName(String aValue) {
        value$name$java$lang$String = aValue;
        isSet$name$java$lang$String = true;
        return self;
    }

    public MyDataBuilder withStart(Date aValue) {
        value$start$java$util$Date = aValue;
        isSet$start$java$util$Date = true;
        return self;
    }

    @Override
    public Object clone() {
        try {
            @SuppressWarnings("unchecked")
            MyDataBuilder result = (MyDataBuilder)super.clone();
            result.self = result;
            return result;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public MyDataBuilder but() {
        return (MyDataBuilder)clone();
    }

    public MyData build() {
        MyData result = new MyData(value$start$java$util$Date, value$end$java$util$Date);
        if (isSet$name$java$lang$String) {
            result.setName(value$name$java$lang$String);
        }
        return result;
    }
}