package spaceinvaders.spaceinvaders_framework;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import Bullet.Bullet;

import java.util.Vector;

/**
 * SpaceshipTest tests all the methods in the spaceship class.
 * 
 * @author Group 23
 */
public class SpaceshipTest {
  
    /**
     * The game object that is used in all of the test cases.
     */
  private Game game;
  
  /**
   * Launch the user interface.
   */
  @Before
  public void setUpGame() {
    game = new Game();
    game.setSpriteSheet("res/sprite_sheet.png");
    game.init();
  }
  
  /**
   * the JUnit test of the Constuctor method of Spaceship.
   */
  @Test
  public void testSpaceship() {
    final Spaceship ship = new Spaceship(game);
    assertEquals((double) 304, ship.getPosX(), 0.00001);
    assertEquals((double) 425, ship.getPosY(), 0.00001);
  }
  
  /**
   * the JUnit test of the MoveLeft method of Spaceship.
   */
  @Test
  public void testMoveLeft() {
    final Spaceship ship = new Spaceship(game);
    game.setrunning(true);
    
    ship.moveLeft();
    assertEquals((double) 302, ship.getPosX(), 0.00001);
    assertTrue(game.getrunning());
  }

  /**
   * the JUnit test of the MoveRight method of Spaceship.
   */
  @Test
  public void testMoveRight() {
    final Spaceship ship = new Spaceship(game);
    game.setrunning(true);
    
    ship.moveRight();
    assertEquals((double) 306, ship.getPosX(), 0.00001);
    assertTrue(game.getrunning());
  }

  /**
   * the JUnit test of the Shoot method of Spaceship.
   */
  @Test
  public void testShoot() {
    final Spaceship ship = new Spaceship(game);
    game.setrunning(true);
    
    final Bullet bullet = ship.shoot();
    assertEquals((double) ship.getPosX() + 10, bullet.getX(), 0.00001);
    assertEquals((double) ship.getPosY() + 2, bullet.getY(), 0.00001);
    assertTrue(game.getrunning());
  }

  /**
   * the JUnit test of the ifHit method of Spaceship.
   */
  @Test
  public void testIfHit() {
    final Spaceship ship = new Spaceship(game);
    game.setrunning(true);
    final Vector<Bullet> alienBullets = new Vector<Bullet>(0);
        
    alienBullets.add(new Bullet(5, 5, new SpriteSheet(game.getSpriteSheet())));
    assertEquals(ship.ifHit(alienBullets),-1); 
    alienBullets.removeAllElements();
        
    alienBullets.add(new Bullet(317, 425, new SpriteSheet(game.getSpriteSheet())));
    assertEquals(ship.ifHit(alienBullets),0);
    assertEquals(ship.getLives(),2);
  }
  
  /**
   * The JUnit test of the getlives method of Spaceship. 
   * the getlives method returns the amount of lives the spaceship has left.
   */
  @Test
  public void testGetLives() {
    final Spaceship ship = new Spaceship(game);
    assertEquals(ship.getLives(),3);
  }

}
