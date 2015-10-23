package state;

public class GameState implements State {

	Executor exec;
	
	public GameState(Executor ex) {
		this.exec = ex;
	}
	
	public void start() {
		//Do nothing
	}
	
	public void scores() {
		//End game and show highscores
	}
	
	public void returning() {
		//End game and return to menu
	}
	
	public void quit() {
		//Do nothing
	}	
	
}
