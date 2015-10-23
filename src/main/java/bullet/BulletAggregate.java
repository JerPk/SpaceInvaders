package bullet;

import interfaces.Aggregate;
import interfaces.Iterator;

import java.util.Vector;

public class BulletAggregate implements Aggregate{

    @Override
    public Iterator createIterator(Vector<Bullet> bullets) {
        return new BulletIterator(bullets);
    }

}
