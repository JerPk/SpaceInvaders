package level;

import java.util.Vector;

import alien.Alien;
import alien.AlienFactory;
import spaceinvaders.Barrier;
import spaceinvaders.Screen;

/**
 * Class of the standard level.
 * 
 * @author Group 23 (TI2206)
 */
public class StandardLevel extends Level {

  /**
   * Constructor of standard level class.
   * 
   * @param number
   *          Level number
   */
  public StandardLevel(final int number) {
    super(number);
    levelNumber = number;
  }

  /**
   * Creates the aliens and returns them.
   * 
   * @return the created aliens
   */
  @Override
  public final Vector<Alien> createAliens() {
    int offsetY = 0;
    int offsetX = 75;
    Vector<Alien> tempAliens = new Vector<Alien>(0);

    if (levelNumber > 0 && levelNumber <= 5) {
      alienType = "easy";
    } else if (levelNumber > 5 && levelNumber <= 10) {
      alienType = "normal";
    } else if (levelNumber > 10 && levelNumber <= 15) {
      alienType = "hard";
    }

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 18; j++) {
        Alien alien = AlienFactory.getAlien(alienType, offsetX + (25 * j) - 3,
            offsetY);
        tempAliens.addElement(alien);
      }
      offsetY += 25;
    }

    return tempAliens;
  }

  /**
   * Creates the barriers and returns them.
   * 
   * @return the created barriers
   */
  @Override
  public final Vector<Barrier> createBarriers() {
    Vector<Barrier> tempBarriers = new Vector<Barrier>(0);
    int numberOfBariers = 0;

    if (levelNumber % 5 != 0) {
      numberOfBariers = 5 - (levelNumber % 5);
    }

    for (int i = 1; i <= numberOfBariers; i++) {
      tempBarriers.addElement(new Barrier(Screen.WIDTH / (numberOfBariers + 1)
          * i - 22, 370));
    }

    return tempBarriers;
  }
}
