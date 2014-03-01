package samples.with.builderbaseclass;

import javax.annotation.Generated;

@Generated("PojoBuilder")
public class ContactBuilder extends BaseBuilder implements Cloneable {
    protected ContactBuilder self;

    protected String value$email$java$lang$String;
    protected boolean isSet$email$java$lang$String = false;

    protected String value$name$java$lang$String; // mandatory construction parameter
    protected boolean isSet$name$java$lang$String = false;

    public ContactBuilder() {
        self = (ContactBuilder)this;
    }

    public ContactBuilder withEmail(String aValue) {
        value$email$java$lang$String = aValue;
        isSet$email$java$lang$String = true;
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
        Contact result = new Contact(value$name$java$lang$String);
        if (isSet$email$java$lang$String) {
            result.setEmail(value$email$java$lang$String);
        }
        return result;
    }
}