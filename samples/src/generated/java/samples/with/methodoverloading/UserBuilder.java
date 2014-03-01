package samples.with.methodoverloading;

import java.io.File;
import java.util.Date;
import javax.annotation.Generated;

@Generated("PojoBuilder")
public class UserBuilder implements Cloneable {
    protected UserBuilder self;

    protected Date value$created$java$util$Date; // mandatory construction parameter
    protected boolean isSet$created$java$util$Date = false;

    protected File value$homeDirectory$java$io$File;
    protected boolean isSet$homeDirectory$java$io$File = false;

    protected String value$homeDirectory$java$lang$String;
    protected boolean isSet$homeDirectory$java$lang$String = false;

    protected long value$id$long; // mandatory construction parameter
    protected boolean isSet$id$long = false;

    protected String value$passwordHash$java$lang$String;
    protected boolean isSet$passwordHash$java$lang$String = false;

    protected String value$name$java$lang$String; // mandatory construction parameter
    protected boolean isSet$name$java$lang$String = false;

    public UserBuilder() {
        self = (UserBuilder)this;
    }

    public UserBuilder withCreated(Date aValue) {
        value$created$java$util$Date = aValue;
        isSet$created$java$util$Date = true;
        return self;
    }

    public UserBuilder withHomeDirectory(File aValue) {
        value$homeDirectory$java$io$File = aValue;
        isSet$homeDirectory$java$io$File = true;
        return self;
    }

    public UserBuilder withHomeDirectory(String aValue) {
        value$homeDirectory$java$lang$String = aValue;
        isSet$homeDirectory$java$lang$String = true;
        return self;
    }

    public UserBuilder withId(long aValue) {
        value$id$long = aValue;
        isSet$id$long = true;
        return self;
    }

    public UserBuilder withPasswordHash(String aValue) {
        value$passwordHash$java$lang$String = aValue;
        isSet$passwordHash$java$lang$String = true;
        return self;
    }

    public UserBuilder withName(String aValue) {
        value$name$java$lang$String = aValue;
        isSet$name$java$lang$String = true;
        return self;
    }

    @Override
    public Object clone() {
        try {
            @SuppressWarnings("unchecked")
            UserBuilder result = (UserBuilder)super.clone();
            result.self = result;
            return result;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public UserBuilder but() {
        return (UserBuilder)clone();
    }

    public User build() {
        User result = new User(value$id$long, value$name$java$lang$String, value$created$java$util$Date);
        if (isSet$homeDirectory$java$io$File) {
            result.setHomeDirectory(value$homeDirectory$java$io$File);
        }
        if (isSet$homeDirectory$java$lang$String) {
            result.setHomeDirectory(value$homeDirectory$java$lang$String);
        }
        if (isSet$passwordHash$java$lang$String) {
            result.setPasswordHash(value$passwordHash$java$lang$String);
        }
        return result;
    }
}