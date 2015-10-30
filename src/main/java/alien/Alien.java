package alien;

import bullet.Bullet;
import iterator.ConcreteAggregate;
import iterator.Iterator;
import spaceinvaders.LogFile;
import spaceinvaders.SpriteSheet;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Vector;

/**
 * The Alien class is the abstract super class of all the alien types.
 * 
 * @author Group 23
 */
public abstract class Alien {

  /**
   * the score the player gets for killing an alien.
   */
  private int score;
  /**
   * the x coordinate of the Alien.
   */
  protected double xpos;

  /**
   * the y coordinate of the Alien.
   */
  protected double ypos;

  /**
   * the horizontal movement speed of the aliens.
   */
  private int movementSpeed = 1;

  /**
   * the bufferedImage of the alien.
   */
  private BufferedImage alien;

  /**
   * the health of the alien. essentially how often the space ship has to hit
   * the alien before it dies.
   */
  protected int health = 1;

  /**
   * a boolean that is only true if the aliens need to be updated. It is used
   * for moving all the aliens down simultaneously.
   * 
   */
  private static boolean logicRequiredThisLoop = false;

  /**
   * ConcreteAggregate for iteration through loops.
   */
  protected ConcreteAggregate concreteAggregate = new ConcreteAggregate();

  /**
   * the constructor of the Alien class.
   * 
   * @param double
   *          x coordinate
   * @param double
   *          y coordinate
   */
  public Alien(final double x, final double y) {
    this.xpos = x;
    this.ypos = y;

  }

  /**
   * the method that moves the aliens in the horizontal direction.
   */
  public void hmovement() {
    // check if the alien has reached if the alien has reached the right
    // hand border.
    if (movementSpeed > 0 && xpos >= 630) {
      setUpdateLogic(true);
    }

    // check if the alien has reached if the alien has reached the left hand
    // border.
    if (movementSpeed < 0 && xpos <= 2) {
      setUpdateLogic(true);
    }

    // moves the alien in the horizontal direction.
    xpos += movementSpeed;
  }

  /**
   * the method that moves the alien in the vertical direction.
   */
  public void vmovement() {
    // move the alien in the vertical direction

    ypos += 20;

    // flip the movement speed so now the alien will move in
    // the other direction.
    movementSpeed = -movementSpeed;

    setUpdateLogic(false);
  }

  /**
   * the method creates a new bullet on the position of the alien and returns
   * it.
   *
   * @return Bullet newBullet
   */
  public Bullet shoot() {
    return null;
  }

  /**
   * the method to add the score of the killed alien.
   * 
   * @param s
   *          already scored points
   * @return new score
   */
  public final int addScore(int s) {
    return score + s;
  }

  /**
   * method to set the spritesheet of the alien.
   * 
   * @param row
   *          x coordinate of left edge
   * @param col
   *          y coordinate of top edge
   * @param x
   *          width
   * @param y
   *          height
   */
  public final void setSpritesheet(int row, int col, int x, int y) {
    alien = SpriteSheet.getInstance().grabImage(row, col, x, y);
  }

  /**
   * the method used to draw the aliens on the screen.
   * 
   * @param Graphics
   *          graphics
   */
  public final void render(Graphics graphics) {
    graphics.drawImage(alien, (int) xpos, (int) ypos, null);
  }

  /**
   * the get method for the X coordinate.
   * 
   * @return x coordinate
   */
  public final double getX() {
    return xpos;
  }

  /**
   * the set method for the X coordinate.
   * 
   * @param x
   *          coordinate
   */
  public final void setX(double newX) {
    xpos = newX;
  }

  /**
   * the get method for the Y coordinate.
   * 
   * @return y coordinate
   */
  public final double getY() {
    return ypos;
  }

  /**
   * the set method for the Y coordinate.
   * 
   * @param y
   *          coordinate
   */
  public final void setY(double newY) {
    ypos = newY;
  }

  /**
   * getter method for the LogicRequiredThisLoop boolean.
   * 
   * @return boolean
   */
  public static boolean getUpdateLogic() {
    return logicRequiredThisLoop;
  }

  /**
   * setter method for the LogicRequiredThisLoop boolean.
   * 
   * @return boolean
   */
  public static void setUpdateLogic(final boolean bool) {
    logicRequiredThisLoop = bool;
  }

  /**
   * the set method for the MovementSpeed.
   */
  public void setMovementSpeed(int x) {
    movementSpeed = x;
  }

  /**
   * ifHit method compares every bullet from shipBullets with the coordinates of
   * every alien if the coordinates match the alien is considered hit and the
   * integer of its place in the array is returned.
   * 
   * @param shipBullets
   * @return position of iterator
   */
  public int ifHit(Vector<Bullet> shipBullets) {
    Iterator iterShipBullets = concreteAggregate.createIterator(shipBullets);

    while (iterShipBullets.hasNext()) {

      Bullet bullet = (Bullet) iterShipBullets.next();
      if (bullet.getY() > ypos - 12 && bullet.getY() < ypos + 16) {
        if (bullet.getX() > xpos - 6 && bullet.getX() < xpos + 16) {
          LogFile.getInstance().writeHit("Alien", xpos, ypos);
          health--;
          return iterShipBullets.position();
        }
      }

    }

    return -1;
  }

  /**
   * the method that checks if the alien has any health left.
   *
   * @return true/false
   */
  public final boolean defeated() {
    return health <= 0;
  }

  /**
   * This method checks if the alien has reached a certain height.
   * 
   * @param yCoordinate the y coordinate to check
   * @return true/false
   */
  public final boolean reachedY(final double yCoordinate) {
    return ypos >= yCoordinate;
  }

  /**
   * the method that returns the score.
   * 
   * @return score
   */
  public final int getScore() {
    return score;
  }

  /**
   * The method that sets the score.
   * 
   * @param scoreNew
   *          the new score
   */
  public final void setScore(final int scoreNew) {
    score = scoreNew;
  }

  /**
   * the method that sets the alien's health.
   * 
   * @param newHealth the new health
   */
  public final void setHealth(int newHealth) {
    health = newHealth;
  }

  /**
   * the method that returns the health of the alien.
   * 
   * @return health
   */
  public final int getHealth() {
    return health;
  }

  /**
   * the method that creates and returns an alien of type 1.
   * 
   * @param x
   *          coordinate
   * @param y
   *          coordinate
   * @return created alien
   */
  public static Alien createAlienType1(final double x, final double y) {
    Alien alien = new AlienType1(x, y);
    return alien;
  }

  /**
   * the method that creates and returns an alien of type 2.
   * 
   * @param x
   *          coordinate
   * @param y
   *          coordinate
   * @return created alien
   */
  public static Alien createAlienType2(final double x, final double y) {
    Alien alien = new AlienType2(x, y);
    return alien;
  }

  /**
   * the method that creates and returns an alien of type 3.
   * 
   * @param x
   *          coordinate
   * @param y
   *          coordinate
   * @return created alien
   */
  public static Alien createAlienType3(final double x, final double y) {
    Alien alien = new AlienType3(x, y);
    return alien;
  }

  /**
   * the method that creates and returns a boss alien.
   * 
   * @param x
   *          coordinate
   * @param y
   *          coordinate
   * @return created boss alien
   */
  public static Alien createBossAlien(final double x, final double y) {
    Alien alien = new BossAlien(x, y);
    return alien;
  }

  /**
   * the method which boss alien overrides to shoot.
   * 
   * @return
   */
  public Vector<Bullet> BossShoot() {
    return null;
  }
}