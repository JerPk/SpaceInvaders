package bullet;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import spaceinvaders.spaceinvaders_framework.Game;
import spaceinvaders.spaceinvaders_framework.Screen;

public class Bullet {

    private BufferedImage bullet;
    //Bullet coordinates
    private double xpos,ypos;
    protected double downspeed = 2.2;
    
    public Bullet(double x, double y){
        this.xpos = x;
        this.ypos = y;

        Game.logfile.writeCreate("Bullet", xpos, ypos);
        
        bullet = Screen.spritesheet.grabImage(413, 277, 6, 12);
    }
    //Move the bullet up
    public void moveUp(){   
        ypos -=  2.2;
    }
        
    //Move the bullet down
    public void moveDown(){
        ypos += downspeed;
    }
    
    //Draw the bullet
    public void render(Graphics g){
        g.drawImage(bullet,(int) xpos,(int) ypos, null);
    }
    
    public double getX() {
        return xpos;
    }
    
    public void setX(double newX) {
        xpos = newX;
    }
    
    public double getY() {
        return ypos;
    }
    
    public void setY(double newY) {
        ypos = newY;
    }
    
    /**
     * This method checks if the bullet has reached a certain height
     * 
     * @return true/false
     */
    public boolean reachedY(double i) {
    	if (ypos >= i) {
    		return true;
    	} else {
    		return false;
    	}
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
    
    public void setDownSpeed(double speed) {
        downspeed = speed;
    }
    
    public void setSpritesheet(int row, int col, int x, int y){
       bullet = Screen.spritesheet.grabImage(row, col, x, y);
    }
    
}
