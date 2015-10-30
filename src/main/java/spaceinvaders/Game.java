package spaceinvaders;

import alien.Alien;
import bullet.Bullet;
import iterator.ConcreteAggregate;
import iterator.Iterator;
import level.Level;
import level.LevelFactory;
import state.Executor;

import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.Random;
import java.util.Vector;

/**
 * The game class is the main class.
 * 
 * @author Group 23
 *
 */
public class Game implements Runnable {

  /**
   * running == true when the game is running.
   */
  private boolean running = false;

  // private ScoreMenu s_menu;

  private boolean bossLevel;

  private ConcreteAggregate concreteAggregate = new ConcreteAggregate();

  /**
   * the main Thread we use for the game.
   */
  private Thread thread;

  private Executor exec;

  /**
   * Vector to store all alien, bullet and barrier objects.
   */
  private Vector<Alien> aliens;
  private Vector<Bullet> alienBullets;
  private Vector<Bullet> shipBullets;
  private Vector<Barrier> barriers;

  private int counter;
  private int score = 0;

  private Spaceship spaceship;
  public static LogFile logfile;
  private HighscoreManager highscoremanager;
  private Screen screen;

  private Level level;
  private int levelNumber = 0;

  /**
   * The constructor for the Game class.
   */
  public Game(Executor ex) {
    exec = ex;
    counter = 0;
    screen = new Screen(this);

    highscoremanager = new HighscoreManager();

  }

  /**
   * The start method will be called once at the start of the game. it is mainly
   * used to start up the main thread of our game.
   */
  public synchronized void start() {
    // an if statement that is to prevent that the start method creates two
    // threads if it accidently called twice.
    if (running) {
      return;
    }
    running = true;

    // create and start the main thread of our game.
    thread = new Thread(this);
    thread.start();
  }

  /**
   * the stop method of the game. it is called only if we accidently leave the
   * game loop. if the game has no errors this method will not be called.
   */
  private synchronized void stop() {
    // returns if for some accident the stop method is called before the
    // game has started.
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

    logfile.writeString("Game ended because of an error at " + new Date());
    logfile.close();

    // exits the application.
    System.exit(1);
  }

  /**
   * the init method initializes all the entities that will appear in the game.
   */
  public void init() {
    aliens = new Vector<Alien>(0);
    alienBullets = new Vector<Bullet>(0);
    shipBullets = new Vector<Bullet>(0);
    barriers = new Vector<Barrier>(0);

    logfile = LogFile.getInstance();
    logfile.open();
    logfile.writeString("Game started at " + new Date());

    spaceship = new Spaceship();

    generateLevel();
  }

  /**
   * the run method is the method that has the main game loop that will be
   * called repeatedly when the game is ongoing.
   */
  public void run() {
    // initialize all the entities
    init();

    while (running) {
      screen.repaint();
      counter++;
      if (counter >= 50) {
        alienShoot();
        counter = 0;
      }
      if (levelNumber > 15) {
        end();
      } else if (aliens.size() == 0) {
        generateLevel();
        if (levelNumber % 5 == 0) {
          bossLevel = true;
        } else {
          bossLevel = false;
        }
      } else if (aliens.get(aliens.size() - 1).reachedY(400)) {
        end();
      } else if (aliens.get(aliens.size() - 1).reachedY(360)) {
        barriers.clear();
      }
      removeOffScreenBullets();
      listenForKeys();
      checkIfHit();
      moveAliens();

      try {
        Thread.sleep(15);
      } catch (Exception e) {
        // Catch if needed
      }
    }

    stop();
  }

  /**
   * Generate level by adding aliens and barries, resetting the score and the
   * spaceship position.
   */
  public void generateLevel() {
    clearVectors();
    level = LevelFactory.createLevel(++levelNumber);
    spaceship.resetPosition();
    aliens = level.createAliens();
    barriers = level.createBarriers();

    CardWindow.getInstance().addCard(level.createTransitionPanel(),
        "TRANSITIONCARD");
    CardWindow.getInstance().showCard("TRANSITIONCARD");

    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    CardWindow.getInstance().showCard("GAMECARD");
    screen.requestFocusInWindow();
  }

  /**
   * moveAliens method is responsible for moving the aliens both horizontally
   * and vertically.
   */
  public void moveAliens() {
    Iterator iterAliens = concreteAggregate.createIterator(aliens);

    // in this while loop all the aliens are moved vertically.
    while (iterAliens.hasNext()) {
      Alien alien = (Alien) iterAliens.next();
      alien.hmovement();
    }

    // this if statement will only be used if all the aliens need to be
    // updated simultaneously. this is the case when all the aliens need
    // to be moved vertically.
    if (Alien.getupdateLogic()) {

      Iterator iterAliens2 = concreteAggregate.createIterator(aliens);

      while (iterAliens2.hasNext()) {
        Alien alien = (Alien) iterAliens2.next();
        alien.vmovement();
      }

      Game.logfile.writeString("Aliens reached a border and moved down");
    }
  }

  /**
   * Listen to what keys are pressed to make the spaceship move.
   */
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

