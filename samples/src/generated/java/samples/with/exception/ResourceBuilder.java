package samples.with.exception;

import java.net.MalformedURLException;
import javax.annotation.Generated;

@Generated("PojoBuilder")
public class ResourceBuilder implements Cloneable {
    protected ResourceBuilder self;

    protected String value$mimeType$java$lang$String;
    protected boolean isSet$mimeType$java$lang$String = false;

    protected String value$urlString$java$lang$String; // mandatory construction parameter
    protected boolean isSet$urlString$java$lang$String = false;

    public ResourceBuilder() {
        self = (ResourceBuilder)this;
    }

    public ResourceBuilder withMimeType(String aValue) {
        value$mimeType$java$lang$String = aValue;
        isSet$mimeType$java$lang$String = true;
        return self;
    }

    public ResourceBuilder withUrlString(String aValue) {
        value$urlString$java$lang$String = aValue;
        isSet$urlString$java$lang$String = true;
        return self;
    }

    @Override
    public Object clone() {
        try {
            @SuppressWarnings("unchecked")
            ResourceBuilder result = (ResourceBuilder)super.clone();
            result.self = result;
            return result;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public ResourceBuilder but() {
        return (ResourceBuilder)clone();
    }

    public Resource build() throws MalformedURLException {
        Resource result = new Resource(value$urlString$java$lang$String);
        if (isSet$mimeType$java$lang$String) {
            result.setMimeType(value$mimeType$java$lang$String);
        }
        return result;
    }
}