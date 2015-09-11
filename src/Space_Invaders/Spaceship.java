package Space_Invaders;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Vector;

public class Spaceship {


	// the x and y coordinates of the Spaceship.
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
			x -= 2;
		}
	}

	/**
	 * this method makes the ship move right by 1 as long as it hasn't reached
	 * the border
	 *
	 */
	public void moveRight() {
		if (x < 600) {
			x += 2;
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
	
	/**
	 * the method creates a new bullet on the position of the ship
	 * and returns it
	 *
	 * @return Bullet newBullet
	 */
	public Bullet shoot() {
		
		
		SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
		Bullet newBullet = new Bullet(x,y,ss);
		return newBullet;
	}
	
	public boolean ifHit(Vector<Bullet> alienBullets) {
		for (Bullet bullet : alienBullets) {
			if (bullet.getX() >= x && bullet.getX() <= x+20) {
				if (bullet.getY() >= y && bullet.getX() <= y+5) {
					return true;
				}
			}
		}
		return false;
	}


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
