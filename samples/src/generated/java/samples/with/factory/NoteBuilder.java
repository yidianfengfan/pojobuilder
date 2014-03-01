package samples.with.factory;

import javax.annotation.Generated;

@Generated("PojoBuilder")
public class NoteBuilder implements Cloneable {
    protected NoteBuilder self;

    protected String value$text$java$lang$String;
    protected boolean isSet$text$java$lang$String = false;

    public NoteBuilder() {
        self = (NoteBuilder)this;
    }

    public NoteBuilder withText(String aValue) {
        value$text$java$lang$String = aValue;
        isSet$text$java$lang$String = true;
        return self;
    }

    @Override
    public Object clone() {
        try {
            @SuppressWarnings("unchecked")
            NoteBuilder result = (NoteBuilder)super.clone();
            result.self = result;
            return result;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public NoteBuilder but() {
        return (NoteBuilder)clone();
    }

    public Note build() {
        Note result = PojoFactory.createNote();
        if (isSet$text$java$lang$String) {
            result.text = value$text$java$lang$String;
        }
        return result;
    }
}