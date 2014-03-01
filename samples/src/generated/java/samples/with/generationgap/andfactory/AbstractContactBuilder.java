package samples.with.generationgap.andfactory;

import java.util.List;
import javax.annotation.Generated;

@Generated("PojoBuilder")
public abstract class AbstractContactBuilder implements Cloneable {
    protected ContactBuilder self;

    protected List<String> value$emailAddresses$java$util$List;
    protected boolean isSet$emailAddresses$java$util$List = false;

    protected String value$name$java$lang$String;
    protected boolean isSet$name$java$lang$String = false;

    public AbstractContactBuilder() {
        self = (ContactBuilder)this;
    }

    public ContactBuilder withEmailAddresses(List<String> aValue) {
        value$emailAddresses$java$util$List = aValue;
        isSet$emailAddresses$java$util$List = true;
        return self;
    }

    public ContactBuilder withName(String aValue) {
        value$name$java$lang$String = aValue;
        isSet$name$java$lang$String = true;
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
        Contact result = ContactFactory.newContact();
        if (isSet$emailAddresses$java$util$List) {
            result.setEmailAddresses(value$emailAddresses$java$util$List);
        }
        if (isSet$name$java$lang$String) {
            result.setName(value$name$java$lang$String);
        }
        return result;
    }
}