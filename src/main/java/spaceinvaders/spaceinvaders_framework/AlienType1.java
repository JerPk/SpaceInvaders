package spaceinvaders.spaceinvaders_framework;

/**
 * the first alien type is considered the easiest. it has the least health
 * and the bullets fly the slowest.
 * 
 * @author Group 23
 *
 */
public class AlienType1 extends Alien {

    /**
     * the constructor of alien type 1.
     * 
     * @param x
     * @param y
     * @param g
     */
    public AlienType1(double x, double y, Game g) {
        super(x, y, g);
        setSpritesheet(7, 225, 16, 16);
        setScore(10);
        setHealth(1);
    }

}
