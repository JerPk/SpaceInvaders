package interfaces;

import java.util.Vector;

import bullet.Bullet;

public interface Aggregate {

    public Iterator createIterator(Vector<Bullet> vector);

}
