package samples.with.generics;

import java.io.File;
import java.util.Set;
import javax.annotation.Generated;

@Generated("PojoBuilder")
public class ProjectBuilder implements Cloneable {
    protected ProjectBuilder self;

    protected Set<File> value$files$java$util$Set;
    protected boolean isSet$files$java$util$Set = false;

    protected String value$name$java$lang$String; // mandatory construction parameter
    protected boolean isSet$name$java$lang$String = false;

    public ProjectBuilder() {
        self = (ProjectBuilder)this;
    }

    public ProjectBuilder withFiles(Set<File> aValue) {
        value$files$java$util$Set = aValue;
        isSet$files$java$util$Set = true;
        return self;
    }

    public ProjectBuilder withName(String aValue) {
        value$name$java$lang$String = aValue;
        isSet$name$java$lang$String = true;
        return self;
    }

    @Override
    public Object clone() {
        try {
            @SuppressWarnings("unchecked")
            ProjectBuilder result = (ProjectBuilder)super.clone();
            result.self = result;
            return result;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public ProjectBuilder but() {
        return (ProjectBuilder)clone();
    }

    public Project build() {
        Project result = new Project(value$name$java$lang$String);
        if (isSet$files$java$util$Set) {
            result.setFiles(value$files$java$util$Set);
        }
        return result;
    }
}