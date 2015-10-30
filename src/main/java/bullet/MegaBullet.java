package bullet;

/**
 * MegaBullet is a subclass of bullet. this new Bullet is way larger than a
 * normal bullet.
 * 
 * @author Group 23
 */
public class MegaBullet extends Bullet {

  /**
   * Constructor of the Mega bullet.
   * 
   * @param x
   *          x coordinate
   * @param y
   *          y coordinate
   */
  public MegaBullet(final double x, final double y) {
    super(x, y);
    setSpritesheet(475, 890, 30, 60);
  }

}
