package samples.with.name;

import javax.annotation.Generated;

@Generated("PojoBuilder")
public class FluentContactBuilder implements Cloneable {
    protected FluentContactBuilder self;

    protected String value$surname$java$lang$String; // mandatory construction parameter
    protected boolean isSet$surname$java$lang$String = false;

    protected String value$firstname$java$lang$String; // mandatory construction parameter
    protected boolean isSet$firstname$java$lang$String = false;

    protected String value$email$java$lang$String;
    protected boolean isSet$email$java$lang$String = false;

    public FluentContactBuilder() {
        self = (FluentContactBuilder)this;
    }

    public FluentContactBuilder withSurname(String aValue) {
        value$surname$java$lang$String = aValue;
        isSet$surname$java$lang$String = true;
        return self;
    }

    public FluentContactBuilder withFirstname(String aValue) {
        value$firstname$java$lang$String = aValue;
        isSet$firstname$java$lang$String = true;
        return self;
    }

    public FluentContactBuilder withEmail(String aValue) {
        value$email$java$lang$String = aValue;
        isSet$email$java$lang$String = true;
        return self;
    }

    @Override
    public Object clone() {
        try {
            @SuppressWarnings("unchecked")
            FluentContactBuilder result = (FluentContactBuilder)super.clone();
            result.self = result;
            return result;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public FluentContactBuilder but() {
        return (FluentContactBuilder)clone();
    }

    public Contact build() {
        Contact result = new Contact(value$surname$java$lang$String, value$firstname$java$lang$String);
        if (isSet$email$java$lang$String) {
            result.setEmail(value$email$java$lang$String);
        }
        return result;
    }
}