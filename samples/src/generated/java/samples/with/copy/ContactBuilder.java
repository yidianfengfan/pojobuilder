package samples.with.copy;

import java.util.Date;
import javax.annotation.Generated;

@Generated("PojoBuilder")
public class ContactBuilder implements Cloneable {
    protected ContactBuilder self;

    protected String value$surname$java$lang$String; // mandatory construction parameter
    protected boolean isSet$surname$java$lang$String = false;

    protected Date value$birthdate$java$util$Date;
    protected boolean isSet$birthdate$java$util$Date = false;

    protected String value$firstname$java$lang$String;
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

    public ContactBuilder withBirthdate(Date aValue) {
        value$birthdate$java$util$Date = aValue;
        isSet$birthdate$java$util$Date = true;
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

    public ContactBuilder copy(Contact original) {
        withBirthdate(original.birthdate);
        withFirstname(original.firstname);
        withEmail(original.email);
        return self;
    }

    public Contact build() {
        Contact result = new Contact(value$surname$java$lang$String);
        if (isSet$birthdate$java$util$Date) {
            result.birthdate = value$birthdate$java$util$Date;
        }
        if (isSet$firstname$java$lang$String) {
            result.firstname = value$firstname$java$lang$String;
        }
        if (isSet$email$java$lang$String) {
            result.email = value$email$java$lang$String;
        }
        return result;
    }
}