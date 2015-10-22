package spaceinvaders.spaceinvaders_framework;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;

import alien.Alien;
import bullet.Bullet;

public class Screen extends Canvas implements KeyListener {
	
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
     * the main BufferedImage of the game class.
     */
    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    
    /**
     * booleans related to the spaceships action
     */
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private boolean spacePressed = false;
    
    /**
     * long that is used to set a limit between the spaceship
     * being able to fire.
     */
    private long lastFire = 0;
    
    private Game game;
    
    /**
     * Spritesheet that contains all the sprites we use.
     */
    public static SpriteSheet spritesheet;
	
	public Screen() {
		setFocusable(true);
		addKeyListener(this);
		
//        BuffereImageLoader loader = new BuffereImageLoader();

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
        
        spritesheet = SpriteSheet.getInstance();
        spritesheet.init();
        
        frame.setVisible(true);
	}
	
	   /**
     * the render method is used to project all the objects on the screen for
     * the player to see.
     */
    public void render(Game g) {
    	game = g;
        BufferStrategy buff_strat = this.getBufferStrategy();

        if (buff_strat == null) {
            createBufferStrategy(4);
            return;
        }

        Graphics graphic = buff_strat.getDrawGraphics();

        // here we draw the black background.
        graphic.drawImage(image, 0, 0, getWidth(), getHeight(), this);

        // here we draw all the aliens.
        for (int i = 0; i < game.getAlienVector().size(); i++) {
            Alien alien_obj = (Alien) game.getAlienVector().get(i);
            alien_obj.render(graphic);
        }

        for (int i = 0; i < game.getBarriers().size(); i++) {
            game.getBarriers().get(i).render(graphic);
        }

        renderScore(game.getScore());
        renderHighScore(game.getHSManager());
        renderLevelNumber(game.getLevelNumber());

        // Draw the bullets and spaceship
        renderBulletShip(game.getShipBullets());
        renderBulletAlien(game.getAlienBullets());
        game.getSpaceship().render(graphic);

        graphic.dispose();
        buff_strat.show();

    }
    
    public void renderBulletShip(Vector<Bullet> shipBullets) {

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
    public void renderBulletAlien(Vector<Bullet> alienBullets) {
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
    public void renderHighScore(HighscoreManager hm) {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.white);
        ArrayList<Score> scores = hm.getScores();
        Score topscore = scores.get(0);

        g.drawString("Highscore : " + topscore.getScore(), 550, 470);
    }

    /**
     * renders the players current score on the screen.
     */
    public void renderScore(int score) {
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
     * renders level number on the screen.
     */
    public void renderLevelNumber(int levelNumber) {
    	BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.white);
        g.drawString("Level: " + levelNumber, 550, 440);
    }

    /**
     * the keyReleased method is called when a key has been released.
     */
    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            leftPressed = false;
            //logfile.writeMove("Spaceship", spaceship.getPosX(),
                    //spaceship.getPosY());
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            rightPressed = false;
            //logfile.writeMove("Spaceship", spaceship.getPosX(),
                    //spaceship.getPosY());
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
    
//    /**
//     * get method to get the spritesheet.
//     */
//    public BufferedImage getSpriteSheet() {
//        return SpriteSheet;
//    }
//    
//    /**
//     * The setter method for the sprite sheet from the path that is written as a
//     * string.
//     * 
//     * @param s
//     */
//    public void setSpriteSheet(String s) {
//        BuffereImageLoader loader = new BuffereImageLoader();
//        // tries to load the spritesheet from the string.
//        try {
//            SpriteSheet = loader.LoadImage(s);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    
    /**
     * The method that returns the right pressed boolean.
     * 
     * @return
     */
    public boolean getRightPressed() {
        return rightPressed;
    }
    
    /**
     * getter method for the leftpressed boolean
     */
    public boolean getLeftPressed() {
        return leftPressed;
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
    
    public void blockSpace() {
    	spacePressed = false;
    }

    /**
     * the method that sets the space pressed boolean.
     * 
     * @param b
     */
    public void setPressedSpace(boolean b) {
        spacePressed = b;
    }
    
    public void close() {
        frame.setVisible(false);
    }
}
