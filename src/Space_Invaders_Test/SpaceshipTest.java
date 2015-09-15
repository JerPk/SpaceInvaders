package Space_Invaders_Test;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import Space_Invaders.Spaceship;
import Space_Invaders.Bullet;
import Space_Invaders.Game;
import Space_Invaders.SpriteSheet;

public class SpaceshipTest {
	
	private Game g;
	
	/**
	 * Launch the user interface.
	 */
	@Before
	public void setUpGame() {
		g = new Game();
		g.setSpriteSheet("res/sprite_sheet.png");
	}
	
	/**
	 * the JUnit test of the Constuctor method of Spaceship.
	 */
	@Test
	public void testSpaceship() {
		Spaceship s = new Spaceship(g);
		assertEquals((double) 317, s.getPosX(), 0.00001);
		assertEquals((double) 425, s.getPosY(), 0.00001);
	}
	
	/**
	 * the JUnit test of the MoveLeft method of Spaceship.
	 */
	@Test
	public void testMoveLeft() {
		Spaceship s = new Spaceship(g);
        g.setrunning(true);
		
		s.moveLeft();
		assertEquals((double) 312, s.getPosX(), 0.00001);
		assertEquals(true, g.getrunning());
	}

	/**
	 * the JUnit test of the MoveRight method of Spaceship.
	 */
	@Test
	public void testMoveRight() {
		Spaceship s = new Spaceship(g);
        g.setrunning(true);
		
		s.moveRight();
		assertEquals((double) 322, s.getPosX(), 0.00001);
		assertEquals(true, g.getrunning());
	}

	/**
	 * the JUnit test of the Shoot method of Spaceship.
	 */
	@Test
	public void testShoot() {
		Spaceship s = new Spaceship(g);
        g.setrunning(true);
		
		Bullet b = s.shoot();
		assertEquals((double) s.getPosX(), b.getX(), 0.00001);
		assertEquals((double) s.getPosY(), b.getY(), 0.00001);
		assertEquals(true, g.getrunning());
	}

	/**
	 * the JUnit test of the ifHit method of Spaceship.
	 */
	@Test
	public void testIfHit() {
		Spaceship s = new Spaceship(g);
        g.setrunning(true);
        Vector<Bullet> alienBullets = new Vector<Bullet>(0);
        
        alienBullets.add(new Bullet(5, 5, new SpriteSheet(g.getSpriteSheet())));
       // assertFalse(s.ifHit(alienBullets));
        alienBullets.removeAllElements();
        
        alienBullets.add(new Bullet(317, 425, new SpriteSheet(g.getSpriteSheet())));
       // assertTrue(s.ifHit(alienBullets));
	}

}
