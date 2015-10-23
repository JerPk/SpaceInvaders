package state;

public class HighScoreState implements State {
	
	Executor exec;
	
	public HighScoreState(Executor ex) {
		this.exec = ex;
	}
	
	public void start() {
		exec.setState(exec.getGameState());
	}
	
	public void scores() {
		//Do nothing
	}
	
	public void returning() {
		//Return to menu
	}
	
	public void quit() {
		//Quit system
	}

}
