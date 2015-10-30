package spaceinvaders.spaceinvaders_framework;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import bullet.Bullet;
import state.Executor;
import alien.Alien;
import java.awt.image.BufferedImage;
import java.util.Vector;

/**
 * GameTest tests almost all the methods in the Game class. Methods not tested
 * are the main game loop and certain GUI related methods.
 * 
 * @author Group 23
 *
 */
public class GameTest {

    /**
     * The game object that is used in all of the test cases.
     */
    private Game game;

    protected BufferedImage BImg = null;
    private Executor exec;

    /**
     *  This method is executed before every test. It creates the game
     * class. //
     */
    @Before
    public void setupGame() {
        exec = new Executor();
        exec.run();
        game = new Game(exec);
    }

    /**
     * The JUnit test of the move aliens method of Game.
     */
    @Test
    public void testMoveAliens() {
        final Game ngame = new Game(exec);
        ngame.init();

        Vector<Alien> alien = ngame.getAlienVector();
        assertEquals(72, alien.get(0).getX(), 0.001);
        ngame.moveAliens();
        assertEquals(73, alien.get(0).getX(), 0.001);

        for (int i = 0; i < 400; i++) {
            ngame.moveAliens();
        }

        Vector<Alien> alien2 = ngame.getAlienVector();
        assertEquals(40, alien2.get(0).getY(), 0.001);

    }

    /**
     * the Junit test of the alien shoot method of game.
     */
    @Test
    public void testAlienShoot() {
        final Game ngame = new Game(exec);
        ngame.init();
        ngame.alienShoot();
        Vector<Bullet> bullet = ngame.getAlienBullets();
        assertEquals(bullet.size(), 1);

    }

    
    /**
     * the Junit test of the remove bullets method of game.
     */
    @Test
    public void testRemoveBullets() {
        game.init();

        final Bullet newBullet = new Bullet(10, 500);
        final Bullet newBullet2 = new Bullet(10, -2);

        game.addAlienBullets(newBullet);
        game.addAlienBullets(newBullet2);

        game.addShipBullets(newBullet);
        game.addShipBullets(newBullet2);

        assertEquals(2, game.getAlienBullets().size());
        assertEquals(2, game.getShipBullets().size());

        game.removeOffScreenBullets();
        assertEquals(1, game.getAlienBullets().size());
        assertEquals(1, game.getShipBullets().size());

    }

    /**
     * the Junit test of the check if hit method of game.
     */
    @Test
    public void testCheckIfHit() {
        game.init();

        final Bullet newBullet = new Bullet(72, 0);
        final Bullet newBullet2 = new Bullet(304, 425);

        game.addAlienBullets(newBullet);
        game.addAlienBullets(newBullet2);

        game.addShipBullets(newBullet);
        game.addShipBullets(newBullet2);

        assertEquals(2, game.getShipBullets().size());
        assertEquals(2, game.getAlienBullets().size());
        game.checkIfHit();
        assertEquals(1, game.getShipBullets().size());
        assertEquals(1, game.getAlienBullets().size());
    }

}
