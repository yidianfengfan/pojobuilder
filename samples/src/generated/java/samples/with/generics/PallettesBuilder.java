package samples.with.generics;

import java.awt.Color;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;

@Generated("PojoBuilder")
public class PallettesBuilder implements Cloneable {
    protected PallettesBuilder self;

    protected List<Set<Color>> value$elements$java$util$List;
    protected boolean isSet$elements$java$util$List = false;

    public PallettesBuilder() {
        self = (PallettesBuilder)this;
    }

    public PallettesBuilder withElements(List<Set<Color>> aValue) {
        value$elements$java$util$List = aValue;
        isSet$elements$java$util$List = true;
        return self;
    }

    @Override
    public Object clone() {
        try {
            @SuppressWarnings("unchecked")
            PallettesBuilder result = (PallettesBuilder)super.clone();
            result.self = result;
            return result;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public PallettesBuilder but() {
        return (PallettesBuilder)clone();
    }

    public Pallettes build() {
        Pallettes result = new Pallettes();
        if (isSet$elements$java$util$List) {
            result.setElements(value$elements$java$util$List);
        }
        return result;
    }
}