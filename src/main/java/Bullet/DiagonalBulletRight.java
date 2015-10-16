package Bullet;

import spaceinvaders.spaceinvaders_framework.SpriteSheet;

/**
 * DiagonalBulletLeft is a subclass of bullet.
 * this new Bullet moves both vertically and horizontally at the same time.
 * 
 * @author Group 23
 *
 */
public class DiagonalBulletRight extends Bullet {

    /**
     * the constructor method of the Diagonal bullet.
     * 
     * @param x
     * @param y
     * @param ss
     */
    public DiagonalBulletRight(double x, double y, SpriteSheet ss) {
        super(x, y, ss);
    }

  //Move the bullet down and sideways.
    @Override
    public void moveDown(){
        setY(getY() + 2.2);
        setX(getX() + 0.7);
    }
}
