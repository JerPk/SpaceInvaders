package Space_Invaders;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Alien {

	// the x and y coordinates of the Alien.
	private int x, y;
	// the game the alien is a part of.
	private Game game;
	// the horizontal movement speed of the aliens.
	private int MovementSpeed = 1;
	
	//the bufferedImage of the alien.
	private BufferedImage Alien;
	
	/**
	 * the constructor of the Alien class.
	 * 
	 * @param int x
	 * @param int y
	 * @param  Game g
	 */
	public Alien(int x, int y, Game g){
		this.x = x;
		this.y = y;
		game = g;
		
		SpriteSheet ss = new SpriteSheet(g.getSpriteSheet());
		Alien = ss.grabImage(7, 225, 16, 16);
	}
	
	/**
	 * the method that moves the aliens in the horizontal direction
	 */
	public void HMovement(){
		//check if the alien has reached if the alien has reached the right hand border.
		if(MovementSpeed > 0 && x >= 630){
			game.updateLogic();
		}
		
		//check if the alien has reached if the alien has reached the left hand border.
		if(MovementSpeed < 0 && x <= 2){
			game.updateLogic();
		}
		
		//moves the alien in the horizontal direction.
		x +=  MovementSpeed;
	}
	
	/**
	 * the method that moves the alien in the vertical direction
	 */
	public void VMovement(){
        //move the alien in the vertical direction
		y += 20;
		
		//flip the movement speed so now the alien will move in
		//the other direction.
		MovementSpeed = -MovementSpeed;
		
		//check if the alien has reached the bootom ofthe screen if so.
		//end the game.
		if(y > 450){
			game.end();
		}
	}
	
	/**
	 * the method used to draw the aliens on the screen.
	 * 
	 * @param Graphics g
	 */
	public void render(Graphics g){
		g.drawImage(Alien,(int) x,(int) y, null);
	}
	
	/**
	 * the method used to let an alien shoot
	 * it creates a bullet with type alien
	 * and the x and y position of the alien
	 */
	public void shoot(){
		new Bullet(/*type,*/ x, y);
	}
	
	/**
	 * the method that returns the x position
	 * 
	 * @return x position
	 */
	public int getPosX(){
		return x;
	}
	
	/**
	 * the method that returns the y position
	 * 
	 * @return y position
	 */
	public int getPosY(){
		return y;
	}
}
