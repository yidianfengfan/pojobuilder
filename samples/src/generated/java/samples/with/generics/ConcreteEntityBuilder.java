package samples.with.generics;

import javax.annotation.Generated;

@Generated("PojoBuilder")
public class ConcreteEntityBuilder implements Cloneable {
    protected ConcreteEntityBuilder self;

    protected Long value$key$java$lang$Long;
    protected boolean isSet$key$java$lang$Long = false;

    protected String value$name$java$lang$String;
    protected boolean isSet$name$java$lang$String = false;

    public ConcreteEntityBuilder() {
        self = (ConcreteEntityBuilder)this;
    }

    public ConcreteEntityBuilder withKey(Long aValue) {
        value$key$java$lang$Long = aValue;
        isSet$key$java$lang$Long = true;
        return self;
    }

    public ConcreteEntityBuilder withName(String aValue) {
        value$name$java$lang$String = aValue;
        isSet$name$java$lang$String = true;
        return self;
    }

    @Override
    public Object clone() {
        try {
            @SuppressWarnings("unchecked")
            ConcreteEntityBuilder result = (ConcreteEntityBuilder)super.clone();
            result.self = result;
            return result;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public ConcreteEntityBuilder but() {
        return (ConcreteEntityBuilder)clone();
    }

    public ConcreteEntity build() {
        ConcreteEntity result = new ConcreteEntity();
        if (isSet$key$java$lang$Long) {
            result.setKey(value$key$java$lang$Long);
        }
        if (isSet$name$java$lang$String) {
            result.setName(value$name$java$lang$String);
        }
        return result;
    }
}