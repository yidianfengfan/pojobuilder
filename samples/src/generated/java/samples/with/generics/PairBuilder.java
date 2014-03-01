package samples.with.generics;

import javax.annotation.Generated;

@Generated("PojoBuilder")
public class PairBuilder<A extends Comparable<A>, B extends Number> implements Cloneable {
    protected PairBuilder<A, B> self;

    protected A value$valueA$A;
    protected boolean isSet$valueA$A = false;

    protected B value$valueB$B;
    protected boolean isSet$valueB$B = false;

    public PairBuilder() {
        self = (PairBuilder<A, B>)this;
    }

    public PairBuilder<A, B> withValueA(A aValue) {
        value$valueA$A = aValue;
        isSet$valueA$A = true;
        return self;
    }

    public PairBuilder<A, B> withValueB(B aValue) {
        value$valueB$B = aValue;
        isSet$valueB$B = true;
        return self;
    }

    @Override
    public Object clone() {
        try {
            @SuppressWarnings("unchecked")
            PairBuilder<A, B> result = (PairBuilder<A, B>)super.clone();
            result.self = result;
            return result;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public PairBuilder<A, B> but() {
        return (PairBuilder<A, B>)clone();
    }

    public Pair<A, B> build() {
        Pair<A, B> result = new Pair<A, B>();
        if (isSet$valueA$A) {
            result.setValueA(value$valueA$A);
        }
        if (isSet$valueB$B) {
            result.setValueB(value$valueB$B);
        }
        return result;
    }
}