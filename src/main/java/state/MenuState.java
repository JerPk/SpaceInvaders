package state;

import spaceinvaders.spaceinvaders_framework.Menu;

public class MenuState implements State{
	
	Executor exec;
	Menu menu;
	
	public MenuState(Executor ex) {
		this.exec = ex;
		menu = new Menu(exec);
	}

	public void start() {
		exec.setState(exec.getGameState());
	}
	
	public void run() {
		menu.runMenu();
	}
	
	public void scores() {
		exec.setState(exec.getHighScoreState());
		exec.run();
	}
	
	public void returning() {
		//Do nothing
	}
	
	public void quit() {
		System.exit(1);
	}
}
