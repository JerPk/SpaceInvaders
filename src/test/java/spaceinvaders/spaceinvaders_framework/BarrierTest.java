package spaceinvaders.spaceinvaders_framework;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import bullet.Bullet;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Vector;
/**
 * BarrierTest tests all the methods in the Barrier class.
 * 
 * @author Group 23
 *
 */
public class BarrierTest {
    
    /**
     * The game object that is used in all of the test cases.
     */ 
  private Game game;
  
  protected BufferedImage BImg = null;

  /**
   * This method is executed before every test.
   * It creates the game class and sets up the required spritesheet.
   */
  @Before
    public void setUpGame() {
    game = new Game();
    game.init();
    
    BuffereImageLoader loader = new BuffereImageLoader();
    try {
        BImg = loader.LoadImage("res/sprite_sheet.png");
    } catch (IOException e) {
        e.printStackTrace();
    }
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

    assertEquals(barrier.ifHit(alienBullets), -1);

    final SpriteSheet spritesheet = new SpriteSheet(BImg);

    final Bullet testBullet = new Bullet(20, 15, spritesheet);
    alienBullets.add(testBullet);
        
    assertEquals(barrier.ifHit(alienBullets),0);

  }
    
    /**
     * The JUnit test of the getPosX method of Barrier.
     */
  @Test
    public void testGetPosX() {
    final Barrier barrier = new Barrier(3.0, 5.0);
    assertEquals(barrier.getPosX(),3.0,0.00001);
  }
    
    /**
     * The JUnit test of the getPosY method of Barrier.
     */
  @Test
    public void testGetPosY() {
    final Barrier barrier = new Barrier(3.0, 5.0);
    assertEquals(barrier.getPosY(),5.0,0.00001);
  }
    
    /**
     * The JUnit test of the getState method of Barrier.
     */
  @Test
    public void testGetState() {
    final Barrier barrier = new Barrier(3.0, 5.0);
    assertEquals(barrier.getState(),0);
  }
}
