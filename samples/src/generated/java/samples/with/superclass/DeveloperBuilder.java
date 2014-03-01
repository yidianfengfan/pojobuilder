package samples.with.superclass;

import javax.annotation.Generated;

@Generated("PojoBuilder")
public class DeveloperBuilder implements Cloneable {
    protected DeveloperBuilder self;

    protected Long value$id$java$lang$Long; // mandatory construction parameter
    protected boolean isSet$id$java$lang$Long = false;

    protected String[] value$languages$java$lang$String$;
    protected boolean isSet$languages$java$lang$String$ = false;

    protected String value$name$java$lang$String;
    protected boolean isSet$name$java$lang$String = false;

    public DeveloperBuilder() {
        self = (DeveloperBuilder)this;
    }

    public DeveloperBuilder withId(Long aValue) {
        value$id$java$lang$Long = aValue;
        isSet$id$java$lang$Long = true;
        return self;
    }

    public DeveloperBuilder withLanguages(String[] aValue) {
        value$languages$java$lang$String$ = aValue;
        isSet$languages$java$lang$String$ = true;
        return self;
    }

    public DeveloperBuilder withName(String aValue) {
        value$name$java$lang$String = aValue;
        isSet$name$java$lang$String = true;
        return self;
    }

    @Override
    public Object clone() {
        try {
            @SuppressWarnings("unchecked")
            DeveloperBuilder result = (DeveloperBuilder)super.clone();
            result.self = result;
            return result;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public DeveloperBuilder but() {
        return (DeveloperBuilder)clone();
    }

    public Developer build() {
        Developer result = new Developer(value$id$java$lang$Long);
        if (isSet$languages$java$lang$String$) {
            result.setLanguages(value$languages$java$lang$String$);
        }
        if (isSet$name$java$lang$String) {
            result.setName(value$name$java$lang$String);
        }
        return result;
    }
}