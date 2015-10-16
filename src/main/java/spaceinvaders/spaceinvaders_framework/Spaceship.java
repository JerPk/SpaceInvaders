package spaceinvaders.spaceinvaders_framework;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Vector;

import Bullet.Bullet;
import Bullet.MegaBullet;

public class Spaceship {

    // the x and y coordinates of the Spaceship.
    private double x;
    private static double y = 425;
    private int lives;

    // the game the alien is a part of.
    private Game game;
    // the horizontal movement speed of the aliens.
    private int MovementSpeed = 1;

    // the bufferedImage of the spaceship.
    private BufferedImage Spaceship;

    /**
     * the constructor of the Spaceship class.
     * 
     * @param int x
     * @param int y
     * @param Game
     *            g
     */

    public Spaceship(Game g){

        game = g;
        x = game.WIDTH/2-13;
        
        lives = 3;

        Game.logfile.writeCreate("Spaceship", x, y);

        SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());

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
     * the method creates a new bullet on the position of the ship and returns
     * it
     *
     * @return Bullet newBullet
     */
    public Bullet shoot() {
        SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
        Bullet newBullet = new Bullet(x + 10, y + 2, ss);

        Game.logfile.writeShoot("Spaceship", getPosX(), getPosY());

        newBullet.setSpritesheet(423, 277, 6, 12);

        return newBullet;
    }

    public int ifHit(Vector<Bullet> alienBullets) {
        for (int i = 0; i < alienBullets.size(); i++) {
            Bullet testBullet = alienBullets.get(i);
            if (testBullet instanceof MegaBullet) {
                if (ifHitMega(alienBullets,i) == true) {
                    return i;
                }

            }
            if (alienBullets.get(i).getX() >= x
                    && alienBullets.get(i).getX() <= x + 26) {
                if (alienBullets.get(i).getY() >= y
                        && alienBullets.get(i).getY() <= y + 16) {
                    lives -= 1;
                    Game.logfile.writeHit("Spaceship", alienBullets.get(i)
                            .getX(), alienBullets.get(i).getY());
                    if (lives > 0) {
                        Game.logfile.writeString("Spaceship has "
                                + String.valueOf(lives) + " lives left");
                    } else {
                        Game.logfile.writeString("Spaceship has no lives left");
                    }
                    return i;
                }
            }
        }
        return -1;
    }
    
    /**
     * 
     */
    public void resetPosition() {
    	x = game.WIDTH/2-13;
    }

    private boolean ifHitMega(Vector<Bullet> alienBullets, int i) {
        if (alienBullets.get(i).getX() + 15 >= x
                && alienBullets.get(i).getX() + 15 <= x + 26) {
            if (alienBullets.get(i).getY() + 50 >= y
                    && alienBullets.get(i).getY() + 50 <= y + 16) {
                lives -= 1;
                Game.logfile.writeHit("Spaceship", alienBullets.get(i).getX(),
                        alienBullets.get(i).getY());
                if (lives > 0) {
                    Game.logfile.writeString("Spaceship has "
                            + String.valueOf(lives) + " lives left");
                } else {
                    Game.logfile.writeString("Spaceship has no lives left");
                }
                return true;
            }
        }
        return false;

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
    public double getPosY() {
        return y;
    }

    /**
     * the method that returns the amount of lives left.
     * 
     * @return amount of lives
     */
    public int getLives() {
        return lives;
    }

    /**
     * the method used to draw the spaceship on the screen.
     * 
     * @param Graphics
     *            g
     */
    public void render(Graphics g) {
        g.drawImage(Spaceship, (int) x, (int) y, null);

        for (int i = 1; i <= lives; i++) {
            g.drawImage(Spaceship, 10 + 30 * (i - 1), 452, null);
        }

    }

    /**
     * the method that returns the game the spaceship was created in
     */
    public Game getGame() {
        return game;
    }

    /**
     * the method that returns the bufferedImage of the spaceship
     */
    public BufferedImage getImage() {
        return Spaceship;
    }

    /**
     * the equals method returns if two spaceships are equal.
     */
    @Override
    public boolean equals(Object other) {
        boolean result = false;
        if (other instanceof Spaceship) {
            Spaceship that = (Spaceship) other;
            if (this.getPosX() == that.getPosX()) {
                if (this.getGame().equals(that.getGame())) {
                    Game g = this.getGame();
                    if (g.compareImages(this.getImage(), that.getImage())) {
                        result = true;
                    }
                }
            }
        }
        return result;
    }

}
