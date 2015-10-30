package state;

import spaceinvaders.Game;

public class GameState implements State {

  Executor exec;
  Game game;

  public GameState(Executor ex) {
    this.exec = ex;
  }

  public void start() {
    // Do nothing
  }

  public void run() {
    game = new Game(exec);
    game.start();
  }

  public void scores() {
    // Do nothing
  }

  public void returning() {
    exec.setState(exec.getHighScoreState());
    exec.run();
  }

  public void quit() {
    // Do nothing
  }

}
