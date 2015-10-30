package spaceinvaders;

import bullet.Bullet;
import bullet.MegaBullet;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Vector;

public class Barrier {

  private double xpos;
  private double ypos;
  private int state;

  private BufferedImage[] barrier = new BufferedImage[5];

  /**
   * Constructor of the barrier class.
   * 
   */
  public Barrier(double xco, double yco) {
    this.xpos = xco;
    this.ypos = yco;
    this.state = 0;

    LogFile.getInstance().writeCreate("Barrier", xpos, ypos);

    barrier[0] = SpriteSheet.getInstance().grabImage(316, 213, 44, 32);
    barrier[1] = SpriteSheet.getInstance().grabImage(480, 210, 44, 32);
    barrier[2] = SpriteSheet.getInstance().grabImage(480, 265, 44, 32);
    barrier[3] = SpriteSheet.getInstance().grabImage(373, 211, 44, 32);
    barrier[4] = SpriteSheet.getInstance().grabImage(428, 210, 44, 32);
  }

  /**
   * Checks if barrier is hit by alien bullets.
   * 
   * @param alienBullets
   * @return i
   */
  public int ifHit(Vector<Bullet> alienBullets) {
    for (int i = 0; i < alienBullets.size(); i++) {
      Bullet testBullet = alienBullets.get(i);
      if (testBullet instanceof MegaBullet) {
        if (ifHitMega(alienBullets, i) == true) {
          return i;
        }

      } else if (alienBullets.get(i).getY() > ypos - 10
          && alienBullets.get(i).getY() < ypos + 32) {
        if (alienBullets.get(i).getX() > xpos - 6
            && alienBullets.get(i).getX() < xpos + 44) {
          LogFile.getInstance().writeHit("Barrier", xpos, ypos);
          state++;
          return i;
        }
      }
    }
    return -1;
  }

  /**
   * Check if barrier is hit 5 times and therefore destroyed.
   * 
   * @return true/false
   */
  public boolean destroyed() {
    if (state >= 4) {
      return true;
    } else {
      return false;
    }
  }

  private boolean ifHitMega(Vector<Bullet> alienBullets, int index) {
    if (alienBullets.get(index).getX() + 15 >= xpos
        && alienBullets.get(index).getX() + 15 <= xpos + 44) {
      if (alienBullets.get(index).getY() + 50 >= ypos
          && alienBullets.get(index).getY() + 50 <= ypos + 32) {
        return true;
      }
    }
    return false;
  }

  /**
   * the method that returns the state.
   */
  public int getState() {
    return state;
  }

  /**
   * the method that returns the x position.
   *
   * @return x position
   */
  public double getPosX() {
    return xpos;
  }

  /**
   * the method that returns the y position.
   *
   * @return y position
   */
  public double getPosY() {
    return ypos;
  }

  public void render(Graphics graphic) {
    graphic.drawImage(barrier[state], (int) xpos, (int) ypos, null);

  }
}
