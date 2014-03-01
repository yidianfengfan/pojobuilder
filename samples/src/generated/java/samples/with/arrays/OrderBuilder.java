package samples.with.arrays;

import javax.annotation.Generated;
import samples.with.arrays.Item;
import samples.with.arrays.Order;

/**
 * The {@link OrderBuilder} is a Builder for {@link Order} objects.
 *
 * <p>
 *     This class has been generated.
 *     Please DO NOT MODIFIY this file
 *     since it will be overwritten
 *     by the PojoBuilder generator.
 * </p>
 */
@Generated("PojoBuilder")
public class OrderBuilder implements Cloneable {
    protected OrderBuilder self;
    protected String value$customer$java$lang$String; 
    protected boolean isSet$customer$java$lang$String = false;

    protected Item[] value$items$samples$with$arrays$Item$; 
    protected boolean isSet$items$samples$with$arrays$Item$ = false;



    /**
     * Creates a new {@link OrderBuilder}.
     */
    public OrderBuilder() {
        self = (OrderBuilder)this;
    }

    /**
     * Sets the default value for the {@link Order#customer} property.
     * @param value the default value
     * @return this builder
     */
    public OrderBuilder withCustomer( String value) {
        this.value$customer$java$lang$String = value;
        this.isSet$customer$java$lang$String = true;
        return self;
    }
    /**
     * Sets the default value for the {@link Order#items} property.
     * @param value the default value
     * @return this builder
     */
    public OrderBuilder withItems( Item[] value) {
        this.value$items$samples$with$arrays$Item$ = value;
        this.isSet$items$samples$with$arrays$Item$ = true;
        return self;
    }


    /**
     * Returns a clone of this builder.
     * @return the clone
     */
    @Override    
    public Object clone() {
        try {
            OrderBuilder result = (OrderBuilder)super.clone();
            result.self = result;
            return result;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e.getMessage());
        }
    }

    /**
     * Returns a clone of this builder.
     * @return the clone
     */
    public OrderBuilder but() {
        return (OrderBuilder)clone();
    }

    /**
     * Creates a new {@link Order} based on this builder's settings.
     * @return the created Order
     */
    public Order build() {
        try {
            Order result = new Order( );

            if ( this.isSet$customer$java$lang$String) {
                result.setCustomer( this.value$customer$java$lang$String);
            }
            if ( this.isSet$items$samples$with$arrays$Item$) {
                result.setItems( this.value$items$samples$with$arrays$Item$);
            }

            return result;
        } catch (RuntimeException ex) {
            throw ex;
        } catch (Throwable t) {
            throw new java.lang.reflect.UndeclaredThrowableException(t);
        }
    }

}