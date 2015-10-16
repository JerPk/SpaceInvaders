package spaceinvaders.spaceinvaders_framework;

import java.awt.Canvas;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import java.util.Date;

import javax.swing.JOptionPane;

/**
 * The game class is the main class.
 * 
 * @author Group 23
 *
 */
public class Game extends Canvas /*implements Runnable*/ {

    /**
     * running == true when the game is running.
     */
    private boolean running = false;

    /**
     * // a boolean that is only true if the aliens need to be updated. // here
     * it used for moving all the aliens down simultaneously.
     * 
     */
    private boolean logicRequiredThisLoop = false;

    /**
     * boolean to update bullet
     */
    private boolean updateBullet = false;

    /**
     * Vector to store all alien, bullet and barrier objects
     */
    private Vector<Alien> aliens;
    private Vector<Bullet> alienBullets;
    private Vector<Bullet> shipBullets;
    private Vector<Barrier> barriers;

    private int counter;
    private HighscoreManager highscoremanager;
    private int score = 0;

    private Spaceship spaceship;
    public static LogFile logfile;
    private Screen screen;
    
    //////DELETE//////
    /**
     * the bufferdImage which will be the spritesheet that contains
     *
     * all the sprites we use.
     */
    private BufferedImage SpriteSheet = null;

    public Game() {
        counter = 0;
        screen = new Screen();
    }

    /**
     * The start method will be called once at the start of the game. it is
     * mainly used to start up the main thread of our game.
     */
    public synchronized void start() {
        // an if statement that is to prevent that the start method creates two threads if it accidently called twice.
        if (running) {
            return;
        }
        running = true;
        // initialize all the entities
        init();
    }

    /**
     * the stop method of the game. it is called only if we accidently leave the
     * game loop. if the game has no errors this method will not be called.
     */
    private synchronized void stop() {
        // returns if for some accident the stop method is called before the game has started.
        if (!running) {
            return;
        }
        running = false;
        
        /*
        // tries to join all the threads together.
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */

        logfile.writeString("Game ended because of an error at " + new Date());
        logfile.close();

        // exits the application.
        System.exit(1);
    }

    /**
     * the init method initializes all the entities that will appear in the
     * game.
     */
    public void init() {
        aliens = new Vector<Alien>(0);
        alienBullets = new Vector<Bullet>(0);
        shipBullets = new Vector<Bullet>(0);
        barriers = new Vector<Barrier>(0);

        logfile = LogFile.getInstance();
        logfile.open();
        logfile.writeString("Game started at " + new Date());
        
        ///DELETE///
        BuffereImageLoader loader = new BuffereImageLoader();
        try {
            SpriteSheet = loader.LoadImage("res/sprite_sheet.png");
        } catch (IOException e) {
            e.printStackTrace();
        }

        CreateAliens();

        spaceship = new Spaceship(this);
        highscoremanager = new HighscoreManager();

        // creates all barriers and adds them to the barrier vector
        for (int i = 1; i <= 4; i++) {
            barriers.addElement(new Barrier(635 / 5 * i - 22, 370));
        }
    }

    ///DELETE///
    /**
     * get method to get the spritesheet.
     */
    public BufferedImage getSpriteSheet() {
        return SpriteSheet;
    }

    /**
     * creates all the aliens and adds them to the alien vector.
     * 
     * @param aliens
     */
    public void CreateAliens() {
        int startYOffsetAlien = 0;
        int startXOffsetAlien = 75;

        for (int x = 0; x < 18; x++) {
            Alien alien = AlienFactory.getAlien("hard", startXOffsetAlien
                    + (25 * x) - 3, startYOffsetAlien, this);
            aliens.addElement(alien);
        }
        for (int x = 0; x < 18; x++) {
            Alien alien = AlienFactory.getAlien("normal", startXOffsetAlien
                    + (25 * x) - 3, startYOffsetAlien + 25, this);
            aliens.addElement(alien);
        }
        for (int x = 0; x < 18; x++) {
            Alien alien = AlienFactory.getAlien("easy", startXOffsetAlien
                    + (25 * x) - 3, startYOffsetAlien + 50, this);
            aliens.addElement(alien);
        }
    }

