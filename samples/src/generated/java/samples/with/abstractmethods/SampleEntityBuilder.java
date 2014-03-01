package samples.with.abstractmethods;

import javax.annotation.Generated;

@Generated("PojoBuilder")
public class SampleEntityBuilder implements Cloneable {
    protected SampleEntityBuilder self;

    protected Long value$id$java$lang$Long;
    protected boolean isSet$id$java$lang$Long = false;

    protected Long value$key$java$lang$Long;
    protected boolean isSet$key$java$lang$Long = false;

    protected String value$name$java$lang$String;
    protected boolean isSet$name$java$lang$String = false;

    public SampleEntityBuilder() {
        self = (SampleEntityBuilder)this;
    }

    public SampleEntityBuilder withId(Long aValue) {
        value$id$java$lang$Long = aValue;
        isSet$id$java$lang$Long = true;
        return self;
    }

    public SampleEntityBuilder withKey(Long aValue) {
        value$key$java$lang$Long = aValue;
        isSet$key$java$lang$Long = true;
        return self;
    }

    public SampleEntityBuilder withName(String aValue) {
        value$name$java$lang$String = aValue;
        isSet$name$java$lang$String = true;
        return self;
    }

    @Override
    public Object clone() {
        try {
            @SuppressWarnings("unchecked")
            SampleEntityBuilder result = (SampleEntityBuilder)super.clone();
            result.self = result;
            return result;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public SampleEntityBuilder but() {
        return (SampleEntityBuilder)clone();
    }

    public SampleEntity build() {
        SampleEntity result = new SampleEntity();
        if (isSet$id$java$lang$Long) {
            result.setId(value$id$java$lang$Long);
        }
        if (isSet$key$java$lang$Long) {
            result.setKey(value$key$java$lang$Long);
        }
        if (isSet$name$java$lang$String) {
            result.setName(value$name$java$lang$String);
        }
        return result;
    }
}