package Space_Invaders;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Bullet {

	private BufferedImage bullet;
	//Bullet coordinates
	private double xpos,ypos;
	
	public Bullet(double x, double y, SpriteSheet ss){
		this.xpos = x;
		this.ypos = y;

		Game.logfile.writeCreate("Bullet", xpos, ypos);
		bullet = ss.grabImage(413, 277, 6, 12);
	}
	//Move the bullet up
	public void moveUp(){   
		ypos -=  2.2;
	}
		
	//Move the bullet down
	public void moveDown(){
		ypos += 2.2;
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
	
	@Override
	public boolean equals(Object other){
        boolean result = false;
        
        if(other instanceof Bullet){
            Bullet that = (Bullet) other;
            if(this.getX() == that.getX()){
                if(this.getY() == that.getY()){
                    result = true;
                }
            }
        }
        
	    return result;
	    
	}
	
}
