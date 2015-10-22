package alien;

import bullet.Bullet;
import spaceinvaders.spaceinvaders_framework.Game;
import spaceinvaders.spaceinvaders_framework.SpriteSheet;

/**
 * the third alien type is considered the hardest.
 * it has the most health and the bullets fly the fastests.
 * @author Group 23
 *
 */
public class AlienType3 extends Alien{

    /**
     * the constructor of alien type 3.
     * 
     * @param x
     * @param y
     * @param g
     */
    public AlienType3(double x, double y) {
        super(x, y);
        Game.logfile.writeCreate("AlienType3", x, y);
        setSpritesheet(74, 225, 22, 16);
        setScore(30);
        setHealth(3);
    }

    /**
     * the method creates a new bullet on the position of the alien
     * and returns it. overrides the standard method because this bullet
     * moves faster and has a different sprite.
     *
     * @return Bullet newBullet
     */
    @Override
    public Bullet shoot() {
//      final SpriteSheet spritesheet = new SpriteSheet(BImg);
      final Bullet newBullet = new Bullet(getX() + 5, getY() + 2);
      Game.logfile.writeShoot("AlienType3", getX(), getY());
      
      newBullet.setDownSpeed(6.6);
      newBullet.setSpritesheet(200,605,6,12);
      
      
      return newBullet;
    }
}
