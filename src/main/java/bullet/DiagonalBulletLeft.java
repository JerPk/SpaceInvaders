package bullet;

import spaceinvaders.spaceinvaders_framework.SpriteSheet;

/**
 * DiagonalBulletLeft is a subclass of bullet.
 * this new Bullet moves both vertically and horizontally at the same time.
 * 
 * @author Group 23
 *
 */
public class DiagonalBulletLeft extends Bullet {

    /**
     * the constructor method of the Diagonal bullet.
     * 
     * @param x
     * @param y
     * @param ss
     */
    public DiagonalBulletLeft(double x, double y) {
        super(x, y);
    }

  //Move the bullet down and sideways.
    @Override
    public void moveDown(){
        setY(getY() + downspeed);
        setX(getX() - 0.7);
    }
}
