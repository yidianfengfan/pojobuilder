package samples.with.generics;

import java.util.Map;
import javax.annotation.Generated;

@Generated("PojoBuilder")
public class SettingsBuilder implements Cloneable {
    protected SettingsBuilder self;

    protected Map<String, Object> value$entries$java$util$Map;
    protected boolean isSet$entries$java$util$Map = false;

    public SettingsBuilder() {
        self = (SettingsBuilder)this;
    }

    public SettingsBuilder withEntries(Map<String, Object> aValue) {
        value$entries$java$util$Map = aValue;
        isSet$entries$java$util$Map = true;
        return self;
    }

    @Override
    public Object clone() {
        try {
            @SuppressWarnings("unchecked")
            SettingsBuilder result = (SettingsBuilder)super.clone();
            result.self = result;
            return result;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public SettingsBuilder but() {
        return (SettingsBuilder)clone();
    }

    public Settings build() {
        Settings result = new Settings();
        if (isSet$entries$java$util$Map) {
            result.entries = value$entries$java$util$Map;
        }
        return result;
    }
}