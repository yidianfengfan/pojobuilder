package samples.with.constructorproperties;

import javax.annotation.Generated;

@Generated("PojoBuilder")
public class AddressDTOBuilder implements Cloneable {
    protected AddressDTOBuilder self;

    protected String value$street$java$lang$String; // mandatory construction parameter
    protected boolean isSet$street$java$lang$String = false;

    protected String value$postCode$java$lang$String; // mandatory construction parameter
    protected boolean isSet$postCode$java$lang$String = false;

    protected String value$city$java$lang$String; // mandatory construction parameter
    protected boolean isSet$city$java$lang$String = false;

    protected String value$name$java$lang$String; // mandatory construction parameter
    protected boolean isSet$name$java$lang$String = false;

    public AddressDTOBuilder() {
        self = (AddressDTOBuilder)this;
    }

    public AddressDTOBuilder withStreet(String aValue) {
        value$street$java$lang$String = aValue;
        isSet$street$java$lang$String = true;
        return self;
    }

    public AddressDTOBuilder withPostCode(String aValue) {
        value$postCode$java$lang$String = aValue;
        isSet$postCode$java$lang$String = true;
        return self;
    }

    public AddressDTOBuilder withCity(String aValue) {
        value$city$java$lang$String = aValue;
        isSet$city$java$lang$String = true;
        return self;
    }

    public AddressDTOBuilder withName(String aValue) {
        value$name$java$lang$String = aValue;
        isSet$name$java$lang$String = true;
        return self;
    }

    @Override
    public Object clone() {
        try {
            @SuppressWarnings("unchecked")
            AddressDTOBuilder result = (AddressDTOBuilder)super.clone();
            result.self = result;
            return result;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public AddressDTOBuilder but() {
        return (AddressDTOBuilder)clone();
    }

    public AddressDTO build() {
        AddressDTO result = new AddressDTO(value$name$java$lang$String, value$street$java$lang$String, value$city$java$lang$String, value$postCode$java$lang$String);
        return result;
    }
}