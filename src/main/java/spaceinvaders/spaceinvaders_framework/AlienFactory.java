package spaceinvaders.spaceinvaders_framework;
/**
 * Alien factory is the class that is used to 
 * create all the different types of aliens.
 * @author Group 23
 *
 */
public class AlienFactory {

    /**
     * the main method of the alien factory class.
     * it takes the criteria and coordinates. and
     * returns the alien type specified by the criteria.
     * 
     * @param criteria
     * @param x
     * @param y
     * @param g
     * @return
     */
    public static Alien getAlien(String criteria, int x, int y ,Game g){
        
        if ( criteria.equals("easy") )
            return new AlienType1(x,y,g);
          else if ( criteria.equals("normal") )
            return new AlienType2(x,y,g);
          else if ( criteria.equals("hard") )
            return new AlienType3(x,y,g);
        
        
        
        return null;
        
    }
}
