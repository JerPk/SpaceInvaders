package state;

import spaceinvaders.spaceinvaders_framework.ScoreMenu;

public class HighScoreState implements State {
	
	Executor exec;
	ScoreMenu menu;
	
	public HighScoreState(Executor ex) {
		this.exec = ex;
		menu = new ScoreMenu();
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
		//Return to menu
	}
	
	public void quit() {
		System.exit(1);
	}

}
