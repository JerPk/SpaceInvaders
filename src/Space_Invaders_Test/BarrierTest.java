package Space_Invaders_Test;

import static org.junit.Assert.assertEquals;

import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import Space_Invaders.Barrier;
import Space_Invaders.Bullet;
import Space_Invaders.Game;
import Space_Invaders.Spaceship;
import Space_Invaders.SpriteSheet;

public class BarrierTest {
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
     * the JUnit test of the Constuctor method of Barrier.
     */
    @Test
    public void testBarrier() {
        Barrier b = new Barrier(3.0, 3.0, new SpriteSheet(g.getSpriteSheet()));

        assertEquals(b.getPosX(), 3.0, 0.00001);
        assertEquals(b.getPosY(), 3.0, 0.00001);
        assertEquals(b.getState(), 0);

    }

    /**
     * The JUnit test of the IfHit method of Barrier.
     */
    @Test
    public void testIfHit() {
        Barrier b = new Barrier(3.0, 3.0, new SpriteSheet(g.getSpriteSheet()));
        Vector<Bullet> alienBullets = new Vector<Bullet>(0);

        assertEquals(b.ifHit(alienBullets), -1);

        SpriteSheet ss = new SpriteSheet(g.getSpriteSheet());

        Bullet testBullet = new Bullet(20, 15, ss);
        alienBullets.add(testBullet);
        
        assertEquals(b.ifHit(alienBullets),0);

    }
    
    /**
     * The JUnit test of the decreaseState method of Barrier.
     */
    @Test
    public void testDecreaseState(){
        Barrier b = new Barrier(3.0, 3.0, new SpriteSheet(g.getSpriteSheet()));
        assertEquals(b.getState(),0);
        b.decreaseState();
        assertEquals(b.getState(),1);
    }
    
    /**
     * The JUnit test of the getPosX method of Barrier.
     */
    @Test
    public void testGetPosX(){
        Barrier b = new Barrier(3.0, 5.0, new SpriteSheet(g.getSpriteSheet()));
        assertEquals(b.getPosX(),3.0,0.00001);
    }
    
    /**
     * The JUnit test of the getPosY method of Barrier.
     */
    @Test
    public void testGetPosY(){
        Barrier b = new Barrier(3.0, 5.0, new SpriteSheet(g.getSpriteSheet()));
        assertEquals(b.getPosY(),5.0,0.00001);
    }
    
    /**
     * The JUnit test of the getState method of Barrier.
     */
    @Test
    public void testGetState(){
        Barrier b = new Barrier(3.0, 5.0, new SpriteSheet(g.getSpriteSheet()));
        assertEquals(b.getState(),0);
    }
}
