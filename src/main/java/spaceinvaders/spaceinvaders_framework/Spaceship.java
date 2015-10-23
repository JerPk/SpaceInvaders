package spaceinvaders.spaceinvaders_framework;

import iterator.ConcreteAggregate;
import iterator.Iterator;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Vector;

import bullet.Bullet;
import bullet.MegaBullet;

public class Spaceship {

    // the x and y coordinates of the Spaceship.
    private double xpos;
    private static double ypos = 425;
    private int lives;

    // the bufferedImage of the spaceship.
    private BufferedImage Spaceship;

    protected ConcreteAggregate ConcreteAggregate = new ConcreteAggregate();

    /**
     * the constructor of the Spaceship class.
     * 
     * @param int x
     * @param int y
     * @param Game
     *            g
     */
    public Spaceship() {
        xpos = Screen.WIDTH / 2 - 13;
        lives = 3;

        Game.logfile.writeCreate("Spaceship", xpos, ypos);

        Spaceship = SpriteSheet.getInstance().grabImage(277, 228, 26, 16);

    }

    /**
     * this method makes the ship move left by 1 as long as it hasn't reached
     * the border
     *
     */
    public void moveLeft() {
        if (xpos > 10) {
            xpos -= 2;
        }
    }

    /**
     * this method makes the ship move right by 1 as long as it hasn't reached
     * the border
     *
     */
    public void moveRight() {
        if (xpos < 600) {
            xpos += 2;
        }
    }

    /**
     * the method creates a new bullet on the position of the ship and returns
     * it
     *
     * @return Bullet newBullet
     */
    public Bullet shoot() {
        Bullet newBullet = new Bullet(xpos + 10, ypos + 2);
        Game.logfile.writeShoot("Spaceship", getPosX(), getPosY());

        newBullet.setSpritesheet(423, 277, 6, 12);
        return newBullet;
    }

    public int ifHit(Vector<Bullet> alienBullets) {
        Iterator iterAlienBullets = ConcreteAggregate.createIterator(alienBullets);

        while (iterAlienBullets.hasNext()) {
            Bullet bullet = (Bullet) iterAlienBullets.next();

            if (bullet instanceof MegaBullet) {
                if (ifHitMega(bullet, iterAlienBullets.position()) == true) {
                    return iterAlienBullets.position();
                }
            }

            if (bullet.getY() > ypos - 10 && bullet.getY() < ypos + 16) {
                if (bullet.getX() > xpos - 6 && bullet.getX() < xpos + 26) {
                    lives -= 1;
                    Game.logfile.writeHit("Spaceship", bullet.getX(),
                            bullet.getY());
                    if (lives > 0) {
                        Game.logfile.writeString("Spaceship has "
                                + String.valueOf(lives) + " lives left");
                    } else {
                        Game.logfile.writeString("Spaceship has no lives left");
                    }
                    return iterAlienBullets.position();
                }
            }
        }
        return -1;

    }

    /**
     * method to reset the position of the spaceship.
     */
    public void resetPosition() {
        xpos = Screen.WIDTH / 2 - 13;
    }

    private boolean ifHitMega(Bullet bullet, int i) {

        if (bullet.getX() + 15 >= xpos && bullet.getX() + 15 <= xpos + 26) {
            if (bullet.getY() + 50 >= ypos && bullet.getY() + 50 <= ypos + 16) {
                lives -= 1;
                Game.logfile
                        .writeHit("Spaceship", bullet.getX(), bullet.getY());
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
        return xpos;
    }

    /**
     * the method that returns the y position
     *
     * @return y position
     */
    public double getPosY() {
        return ypos;

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
        g.drawImage(Spaceship, (int) xpos, (int) ypos, null);

        for (int i = 1; i <= lives; i++) {
            g.drawImage(Spaceship, 10 + 30 * (i - 1), 452, null);

        }

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
                // if (this.getGame().equals(that.getGame())) {
                // Game g = this.getGame();
                // if (g.compareImages(this.getImage(), that.getImage())) {
                result = true;
                // }
                // }
            }
        }
        return result;
    }

}
