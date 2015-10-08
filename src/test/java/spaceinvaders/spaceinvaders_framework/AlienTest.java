package spaceinvaders.spaceinvaders_framework;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

/**
 * AlienTest tests all the methods in the alien class.
 * 
 * @author Group 23
 */
public class AlienTest {

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
     * the JUnit test of the Constuctor method of Alien.
   */
  @Test
    public void testConstructorAlien() {
      
    final Alien alien =  AlienFactory.getAlien("easy",3, 3, game);
    assertEquals((double) 3, alien.getX(), 0.00001);
    assertEquals((double) 3, alien.getY(), 0.00001);

  }

    /**
     * The JUnit test of a successful HMovement.
     */
  @Test
    public void testSuccessfulHMovement() {
    final Alien alien =  AlienFactory.getAlien("easy",33, 33, game);

    assertFalse(game.getupdateLogic());

    alien.hmovement();
    assertFalse(game.getupdateLogic());
    assertEquals(34, alien.getX(), 0.00001);
  }

    /**
     * The JUnit test of a HMovement where the Alien has reached the rigameht
     * hand Border.
     */
  @Test
    public void testHMovementRigamehtBorder() {
    final Alien alien = AlienFactory.getAlien("easy",643, 33, game);

    assertFalse(game.getupdateLogic());

    alien.hmovement();
    assertEquals((double) 644, alien.getX(), 0.0001);
    assertTrue(game.getupdateLogic());
  }

    /**
     * The JUnit test of a HMovement where the Alien has reached the left hand
     * Border.
     */
  @Test
    public void testHMovementLeftBorder() {
    final Alien alien = AlienFactory.getAlien("easy",2, 33, game);
    alien.setMovementSpeed(-4);

    assertFalse(game.getupdateLogic());

    alien.hmovement();
    assertEquals((double) -2, alien.getX(), 0.0001);
    assertTrue(game.getupdateLogic());
  }

    /**
     * The JUnit test of a HMovement where the Alien has not reached reached the
     * left hand Border, but the Movement speed is in the other direction.
     */
  @Test
    public void testHMovementInverseMovement() {
    final Alien alien = AlienFactory.getAlien("easy",7, 33, game);
    alien.setMovementSpeed(-5);

    assertFalse(game.getupdateLogic());

    alien.hmovement();
    assertEquals((double) 2, alien.getX(), 0.0001);
    assertFalse(game.getupdateLogic());
  }

    /**
     * The JUnit test of VMovement where the Alien has not reached the bottom of
     * the screen.
     */
  @Test
    public void testVMovement() {
    final Alien alien = AlienFactory.getAlien("easy", 7, 30, game);
    game.setrunning(true);

    alien.vmovement();
    assertEquals((double) 50, alien.getY(), 0.0001);
    assertTrue(game.getrunning());

  }

  @Test
    public void testShoot() {
    final Alien alien = AlienFactory.getAlien("easy", 7, 30, game);
    final Bullet bullet = alien.shoot();

    final SpriteSheet spritesheet = new SpriteSheet(game.getSpriteSheet());

    final Bullet testBullet = new Bullet(12, 32, spritesheet);

    assertEquals(bullet, testBullet);
    assertNotSame(bullet, testBullet);
  }

  @Test
    public void testIfHit() {
    final Alien alien = AlienFactory.getAlien("easy", 7, 30, game);
    final Vector<Bullet> shipBullets = new Vector<Bullet>(0);

    // assert that the ifhit method returns -1 if
    // the vector is empty.
    assertEquals(alien.ifHit(shipBullets), -1);

    final SpriteSheet spritesheet = new SpriteSheet(game.getSpriteSheet());

    final Bullet testBullet = new Bullet(7, 30, spritesheet);
    shipBullets.add(testBullet);

    // test that ifhit method results in 0
    // if the alien is hit by the first bullet from the bullet vector.
    assertEquals(alien.ifHit(shipBullets), 0);

  }
  
  @Test
  public void testGetScore() {
      final Alien alien = AlienFactory.getAlien("easy", 7, 30, game);
      assertEquals(alien.getScore(),10);
  }
  
  @Test
  public void testGetHealth() {
      final Alien alien = AlienFactory.getAlien("easy", 7, 30, game);
      assertEquals(alien.getHealth(),1);
  }
  
  @Test
  public void testGetGame() {
      final Alien alien = AlienFactory.getAlien("easy", 7, 30, game);
      assertEquals(alien.getGame(),game);
  }
  
  @Test
  public void testAlienFactory() {
      final Alien alien = AlienFactory.getAlien("Nonesense", 7, 30, game);
      assertEquals(alien,null);
  }

  @Test
  public void testShootAlienType2() {
      final Alien alien = AlienFactory.getAlien("normal", 7, 30, game);
      final Bullet bullet = alien.shoot();
      
      final SpriteSheet spritesheet = new SpriteSheet(game.getSpriteSheet());

      final Bullet testBullet = new Bullet(12, 32, spritesheet);

      assertEquals(bullet, testBullet);
      assertNotSame(bullet, testBullet);
  }
  
  @Test
  public void testShootAlienType3() {
      final Alien alien = AlienFactory.getAlien("hard", 7, 30, game);
      final Bullet bullet = alien.shoot();
      
      final SpriteSheet spritesheet = new SpriteSheet(game.getSpriteSheet());

      final Bullet testBullet = new Bullet(12, 32, spritesheet);

      assertEquals(bullet, testBullet);
      assertNotSame(bullet, testBullet);
  }
}
