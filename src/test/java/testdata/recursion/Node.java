package testdata.recursion;

import java.util.List;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder
public class Node<T> {
    private T value;
    private List<Node<T>> children;
    
    public T getValue() {
        return value;
    }
    public void setValue(T value) {
        this.value = value;
    }
    public List<Node<T>> getChildren() {
        return children;
    }
    public void setChildren(List<Node<T>> children) {
        this.children = children;
    }
    
}
