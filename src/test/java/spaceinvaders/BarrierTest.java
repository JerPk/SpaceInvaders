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
 * BarrierTest tests all the methods in the Barrier class.
 * 
 * @author Group 23
 *
 */
public class BarrierTest {

  /**
   * Open the logfile.
   */
  @Before
  public void setLogFile() {
    LogFile.getInstance().open();
  }

  /**
   * the JUnit test of the Constuctor method of Barrier.
   */
  @Test
  public void testBarrier() {
    final Barrier barrier = new Barrier(3.0, 3.0);

    assertEquals(barrier.getPosX(), 3.0, 0.00001);
    assertEquals(barrier.getPosY(), 3.0, 0.00001);
    assertEquals(barrier.getState(), 0);

  }

  /**
   * The JUnit test of the IfHit method of Barrier.
   */
  @Test
  public void testIfHit() {
    final Barrier barrier = new Barrier(3.0, 3.0);
    final Vector<Bullet> alienBullets = new Vector<Bullet>(0);

    Bullet testBullet = new Bullet(100, 15);
    alienBullets.add(testBullet);
    assertEquals(barrier.ifHit(alienBullets), -1);
    alienBullets.clear();
    
    testBullet = new Bullet(20, 15);
    alienBullets.add(testBullet);
    assertEquals(barrier.ifHit(alienBullets), 0);
    alienBullets.clear();
    
    testBullet = new MegaBullet(100, 15);
    alienBullets.add(testBullet);
    assertEquals(barrier.ifHit(alienBullets), -1);
    alienBullets.clear();
    
    testBullet = new MegaBullet(20, 15);
    alienBullets.add(testBullet);
    assertEquals(barrier.ifHit(alienBullets), 0);
  }
  
  /**
   * The JUnit test of the destroyes method of Barrier.
   */
  @Test
  public void testDestroyed() {
    final Barrier barrier = new Barrier(3.0, 3.0);
    final Vector<Bullet> alienBullets = new Vector<Bullet>(0);
    
    assertFalse(barrier.destroyed());
    
    final Bullet testBullet = new Bullet(20, 15);
    alienBullets.add(testBullet);
    assertEquals(barrier.ifHit(alienBullets), 0);

    alienBullets.add(testBullet);
    assertEquals(barrier.ifHit(alienBullets), 0);
    
    alienBullets.add(testBullet);
    assertEquals(barrier.ifHit(alienBullets), 0);
    
    alienBullets.add(testBullet);
    assertEquals(barrier.ifHit(alienBullets), 0);
    
    assertEquals(barrier.getState(), 4);
    assertTrue(barrier.destroyed());
  }

  /**
   * The JUnit test of the getPosX method of Barrier.
   */
  @Test
  public void testGetPosX() {
    final Barrier barrier = new Barrier(3.0, 5.0);
    assertEquals(barrier.getPosX(), 3.0, 0.00001);
  }

  /**
   * The JUnit test of the getPosY method of Barrier.
   */
  @Test
  public void testGetPosY() {
    final Barrier barrier = new Barrier(3.0, 5.0);
    assertEquals(barrier.getPosY(), 5.0, 0.00001);
  }

  /**
   * The JUnit test of the getState method of Barrier.
   */
  @Test
  public void testGetState() {
    final Barrier barrier = new Barrier(3.0, 5.0);
    assertEquals(barrier.getState(), 0);
  }
}