  /**
   * Makes spaceship, aliens and barriers check if they are hit by any bullets.
   */
  public void checkIfHit() {
    if (!spaceship.defeated()) {
      int hit = spaceship.ifHit(alienBullets);
      if (hit != -1) {
        alienBullets.removeElementAt(hit);
      }
    } else {
      end();
    }

    Iterator iterAliens = concreteAggregate.createIterator(aliens);

    synchronized (aliens) {
      while (iterAliens.hasNext()) {
        Alien alien = (Alien) iterAliens.next();
        int hit = alien.ifHit(shipBullets);
        if (hit != -1) {
          if (alien.defeated()) {
            score = alien.addScore(score);
            aliens.removeElementAt(iterAliens.position());
          }
          shipBullets.removeElementAt(hit);
        }
      }
    }
    Iterator iterBarriers = concreteAggregate.createIterator(barriers);

    while (iterBarriers.hasNext()) {
      Barrier barrier = (Barrier) iterBarriers.next();
      int hit = barrier.ifHit(alienBullets);
      if (hit != -1) {
        alienBullets.removeElementAt(hit);
        if (barrier.destroyed()) {
          barriers.removeElementAt(iterBarriers.position());
        }
      }
    }

  }

  /**
   * this method clears the vectors of the aliens, alienbullets,
   * spaceshipbullets and barriers for the next level.
   */
  private void clearVectors() {
    aliens.clear();
    barriers.clear();
    alienBullets.clear();
    shipBullets.clear();
  }

  /**
   * The method that that randomly selects an alien. and adds its bullet to the
   * vector.
   */
  public void alienShoot() {
    if (bossLevel != true) {
      Random rand = new Random();
      synchronized (aliens) {
        if (aliens.size() != 0) {
          int randNr = rand.nextInt(aliens.size());
          alienBullets.addElement(aliens.get(randNr).shoot());
        }
      }
    } else {
      alienBullets.addAll(aliens.get(0).BossShoot());
    }
  }

  /**
   * The method that removes all the bullets that are offscreen.
   */
  public void removeOffScreenBullets() {
    Iterator iterAlienBullets = concreteAggregate.createIterator(alienBullets);

    while (iterAlienBullets.hasNext()) {

      Bullet bullet = (Bullet) iterAlienBullets.next();
      if (bullet.reachedY(450)) {
        Game.logfile.writeOffscreen("Alien", bullet.getX());
        alienBullets.removeElementAt(iterAlienBullets.position());
      }

    }

    Iterator iterShipBullets = concreteAggregate.createIterator(shipBullets);

    while (iterShipBullets.hasNext()) {

      Bullet bullet = (Bullet) iterShipBullets.next();
      if (bullet.reachedY(450)) {
        Game.logfile.writeOffscreen("Spaceship", bullet.getX());
        shipBullets.removeElementAt(iterShipBullets.position());
      }

    }

  }

  /**
   * getter method for the LogicrequiredThisLoop boolean.
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
  public void setRunning(boolean bool) {
    running = bool;
  }

  /**
   * getter method for the counter integer.
   */
  public int getcounter() {
    return counter;
  }

  /**
   * getter method for the spaceship object.
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
   * @return whether the images are both the same.
   */
  public boolean compareImages(Object objA, Object objB) {

    if (objA instanceof BufferedImage && objB instanceof BufferedImage) {
      BufferedImage imgA = (BufferedImage) objA;
      BufferedImage imgB = (BufferedImage) objB;

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
   * @return vector containing all aliens currently active in the game
   */
  public Vector<Alien> getAlienVector() {
    return aliens;
  }

  /**
   * the method that adds a bullet to the shipbullets vector.
   * 
   * @param bill
   *          bullets to add to the vector
   */
  public void addShipBullets(Bullet bill) {
    shipBullets.add(bill);
  }

  /**
   * the getter methdo that returns the shipbullets vector.
   * 
   * @return vector containing ship's bullets
   */
  public Vector<Bullet> getShipBullets() {
    return shipBullets;
  }

  /**
   * the method that adds a bullet to the alienbullets vector.
   * 
   * @param bill
   *          bullets to add to the vector
   */
  public void addAlienBullets(Bullet bill) {
    alienBullets.add(bill);
  }

  /**
   * the getter method that returns the alienbullets vector.
   * 
   * @return vector containing aliens' bullets
   */
  public Vector<Bullet> getAlienBullets() {
    return alienBullets;
  }

  /**
   * the method that adds an alien to the alien vector.
   * 
   * @param alien
   *          to add to the vector
   */
  public void addAlien(Alien alien) {
    aliens.add(alien);
  }

  public Vector<Barrier> getBarriers() {
    return barriers;
  }

  /**
   * the method we use to exit the application.
   */
  public void end() {
    running = false;
    highscoremanager.addScore(score);
    logfile.writeString("Game ended at " + new Date());
    logfile.close();
    exec.returning();
  }

  public int getLevelNumber() {
    return levelNumber;
  }

  public int getScore() {
    return score;
  }

  public HighscoreManager getHsmanager() {
    return highscoremanager;
  }

  /**
   * the set highscoremanager sets the new Highscoremanager. This method is used
   * only for testing purposes.
   */
  public void setHighscoremanager(HighscoreManager manager) {
    highscoremanager = manager;
  }

  /**
   * the set score method is able to manually set the score of the player. this
   * method is only used for testing purposes.
   * 
   * @param score1
   *          set attribute score
   */
  public void setscore(int score1) {
    score = score1;
  }
}
