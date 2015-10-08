package spaceinvaders.spaceinvaders_framework;
/**
 * the second alien type is considered normal.
 * it has both normal health and medium speed for the bullets
 * 
 * @author Group 23
 *
 */
public class AlienType2 extends Alien{

    /**
     * the constructor of alien type 2.
     * 
     * @param x
     * @param y
     * @param g
     */
    public AlienType2(double x, double y, Game g) {
        super(x, y, g);
        setSpritesheet(40, 225, 16, 16);
        setScore(20);
        setHealth(2);
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
      final SpriteSheet spritesheet = new SpriteSheet(getGame().getSpriteSheet());
      final Bullet newBullet = new Bullet(getX() + 5, getY() + 2, spritesheet);
      Game.logfile.writeShoot("Alien", getX(), getY());
      
      newBullet.setDownSpeed(4.4);
      newBullet.setSpritesheet(172,605,6,16);
      
      
      return newBullet;
    }

}