    /**
     * the run method is the method that has the main game loop that will be
     * called repeatedly when the game is ongoing.
     */
    public void runGame() {
    	screen.render(this);
    	counter++;
    	if (counter >= 50) {
    		alienShoot();
    		counter = 0;
    	}
    	if (aliens.size() == 0
    			|| aliens.get(aliens.size() - 1).reachedY(400)) {
    		end();
    	}	else if (aliens.get(aliens.size() - 1).reachedY(360)) {
    		barriers.clear();
    	}
    	removeOffScreenBullets();
    	listenForKeys();
    	checkIfHit();
    	moveAliens();
    }

    public void moveAliens() {
        for (int i = 0; i < aliens.size(); i++) {
            Alien alien_obj = (Alien) aliens.get(i);
            alien_obj.hmovement();
        }	
        // this if statement will only be used if all the aliens need to be updated simultaneously.
        if (logicRequiredThisLoop) {
            for (int i = 0; i < aliens.size(); i++) {
                Alien alien_obj = (Alien) aliens.get(i);
                alien_obj.vmovement();
            }
            logicRequiredThisLoop = false;

            Game.logfile.writeString("Aliens reached a border and moved down");
        }
    }

    public void listenForKeys() {
        // resolve the movement of the ship.
        if ((screen.getLeftPressed()) && (!screen.getRightPressed())) {
            spaceship.moveLeft();
            if (screen.getSpacePressed()) {
                spaceship.shoot();
            }
        } else if ((screen.getRightPressed()) && (!screen.getLeftPressed())) {
            spaceship.moveRight();
            if (screen.getSpacePressed()) {
                spaceship.shoot();
            }
        }
        // if we're pressing fire, attempt to fire
        if (screen.getSpacePressed()) {
            shipBullets.addElement(spaceship.shoot());
            screen.blockSpace();
        }
    }

    public void checkIfHit() {
        if (!spaceship.defeated()) {
        	int hit = spaceship.ifHit(alienBullets);
            if (hit != -1) {
                alienBullets.removeElementAt(hit);
            }
        } else {
        	end();
        }
        for (int i = 0; i < aliens.size(); i++) {
            int hit = aliens.get(i).ifHit(shipBullets);
            if (hit != -1) {               
                if (aliens.get(i).defeated()) {
                    score += aliens.get(i).getScore();
                    aliens.removeElementAt(i);
                }
                shipBullets.removeElementAt(hit);
            }
        }
        for (int i = 0; i < barriers.size(); i++) {
        	int hit = barriers.get(i).ifHit(alienBullets);
            if (hit != -1) {
                alienBullets.removeElementAt(hit);
                if (barriers.get(i).destroyed()) {
                    barriers.removeElementAt(i);
                }
            }
        }
    }

    /**
     * The method that that randomly selects an alien. and adds its bullet to the vector.
     */
    public void alienShoot() {
        Random rand = new Random();
        int randNr = rand.nextInt(aliens.size());
        alienBullets.addElement(aliens.get(randNr).shoot());
    }

    /**
     * The method that removes all the bullets that are offscreen.
     */
    public void removeOffScreenBullets() {
        for (int i = 0; i < alienBullets.size(); i++) {
            if (alienBullets.get(i).reachedY(450)) {
                Game.logfile
                        .writeOffscreen("Alien", alienBullets.get(i).getX());
                alienBullets.removeElementAt(i);
                i--;
            }
        }
        for (int j = 0; j < shipBullets.size(); j++) {
            if (shipBullets.get(j).getY() <= 0) {
                Game.logfile.writeOffscreen("Spaceship", shipBullets.get(j)
                        .getX());
                shipBullets.removeElementAt(j);
                j--;
            }
        }
    }

    /**
     * the method used to put the logicRequiredThisLoop boolean to true.
     */
    public void updateLogic() {
        logicRequiredThisLoop = true;
        updateBullet = true;
    }

