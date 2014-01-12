package net.karneim.pojobuilder.util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class DefaultFluentIterable<T, SELF extends DefaultFluentIterable<T, SELF>> implements FluentIterable<T, SELF>, Cloneable  {

	protected List<T> elements;
	
	public DefaultFluentIterable(List<T> elements) {
        this.elements = elements;
    }

    public Iterator<T> iterator() {
		if ( elements==null) {
			return Collections.emptyIterator();
		}
		return Collections.unmodifiableCollection(elements).iterator();
	}

    @SuppressWarnings("unchecked")
    @Override
    protected Object clone() {
        try {
            DefaultFluentIterable<T,SELF> result = (DefaultFluentIterable<T,SELF>)super.clone();
            result.elements = new ArrayList<T>(elements);
            return result;
        } catch (CloneNotSupportedException e) {
            throw new Error(e);
        }
    }
    
    protected SELF clone(List<T> newElements) {
        try {
            @SuppressWarnings("unchecked")
            SELF result = (SELF)super.clone();
            result.elements = new ArrayList<T>(newElements);
            return result;
        } catch (CloneNotSupportedException e) {
            throw new Error(e);
        }
    }

}
