package spaceinvaders.spaceinvaders_framework;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import bullet.Bullet;
import alien.Alien;
import alien.AlienFactory;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JFrame;


/**
 * GameTest tests almost all the methods in the Game class. Methods not tested
 * are the main game loop and certain GUI related methods.
 * 
 * @author Group 23
 *
 */
public class GameTest {

	/**
	 * The game object that is used in all of the test cases.
	 */
	private Game game;
	private Screen screen;

	protected BufferedImage BImg = null;

    /**
     * This method is executed before every test. It creates the game class.
     */
  @Before
    public void setupGame() {
    game = new Game();
    screen = new Screen(game);
  }

    /**
     * The JUnit test of the constructor method of Game.
     */
  @Test
    public void testConstructor() {
    final Game ngame = new Game();

    assertEquals(0, ngame.getcounter());
    assertNotSame(ngame, null);

  }

//    /**
//     * the JUnit test of the init method. This particular test case tests
//     * whether or not the spritesheet was successfully loaded from the png file.
//     */
//  @Test
//    public void testInitSpriteSheet() {
//
//    //assertSame(game.getSpriteSheet(), null);
//
//    game.init();
//    try {
//      final BuffereImageLoader loader = new BuffereImageLoader();
//      final BufferedImage testSprite = loader.LoadImage("res/sprite_sheet.png");
//      assertTrue(game.compareImages(BImg, testSprite));
//      assertNotSame(BImg, testSprite);
//      assertNotSame(BImg, null);
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//  }

    /**
     * The JUnit test of the init method. This particual test case tests wheter
     * or not the spaceship was successfully created.
     */
  @Test
    public void testInitSpaceship() {
    assertSame(game.getSpaceship(), null);

    game.init();
    final Spaceship shipTest = new Spaceship();

    assertNotSame(game.getSpaceship(), null);
    assertEquals(game.getSpaceship(), shipTest);
//    assertNotSame(game.getSpaceship(), shipTest);

  }

//    /**
//     * The JUnit test of the do action method.
//     */
//  @Test
//    public void testDoAction() {
//    game.init();
//    final Vector<Alien> Alienvector = game.getAlienVector();
//    final Alien alien = Alienvector.get(0);
//    assertEquals(alien.getX(), 72, 0.0001);
//
//    game.doAction();
//    final Alien alien2 = Alienvector.get(0);
//    assertEquals(alien2.getX(), 73, 0.0001);
//
//  }

    /**
     * The JUnit test of the render bulletShip method.
     */
  @Test
    public void testRenderBulletShip() {

    game.init();

    final Bullet newBullet = new Bullet(10, 2);

    game.addShipBullets(newBullet);

    assertEquals(game.getShipBullets().get(0).getY(), 2, 0.0001);

    // creates a basic JFrame that will be used for the test.
    final JFrame frame = new JFrame(screen.TITLE);
    frame.add(game);
    frame.pack();

    screen.renderBulletShip(game.getShipBullets());

    // because we dont have a buffer strategy yet the bullet shouldnt
    // be changed
    assertEquals(game.getShipBullets().get(0).getY(), 2, 0.0001);

    screen.renderBulletShip(game.getShipBullets());

    // now on the second time we do have a buffer strategy therefore this
    // time
    // the bullets coordinates should change.
    assertEquals(game.getShipBullets().get(0).getY(), -0.2, 0.0001);
  }

    /**
     * The JUnit test of the render bulletAlien method.
     */
  @Test
    public void testRenderBulletAlien() {
    game.init();

    final Bullet newBullet = new Bullet(10, 2);

    game.addAlienBullets(newBullet);

    assertEquals(game.getAlienBullets().get(0).getY(), 2, 0.0001);

    // creates a basic JFrame that will be used for the test.
    final JFrame frame = new JFrame(screen.TITLE);
    frame.add(game);
    frame.pack();

    screen.renderBulletAlien(game.getAlienBullets());

    // because we dont have a buffer strategy yet the bullet shouldnt
    // be changed
    assertEquals(game.getAlienBullets().get(0).getY(), 2, 0.0001);

    screen.renderBulletAlien(game.getAlienBullets());

    // now on the second time we do have a buffer strategy therefore this
    // time
    // the bullets coordinates should change.
    assertEquals(game.getAlienBullets().get(0).getY(), 4.2, 0.0001);
  }
  
//////////// Has to be moved to Screen test
//    /**
//     * The JUnit test of the Render method.
//     */
//  @Test
//    public void testRender() {
//    game.init();
//
//    final SpriteSheet spritesheet = new SpriteSheet(BImg);
//    final Bullet newBullet = new Bullet(10, 2, spritesheet);
//    final Bullet newBullet2 = new Bullet(10, 2, spritesheet);
//
//    game.addAlienBullets(newBullet);
//    game.addShipBullets(newBullet2);
//
//    assertEquals(game.getAlienBullets().get(0).getY(), 2, 0.0001);
//    assertEquals(game.getShipBullets().get(0).getY(), 2, 0.0001);
//
//    // creates a basic JFrame that will be used for the test.
//    final JFrame frame = new JFrame(screen.TITLE);
//    frame.add(game);
//    frame.pack();
//
//    screen.render(game);
//
//    // because we dont have a buffer strategy yet the bullet shouldnt
//    // be changed
//    assertEquals(game.getAlienBullets().get(0).getY(), 2, 0.0001);
//    assertEquals(game.getShipBullets().get(0).getY(), 2, 0.0001);
//
//    screen.render(game);
//
//    // now on the second time we do have a buffer strategy therefore this
//    // time
//    // the bullets coordinates should change.
//    assertEquals(game.getAlienBullets().get(0).getY(), 2.0, 0.0001);
//    assertEquals(game.getShipBullets().get(0).getY(), 2.0, 0.0001);
//
//  }

