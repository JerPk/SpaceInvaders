package state;

public class MenuState implements State{
	
	Executor exec;
	
	public MenuState(Executor ex) {
		this.exec = ex;
	}

	public void start() {
		exec.setState(exec.getGameState());
	}
	
	public void scores() {
		exec.setState(exec.getHighScoreState());
	}
	
	public void returning() {
		//Do nothing
	}
	
	public void quit() {
		//Quit system
	}
}
