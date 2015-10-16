package Bullet;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import spaceinvaders.spaceinvaders_framework.Game;
import spaceinvaders.spaceinvaders_framework.SpriteSheet;

public class Bullet {

    private BufferedImage bullet;
    private SpriteSheet spritesheet;
    //Bullet coordinates
    private double xpos,ypos;
    private double downspeed = 2.2;
    
    public Bullet(double x, double y, SpriteSheet ss){
        this.xpos = x;
        this.ypos = y;

        Game.logfile.writeCreate("Bullet", xpos, ypos);
        spritesheet = ss;
        bullet = spritesheet.grabImage(413, 277, 6, 12);
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
       bullet = spritesheet.grabImage(row, col, x, y);
    }
    
}
