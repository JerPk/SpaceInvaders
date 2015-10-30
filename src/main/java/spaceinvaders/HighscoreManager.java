package spaceinvaders;

import java.io.*;
import java.util.*;

import javax.swing.JOptionPane;


/**
 * The HihgscoreManager is the main class in terms of reading and writing the
 * highscores from a file(scores.dat to be precise) to an arraylist that will be
 * used by the game.
 * 
 * @author Group 23
 *
 */
public class HighscoreManager {

  private static HighscoreManager uniqueInstance;

  /**
   * An arraylist of the type "score" that will be used to get all the scores in
   * the game class.
   */
  private ArrayList<Score> scores;

  /**
   * Initialising an outputStream for working with the file
   */
  private ObjectOutputStream outputStream = null;

  /**
   * Initialising an inputStream for working with the file
   */
  private ObjectInputStream inputStream = null;

  /**
   * the HighscoreManager is the main class in terms of the scores. it contains
   * the methods that are used to read and write to the highscores.dat file
   * which contain all the highscores.
   */
  private HighscoreManager() {
    scores = new ArrayList<Score>();
    loadScoreFile();
  }

  public static synchronized HighscoreManager getInstance() {
    if (uniqueInstance == null) {
      uniqueInstance = new HighscoreManager();
    }
    return uniqueInstance;
  }

  /**
   * returns an arraylist of scores this arraylist contains the score objects
   * that are associated with the games highscores.
   * 
   * @return ArrayList return the array containing all the scores
   */
  public ArrayList<Score> getScores() {
    loadScoreFile();
    sort();
    return scores;
  }

  /**
   * sort method uses java collections.sort and a scorecomparator object to sort
   * the scores so that the top score is at the top and the bottom score is at
   * the bottom.
   */
  private void sort() {
    ScoreComparator comparator = new ScoreComparator();
    Collections.sort(scores, comparator);

  }

  /**
   * addscore method calls the loadscorefile which loads the score file then
   * adds a new score to it and calls updatescorefile to store the new
   * scoreslist.
   * 
   */
  public void addScore(String name, int score) {
    loadScoreFile();
    scores.add(new Score(name, score));
    updateScoreFile();
  }

  /**
   * The addscore method compares the players score to the 10 object in the
   * scores array. If the player scores higher the object is added to the array.
   */
  public void addScore(int score) {
    Score lowestTop10score;
    if (scores.size() == 0) {
      lowestTop10score = new Score("New", 0);
    } else if (scores.size() < 10) {
      lowestTop10score = scores.get(scores.size() - 1);
    } else {
      lowestTop10score = scores.get(9);
    }
    if (score >= lowestTop10score.getScore()) {
      String name = JOptionPane
          .showInputDialog("Congratulations, you are on the leaderboards! What is your name?");
      addScore(name, score);
    }
  }

  /**
   * This is the method that is used to load all the scores from the scores.dat
   * file into a HighscoreManager Object.
   */
  public void loadScoreFile() {
    try {
      // Tries to read the file into the scores arraylist.
      inputStream = new ObjectInputStream(new FileInputStream("res/scores.dat"));
      scores = (ArrayList<Score>) inputStream.readObject();
    } catch (Exception e) {
      System.out.println("something went wrong while loading");
      e.printStackTrace();
    } finally {
      // After it has read the file the method tries to close the
      // outputstream.
      try {
        if (outputStream != null) {
          outputStream.flush();
          outputStream.close();
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * This is the method that is used to write the highscores to the scores.dat
   * file.
   */
  public void updateScoreFile() {
    try {
      // Tries to write to the scores.dat file
      outputStream = new ObjectOutputStream(new FileOutputStream(
          "res/scores.dat"));
      outputStream.writeObject(scores);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        // After the writing is done the outputstream is tried to be
        // closed.
        if (outputStream != null) {
          outputStream.flush();
          outputStream.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * method that clears the highscores.
   */
  public void clear() {
    scores.clear();
    updateScoreFile();
    for (int i = 0; i < 10; i++) {
      addScore("", 0);
    }
    updateScoreFile();
  }

  /**
   * method that sets the outputstream. this method is only used for Testing
   * purposes.
   */
  public void setOutputstream() throws FileNotFoundException, IOException {
    outputStream = new ObjectOutputStream(
        new FileOutputStream("res/scores.dat"));
  }

}