package spaceinvaders.spaceinvaders_framework;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import bullet.Bullet;
import state.Executor;
import alien.Alien;
import alien.AlienFactory;
import alien.BossAlien;

/**
 * AlienTest tests all the methods in the alien class.
 * 
 * @author Group 23
 */
public class AlienTest {

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
     * the JUnit test of the Constuctor method of Alien.
     */
    @Test
    public void testConstructorAlien() {

        final Alien alien = AlienFactory.getAlien("easy", 3, 3);
        assertEquals((double) 3, alien.getX(), 0.00001);
        assertEquals((double) 3, alien.getY(), 0.00001);

    }

    /**
     * The JUnit test of a successful HMovement.
     */
    @Test
    public void testSuccessfulHMovement() {
        final Alien alien = AlienFactory.getAlien("easy", 33, 33);

        // assertFalse(Alien.getupdateLogic());

        alien.hmovement();
        // assertTrue(Alien.getupdateLogic());
        assertEquals(34, alien.getX(), 0.00001);
    }

    /**
     * The JUnit test of a HMovement where the Alien has reached the rigameht
     * hand Border.
     */
    @Test
    public void testHMovementRightBorder() {
        final Alien alien = AlienFactory.getAlien("easy", 643, 33);

        assertFalse(Alien.getupdateLogic());

        alien.hmovement();
        assertEquals((double) 644, alien.getX(), 0.0001);
        assertTrue(Alien.getupdateLogic());
    }

    /**
     * The JUnit test of a HMovement where the Alien has reached the left hand
     * Border.
     */
    @Test
    public void testHMovementLeftBorder() {
        final Alien alien = AlienFactory.getAlien("easy", 2, 33);
        alien.setMovementSpeed(-4);

        // assertFalse(Alien.getupdateLogic());

        alien.hmovement();
        assertEquals((double) -2, alien.getX(), 0.0001);
        // assertFalse(Alien.getupdateLogic());
    }

    /**
     * The JUnit test of a HMovement where the Alien has not reached reached the
     * left hand Border, but the Movement speed is in the other direction.
     */
    @Test
    public void testHMovementInverseMovement() {
        final Alien alien = AlienFactory.getAlien("easy", 7, 33);
        alien.setMovementSpeed(-5);

        // assertFalse(Alien.getupdateLogic());

        alien.hmovement();
        assertEquals((double) 2, alien.getX(), 0.0001);
        // assertFalse(Alien.getupdateLogic());
    }

    /**
     * The JUnit test of VMovement where the Alien has not reached the bottom of
     * the screen.
     */
    @Test
    public void testVMovement() {
        final Alien alien = AlienFactory.getAlien("easy", 7, 30);
        game.setRunning(true);

        alien.vmovement();
        assertEquals((double) 50, alien.getY(), 0.0001);
        assertTrue(game.getRunning());

    }

    @Test
    public void testShoot() {
        final Alien alien = AlienFactory.getAlien("easy", 7, 30);
        final Bullet bullet = alien.shoot();

        final Bullet testBullet = new Bullet(12, 32);

        assertEquals(bullet, testBullet);
        assertNotSame(bullet, testBullet);
    }

    @Test
    public void testIfHit() {
        final Alien alien = AlienFactory.getAlien("easy", 7, 30);
        final Vector<Bullet> shipBullets = new Vector<Bullet>(0);

        // assert that the ifhit method returns -1 if
        // the vector is empty.
        assertEquals(alien.ifHit(shipBullets), -1);

        final Bullet testBullet = new Bullet(7, 30);
        shipBullets.add(testBullet);

        // test that ifhit method results in 0
        // if the alien is hit by the first bullet from the bullet vector.
        assertEquals(alien.ifHit(shipBullets), 0);

    }

    @Test
    public void testGetScore() {
        final Alien alien = AlienFactory.getAlien("easy", 7, 30);
        assertEquals(alien.getScore(), 10);
    }

    @Test
    public void testGetHealth() {
        final Alien alien = AlienFactory.getAlien("easy", 7, 30);
        assertEquals(alien.getHealth(), 1);
    }

    @Test
    public void testAlienFactory() {
        final Alien alien = AlienFactory.getAlien("Nonesense", 7, 30);
        assertEquals(alien, null);
    }

    @Test
    public void testShootAlienType2() {
        final Alien alien = AlienFactory.getAlien("normal", 7, 30);
        final Bullet bullet = alien.shoot();

        final Bullet testBullet = new Bullet(12, 32);

        assertEquals(bullet, testBullet);
        assertNotSame(bullet, testBullet);
    }

