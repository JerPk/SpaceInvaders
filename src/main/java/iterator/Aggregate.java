package iterator;

import java.util.Vector;

/**
 * The Aggregate interface. It contains the createIterator method which returns
 * the iterator.
 * 
 * @author Group 23
 *
 */
public interface Aggregate {

    /**
     * Takes the input vector and returns
     * an iterator of the vector.
     * 
     * @param vector.
     * @return Iterator.
     */
  public Iterator createIterator(Vector<?> vector);

}
