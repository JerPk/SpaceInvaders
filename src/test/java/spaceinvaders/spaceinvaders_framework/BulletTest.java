package spaceinvaders.spaceinvaders_framework;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.awt.image.BufferedImage;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import bullet.*;
import spaceinvaders.Game;
import state.Executor;

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
	private Executor exec;

	/**
	 * This method is executed before every test. It creates the game class and
	 * sets up the required spritesheet.
	 */
	@Before
	public void setUpGame() {
		exec = new Executor();
		exec.run();
		game = new Game(exec);
		game.init();
	}

	/**
	 * the JUnit test of the Constuctor method of Bullet.
	 */
	@Test
	public void testBullet() {
		Bullet bullet = new Bullet(1, 1);
		assertEquals((double) 1, bullet.getX(), 0.00001);
		assertEquals((double) 1, bullet.getY(), 0.00001);
		
		bullet = new MegaBullet(1, 1);
		assertEquals((double) 1, bullet.getX(), 0.00001);
		assertEquals((double) 1, bullet.getY(), 0.00001);
	}

	/**
	 * the JUnit test of the MoveUp method of Bullet.
	 */
	@Test
	public void testMoveUp() {
		final Bullet bullet = new Bullet(1, 20);
		game.setRunning(true);

		bullet.moveUp();
		assertEquals((double) 17.8, bullet.getY(), 0.00001);
		assertTrue(game.getRunning());
	}

	/**
	 * the JUnit test of the MoveDown method of Bullet.
	 */
	@Test
	public void testMoveDown() {
		Bullet bullet = new Bullet(1, 20);
		game.setRunning(true);

		bullet.moveDown();
		assertEquals((double) 22.2, bullet.getY(), 0.00001);
		assertTrue(game.getRunning());
		
//		game.se
		
		bullet = new DiagonalBulletLeft(20, 20);
		bullet.moveDown();
		assertEquals((double) 22.2, bullet.getY(), 0.00001);
		assertEquals((double) 19.3, bullet.getX(), 0.00001);
		
		bullet = new DiagonalBulletRight(20, 20);
		bullet.moveDown();
		assertEquals((double) 22.2, bullet.getY(), 0.00001);
		assertEquals((double) 20.7, bullet.getX(), 0.00001);
	}

	/**
	 * The JUnit test that test the equals method that compares two bullets, and
	 * checks if they are equal.
	 */
	@Test
	public void testEquals() {
		final Bullet bullet1 = new Bullet(1, 20);
		final Bullet bullet2 = new Bullet(1, 20);

		assertEquals(bullet1, bullet2);
		assertNotSame(bullet1, bullet2);
	}
	
	/**
	 * The JUnit test that tests the setX method.
	 */
	@Test
	public void testSetX() {
		final Bullet bullet = new Bullet(1, 20);
		
		bullet.setX(50);
		assertEquals((double) 50, bullet.getX(), 0.00001);
	}
	
	/**
	 * The JUnit test that tests the setX method.
	 */
	@Test
	public void testSetY() {
		final Bullet bullet = new Bullet(1, 20);
		
		bullet.setY(50);
		assertEquals((double) 50, bullet.getY(), 0.00001);
	}
	
	/**
     * The JUnit test that tests the reachedY method.
     */
	@Test
    public void testReachedY() {
    	final Bullet bullet = new Bullet(1, 20);
    	assertFalse(bullet.reachedY(50));
    	
    	bullet.setY(60);
    	assertTrue(bullet.reachedY(50));
    }
}
