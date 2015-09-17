package Space_Invaders_Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import Space_Invaders.Alien;
import Space_Invaders.Bullet;
import Space_Invaders.Game;
import Space_Invaders.SpriteSheet;

import org.junit.Before;
import org.junit.Test;

import java.util.Vector;

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
  }
  

    /**
     * the JUnit test of the Constuctor method of Alien.
   */
  @Test
    public void testConstructorAlien() {
    final Alien alien = new Alien(3, 3, game);
    assertEquals((double) 3, alien.getX(), 0.00001);
    assertEquals((double) 3, alien.getY(), 0.00001);

  }

    /**
     * The JUnit test of a successful HMovement.
     */
  @Test
    public void testSuccessfulHMovement() {
    final Alien alien = new Alien(33, 33, game);

    assertFalse(game.getupdateLogic());

    alien.HMovement();
    assertFalse(game.getupdateLogic());
    assertEquals(34, alien.getX(), 0.00001);
  }

    /**
     * The JUnit test of a HMovement where the Alien has reached the rigameht
     * hand Border.
     */
  @Test
    public void testHMovementRigamehtBorder() {
    final Alien alien = new Alien(643, 33, game);

    assertFalse(game.getupdateLogic());

    alien.HMovement();
    assertEquals((double) 644, alien.getX(), 0.0001);
    assertTrue(game.getupdateLogic());
  }

    /**
     * The JUnit test of a HMovement where the Alien has reached the left hand
     * Border.
     */
  @Test
    public void testHMovementLeftBorder() {
    final Alien alien = new Alien(2, 33, game);
    alien.setMovementSpeed(-4);

    assertFalse(game.getupdateLogic());

    alien.HMovement();
    assertEquals((double) -2, alien.getX(), 0.0001);
    assertTrue(game.getupdateLogic());
  }

    /**
     * The JUnit test of a HMovement where the Alien has not reached reached the
     * left hand Border, but the Movement speed is in the other direction.
     */
  @Test
    public void testHMovementInverseMovement() {
    final Alien alien = new Alien(7, 33, game);
    alien.setMovementSpeed(-5);

    assertFalse(game.getupdateLogic());

    alien.HMovement();
    assertEquals((double) 2, alien.getX(), 0.0001);
    assertFalse(game.getupdateLogic());
  }

    /**
     * The JUnit test of VMovement where the Alien has not reached the bottom of
     * the screen.
     */
  @Test
    public void testVMovement() {
    final Alien alien = new Alien(7, 30, game);
    game.setrunning(true);

    alien.VMovement();
    assertEquals((double) 50, alien.getY(), 0.0001);
    assertTrue(game.getrunning());

  }

  @Test
    public void testShoot() {
    final Alien alien = new Alien(7, 30, game);
    final Bullet bullet = alien.shoot();

    final SpriteSheet spritesheet = new SpriteSheet(game.getSpriteSheet());

    final Bullet testBullet = new Bullet(12, 32, spritesheet);

    assertEquals(bullet, testBullet);
    assertNotSame(bullet, testBullet);
  }

  @Test
    public void testIfHit() {
    final Alien alien = new Alien(7, 30, game);
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

}
