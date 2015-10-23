package iterator;

import java.util.Vector;

/**
 * The ConcreteAggregate class which implements the Aggregate interface. It
 * contains the createIterator method which returns the iterator.
 * 
 * @author Group 23
 *
 */
public class ConcreteAggregate implements Aggregate {

    /**
     * Takes the input vector and returns an iterator of the vector.
     * 
     * @param vector.
     * @return Iterator.
     */
    @Override
    public Iterator createIterator(Vector<?> vector) {
        return new ConcreteIterator(vector);
    }

}
