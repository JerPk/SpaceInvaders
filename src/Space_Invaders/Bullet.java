package Space_Invaders;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Bullet {

	//Speed of the shot
	private double speed =  -1100;
	//Type
	//String type;
	private BufferedImage bullet;
	//Bullet coordinates
	private double xpos,ypos;
	public Bullet(double x, double y, SpriteSheet ss){
		this.xpos = x;
		this.ypos = y;

		bullet = ss.grabImage(413, 277, 6, 12);
	}
	//Move the bullet up
	public void moveUp(long delta){   
		ypos +=  (delta * speed) / 100000;;
	}
		
	//Move the bullet down
	public void moveDown(long delta){
		ypos -= (delta * speed) / 100000;
		}
	
	//Draw the bullet
	public void render(Graphics g){
		g.drawImage(bullet,(int) xpos,(int) ypos, null);
	}
	
	public double getX() {
		return xpos;
	}
	
	public double getY() {
		return ypos;
	}
	
}
