package state;

import spaceinvaders.ScoreMenu;

public class HighScoreState implements State {
	
	Executor exec;
	ScoreMenu menu;
	
	public HighScoreState(Executor ex) {
		this.exec = ex;
		menu = new ScoreMenu(exec);
	}
	
	public void start() {
		//Add new game button?
		exec.setState(exec.getGameState());
	}
	
	public void run() {
		menu.show();
	}
	
	public void scores() {
		//Do nothing
	}
	
	public void returning() {
		exec.setState(exec.getMenuState());
		exec.run();
	}
	
	public void quit() {
		System.exit(1);
	}

}
