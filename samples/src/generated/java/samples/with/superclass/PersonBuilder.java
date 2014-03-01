package samples.with.superclass;

import javax.annotation.Generated;

@Generated("PojoBuilder")
public class PersonBuilder implements Cloneable {
    protected PersonBuilder self;

    protected boolean value$artificial$boolean; // mandatory construction parameter
    protected boolean isSet$artificial$boolean = false;

    protected Long value$id$java$lang$Long; // mandatory construction parameter
    protected boolean isSet$id$java$lang$Long = false;

    protected String value$name$java$lang$String;
    protected boolean isSet$name$java$lang$String = false;

    public PersonBuilder() {
        self = (PersonBuilder)this;
    }

    public PersonBuilder withArtificial(boolean aValue) {
        value$artificial$boolean = aValue;
        isSet$artificial$boolean = true;
        return self;
    }

    public PersonBuilder withId(Long aValue) {
        value$id$java$lang$Long = aValue;
        isSet$id$java$lang$Long = true;
        return self;
    }

    public PersonBuilder withName(String aValue) {
        value$name$java$lang$String = aValue;
        isSet$name$java$lang$String = true;
        return self;
    }

    @Override
    public Object clone() {
        try {
            @SuppressWarnings("unchecked")
            PersonBuilder result = (PersonBuilder)super.clone();
            result.self = result;
            return result;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public PersonBuilder but() {
        return (PersonBuilder)clone();
    }

    public Person build() {
        Person result = new Person(value$id$java$lang$Long, value$artificial$boolean);
        if (isSet$name$java$lang$String) {
            result.setName(value$name$java$lang$String);
        }
        return result;
    }
}