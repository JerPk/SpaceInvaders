package Alien;

import java.util.Random;
import java.util.Vector;

import Bullet.Bullet;
import Bullet.DiagonalBulletLeft;
import Bullet.DiagonalBulletRight;
import Bullet.MegaBullet;
import spaceinvaders.spaceinvaders_framework.Game;
import spaceinvaders.spaceinvaders_framework.SpriteSheet;

public class BossAlien extends Alien{
    
    /**
     * the horizontal movement speed of the aliens.
     */
    private int movementSpeed = 1;
    private int randomNum;
    private boolean overwrite;
    
    /**
     * the constructor of alien type 3.
     * 
     * @param x
     * @param y
     * @param g
     */
    public BossAlien(double x, double y, Game g) {
        super(x, y, g);
        Game.logfile.writeCreate("BossAlien", x, y);
        setSpritesheet(215, 225, 50, 20);
        setScore(100);
        setHealth(20);
    }
    
    
    /**
     * the method that moves the aliens in the horizontal direction.
     */
    @Override
    public void hmovement() {
        // check if the alien has reached if the alien has reached the right
        // hand border.
        if (movementSpeed > 0 && getX() >= 600) {
            getGame().updateLogic();
        }

        // check if the alien has reached if the alien has reached the left hand
        // border.
        if (movementSpeed < 0 && getX() <= 2) {
            getGame().updateLogic();
        }

        // moves the alien in the horizontal direction.
        setX(getX() + movementSpeed);
    }

    /**
     * the method that moves the alien in the vertical direction.
     */
    @Override
    public void vmovement() {
        // move the alien in the vertical direction

        setY(getY() + 20);

        // flip the movement speed so now the alien will move in
        // the other direction.
        movementSpeed = -movementSpeed;

        // check if the alien has reached the bootom ofthe screen if so.
        // end the game.
        if (getY() > 450) {
            getGame().end();
        }
    }
    
    /**
     * the method creates a new vector of bullets and returns it. the type of bullets returend is randomly decided.
     *
     * @return Bullet newBullet
     */
    @Override
    public Vector<Bullet> BossShoot() {
      final Vector<Bullet> newBullets = new Vector<Bullet>();
      
      // Usually this can be a field rather than a method variable
      Random rand = new Random();
      
      if(overwrite == false){
      randomNum = rand.nextInt(3) + 1;
      }
      
      Game.logfile.writeShoot("BossAlien", getX(), getY());
      
      if(randomNum == 1){
          Vector<Bullet> newTridentBullets = tridentBullets();
          newBullets.addAll(newTridentBullets);
          
       return newBullets; 
      }
      else if(randomNum == 2) {
          
          Vector<Bullet> newSpeedBullets = multipleSpeedBullets();
          newBullets.addAll(newSpeedBullets);
          
       return newBullets;   
      }
      else if(randomNum == 3) {
          Vector<Bullet> newMegaBullet = MegaBullet();
          newBullets.addAll(newMegaBullet);
          return newBullets;  
      }
      return null;
    }
    
    /**
     * method that creates and returns a single mega bullet
     * @return
     */
    public Vector<Bullet> MegaBullet() {
        final SpriteSheet spritesheet = new SpriteSheet(getGame().getSpriteSheet());
        final Vector<Bullet> newBullets = new Vector<Bullet>();
        
        final Bullet MegaBullet0 = new MegaBullet(getX() + 20, getY() + 13, spritesheet);
        newBullets.add(MegaBullet0);
        
        return newBullets; 
    }
    
    /**
     * a method that returns a multitude of fast bullets.
     * @return
     */
    public Vector<Bullet> multipleSpeedBullets() {
        final SpriteSheet spritesheet = new SpriteSheet(getGame().getSpriteSheet());
        final Vector<Bullet> newBullets = new Vector<Bullet>();
        
        final Bullet newSpeedBullet0 = new Bullet(getX() + 0, getY() + 13, spritesheet);
        final Bullet newSpeedBullet1 = new Bullet(getX() + 10, getY() + 13, spritesheet);
        final Bullet newSpeedBullet2 = new Bullet(getX() + 20, getY() + 13, spritesheet);
        final Bullet newSpeedBullet3 = new Bullet(getX() + 30, getY() + 13, spritesheet);
        final Bullet newSpeedBullet4 = new Bullet(getX() + 40, getY() + 13, spritesheet);
        
        newSpeedBullet0.setDownSpeed(6.6);
        newSpeedBullet0.setSpritesheet(200,605,6,12);
        newSpeedBullet1.setDownSpeed(6.6);
        newSpeedBullet1.setSpritesheet(200,605,6,12);
        newSpeedBullet2.setDownSpeed(6.6);
        newSpeedBullet2.setSpritesheet(200,605,6,12);
        newSpeedBullet3.setDownSpeed(6.6);
        newSpeedBullet3.setSpritesheet(200,605,6,12);
        newSpeedBullet4.setDownSpeed(6.6);
        newSpeedBullet4.setSpritesheet(200,605,6,12);
        
        newBullets.add(newSpeedBullet0);
        newBullets.add(newSpeedBullet1);
        newBullets.add(newSpeedBullet2);
        newBullets.add(newSpeedBullet3);
        newBullets.add(newSpeedBullet4);
        
        return newBullets;
    }
    
    /**
     * a method that returns a trident of bullets.
     * @return
     */
    public Vector<Bullet> tridentBullets() {
        final SpriteSheet spritesheet = new SpriteSheet(getGame().getSpriteSheet());
        final Vector<Bullet> newBullets = new Vector<Bullet>();
        
        final Bullet newBullet0 = new DiagonalBulletLeft(getX() + 20, getY() + 13, spritesheet);
        final Bullet newBullet1 = new Bullet(getX() + 20, getY() + 13, spritesheet);
        final Bullet newBullet2 = new DiagonalBulletRight(getX() + 20, getY() + 13, spritesheet);
        
        newBullets.add(newBullet0);
        newBullets.add(newBullet1);
        newBullets.add(newBullet2);
        return newBullets;
        
    }
    
    public void setRand(int random){
        randomNum = random;
        overwrite = true;
    }
    
    @Override
    public int ifHit(Vector<Bullet> shipBullets) {
        for (int i = 0; i < shipBullets.size(); i++) {
        	if (shipBullets.get(i).getY() > ypos-12 && shipBullets.get(i).getY() < ypos + 20) {
        		if (shipBullets.get(i).getX() > xpos-6 && shipBullets.get(i).getX() < xpos + 50) {
                    Game.logfile.writeHit("Alien", xpos, ypos);

                    return i;
                }
            }
        }
        return -1;
    }
}
