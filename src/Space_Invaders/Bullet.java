package Space_Invaders;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Bullet {

	//Speed of the shot
	private double speed =  -300;
	//Type
	//String type;
	private Game game;
	private BufferedImage Bullet;
	//Bullet coordinates
	private double x,y, a,b;
	public Bullet(double x, double y, Game g){
		this.x = x;
		this.y = y;
		
		SpriteSheet ss = new SpriteSheet(g.getSpriteSheet());
		Bullet = ss.grabImage(411, 275, 16, 16); 

	}
	//Move the bullet up
	public void moveBulletUp(long delta){   
		y +=  (delta * speed) / 100000;;
	}
		
	//Move the bullet down
	public void moveBulletDown(long delta){
		y -= (delta * speed) / 100000;
		}
	
	//Draw the bullet
	public void render(Graphics g){
		g.drawImage(Bullet,(int) x,(int) y, null);
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
}
