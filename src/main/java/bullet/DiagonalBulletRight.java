package bullet;

/**
 * DiagonalBulletLeft is a subclass of bullet. this new Bullet moves both
 * vertically and horizontally at the same time.
 * 
 * @author Group 23
 */
public class DiagonalBulletRight extends Bullet {

  /**
   * Constructor method of the Diagonal right bullet.
   * 
   * @param x
   *          x coordinate
   * @param y
   *          y coordinate
   */
  public DiagonalBulletRight(final double x, final double y) {
    super(x, y);
  }

  /**
  * Method to move the bullet down and a bit to the right.
  */
  @Override
  public final void moveDown() {
    setY(getY() + downspeed);
    setX(getX() + 0.7);
  }
}
