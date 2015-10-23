package bullet;

import interfaces.Iterator;

import java.util.Vector;

public class BulletIterator implements Iterator {
    Vector<Bullet> bullets;
    // Bullet[] bullets;
    int position = 0;
    
    public BulletIterator(Vector<Bullet> newBullets) {
        this.bullets = newBullets; }
    
    @Override
    public boolean hasNext() {
        if (position >= bullets.size() || bullets.get(position) == null) {
            return false;
            } else {
            return true; }
    }

    @Override
    public Object next() {
        Bullet nextBullet = bullets.get(position);
        position = position + 1;
        return nextBullet; 
    }
    
    @Override
    public int position() {
        return position - 1;
    }

}
