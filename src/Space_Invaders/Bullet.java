package Space_Invaders;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Bullet {

	//Speed of the shot
	private double speed =  -300;
	//Type
	//String type;
	//Game class, should it manage the removal of bullets?
	private Game game;
	private BufferedImage Bullet;
	//Bullet coordinates
	private double x,y;
	public Bullet(double x, double y, Game g){
		this.x = x;
		this.y = y;
		game = g;
		
		SpriteSheet ss = new SpriteSheet(g.getSpriteSheet());
		Bullet = ss.grabImage(411, 275, 16, 16); //How do i get the coordinates in the sprite? 

	}

	//Move the bullet (speed and time)
	public void moveBullet(long delta) {
		//Move the bullet up
		y += (delta * speed) / 1000;
		//Move the bullet down
		//y -= (delta * speed) / 1000;
		
	
	}
	
	
	//Draw the bullet
	public void render(Graphics g){
		g.drawImage(Bullet,(int) x,(int) y, null);
	}
	
}
