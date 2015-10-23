package iterator;

import java.util.Vector;

public interface Aggregate {

    public Iterator createIterator(Vector<?> vector);

}
