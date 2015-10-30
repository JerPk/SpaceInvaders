package spaceinvaders.spaceinvaders_framework;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import state.Executor;
import state.State;

public class StateTest {

	/**
	 * Junit test of the start method of the executor.
	 */
	@Test
	public void testMenuState() {
		Executor exec = new Executor();
		exec.setState(exec.getMenuState());
		exec.run();
		assertEquals(exec.getMenuState(), exec.getState());
		
		
		exec.setState(exec.getMenuState());
		exec.start();
		assertEquals(exec.getGameState(), exec.getState());
		
		exec.setState(exec.getMenuState());
		exec.scores();
		assertEquals(exec.getHighScoreState(), exec.getState());
		
		exec.setState(exec.getMenuState());
		exec.returning();
		assertEquals(exec.getMenuState(), exec.getState());
	}
	
	/**
	 * Junit test of the start method of the executor.
	 */
	@Test
	public void testGameState() {
		Executor exec = new Executor();
		exec.setState(exec.getGameState());
		exec.run();
		assertEquals(exec.getGameState(), exec.getState());
		
		exec.setState(exec.getGameState());
		exec.start();
		assertEquals(exec.getGameState(), exec.getState());
		
		exec.setState(exec.getGameState());
		exec.scores();
		assertEquals(exec.getGameState(), exec.getState());
		
		exec.setState(exec.getGameState());
		exec.returning();
		assertEquals(exec.getHighScoreState(), exec.getState());
	}
	
	/**
	 * Junit test of the start method of the executor.
	 */
	@Test
	public void testHighScoreState() {
		Executor exec = new Executor();
		exec.setState(exec.getHighScoreState());
		exec.run();
		assertEquals(exec.getHighScoreState(), exec.getState());
		
		exec.setState(exec.getHighScoreState());
		exec.start();
		assertEquals(exec.getGameState(), exec.getState());
		
		exec.setState(exec.getHighScoreState());
		exec.scores();
		assertEquals(exec.getHighScoreState(), exec.getState());
		
		exec.setState(exec.getHighScoreState());
		exec.returning();
		assertEquals(exec.getMenuState(), exec.getState());
	}
}
