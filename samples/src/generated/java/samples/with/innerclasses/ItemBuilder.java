package samples.with.innerclasses;

import java.math.BigDecimal;
import javax.annotation.Generated;
import samples.with.innerclasses.Order.Item;

@Generated("PojoBuilder")
public class ItemBuilder implements Cloneable {
    protected ItemBuilder self;

    protected String value$title$java$lang$String; // mandatory construction parameter
    protected boolean isSet$title$java$lang$String = false;

    protected BigDecimal value$pricePerUnit$java$math$BigDecimal; // mandatory construction parameter
    protected boolean isSet$pricePerUnit$java$math$BigDecimal = false;

    protected int value$units$int; // mandatory construction parameter
    protected boolean isSet$units$int = false;

    public ItemBuilder() {
        self = (ItemBuilder)this;
    }

    public ItemBuilder withTitle(String aValue) {
        value$title$java$lang$String = aValue;
        isSet$title$java$lang$String = true;
        return self;
    }

    public ItemBuilder withPricePerUnit(BigDecimal aValue) {
        value$pricePerUnit$java$math$BigDecimal = aValue;
        isSet$pricePerUnit$java$math$BigDecimal = true;
        return self;
    }

    public ItemBuilder withUnits(int aValue) {
        value$units$int = aValue;
        isSet$units$int = true;
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
        Item result = new Item(value$title$java$lang$String, value$pricePerUnit$java$math$BigDecimal, value$units$int);
        return result;
    }
}