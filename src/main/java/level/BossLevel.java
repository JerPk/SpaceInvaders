package level;

import java.util.Vector;

import alien.Alien;
import alien.AlienFactory;
import spaceinvaders.Barrier;

/**
 * Class of the boss level.
 * 
 * @author Group 23 (TI2206)
 */
public class BossLevel extends Level {

  /**
   * Constructor of boss level class.
   * 
   * @param number
   *          Level number
   */
  public BossLevel(final int number) {
    super(number);
    levelNumber = number;
  }

  /**
   * Creates the boss alien and returns it.
   * 
   * @return the created aliens
   */
  @Override
  public final Vector<Alien> createAliens() {
    final int offsetY = 0;
    final int offsetX = 75;
    final Vector<Alien> tempAliens = new Vector<Alien>(0);
    alienType = "boss";

    final Alien alien = AlienFactory.getAlien(alienType, offsetX - 3, offsetY);
    alien.setHealth(levelNumber);
    alien.setScore(20 * levelNumber);
    tempAliens.addElement(alien);

    return tempAliens;
  }

  /**
   * Creates the empty barrier vector and returns it.
   * 
   * @return the created barriers
   */
  @Override
  public final Vector<Barrier> createBarriers() {
    return new Vector<Barrier>(0);
  }
}
