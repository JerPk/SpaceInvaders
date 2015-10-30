package spaceinvaders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import bullet.Bullet;
import bullet.MegaBullet;

import java.util.Vector;

/**
 * SpaceshipTest tests all the methods in the spaceship class.
 * 
 * @author Group 23
 */
public class SpaceshipTest {

  /**
   * Open the logfile.
   */
  @Before
  public final void setUpLogFile() {
    LogFile.getInstance().open();
  }

  /**
   * The JUnit test of the Constructor method of Spaceship.
   */
  @Test
  public final void testSpaceship() {
    final Spaceship ship = new Spaceship();
    assertEquals((double) 304, ship.getPosX(), 0.00001);
    assertEquals((double) 425, ship.getPosY(), 0.00001);
  }

  /**
   * The JUnit test of the MoveLeft method of Spaceship.
   */
  @Test
  public final void testMoveLeft() {
    final Spaceship ship = new Spaceship();

    ship.moveLeft();
    assertEquals((double) 302, ship.getPosX(), 0.00001);
  }

  /**
   * The JUnit test of the MoveRight method of Spaceship.
   */
  @Test
  public final void testMoveRight() {
    final Spaceship ship = new Spaceship();

    ship.moveRight();
    assertEquals((double) 306, ship.getPosX(), 0.00001);
  }

  /**
   * The JUnit test of the Shoot method of Spaceship.
   */
  @Test
  public final void testShoot() {
    final Spaceship ship = new Spaceship();

    final Bullet bullet = ship.shoot();
    assertEquals((double) ship.getPosX() + 10, bullet.getX(), 0.00001);
    assertEquals((double) ship.getPosY() + 2, bullet.getY(), 0.00001);
  }

  /**
   * The JUnit test of the ifHit method of Spaceship.
   */
  @Test
  public final void testIfHit() {
    final Spaceship ship = new Spaceship();
    final Vector<Bullet> alienBullets = new Vector<Bullet>(0);

    alienBullets.add(new Bullet(5, 5));
    assertEquals(-1, ship.ifHit(alienBullets));
    alienBullets.removeAllElements();

    alienBullets.add(new Bullet(317, 425));
    assertEquals(0, ship.ifHit(alienBullets));
    assertEquals(2, ship.getLives());
    alienBullets.removeAllElements();

    alienBullets.add(new MegaBullet(5, 5));
    assertEquals(-1, ship.ifHit(alienBullets));
    alienBullets.removeAllElements();

    alienBullets.add(new MegaBullet(317, 425));
    assertEquals(ship.ifHit(alienBullets), 0);
    assertEquals(ship.getLives(), 1);

    alienBullets.add(new MegaBullet(317, 425));
    assertEquals(ship.ifHit(alienBullets), 0);
    assertEquals(ship.getLives(), 0);
  }

  /**
   * The JUnit test of resetPosition method of spaceship.
   */
  @Test
  public final void testResetPosition() {
    final Spaceship ship = new Spaceship();

    ship.moveRight();
    assertEquals((double) 306, ship.getPosX(), 0.00001);

    ship.resetPosition();
    assertEquals((double) 304, ship.getPosX(), 0.00001);
  }

  /**
   * The JUnit test of defeated method of spaceship.
   */
  @Test
  public final void testDefeated() {
    final Spaceship ship = new Spaceship();
    final Vector<Bullet> alienBullets = new Vector<Bullet>(0);

    assertFalse(ship.defeated());

    for (int i = 0; i < 3; i++) {
      alienBullets.add(new Bullet(317, 425));
      assertEquals(0, ship.ifHit(alienBullets));
    }

    assertEquals(0, ship.getLives());
    assertTrue(ship.defeated());
  }

  /**
   * The JUnit test of the getLives method of Spaceship. the getLives method
   * returns the amount of lives the spaceship has left.
   */
  @Test
  public final void testGetLives() {
    final Spaceship ship = new Spaceship();
    assertEquals(ship.getLives(), 3);
  }

}
