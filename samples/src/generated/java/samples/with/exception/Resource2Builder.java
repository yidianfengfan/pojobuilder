package samples.with.exception;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import javax.annotation.Generated;

@Generated("PojoBuilder")
public class Resource2Builder implements Cloneable {
    protected Resource2Builder self;

    protected String value$filepath$java$lang$String; // mandatory construction parameter
    protected boolean isSet$filepath$java$lang$String = false;

    protected String value$mimeType$java$lang$String;
    protected boolean isSet$mimeType$java$lang$String = false;

    protected String value$urlString$java$lang$String; // mandatory construction parameter
    protected boolean isSet$urlString$java$lang$String = false;

    public Resource2Builder() {
        self = (Resource2Builder)this;
    }

    public Resource2Builder withFilepath(String aValue) {
        value$filepath$java$lang$String = aValue;
        isSet$filepath$java$lang$String = true;
        return self;
    }

    public Resource2Builder withMimeType(String aValue) {
        value$mimeType$java$lang$String = aValue;
        isSet$mimeType$java$lang$String = true;
        return self;
    }

    public Resource2Builder withUrlString(String aValue) {
        value$urlString$java$lang$String = aValue;
        isSet$urlString$java$lang$String = true;
        return self;
    }

    @Override
    public Object clone() {
        try {
            @SuppressWarnings("unchecked")
            Resource2Builder result = (Resource2Builder)super.clone();
            result.self = result;
            return result;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public Resource2Builder but() {
        return (Resource2Builder)clone();
    }

    public Resource2 build() throws MalformedURLException, FileNotFoundException {
        Resource2 result = new Resource2(value$urlString$java$lang$String, value$filepath$java$lang$String);
        if (isSet$mimeType$java$lang$String) {
            result.setMimeType(value$mimeType$java$lang$String);
        }
        return result;
    }
}