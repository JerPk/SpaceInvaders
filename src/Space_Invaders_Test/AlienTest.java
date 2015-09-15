package Space_Invaders_Test;

import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import Space_Invaders.Alien;
import Space_Invaders.Bullet;
import Space_Invaders.Game;
import Space_Invaders.SpriteSheet;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;


public class AlienTest {

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
	 * the JUnit test of the Constuctor method of Alien.
	 */
	@Test
	public void TestConstructorAlien() {
		Alien a = new Alien(3, 3, g);
		assertEquals((double) 3, a.getX(), 0.00001);
		assertEquals((double) 3, a.getY(), 0.00001);

	}
	
	/**
	 * The JUnit test of a successful HMovement
	 */
	@Test
	public void TestSuccessfulHMovement(){
		Alien a = new Alien(33, 33, g);
		
		assertEquals(false,g.getupdateLogic());
		
		a.HMovement();
		assertEquals(false,g.getupdateLogic());
		assertEquals(34, a.getX(),0.00001);
	}
	
	/**
	 * The JUnit test of a HMovement where the Alien has 
	 * reached the right hand Border.
	 */
	@Test
	public void TestHMovementRightBorder(){
		Alien a = new Alien(643, 33, g);
		
		assertEquals(false,g.getupdateLogic());
		
		a.HMovement();
		assertEquals((double) 644, a.getX(), 0.0001);
		assertEquals(true,g.getupdateLogic());
	}
	
	/**
	 * The JUnit test of a HMovement where the Alien has 
	 * reached the left hand Border.
	 */
	@Test
	public void TestHMovementLeftBorder(){
		Alien a = new Alien(2, 33, g);
		a.setMovementSpeed(-4);
		
		assertEquals(false,g.getupdateLogic());
		
		a.HMovement();
		assertEquals((double) -2, a.getX(), 0.0001);
		assertEquals(true,g.getupdateLogic());
	}
	
	/**
	 * The JUnit test of a HMovement where the Alien has not reached 
	 * reached the left hand Border, but the Movement speed is in
	 * the other direction.
	 */
	@Test
	public void TestHMovementInverseMovement(){
		Alien a = new Alien(7, 33, g);
		a.setMovementSpeed(-5);
		
		assertEquals(false,g.getupdateLogic());
		
		a.HMovement();
		assertEquals((double) 2, a.getX(), 0.0001);
		assertEquals(false,g.getupdateLogic());
	}
	
	/**
	 * The JUnit test of VMovement where the Alien has not reached 
	 * the bottom of the screen.
	 */
	@Test
	public void TestVMovement(){
		Alien a = new Alien(7, 30, g);
        g.setrunning(true);
		
		a.VMovement();
		assertEquals((double) 50, a.getY(), 0.0001);
		assertEquals(true,g.getrunning());

	}
	
	@Test
	public void TestShoot(){
	    Alien a = new Alien(7, 30, g);
	    Bullet aBullet = a.shoot();
	    
	    SpriteSheet ss = new SpriteSheet(g.getSpriteSheet());
	    
	    Bullet testBullet = new Bullet(12,32,ss);
	    
	    assertEquals(aBullet,testBullet);
	    assertNotSame(aBullet,testBullet);
	}
	
	@Test
	public void TestIfHit(){
	    Alien a = new Alien(7, 30, g);
	    Vector<Bullet> shipBullets = new Vector<Bullet>(0);
        
	    //assert that the ifhit method returns -1 if
	    //the vector is empty.
	    assertEquals(a.ifHit(shipBullets),-1);
	    
	    SpriteSheet ss = new SpriteSheet(g.getSpriteSheet());
        
        Bullet testBullet = new Bullet(7,30,ss);
        shipBullets.add(testBullet);
        
        //test that ifhit method results in 0 
        //if the alien is hit by the first bullet from the bullet vector.
        assertEquals(a.ifHit(shipBullets),0);
        
	    
	}
	
	
}
