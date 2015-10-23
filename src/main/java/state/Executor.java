package state;

public class Executor {
	
	private State menuState;
	private State gameState;
	private State highScoreState;
	
	private State state;
	
	public Executor() {
		menuState = new MenuState(this);
		gameState = new GameState(this);
		highScoreState = new HighScoreState(this);
		state = menuState;
	}
	
	public void start() {
		state.start();
	}
	
	public void run() {
		System.out.println("exec run");
		state.run();
	}
	
	public void scores() {
		state.scores();
	}
	
	public void quit() {
		state.quit();
	}
	
	public void returning() {
		state.returning();
	}
	
	public State getState() {
		return state;
	}
	
	public void setState(State newState) {
		state = newState;
	}
	
	public State getGameState() {
		return gameState;
	}
	
	public State getHighScoreState() {
		return highScoreState;
	}
	
	public State getMenuState() {
		return menuState;
	}
	

}
