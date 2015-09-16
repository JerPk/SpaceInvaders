package Space_Invaders_Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import Space_Invaders.Alien;
import Space_Invaders.Bullet;
import Space_Invaders.Game;
import Space_Invaders.SpriteSheet;

public class BulletTest {
	
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
	 * the JUnit test of the Constuctor method of Bullet.
	 */
	@Test
	public void testBullet() {
		Bullet b = new Bullet(1, 1, new SpriteSheet(g.getSpriteSheet()));
		assertEquals((double) 1, b.getX(), 0.00001);
		assertEquals((double) 1, b.getY(), 0.00001);
	}

	/**
	 * the JUnit test of the MoveUp method of Bullet.
	 */
	@Test
	public void testMoveUp() {
		Bullet b = new Bullet(1, 20, new SpriteSheet(g.getSpriteSheet()));
        g.setrunning(true);
		
		b.moveUp();
		assertEquals((double) 17.8, b.getY(), 0.00001);
		assertEquals(true, g.getrunning());
	}

	/**
	 * the JUnit test of the MoveDown method of Bullet.
	 */
	@Test
	public void testMoveDown() {
		Bullet b = new Bullet(1, 20, new SpriteSheet(g.getSpriteSheet()));
        g.setrunning(true);
		
		b.moveDown();
		assertEquals((double) 22.2 , b.getY(), 0.00001);
		assertEquals(true, g.getrunning());
	}
	
	/**
	 * The JUnit test that test the equals method that compares
	 * two bullets, and checks if they are equal.  
	 */
	@Test
	public void testEquals(){
	    Bullet a = new Bullet(1, 20, new SpriteSheet(g.getSpriteSheet()));
	    Bullet b = new Bullet(1, 20, new SpriteSheet(g.getSpriteSheet()));
	    
	    assertEquals(a,b);
	    assertNotSame(a,b);
	}

}
