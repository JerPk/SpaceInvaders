package Space_Invaders;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Vector;

/**
 * The Alien class.
 * 
 * @author Group 23
 *
 */
public class Alien {

  /**
   *  the x coordinate of the Alien.
   */
  private double x;
  
  /**
     *  the y coordinate of the Alien.
     */
  private double y;

  /**
   *  the game the alien is a part of.
   */
  private Game game;
  /**
   *  the horizontal movement speed of the aliens.
   */
  private int movementSpeed = 1;
  
  /**
   * the bufferedImage of the alien.
   */
  private BufferedImage Alien;
  
  /**
   * the constructor of the Alien class.
   * 
   * @param double x
   * @param double y
   * @param  Game g
   */
  public Alien(double x, double y, Game g) {
    this.x = x;
    this.y = y;
    game = g;
    
    Game.logfile.writeCreate("Alien", x, y);
    SpriteSheet spritesheet = new SpriteSheet(g.getSpriteSheet());
    Alien = spritesheet.grabImage(7, 225, 16, 16);
  }
  
  /**
   * the method that moves the aliens in the horizontal direction.
   */
  public void hmovement() {
    //check if the alien has reached if the alien has reached the right hand border.
    if(movementSpeed > 0 && x >= 630) {
      game.updateLogic();
    }
    
    //check if the alien has reached if the alien has reached the left hand border.
    if(movementSpeed < 0 && x <= 2) {
      game.updateLogic();
    }
    
    //moves the alien in the horizontal direction.
    x +=  movementSpeed;
      
    game.logfile.writeMove("Alien", x, y);
  }
  
  /**
   * the method that moves the alien in the vertical direction.
   */
  public void vmovement() {
        //move the alien in the vertical direction
    y += 20;
    
    //flip the movement speed so now the alien will move in
    //the other direction.
    movementSpeed = -movementSpeed;
    
    game.logfile.writeMove("Alien", x, y);
    //check if the alien has reached the bootom ofthe screen if so.
    //end the game.
    if(y > 450) {
      game.end();
    }
    
  }
  
  /**
   * the method creates a new bullet on the position of the alien
   * and returns it.
   *
   * @return Bullet newBullet
   */
  public Bullet shoot() {
    final SpriteSheet spritesheet = new SpriteSheet(game.getSpriteSheet());
    final Bullet newBullet = new Bullet(x + 5, y + 2, spritesheet);
    game.logfile.writeShoot("Alien", x, y);
    return newBullet;
  }
  
  /**
   * ifHit method compares every bullet from shipBullets with the coordinates of every alien
   * if the coordinates match the alien is considered hit and the integer of its place in the 
   * array is returned.
   * 
   * @param shipBullets
   * @return
   */
  public int ifHit(Vector<Bullet> shipBullets) {
    for (int i = 0; i < shipBullets.size(); i++) {
      if (shipBullets.get(i).getX() >= x && shipBullets.get(i).getX() <= x+16) {
        if (shipBullets.get(i).getY() >= y && shipBullets.get(i).getY() <= y+16) {
          return i;
        }
      }
    }
    return -1;
  }

  
  /**
   * the method used to draw the aliens on the screen.
   * 
   * @param Graphics g
   */
  public void render(Graphics g) {
    g.drawImage(Alien,(int) x,(int) y, null);
  }
  
  /**
   * the get method for the X coordinate.
   */
  public double getX() {
    return x;
  }
  
  /**
   * the get method for the Y coordinate
   */
  public double getY(){
    return y;
  }
  
  /**
   * the set method for the MovementSpeed.
   */
  public void setMovementSpeed(int x) {
    movementSpeed = x;
  }
}
