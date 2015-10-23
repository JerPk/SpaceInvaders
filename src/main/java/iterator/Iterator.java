package iterator;

/**
 * the Iterator interface contains all the methods the iterator needs to be able
 * to move trough the collection.
 * 
 * 
 * @author Group 23
 *
 */
public interface Iterator {

    /**
     * The hasNext method. returns a boolean
     * if the iterator isn't at the final object
     * it returns true otherwise false. 
     */
    boolean hasNext();

    /**
     * The next method returns the next
     * object in the iterator.
     */
    Object next();

    /**
     * position returns the position of 
     * the current node.
     */
    int position();

}
