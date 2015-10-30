package spaceinvaders;

import java.io.Serializable;

/**
 * The score class is used to store the highscores. it stores both the name of
 * the player and its score in a score object.
 * 
 * @author Group 23
 *
 */
public class Score implements Serializable {

  private int score;
  private String name;

  /**
   * The getscore method returns the the amount of points the player got as a
   * integer.
   * 
   * @return score
   */
  public int getScore() {
    return score;
  }

  /**
   * The getname method returns the the name the player got as a string.
   * 
   * @return name
   */
  public String getName() {
    return name;
  }

  /**
   * The constructor method of the score class.
   * 
   * @param name
   *          of the person who gained this score
   * @param score
   *          amount scored
   */
  public Score(String name, int score) {
    this.score = score;
    this.name = name;
  }

}
