package state;

import spaceinvaders.spaceinvaders_framework.Game;

public class GameState implements State {

	Executor exec;
	Game game;
	
	public GameState(Executor ex) {
		this.exec = ex;
	}
	
	public void start() {
		//Do nothing
	}
	
	public void run() {
		game = new Game();
		game.start();
	}
	
	public void scores() {
		//Do nothing
	}
	
	public void returning() {
		//End game and return to menu
	}
	
	public void quit() {
		//Do nothing
	}	
	
}
