package iterator;

import java.util.Vector;

import bullet.Bullet;

public class ConcreteAggregate implements Aggregate {

  @Override
  public Iterator createIterator(Vector<?> vector) {
    return new ConcreteIterator(vector);
  }

}
