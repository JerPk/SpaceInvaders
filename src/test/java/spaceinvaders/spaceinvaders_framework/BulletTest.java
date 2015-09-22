package spaceinvaders.spaceinvaders_framework;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * BulletTest tests all the methods in the bullet class.
 * 
 * @author Group 23
 */
public class BulletTest {
  
    /**
     * The game object that is used in all of the test cases.
     */
  private Game game;

  /**
   * This method is executed before every test.
   * It creates the game class and sets up the required spritesheet.
   */
  @Before
  public void setUpGame() {
    game = new Game();
    game.setSpriteSheet("res/sprite_sheet.png");
    game.init();
  }

  /**
   * the JUnit test of the Constuctor method of Bullet.
   */
  @Test
  public void testBullet() {
    final Bullet bullet = new Bullet(1, 1, new SpriteSheet(game.getSpriteSheet()));
    assertEquals((double) 1, bullet.getX(), 0.00001);
    assertEquals((double) 1, bullet.getY(), 0.00001);
  }

  /**
   * the JUnit test of the MoveUp method of Bullet.
   */
  @Test
  public void testMoveUp() {
    final Bullet bullet = new Bullet(1, 20, new SpriteSheet(game.getSpriteSheet()));
    game.setrunning(true);
    
    bullet.moveUp();
    assertEquals((double) 17.8, bullet.getY(), 0.00001);
    assertTrue(game.getrunning());
  }

  /**
   * the JUnit test of the MoveDown method of Bullet.
   */
  @Test
  public void testMoveDown() {
    final Bullet bullet = new Bullet(1, 20, new SpriteSheet(game.getSpriteSheet()));
    game.setrunning(true);
    
    bullet.moveDown();
    assertEquals((double) 22.2 , bullet.getY(), 0.00001);
    assertTrue(game.getrunning());
  }
  
  /**
   * The JUnit test that test the equals method that compares
   * two bullets, and checks if they are equal.  
   */
  @Test
  public void testEquals() {
    final Bullet bullet1 = new Bullet(1, 20, new SpriteSheet(game.getSpriteSheet()));
    final Bullet bullet2 = new Bullet(1, 20, new SpriteSheet(game.getSpriteSheet()));
      
    assertEquals(bullet1,bullet2);
    assertNotSame(bullet1,bullet2);
  }

}
