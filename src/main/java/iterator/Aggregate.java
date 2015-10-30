package iterator;

import java.util.Vector;

/**
 * The Aggregate interface.
 * 
 * @author Group 23
 */
public interface Aggregate {

  /**
   * Method to create the iterator.
   * 
   * @param vector
   *          vector
   * @return the iterator
   */
  Iterator createIterator(Vector<?> vector);

}
