package bullet;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import spaceinvaders.LogFile;
import spaceinvaders.SpriteSheet;

/**
 * The Bullet class.
 * 
 * @author Group 23
 */
public class Bullet {

  /**
   * buffered image of the bullet.
   */
  private BufferedImage bullet;
  /**
   * x position of the bullet.
   */
  private double xpos, ypos;
  
  /**
   * speed at which the bullet moves down.
   */
  protected double downspeed = 2.2;

  /**
   * Constructor of the bullet.
   * 
   * @param x
   *          x coordinate
   * @param y
   *          y coordinate
   */
  public Bullet(final double x, final double y) {
    this.xpos = x;
    this.ypos = y;

    LogFile.getInstance().writeCreate("Bullet", xpos, ypos);
    bullet = SpriteSheet.getInstance().grabImage(413, 277, 6, 12);
  }

  /**
   * Method to move the bullet up.
   */
  public final void moveUp() {
    ypos -= 2.2;
  }

  /**
   * Method to move the bullet down.
   */
  public void moveDown() {
    ypos += downspeed;
  }

  /**
   * Method to draw the bullet.
   * 
   * @param graphic
   */
  public final void render(final Graphics graphic) {
    graphic.drawImage(bullet, (int) xpos, (int) ypos, null);
  }

  /**
   * Method to get the x position.
   * 
   * @return x position
   */
  public final double getX() {
    return xpos;
  }

  /**
   * Method to set the x coordinate.
   * 
   * @param newX
   *          new x coordinate
   */
  public final void setX(final double newX) {
    xpos = newX;
  }

  /**
   * Method to get the y position.
   * 
   * @return y position
   */
  public final double getY() {
    return ypos;
  }

  /**
   * Method to set the y coordinate.
   * 
   * @param newY
   *          new y coordinate
   */
  public final void setY(final double newY) {
    ypos = newY;
  }

  /**
   * This method checks if the bullet has reached a certain height.
   * 
   * @return true/false
   */
  public final boolean reachedY(final double i) {
    return ypos >= i;
  }

  /**
   * method to set the speed of a bullet.
   * 
   * @param speed
   *          speed of the bullet
   */
  public final void setDownSpeed(final double speed) {
    downspeed = speed;
  }

  /**
   * Method to set the spritesheet of the bullet.
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
    bullet = SpriteSheet.getInstance().grabImage(row, col, x, y);
  }

  /**
   * Method to compare two bullets.
   * 
   * @return same bullets or not
   */
  @Override
  public boolean equals(final Object other) {
    boolean result = false;
    if (other instanceof Bullet) {
      Bullet that = (Bullet) other;
      if (this.getX() == that.getX()) {
        if (this.getY() == that.getY()) {
          result = true;
        }
      }
    }

    return result;
  }
}