    @Test
    public void testShootAlienType3() {
        final Alien alien = AlienFactory.getAlien("hard", 7, 30);
        final Bullet bullet = alien.shoot();

        final Bullet testBullet = new Bullet(12, 32);

        assertEquals(bullet, testBullet);
        assertNotSame(bullet, testBullet);
    }

    // @Test
    // public void testMegaBulletBossAlien() {
    // final BossAlien alien = (BossAlien) AlienFactory.getAlien("boss", 7, 30);
    // final Vector<Bullet> bullet = alien.MegaBullet();
    // assertEquals(bullet.size(), 1);
    //
    // }

    // @Test
    // public void testSpeedBulletBossAlien() {
    // final BossAlien alien = (BossAlien) AlienFactory.getAlien("boss", 7, 30);
    // final Vector<Bullet> bullet = alien.multipleSpeedBullets();
    // assertEquals(bullet.size(), 5);
    // }

    // @Test
    // public void testTridentBulletBossAlien() {
    // final BossAlien alien = (BossAlien) AlienFactory.getAlien("boss", 7, 30);
    // final Vector<Bullet> bullet = alien.tridentBullets();
    // assertEquals(bullet.size(), 3);
    //
    // }

    @Test
     public void testHmovementBossAlien() {
     final Alien alien = AlienFactory.getAlien("boss", 33, 33);
    
     assertFalse(Alien.getupdateLogic());
    
     alien.hmovement();
     assertFalse(Alien.getupdateLogic());
     assertEquals(34, alien.getX(), 0.00001);
     }

    /**
     * The JUnit test of a HMovement where the BossAlien has reached the
     * left hand Border.
     */
    @Test
    public void testHMovementRightBorderBossAlien() {
        final Alien alien = AlienFactory.getAlien("boss", 643, 33);

        // assertFalse(Alien.getupdateLogic());

        alien.hmovement();
        assertEquals((double) 644, alien.getX(), 0.0001);
        assertTrue(Alien.getupdateLogic());
    }
    
    /**
     * The JUnit test of VMovement where the BossAlien has not reached the
     * bottom of the screen.
     */
    @Test
    public void testVMovementBossAlien() {
        final Alien alien = AlienFactory.getAlien("boss", 7, 30);
        game.setRunning(true);

        alien.vmovement();
        assertEquals((double) 70, alien.getY(), 0.0001);
        assertTrue(game.getRunning());

    }

    @Test
    public void testBossShoot1() {
        final BossAlien alien = (BossAlien) AlienFactory
                .getAlien("boss", 7, 30);
        alien.setRand(1);
        final Vector<Bullet> bullet = alien.BossShoot();
        assertEquals(bullet.size(), 3);
    }

    @Test
    public void testBossShoot2() {
        final BossAlien alien = (BossAlien) AlienFactory
                .getAlien("boss", 7, 30);
        alien.setRand(2);
        final Vector<Bullet> bullet = alien.BossShoot();
        assertEquals(bullet.size(), 5);
    }

    @Test
    public void testBossShoot3() {
        final BossAlien alien = (BossAlien) AlienFactory
                .getAlien("boss", 7, 30);
        alien.setRand(3);
        final Vector<Bullet> bullet = alien.BossShoot();
        assertEquals(bullet.size(), 1);
    }

    @Test
    public void testBossifHit() {
    
        final BossAlien alien = (BossAlien) AlienFactory
                .getAlien("boss", 7, 30);
        Vector<Bullet> bulletv = new Vector<Bullet>();
        Bullet bill = new Bullet(7, 30);
        int nothit = alien.ifHit(bulletv);
        assertEquals(nothit,-1);
        bulletv.add(bill);
        int hit = alien.ifHit(bulletv);
        assertEquals(hit,0);
        
    }
    
    @Test
    public void testSetY() {
        final Alien alien = AlienFactory
                .getAlien("easy", 7, 30);
        
        assertEquals(alien.getY(), 30, 0.001);
        alien.setY(35);
        assertEquals(alien.getY(), 35, 0.001);   
    }
    
    @Test
    public void testaddScore() {
        final Alien alien = AlienFactory
                .getAlien("easy", 7, 30);
        
        assertEquals(alien.addScore(5), 15);
    }
    
    @Test
    public void testDefeated() {
        final Alien alien = AlienFactory
                .getAlien("easy", 7, 30);
        
        assertFalse(alien.defeated());
        
        alien.setHealth(0);
        assertTrue(alien.defeated());
        
    }
    
    @Test
    public void testReachedY() {
        final Alien alien = AlienFactory
                .getAlien("easy", 7, 30);
        
        assertFalse(alien.reachedY(35));
       
        assertTrue(alien.reachedY(5));
        
    }
    
}
