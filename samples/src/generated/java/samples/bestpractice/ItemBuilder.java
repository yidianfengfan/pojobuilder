package samples.bestpractice;

import javax.annotation.Generated;

@Generated("PojoBuilder")
public class ItemBuilder implements Cloneable {
    protected ItemBuilder self;

    protected int value$amount$int;
    protected boolean isSet$amount$int = false;

    protected String value$name$java$lang$String;
    protected boolean isSet$name$java$lang$String = false;

    public ItemBuilder() {
        self = (ItemBuilder)this;
    }

    public ItemBuilder withAmount(int aValue) {
        value$amount$int = aValue;
        isSet$amount$int = true;
        return self;
    }

    public ItemBuilder withName(String aValue) {
        value$name$java$lang$String = aValue;
        isSet$name$java$lang$String = true;
        return self;
    }

    @Override
    public Object clone() {
        try {
            @SuppressWarnings("unchecked")
            ItemBuilder result = (ItemBuilder)super.clone();
            result.self = result;
            return result;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public ItemBuilder but() {
        return (ItemBuilder)clone();
    }

    public Item build() {
        Item result = new Item();
        if (isSet$amount$int) {
            result.amount = value$amount$int;
        }
        if (isSet$name$java$lang$String) {
            result.name = value$name$java$lang$String;
        }
        return result;
    }
}