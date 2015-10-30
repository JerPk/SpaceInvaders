package spaceinvaders;

//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertNotSame;
//import static org.junit.Assert.assertTrue;
import static org.junit.Assert.*;

import java.util.Vector;

import javax.swing.JPanel;

import org.junit.Before;
import org.junit.Test;

import alien.Alien;
import level.Level;
import level.LevelFactory;
import spaceinvaders.Barrier;
import spaceinvaders.Game;
import state.Executor;

/**
 * LevelTest tests all the methods in the level class.
 * 
 * @author Group 23
 */
public class LevelTest {

	/**
	 * The game object that is used in all of the test cases.
	 */
//	private Game game;
//	private Executor exec;

	/**
	 * This method is executed before every test. It creates the game class and
	 * sets up the required spritesheet.
	 */
	@Before
	public void setUpGame() {
//		exec = new Executor();
//		exec.run();
//		game = new Game(exec);
//		game.init();
		LogFile.getInstance().open();
		HighscoreManager.getInstance();
	}

	/**
	 * The JUnit test of the createAliens method of level.
	 */
	@Test
	public void testCreateAliens() {
		Level level = LevelFactory.createLevel(1);
		Vector<Alien> aliens = level.createAliens();
		assertEquals(3 * 18, aliens.size());
		aliens.clear();

		level = LevelFactory.createLevel(5);
		aliens = level.createAliens();
		assertEquals(1, aliens.size());
		aliens.clear();

		level = LevelFactory.createLevel(6);
		aliens = level.createAliens();
		assertEquals(3 * 18, aliens.size());
		aliens.clear();

		level = LevelFactory.createLevel(11);
		aliens = level.createAliens();
		assertEquals(3 * 18, aliens.size());
		aliens.clear();

		level = LevelFactory.createLevel(16);
		aliens = level.createAliens();
		assertEquals(3 * 18, aliens.size());
	}

	/**
	 * The JUnit test of the createBarriers method of level.
	 */
	@Test
	public void testCreateBarriers() {
		Level level = LevelFactory.createLevel(1);
		Vector<Barrier> barriers = level.createBarriers();
		assertEquals(4, barriers.size());
		barriers.clear();

		level = LevelFactory.createLevel(5);
		barriers = level.createBarriers();
		assertEquals(0, barriers.size());
		barriers.clear();

		level = LevelFactory.createLevel(16);
		barriers = level.createBarriers();
		assertEquals(4, barriers.size());
	}
	
	/**
	 * The JUnit test of the determineAlienType method of level.
	 */
	@Test
	public void testDetermineAlienType() {
		Level level = LevelFactory.createLevel(1);
		JPanel panel = level.createTransitionPanel();
		assertNotNull(panel);
		
		level = LevelFactory.createLevel(5);
		panel = level.createTransitionPanel();
		assertNotNull(panel);
		
		level = LevelFactory.createLevel(6);
		panel = level.createTransitionPanel();
		assertNotNull(panel);
		
		level = LevelFactory.createLevel(11);
		panel = level.createTransitionPanel();
		assertNotNull(panel);
	}

}
