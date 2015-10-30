package iterator;

/**
 * The Iterator class.
 * 
 * @author Group 23
 */
public interface Iterator {

  /**
   * Method to check if there is a next element.
   * 
   * @return has a next element or not
   */
  boolean hasNext();

  /**
   * Method to get next element.
   * 
   * @return the next object
   */
  Object next();

  /**
   * Method to lower the position with one.
   * 
   * @return the new position
   */
  int position();

}
