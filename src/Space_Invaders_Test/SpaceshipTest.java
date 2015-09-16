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
		assertEquals((double) 304, s.getPosX(), 0.00001);
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
		assertEquals((double) 302, s.getPosX(), 0.00001);
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
		assertEquals((double) 306, s.getPosX(), 0.00001);
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
		assertEquals((double) s.getPosX() + 10, b.getX(), 0.00001);
		assertEquals((double) s.getPosY() + 2, b.getY(), 0.00001);
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
       assertEquals(s.ifHit(alienBullets),-1); 
       alienBullets.removeAllElements();
        
        alienBullets.add(new Bullet(317, 425, new SpriteSheet(g.getSpriteSheet())));
       assertEquals(s.ifHit(alienBullets),0);
	}
	
	/**
	 * The JUnit test of the getlives method of Spaceship. 
	 * the getlives method returns the amount of lives the spaceship has left.
	 */
	@Test
	public void testGetLives(){
	    Spaceship s = new Spaceship(g);
	    assertEquals(s.getLives(),3);
	}
	
	/**
     * The JUnit test of the decreaseLives method of Spaceship. 
     * every time the decreaseLives method is called the amount of lives spaceship
     * should be decreased by one.
     */
	@Test
	public void testDecreaseLives(){
	    Spaceship s = new Spaceship(g);
        assertEquals(s.getLives(),3);
        s.decreaseLives();
        assertEquals(s.getLives(),2);
	}

}
