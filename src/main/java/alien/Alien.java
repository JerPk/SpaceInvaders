package alien;

import static org.junit.Assert.assertEquals;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Vector;

import org.junit.Test;

import bullet.Bullet;
import spaceinvaders.spaceinvaders_framework.Game;
import spaceinvaders.spaceinvaders_framework.SpriteSheet;

/**
 * The Alien class is the abstract super class of all the alien types.
 * 
 * @author Group 23
 */
public abstract class Alien {

    /**
     * the score the player gets for killing an alien.
     */
    private int score;
    /**
     * the x coordinate of the Alien.
     */
    protected double xpos;

    /**
     * the y coordinate of the Alien.
     */
    protected double ypos;

    /**
     * the game the alien is a part of.
     */
    private Game game;

    /**
     * the horizontal movement speed of the aliens.
     */
    private int movementSpeed = 1;

    /**
     * the bufferedImage of the alien.
     */
    private BufferedImage Alien;

    /**
     * the health of the alien. essentially how often the space ship has to hit
     * the alien before it dies.
     */
    private int health = 1;

    /**
     * the constructor of the Alien class.
     * 
     * @param double x
     * @param double y
     * @param Game
     *            g
     */
    public Alien(double x, double y, Game g) {
        this.xpos = x;
        this.ypos = y;
        game = g;
    }

    /**
     * the method that moves the aliens in the horizontal direction.
     */
    public void hmovement() {
        // check if the alien has reached if the alien has reached the right
        // hand border.
        if (movementSpeed > 0 && xpos >= 630) {
            game.updateLogic();
        }

        // check if the alien has reached if the alien has reached the left hand
        // border.
        if (movementSpeed < 0 && xpos <= 2) {
            game.updateLogic();
        }

        // moves the alien in the horizontal direction.
        xpos += movementSpeed;
    }

    /**
     * the method that moves the alien in the vertical direction.
     */
    public void vmovement() {
        // move the alien in the vertical direction

        ypos += 20;

        // flip the movement speed so now the alien will move in
        // the other direction.
        movementSpeed = -movementSpeed;

        // check if the alien has reached the bootom ofthe screen if so.
        // end the game.
        if (ypos > 450) {
            game.end();
        }
    }

    /**
     * the method creates a new bullet on the position of the alien and returns
     * it.
     *
     * @return Bullet newBullet
     */
    public Bullet shoot() {
        final SpriteSheet spritesheet = new SpriteSheet(getGame()
                .getSpriteSheet());
        final Bullet newBullet = new Bullet(getX() + 5, getY() + 2, spritesheet);
        Game.logfile.writeShoot("Alien", getX(), getY());

        return newBullet;
    }

    public void setSpritesheet(int row, int col, int x, int y) {
        SpriteSheet spritesheet = new SpriteSheet(game.getSpriteSheet());
        Alien = spritesheet.grabImage(row, col, x, y);
    }

    /**
     * the method used to draw the aliens on the screen.
     * 
     * @param Graphics
     *            g
     */
    public void render(Graphics g) {
        g.drawImage(Alien, (int) xpos, (int) ypos, null);
    }

    /**
     * the get method for the X coordinate.
     */
    public double getX() {
        return xpos;
    }
    
    /**
     * the set method for the X coordinate.
     */
    public void setX(double newX) {
        xpos = newX;
    }
    
    /**
     * the set method for the Y coordinate.
     */
    public void setY(double newY) {
        ypos = newY;
    }
    

    /**
     * the get method for the Y coordinate
     */
    public double getY() {
        return ypos;
    }

    /**
     * the set method for the MovementSpeed.
     */
    public void setMovementSpeed(int x) {
        movementSpeed = x;
    }

    /**
     * ifHit method compares every bullet from shipBullets with the coordinates
     * of every alien if the coordinates match the alien is considered hit and
     * the integer of its place in the array is returned.
     * 
     * @param shipBullets
     * @return
     */
    public int ifHit(Vector<Bullet> shipBullets) {
        for (int i = 0; i < shipBullets.size(); i++) {
        	if (shipBullets.get(i).getY() > ypos-12 && shipBullets.get(i).getY() < ypos + 16) {
        		if (shipBullets.get(i).getX() > xpos-6 && shipBullets.get(i).getX() < xpos + 16) {
                    Game.logfile.writeHit("Alien", xpos, ypos);

                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * the method that returns the score.
     * 
     * @return score
     */
    public int getScore() {
        return score;
    }

    /**
     * The method that sets the score.
     * 
     * @param Score
     *            the new score
     */
    public void setScore(int Score) {
        score = Score;
    }

    /**
     * the method that sets the alien's health.
     * 
     * @param Health
     */
    public void setHealth(int Health) {
        health = Health;
    }

    /**
     * the method that returns the health of the alien.
     * 
     * @return
     */
    public int getHealth() {
        return health;
    }

    /**
     * the method that returns the game associated with the alien.
     * 
     * @return
     */
    public Game getGame() {
        return game;
    }
    
    /**
     * the method that creates and returns an alien of type 1.
     * @param x
     * @param y
     * @param g
     * @return
     */
    public static Alien createAlienType1(double x, double y, Game g){
        Alien alien = new AlienType1(x,y,g);
        return alien;        
    }
    
    /**
     * the method that creates and returns an alien of type 2.
     * @param x
     * @param y
     * @param g
     * @return
     */
    public static Alien createAlienType2(double x, double y, Game g){
        Alien alien = new AlienType2(x,y,g);
        return alien;        
    }
    
    /**
     * the method that creates and returns an alien of type 3.
     * @param x
     * @param y
     * @param g
     * @return
     */
    public static Alien createAlienType3(double x, double y, Game g){
        Alien alien = new AlienType3(x,y,g);
        return alien;        
    }
    
    public Vector<Bullet> BossShoot() {
        return null;   
    }

    public static Alien createBossAlien(int x2, int y2, Game g) {
        Alien alien = new BossAlien(x2,y2,g);
        return alien;        
    }

 
}