package iterator;

import java.util.Vector;

/**
 * The ConcreteAggregate class.
 * 
 * @author Group 23
 */
public class ConcreteAggregate implements Aggregate {

  /**
   * Method to create the iterator.
   * 
   * @param vector
   *          vector
   * @return the iterator
   */
  @Override
  public final Iterator createIterator(Vector<?> vector) {
    return new ConcreteIterator(vector);
  }

}