    /**
     * getter method for the LogicrequiredThisLoop boolean
     * 
     * @return boolean
     */
    public boolean getupdateLogic() {
        return logicRequiredThisLoop;

    }

    /**
     * getter method for the LogicrequiredThisLoop boolean
     * 
     * @return boolean
     */
    public boolean getRunning() {
        return running;
    }

    /**
     * setter method for the running boolean.
     * 
     * @param boolean b
     */
    public void setrunning(boolean b) {
        running = b;
    }

    /**
     * getter method for the counter integer
     */
    public int getcounter() {
        return counter;
    }

    /**
     * getter method for the spaceship object
     */
    public Spaceship getSpaceship() {
        return spaceship;
    }

    /**
     * equals method that compares if two game objects are equal.
     */
    public boolean equals(Object other) {
        boolean result = false;
        if (other instanceof Game) {
            Game that = (Game) other;
            if (this.getcounter() == that.getcounter()) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Compares two images pixel by pixel.
     *
     * @param imgA
     *            - the first image.
     * @param imgB
     *            - the second image.
     * @return whether the images are both the same.
     */
    public boolean compareImages(Object a, Object b) {

        if (a instanceof BufferedImage && b instanceof BufferedImage) {
            BufferedImage imgA = (BufferedImage) a;
            BufferedImage imgB = (BufferedImage) b;

            // The images mush be the same size.
            if (imgA.getWidth() == imgB.getWidth()
                    && imgA.getHeight() == imgB.getHeight()) {
                int width = imgA.getWidth();
                int height = imgA.getHeight();

                // Loop over every pixel.
                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        // Compare the pixels for equality.
                        if (imgA.getRGB(x, y) != imgB.getRGB(x, y)) {
                            return false;
                        }
                    }
                }
            } else {
                return false;
            }

            return true;
        }
        return false;

    }

    /**
     * the getter metod that returns the alienvector.
     * 
     * @return
     */
    public Vector<Alien> getAlienVector() {
        return aliens;
    }

    /**
     * the method that adds a bullet to the shipbullets vector.
     * 
     * @param bill
     */
    public void addShipBullets(Bullet bill) {
        shipBullets.add(bill);
    }

    /**
     * the getter methdo that returns the shipbullets vector.
     * 
     * @return
     */
    public Vector<Bullet> getShipBullets() {
        return shipBullets;
    }

    /**
     * the method that adds a bullet to the alienbullets vector.
     * 
     * @param bill
     */
    public void addAlienBullets(Bullet bill) {
        alienBullets.add(bill);
    }

    /**
     * the getter method that returns the alienbullets vector.
     * 
     * @return
     */
    public Vector<Bullet> getAlienBullets() {
        return alienBullets;
    }

    /**
     * the method that adds an alien to the alien vector.
     * 
     * @param a
     */
    public void addAlien(Alien a) {
        aliens.add(a);
    }

    /**
     * The addscore method compares the players score to the 10 object in the scores array. 
     * If the player scores higher the object is added to the array.
     */
    public void addscore() {
        ArrayList<Score> scores = highscoremanager.getScores();
        Score ninthscore = scores.get(9);
        if (score >= ninthscore.getScore()) {
            String name = JOptionPane
                    .showInputDialog("Congratulations you are on the leaderboards what is your name?");
            highscoremanager.addScore(name, score);
        }
    }
    
    public Vector<Barrier> getBarriers() {
    	return barriers;
    }

    /**
     * the method we use to exit the application.
     */
    public void end() {
        running = false;
        addscore();
        logfile.writeString("Game ended at " + new Date());
        logfile.close();
        screen.close();
    }
    
    public int getScore() {
        return score;
    }
    
    public HighscoreManager getHSManager() {
    	return highscoremanager;
    }

    /**
     * the set highscoremanager sets the new Highscoremanager. This method is
     * used only for testing purposes.
     */
    public void setHighscoremanager(HighscoreManager manager) {
        highscoremanager = manager;
    }
    

    /**
     * the set score method is able to manually set the score of the player.
     * this method is only used for testing purposes.
     * 
     * @param score1
     */
    public void setscore(int score1) {
        score = score1;
    }
}
