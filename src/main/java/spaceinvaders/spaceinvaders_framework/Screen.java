package spaceinvaders.spaceinvaders_framework;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JPanel;

import alien.Alien;
import bullet.Bullet;

public class Screen extends JPanel implements KeyListener {
	
    /**
     * The Width of the screen.
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
    
    private Graphics graphic;
	
	public Screen(Game ga) {
		setFocusable(true);
		addKeyListener(this);
		game = ga;
		setSize(new Dimension(WIDTH, HEIGHT));

        setBackground(Color.black);
        setFocusable(true);
        
        CardWindow.getInstance().addCard(this, "GAMECARD");
//        CardWindow.getInstance().showCard("GAMECARD");
//        requestFocusInWindow();
	}
	
	/**
	 * the paintComponent method is used do draw all the object on the screen
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		graphic = g;

		// here we draw all the aliens.
		for (int i = 0; i < game.getAlienVector().size(); i++) {
			Alien alien_obj = (Alien) game.getAlienVector().get(i);
			alien_obj.render(graphic);
		}

		for (int i = 0; i < game.getBarriers().size(); i++) {
			game.getBarriers().get(i).render(graphic);
		}

		renderScore(game.getScore());
		renderHighScore(HighscoreManager.getInstance());
		renderLevelNumber(game.getLevelNumber());

		// Draw the bullets and spaceship
		renderBulletShip(game.getShipBullets());
		renderBulletAlien(game.getAlienBullets());
		if (game.getSpaceship() != null) {
			game.getSpaceship().render(graphic);
		}
	}

    public void renderBulletShip(Vector<Bullet> shipBullets) {
        for (int i = 0; i < shipBullets.size(); i++) {
            Bullet c = (Bullet) shipBullets.get(i);
            c.render(graphic);
            c.moveUp();
        }
    }

    /**
     * Temporal: Method to render the bullet for the alien
     */
    public void renderBulletAlien(Vector<Bullet> alienBullets) {
        for (int i = 0; i < alienBullets.size(); i++) {
            Bullet b = (Bullet) alienBullets.get(i);
            b.render(graphic);
            b.moveDown();
        }
    }
    
    /**
     * renders the highscore on the screen.
     */
    public void renderHighScore(HighscoreManager hm) {
    	graphic.setColor(Color.white);
		if (hm != null) {
			ArrayList<Score> scores = hm.getScores();
			Score topscore = scores.get(0);
		
        graphic.drawString("Highscore : " + topscore.getScore(), 550, 470);
		}
    }

    /**
     * renders the players current score on the screen.
     */
    public void renderScore(int score) {
    	graphic.setColor(Color.white);
    	graphic.drawString("Score : " + score, 550, 455);
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
    	graphic.setColor(Color.white);
    	graphic.drawString("Level: " + levelNumber, 550, 440);
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
//        frame.setVisible(false);
    }
}
