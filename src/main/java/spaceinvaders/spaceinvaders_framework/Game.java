package spaceinvaders.spaceinvaders_framework;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * The game class is the main class.
 * 
 * @author Group 23
 *
 */
public class Game extends Canvas implements Runnable, KeyListener {

    private static JFrame frame = null;
    /**
     * The Widht of the screen.
     */
    public static final int WIDTH = 635;

    /**
     * The Height of the screen.
     */
    public static final int HEIGHT = 470;
    /**
     * The title of the application.
     */
    public final String TITLE = "Space Invaders";

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
     * booleans related to the spaceships action
     */
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private boolean spacePressed = false;

    private BufferStrategy bs;

    /**
     * long that is used to set a limit between the spaceship
     *
     * being able to fire.
     */
    private long lastFire = 0;

    /**
     * Vector to store all alien, bullet and barrier objects
     */
    private Vector<Alien> aliens;
    private Vector<Bullet> alienBullets;
    private Vector<Bullet> shipBullets;
    private Vector<Barrier> barriers;

    private int counter;
    
    private HighscoreManager highscoremanager;

    /**
     * the main Thread we use for the game.
     */
    private Thread thread;

    /**
     * the main BufferedImage of the game class.
     */
    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
            BufferedImage.TYPE_INT_RGB);

    /**
     * the bufferdImage which will be the spritesheet that contains
     *
     * all the sprites we use.
     */
    private BufferedImage SpriteSheet = null;

    private int score = 0;

    Spaceship spaceship;

    public static LogFile logfile;

    public Game() {
        addKeyListener(this);
        setFocusable(true);
        counter = 0;
        
        BuffereImageLoader loader = new BuffereImageLoader();
        
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setMaximumSize(new Dimension(WIDTH, HEIGHT));
        setMinimumSize(new Dimension(WIDTH, HEIGHT));

        // creates the JFrame that will be used.
        frame = new JFrame(TITLE);
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        
        // tries to load the spritesheet from the png file.
        try {
            SpriteSheet = loader.LoadImage("res/sprite_sheet.png");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * The start method will be called once at the start of the game. it is
     * mainly used to start up the main thread of our game.
     */
    public synchronized void start() {
        // an if statement that is to prevent that the start method creates
        // two threads if it accidently called twice.
        if (running) {
            return;
        }

        running = true;
        
        // create and start the main thread of our game.
        thread = new Thread(this);
        thread.start();
        frame.setVisible(true);

    }

    /**
     * the stop method of the game. it is called only if we accidently leave the
     * game loop. if the game has no errors this method will not be called.
     */
    private synchronized void stop() {
        // returns if for some accident the stop method is called before the
        // game has
        // started.
        if (!running) {
            return;
        }

        running = false;

        // tries to join all the threads together.
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logfile.writeString("Game ended because of an error at "
                + System.currentTimeMillis());
        logfile.close();

        // exits the application.
        System.exit(1);
    }

    /**
     * the init method initializes all the entities that will appear in the
     * game.
     */
    public void init() {
    	
        int maxAlienRowCount = 18;
        int amountAliens = 36;
        int startYOffsetAlien = 0;
        int startXOffsetAlien = 75;
        int row = 0;
        
        aliens = new Vector<Alien>(0);
        alienBullets = new Vector<Bullet>(0);
        shipBullets = new Vector<Bullet>(0);
        barriers = new Vector<Barrier>(0);

        logfile = new LogFile();
        logfile.open();
        logfile.writeString("Game started at " + System.currentTimeMillis());

        // creates all the aliens and adds them to the Aliens vector
        for (int x = 0; x < amountAliens; x++) {
            if (row >= maxAlienRowCount) {
                row = 0;
            }
            row++;
            Alien alien = new Alien(startXOffsetAlien + (25 * row),
                    startYOffsetAlien + (25 * (x / maxAlienRowCount)), this);
            aliens.addElement(alien);
        }

        spaceship = new Spaceship(this);    
        
        highscoremanager = new HighscoreManager();        
        
        // creates all barriers and adds them to the barrier vector
        for (int i = 1; i <= 4; i++) {
            barriers.addElement(new Barrier(WIDTH / 5 * i - 22, 370,
                    new SpriteSheet(getSpriteSheet())));
        }
    }

    public static void main(String argv[]) {
    	Menu gameMenu = new Menu();
    	gameMenu.runMenu();
    }
    
    /**
     * the run method is the method that has the main game loop that will be
     * called repeatedly when the game is ongoing.
     */
    public void run() {
        // initialize all the entities
        init();

        // the while loop that will be active once the game is running.
        while (running) {
            doAction();
            counter++;
            if (counter >= 90) {
                alienShoot();
                counter = 0;
            }
            if (aliens.size() == 0
                    || aliens.get(aliens.size() - 1).getY() >= 400) {
                end();
            }
            else if (aliens.get(aliens.size() - 1).getY() >= 360) {
                barriers.clear();
            }

            render();
            alienDie();
            removeOffScreenBullets();
            listenForKeys();
            addBarriers();
            checkIfHit();
            moveAliens();

            try {

                Thread.sleep(15);
            } catch (Exception e) {
                // Catch if needed
            }
        }

        // if the loop is ended due to some error the stop method is called immediately.
        stop();
    }

    /**
     * the method that is used for all the non player entities to perform their
     * actions
     */
    public void doAction() {
        for (int i = 0; i < aliens.size(); i++) {
            Alien alien_obj = (Alien) aliens.get(i);
            alien_obj.hmovement();
        }

    }
    
    public void moveAliens() {
        // this if statement will only be used if all the aliens need to be updated simultaneously.
        if (logicRequiredThisLoop) {

            // the for loop gets
            for (int i = 0; i < aliens.size(); i++) {
                Alien alien_obj = (Alien) aliens.get(i);
                alien_obj.vmovement();
            }
            logicRequiredThisLoop = false;
            
            Game.logfile.writeString("Aliens reached a border and moved down");
        }
    }
    
    public void listenForKeys() {
    	
        // resolve the movement of the ship. First assume the ship
        // isn't moving. If either cursor key is pressed then
        // update the movement appropriately
        if ((leftPressed) && (!rightPressed)) {
            spaceship.moveLeft();
            if (spacePressed) {
                spaceship.shoot();
            }
        } else if ((rightPressed) && (!leftPressed)) {
            spaceship.moveRight();
            if (spacePressed) {
                spaceship.shoot();
            }
        }

        // if we're pressing fire, attempt to fire
        if (spacePressed) {
            shipBullets.addElement(spaceship.shoot());
            spacePressed = false;
        }
    }
    
    public void addBarriers() {
        for (int i = 0; i < barriers.size(); i++) {
            if (barriers.get(i).ifHit(alienBullets) != -1) {
                alienBullets.removeElementAt(barriers.get(i).ifHit(
                        alienBullets));
                if (barriers.get(i).getState() < 4) {
                    barriers.get(i).decreaseState();
                } else {
                    barriers.removeElementAt(i);
                }
            }
        }
    }
    
    public void checkIfHit() {
        int hit = spaceship.ifHit(alienBullets);
        if (hit != -1) {
            if (spaceship.getLives() > 0) {
                alienBullets.removeElementAt(hit);
            } else {
                end();
            }
        }
    }

    /**
     * the render method is used to project all the objects on the screen for
     * the player to see.
     */
    public void render() {
        BufferStrategy buff_strat = this.getBufferStrategy();

        if (buff_strat == null) {
            createBufferStrategy(4);
            return;
        }

        if (!running) {
        	
        	return;
        }
        Graphics graphic = buff_strat.getDrawGraphics();

        // here we draw the black background.
        graphic.drawImage(image, 0, 0, getWidth(), getHeight(), this);

        // here we draw all the aliens.
        for (int i = 0; i < aliens.size(); i++) {
            Alien alien_obj = (Alien) aliens.get(i);
            alien_obj.render(graphic);
        }

        spaceship.render(graphic);

        for (int i = 0; i < barriers.size(); i++) {
            barriers.get(i).render(graphic);
        }

        renderScore();
        renderHighScore();
        
        // Draw the bullet for the Aliens
        renderBulletShip();
        renderBulletAlien();

        graphic.dispose();
        buff_strat.show();

    }

    /**
     * get method to get the spritesheet.
     */
    public BufferedImage getSpriteSheet() {
        return SpriteSheet;
    }

    // Temporal: Method to draw the bullet for the spaceship
    public void renderBulletShip() {

        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {

            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        for (int i = 0; i < shipBullets.size(); i++) {
            Bullet c = (Bullet) shipBullets.get(i);
            c.render(g);
            c.moveUp();

        }

    }

    /**
     * Temporal: Method to render the bullet for the alien
     */
    public void renderBulletAlien() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        for (int i = 0; i < alienBullets.size(); i++) {
            Bullet b = (Bullet) alienBullets.get(i);
            b.render(g);
            b.moveDown();

        }
    }
    /**
     * renders the highscore on the screen.
     */
    public void renderHighScore() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.white);
        ArrayList<Score> scores = highscoremanager.getScores();
        Score topscore = scores.get(0);
        
        g.drawString("Highscore : " + topscore.getScore(), 550, 470);
    }

    /**
     * renders the players current score on the screen.
     */
    public void renderScore(){
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.white);
        g.drawString("Score : " + score, 550, 455); 
    }
    
    
    /**
     * The method that that randomly selects an alien. and adds its bullet to
     * the vector.
     */
    public void alienShoot() {
        Random rand = new Random();
        int randNr = rand.nextInt(aliens.size());
        alienBullets.addElement(aliens.get(randNr).shoot());
    }

    /**
     * The method alien die compares every spaceship bullet with alien. if the
     * alien is shot it is removed from the game.
     */
    public void alienDie() {
        for (int i = 0; i < aliens.size(); i++) {
            int hit = aliens.get(i).ifHit(shipBullets);
            if (hit != -1) {
                score += 10;
                aliens.removeElementAt(i);
                shipBullets.removeElementAt(hit);
            }
        }
    }

    /**
     * The method that removes all the bullets that are offscreen.
     */
    public void removeOffScreenBullets() {
        for (int i = 0; i < alienBullets.size(); i++) {
            if (alienBullets.get(i).getY() >= 450) {
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
     * the keyPressed method is called when a key is pressed down.
     */
    public void keyPressed(KeyEvent vkLeft) {
        if (vkLeft.getKeyCode() == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }
        if (vkLeft.getKeyCode() == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }
        if (vkLeft.getKeyCode() == KeyEvent.VK_SPACE) {
            // check if the time interval in between bullets is large enough.
            if (System.currentTimeMillis() - lastFire > 400) {
                lastFire = System.currentTimeMillis();
                spacePressed = true;
            }
        }
    }

    /**
     * the keyReleased method is called when a key has been released.
     */
    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            leftPressed = false;
            logfile.writeMove("Spaceship", spaceship.getPosX(),
                    spaceship.getPosY());
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            rightPressed = false;
            logfile.writeMove("Spaceship", spaceship.getPosX(),
                    spaceship.getPosY());
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            spacePressed = false;
        }
    }

    /**
     * The keytyped method isnt used in game class.
     */
    public void keyTyped(KeyEvent e) {

    }

    /**
     * The setter method for the sprite sheet from the path that is written as a
     * string.
     * 
     * @param s
     */
    public void setSpriteSheet(String s) {
        BuffereImageLoader loader = new BuffereImageLoader();
        // tries to load the spritesheet from the string.
        try {
            SpriteSheet = loader.LoadImage(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    public boolean getrunning() {
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
     * getter method for the leftpressed boolean
     */
    public boolean getLeftPressed() {
        return leftPressed;
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
     * The method that returns the right pressed boolean.
     * 
     * @return
     */
    public boolean getRightPressed() {

        return rightPressed;
    }

    /**
     * the method that returns the space pressed boolean.
     * 
     * @return
     */
    public boolean getSpacePressed() {
        return spacePressed;
    }

    /**
     * the method that sets the left pressed boolean.
     * 
     * @param b
     */
    public void setPressedLeft(boolean b) {
        leftPressed = b;

    }

    /**
     * the method that sets the right pressed boolean.
     * 
     * @param b
     */
    public void setPressedRight(boolean b) {
        rightPressed = b;

    }

    /**
     * the method that sets the space pressed boolean.
     * 
     * @param b
     */
    public void setPressedSpace(boolean b) {
        spacePressed = b;

    }
    
    /**
     * The addscore method compares the players score to the 10 object
     * in the scores array. if the player scores higher the object is added to
     * the array.
     */
    public void addscore(){
        ArrayList<Score> scores = highscoremanager.getScores();
        Score ninthscore = scores.get(9);
        if(score >= ninthscore.getScore()){
            String name = JOptionPane.showInputDialog("Congratulations you are on the leaderboards what is your name?");
            highscoremanager.addScore(name, score);
        }
    }
    
    /**
     * the method we use to exit the application. this method is called when the
     * aliens have reached the bottom of the screen
     * 
     */
    public void end() {
    	running = false;
        addscore();
        ScoreMenu s_menu = new ScoreMenu();
        s_menu.show();
        logfile.writeString("Game ended at " + System.currentTimeMillis());
        logfile.close();
        frame.setVisible(false);
//        System.exit(0);
        
    }

    /**
     * the set highscoremanager sets the new Highscoremanager.
     * This method is used mainly for testing purposes.
     */
    public void setHighscoremanager(HighscoreManager manager){
        highscoremanager = manager;
    }
    
    /**
     * the set score method is able to manually set the score
     * of the player. this method is only used for testing purposes.
     * 
     * @param score1
     */
    public void setscore(int score1){
        score = score1;
    }
}
