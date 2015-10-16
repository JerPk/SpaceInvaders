package spaceinvaders.spaceinvaders_framework;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Vector;

public class Spaceship {

    // the x and y coordinates of the Spaceship.
    private double x;
    private static double y = 425;
    private int lives;

    // the bufferedImage of the spaceship.
    private BufferedImage Spaceship;
    private SpriteSheet ss;
    
    /**
     * the constructor of the Spaceship class.
     * 
     * @param int x
     * @param int y
     * @param  Game g
     */
    public Spaceship(){
        x = Screen.WIDTH/2-13;
        lives = 3;
        
        Game.logfile.writeCreate("Spaceship", x, y);
        
        BuffereImageLoader loader = new BuffereImageLoader();
        BufferedImage BImg = null;
        try {
            BImg = loader.LoadImage("res/sprite_sheet.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        ss = new SpriteSheet(BImg);
        Spaceship = ss.grabImage(277, 228, 26, 16);
        
    }
    
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
     * the method creates a new bullet on the position of the ship
     * and returns it
     *
     * @return Bullet newBullet
     */
    public Bullet shoot() {
        Bullet newBullet = new Bullet(x+10, y+2, ss);
        
        Game.logfile.writeShoot("Spaceship", getPosX(), getPosY());
        
        newBullet.setSpritesheet(423, 277, 6, 12);
        
        return newBullet;
    }
    
    public int ifHit(Vector<Bullet> alienBullets) {
        for (int i = 0; i < alienBullets.size(); i++) {
            if (alienBullets.get(i).getX() >= x && alienBullets.get(i).getX() <= x+26) {
                if (alienBullets.get(i).getY() >= y && alienBullets.get(i).getY() <= y+16) {
                    lives -= 1;
                    Game.logfile.writeHit("Spaceship", alienBullets.get(i).getX(), alienBullets.get(i).getY());
                    if (lives > 0) {
                        Game.logfile.writeString("Spaceship has " + String.valueOf(lives) + " lives left");
                    }
                    else {
                        Game.logfile.writeString("Spaceship has no lives left");
                    }
                    return i;
                }
            }
        }
        return -1;
    }
    
    /**
     * the method that checks if the ship has any lives left
     *
     * @return true/false
     */
    public boolean defeated() {
    	if (lives <= 0) {
    		return true;
    	} else {
    		return false;
    	}
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
     * the method that returns the amount of lives left.
     * 
     * @return amount of lives
     */
    public int getLives(){
        return lives;
    }
    
    /**
     * the method used to draw the spaceship on the screen.
     * 
     * @param Graphics g
     */
    public void render(Graphics g){
        g.drawImage(Spaceship,(int) x,(int) y, null);
        
        for (int i=1; i<=lives; i++){
            g.drawImage(Spaceship, 10+30*(i-1), 452, null);
        }
        
    }
    
    /**
     * the method that returns the bufferedImage of the spaceship
     */
    public BufferedImage getImage(){
        return Spaceship;
    }
    
    /**
     * the equals method returns if two spaceships are equal.
     */
    @Override
    public boolean equals(Object other){
        boolean result = false;
        if(other instanceof Spaceship){
            Spaceship that = (Spaceship) other;
            if(this.getPosX() == that.getPosX()){
                    //if(g.compareImages(this.getImage(), that.getImage())){
                        result = true;
                    //}
            }
        }
        return result;
    }
    
}
