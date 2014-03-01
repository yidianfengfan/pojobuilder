package samples.with.factory;

import javax.annotation.Generated;

@Generated("PojoBuilder")
public class ContactBuilder implements Cloneable {
    protected ContactBuilder self;

    protected String value$surname$java$lang$String; // mandatory construction parameter
    protected boolean isSet$surname$java$lang$String = false;

    protected String value$firstname$java$lang$String; // mandatory construction parameter
    protected boolean isSet$firstname$java$lang$String = false;

    protected String value$email$java$lang$String;
    protected boolean isSet$email$java$lang$String = false;

    public ContactBuilder() {
        self = (ContactBuilder)this;
    }

    public ContactBuilder withSurname(String aValue) {
        value$surname$java$lang$String = aValue;
        isSet$surname$java$lang$String = true;
        return self;
    }

    public ContactBuilder withFirstname(String aValue) {
        value$firstname$java$lang$String = aValue;
        isSet$firstname$java$lang$String = true;
        return self;
    }

    public ContactBuilder withEmail(String aValue) {
        value$email$java$lang$String = aValue;
        isSet$email$java$lang$String = true;
        return self;
    }

    @Override
    public Object clone() {
        try {
            @SuppressWarnings("unchecked")
            ContactBuilder result = (ContactBuilder)super.clone();
            result.self = result;
            return result;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public ContactBuilder but() {
        return (ContactBuilder)clone();
    }

    public Contact build() {
        Contact result = PojoFactory.createContact(value$firstname$java$lang$String, value$surname$java$lang$String);
        if (isSet$email$java$lang$String) {
            result.setEmail(value$email$java$lang$String);
        }
        return result;
    }
}