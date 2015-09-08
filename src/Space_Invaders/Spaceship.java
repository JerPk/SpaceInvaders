package Space_Invaders;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Spaceship {

	// the x and y coordinates of the Spaceship.
<<<<<<< HEAD
	private int x;
  private static int y;

  /**
   * this method makes the ship move left by 1
   * as long as it hasn't reached the border
   *
   */
  public void moveLeft() {
    if (x > 10) {
      x-= 1;
    }
  }

  /**
   * this method makes the ship move right by 1
   * as long as it hasn't reached the border
   *
   */
  public void moveRight() {
    if (x < 590) {
      x += 1;
    }
  }
=======
	private double x;
	private static double y = 425;
	
	// the game the alien is a part of.
	private Game game;
	// the horizontal movement speed of the aliens.
	private int MovementSpeed = 1;

	// the bufferedImage of the spaceship.
	private BufferedImage Spaceship;
	
	/**
	 * this method makes the ship move left by 1 as long as it hasn't reached
	 * the border
	 *
	 */
	public void moveLeft() {
		if (x > 10) {
			x -= 1;
		}
	}

	/**
	 * this method makes the ship move right by 1 as long as it hasn't reached
	 * the border
	 *
	 */
	public void moveRight() {
		if (x < 625) {
			x += 1;
		}
	}
	
	/**
	 * the constructor of the Spaceship class.
	 * 
	 * @param int x
	 * @param int y
	 * @param  Game g
	 */
	public Spaceship(Game g){
		x = 317;
		game = g;
		
		SpriteSheet ss = new SpriteSheet(g.getSpriteSheet());
		Spaceship = ss.grabImage(277, 228, 26, 16);
	}
	
	public Bullet Shoot() {
		Bullet newBullet = new Bullet();
		//Bullet newBullet = new Bullet(x,y,game);
		return newBullet;
	}
>>>>>>> origin/Jeroen

	/**
	 * the method that returns the x position
	 *
	 * @return x position
	 */
	public double getPosX() {
		return x;
	}

	/**
	 * the method that returns the y position
	 *
	 * @return y position
	 */
	public double getPosY(){
		return y;
	}
	
	/**
	 * the method used to draw the aliens on the screen.
	 * 
	 * @param Graphics g
	 */
	public void render(Graphics g){
		g.drawImage(Spaceship,(int) x,(int) y, null);
	}
	
}
