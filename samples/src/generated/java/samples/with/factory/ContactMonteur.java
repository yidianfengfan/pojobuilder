package samples.with.factory;

import javax.annotation.Generated;

@Generated("PojoBuilder")
public class ContactMonteur implements Cloneable {
    protected ContactMonteur self;

    protected String value$surname$java$lang$String; // mandatory construction parameter
    protected boolean isSet$surname$java$lang$String = false;

    protected String value$firstname$java$lang$String; // mandatory construction parameter
    protected boolean isSet$firstname$java$lang$String = false;

    protected String value$email$java$lang$String;
    protected boolean isSet$email$java$lang$String = false;

    public ContactMonteur() {
        self = (ContactMonteur)this;
    }

    public ContactMonteur withSurname(String aValue) {
        value$surname$java$lang$String = aValue;
        isSet$surname$java$lang$String = true;
        return self;
    }

    public ContactMonteur withFirstname(String aValue) {
        value$firstname$java$lang$String = aValue;
        isSet$firstname$java$lang$String = true;
        return self;
    }

    public ContactMonteur withEmail(String aValue) {
        value$email$java$lang$String = aValue;
        isSet$email$java$lang$String = true;
        return self;
    }

    @Override
    public Object clone() {
        try {
            @SuppressWarnings("unchecked")
            ContactMonteur result = (ContactMonteur)super.clone();
            result.self = result;
            return result;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public ContactMonteur but() {
        return (ContactMonteur)clone();
    }

    public Contact build() {
        Contact result = PojoFactory.construireContact(value$firstname$java$lang$String, value$surname$java$lang$String);
        if (isSet$email$java$lang$String) {
            result.setEmail(value$email$java$lang$String);
        }
        return result;
    }
}