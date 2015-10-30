package spaceinvaders;

import alien.Alien;
import bullet.Bullet;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JPanel;

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
  public final String title = "Space Invaders";

  /**
   * booleans related to the spaceships action.
   */
  private boolean leftPressed = false;
  private boolean rightPressed = false;
  private boolean spacePressed = false;

  /**
   * long that is used to set a limit between the spaceship being able to fire.
   */
  private long lastFire = 0;

  private Game game;

  private Graphics graphic;

  /**
   * Constructor of the Screen class.
   * 
   * @param ga
   *          game containing the entities that need to be displayed
   */
  public Screen(Game ga) {
    setFocusable(true);
    addKeyListener(this);
    game = ga;
    setSize(new Dimension(WIDTH, HEIGHT));

    setBackground(Color.black);
    setFocusable(true);

    CardWindow.getInstance().addCard(this, "GAMECARD");
    // CardWindow.getInstance().showCard("GAMECARD");
    // requestFocusInWindow();
  }

  /**
   * the paintComponent method is used do draw all the object on the screen.
   */
  public void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);
    graphic = graphics;

    // here we draw all the aliens.
    for (int i = 0; i < game.getAlienVector().size(); i++) {
      Alien alienObj = (Alien) game.getAlienVector().get(i);
      alienObj.render(graphic);
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

  /**
   * Method to render the ship's bullets.
   * 
   * @param shipBullets
   *          vector containing ship's bullets
   */
  public void renderBulletShip(Vector<Bullet> shipBullets) {
    for (int i = 0; i < shipBullets.size(); i++) {
      Bullet bul = (Bullet) shipBullets.get(i);
      bul.render(graphic);
      bul.moveUp();
    }
  }

  /**
   * Method to render the bullets for the aliens.
   * 
   * @param alienBullets
   *          vector containing aliens' bullets
   */
  public void renderBulletAlien(Vector<Bullet> alienBullets) {
    for (int i = 0; i < alienBullets.size(); i++) {
      Bullet bul = (Bullet) alienBullets.get(i);
      bul.render(graphic);
      bul.moveDown();
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
  public void keyReleased(KeyEvent ke) {

    if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
      leftPressed = false;
      // logfile.writeMove("Spaceship", spaceship.getPosX(),
      // spaceship.getPosY());
    }

    if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
      rightPressed = false;
      // logfile.writeMove("Spaceship", spaceship.getPosX(),
      // spaceship.getPosY());
    }

    if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
      spacePressed = false;
    }
  }

  /**
   * The keytyped method isnt used in game class.
   */
  public void keyTyped(KeyEvent ke) {

  }

  /**
   * The method that returns the right pressed boolean.
   * 
   * @return rightPressed to check if the right arrow key is pressed
   */
  public boolean getRightPressed() {
    return rightPressed;
  }

  /**
   * getter method for the leftpressed boolean.
   */
  public boolean getLeftPressed() {
    return leftPressed;
  }

  /**
   * the method that returns the space pressed boolean.
   * 
   * @return to check if the space bar is pressed
   */
  public boolean getSpacePressed() {
    return spacePressed;
  }

  /**
   * the method that sets the left pressed boolean for testing purposes.
   * 
   * @param bool
   *          to set the left arrow key
   */
  public void setPressedLeft(boolean bool) {
    leftPressed = bool;
  }

  /**
   * the method that sets the right pressed boolean for testing purposes.
   * 
   * @param bool
   *          to set the right arrow key
   */
  public void setPressedRight(boolean bool) {
    rightPressed = bool;

  }

  public void blockSpace() {
    spacePressed = false;
  }

  /**
   * the method that sets the space pressed boolean.
   * 
   * @param bool
   *          to set the space bar
   */
  public void setPressedSpace(boolean bool) {
    spacePressed = bool;
  }
}
