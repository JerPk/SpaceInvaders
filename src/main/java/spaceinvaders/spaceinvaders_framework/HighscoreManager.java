package spaceinvaders.spaceinvaders_framework;

import java.util.*;
import java.io.*;

/**
 * The HihgscoreManager is the main class in terms of reading and writing the
 * highscores from a file(scores.dat to be precise) to an arraylist that will be
 * used by the game.
 * 
 * @author Group 23
 *
 */
public class HighscoreManager {

    /**
     * An arraylist of the type "score" that will be used to get all the scores
     * in the game class.
     */
    private ArrayList<Score> scores;

    /**
     * Initialising an outputStream for working with the file
     */
    ObjectOutputStream outputStream = null;

    /**
     * Initialising an inputStream for working with the file
     */
    ObjectInputStream inputStream = null;

    /**
     * the HighscoreManager is the main class in terms of the scores. it
     * contains the methods that are used to read and write to the
     * highscores.dat file which contain all the highscores.
     */
    public HighscoreManager() {
        scores = new ArrayList<Score>();
    }

    /**
     * returns an arraylist of scores this arraylist contains the score objects
     * that are associated with the games highscores.
     * 
     * @return ArrayList<Score>
     */
    public ArrayList<Score> getScores() {
        loadScoreFile();
        sort();
        return scores;
    }

    /**
     * sort method uses java collections.sort and a scorecomparator object to
     * sort the scores so that the top score is at the top and the bottom score
     * is at the bottom.
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
     * @param String
     *            name
     * @param Int
     *            score
     */
    public void addScore(String name, int score) {
        loadScoreFile();
        scores.add(new Score(name, score));
        updateScoreFile();
    }

    /**
     * This is the method that is used to load all the scores from the
     * scores.dat file into a HighscoreManager Object.
     */
    public void loadScoreFile() {
        try {
            // Tries to read the file into the scores arraylist.
            inputStream = new ObjectInputStream(new FileInputStream(
                    "res/scores.dat"));
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
     * method that sets the outputstream. this method is only used for Testing purposes.
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void setOutputstream() throws FileNotFoundException, IOException{
        outputStream = new ObjectOutputStream(new FileOutputStream(
                "res/scores.dat"));
    }
}