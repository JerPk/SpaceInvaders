package iterator;

import java.util.Vector;

/**
 * The ConcreteIterator class which implements the Iterator interface.
 * it contains all the methods the iterator needs to be able to move
 * trough the collection.
 * 
 * 
 * @author Group 23
 *
 */
public class ConcreteIterator implements Iterator {
    /**
     * items is the vector used to store the data in.
     */
    Vector<?> items;

    /**
     * position keeps track of where we are on the iterator.
     */
    int position = 0;
    
    /**
     * constructor method for the ConcreteIterator class.
     * @param newItems
     */
    public ConcreteIterator(Vector<?> newItems) {
        this.items= newItems; }
    
    /**
     * The hasNext method. returns a boolean
     * if the iterator isn't at the final object
     * it returns true otherwise false. 
     */
    @Override
    public boolean hasNext() {
        if (position >= items.size() || items.get(position) == null) {
            return false;
            } else {
            return true; }
    }

    /**
     * The next method returns the next
     * object in the iterator.
     */
    @Override
    public Object next() {
        Object nextObject = items.get(position);
        position = position + 1;
        return nextObject; 
    }
    
    /**
     * position returns the position of 
     * the current node.
     */
    @Override
    public int position() {
        return position - 1;
    }

}
