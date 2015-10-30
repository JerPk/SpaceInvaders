package iterator;

import java.util.Vector;

/**
 * The ConcreteIterator class which implements the iterator.
 * 
 * @author Group 23
 */
public class ConcreteIterator implements Iterator {

  /**
   * The vector with the items.
   */
  private Vector<?> items;

  /**
   * The position of the iterator.
   */
  private int position = 0;

  /**
   * Constructor of the iterator.
   * 
   * @param newItems
   *          the items to iterate over
   */
  public ConcreteIterator(final Vector<?> newItems) {
    this.items = newItems;
  }

  /**
   * Method to check if there is a next element.
   * 
   * @return has a next element or not
   */
  @Override
  public final boolean hasNext() {
    return !(position >= items.size() || items.get(position) == null);
  }

  /**
   * Method to get next element.
   * 
   * @return the next object
   */
  @Override
  public final Object next() {
    Object nextObject = items.get(position);
    position = position + 1;
    return nextObject;
  }

  /**
   * Method to lower the position with one.
   * 
   * @return the new position
   */
  @Override
  public final int position() {
    return position - 1;
  }

}
