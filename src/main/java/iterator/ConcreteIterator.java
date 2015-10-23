package iterator;

import java.util.Vector;

public class ConcreteIterator implements Iterator {
    Vector<?> items;

    int position = 0;
    
    public ConcreteIterator(Vector<?> newItems) {
        this.items= newItems; }
    
    @Override
    public boolean hasNext() {
        if (position >= items.size() || items.get(position) == null) {
            return false;
            } else {
            return true; }
    }

    @Override
    public Object next() {
        Object nextObject = items.get(position);
        position = position + 1;
        return nextObject; 
    }
    
    @Override
    public int position() {
        return position - 1;
    }

}