    /**
     * The JUnit test of the alien shoot method.
     */
  @Test
    public void testalienShoot() {
    game.init();
    
    final Bullet newBullet = new Bullet(10, 2);

    game.addAlienBullets(newBullet);
    assertEquals(game.getAlienBullets().size(), 1);

    game.alienShoot();
    assertEquals(game.getAlienBullets().size(), 2);
  }

//////////// Has to be implemented in the testIfHit method
//    /**
//     * The JUnit test of the alien die method.
//     */
//  @Test
//    public void testalienDie() {
//    game.init();
//
//    final Alien alien = AlienFactory.getAlien("easy", 3, 3);
//    game.addAlien(alien);
//
//    final SpriteSheet spritesheet = new SpriteSheet(BImg);
//    final Bullet newBullet = new Bullet(3, 3, spritesheet);
//    game.addShipBullets(newBullet);
//
//    assertEquals(game.getAlienVector().size(), 55);
//    assertEquals(game.getShipBullets().size(), 1);
//
//    game.alienDie();
//
//    assertEquals(game.getAlienVector().size(), 54);
//    assertEquals(game.getShipBullets().size(), 0);
//  }

    /**
     * The JUnit test of the remove bullets method.
     */
  @Test
    public void testRemoveBullets() {
    game.init();

    final Bullet newBullet = new Bullet(10, 500);
    final Bullet newBullet2 = new Bullet(10, -2);

    game.addAlienBullets(newBullet);
    game.addAlienBullets(newBullet2);

    game.addShipBullets(newBullet);
    game.addShipBullets(newBullet2);

    assertEquals(game.getAlienBullets().size(), 2);
    assertEquals(game.getShipBullets().size(), 2);

    game.removeOffScreenBullets();
    assertEquals(game.getAlienBullets().size(), 1);
    assertEquals(game.getShipBullets().size(), 1);

  }

    /**
     * The JUnite test of the KeyPressed method.
     * This particular method tests if Left key is pressed.
     */
  @Test
    public void testKeyPressedLeft() {

    assertFalse(screen.getLeftPressed());

    final KeyEvent key = new KeyEvent(game, 0, System.currentTimeMillis(), 0,
                KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED);

    screen.keyPressed(key);

    assertTrue(screen.getLeftPressed());

  }

    /**
     * The JUnite test of the KeyPressed method.
     * This particular method tests if Right key is pressed.
     */
  @Test
    public void testKeyPressedRight() {

    assertFalse(screen.getRightPressed());

    final KeyEvent key = new KeyEvent(game, 0, System.currentTimeMillis(), 0,
                KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED);

    screen.keyPressed(key);

    assertTrue(screen.getRightPressed());

  }

    /**
     * The JUnite test of the KeyPressed method.
     * This particular method tests if space is pressed.
     */
  @Test
    public void testKeyPressedSpace() {

    assertFalse(screen.getSpacePressed());

    final KeyEvent key = new KeyEvent(game, 0, System.currentTimeMillis(), 0,
                KeyEvent.VK_SPACE, KeyEvent.CHAR_UNDEFINED);

    screen.keyPressed(key);

    assertTrue(screen.getSpacePressed());

  }

    /**
     * The JUnite test of the KeyReleased method.
     * This particular method tests if Left is released.
     */
  @Test
    public void testKeyReleasedLeft() {
    game.init();
    screen.setPressedLeft(true);

    assertTrue(screen.getLeftPressed());

    final KeyEvent key = new KeyEvent(game, 0, System.currentTimeMillis(), 0,
                KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED);

    screen.keyReleased(key);
    assertFalse(screen.getLeftPressed());
  }

    /**
     * The JUnite test of the KeyReleased method.
     * This particular method tests if Right is released.
     */
  @Test
    public void testKeyReleasedRight() {
    game.init();
    screen.setPressedRight(true);
    
    assertTrue(screen.getRightPressed());

    final KeyEvent key = new KeyEvent(game, 0, System.currentTimeMillis(), 0,
                KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED);

    screen.keyReleased(key);
    assertFalse(screen.getRightPressed());
  }

    /**
     * The JUnite test of the KeyReleased method.
     * This particular method tests if Space is released.
     */
  @Test
    public void testKeyReleasedSpace() {
	  screen.setPressedSpace(true);
    
    assertTrue(screen.getSpacePressed());

    final KeyEvent key = new KeyEvent(game, 0, System.currentTimeMillis(), 0,
                KeyEvent.VK_SPACE, KeyEvent.CHAR_UNDEFINED);

    screen.keyReleased(key);
    assertFalse(screen.getSpacePressed());
  }
  
  /**
   * tests the start method.
   */
  @Test
  public void testStart() {
//      assertNull(game.getThread());
      game.setRunning(true);
      game.start();
//      assertNull(game.getThread());
      game.setRunning(false);
      game.start();
//      assertNotNull(game.getThread());
  }

//////////// Not working because of fault in .dat file?
//  /**
//   * tests the start method.
//   */
//  @Test
//  public void testEnd() { 
//      
//      game.init();
//      game.start();
//      game.setscore(10);
//      assertTrue(game.getRunning());
//      assertNull(game.getScoreMenu());
//      game.end();
//      assertFalse(game.getRunning());
//      assertNotNull(game.getScoreMenu());
//  }
}
