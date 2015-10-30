package alien;

/**
 * Alien factory is the class that is used to create all the different types of
 * aliens.
 * 
 * @author Group 23
 *
 */
public class AlienFactory {

  /**
   * the main method of the alien factory class. it takes the criteria and
   * coordinates. and returns the alien type specified by the criteria.
   * 
   * @param name
   *          alien type
   * @param x
   *          x coordinate
   * @param y
   *          y coordinate
   * @return the new alien
   */
  public static Alien getAlien(String name, final double x, final double y) {

    if (name.equals("easy")) {
      return Alien.createAlienType1(x, y);
    } else if (name.equals("normal")) {
      return Alien.createAlienType2(x, y);
    } else if (name.equals("hard")) {
      return Alien.createAlienType3(x, y);
    } else if (name.equals("boss")) {
      return Alien.createBossAlien(x, y);
    }

    return null;
  }
}
