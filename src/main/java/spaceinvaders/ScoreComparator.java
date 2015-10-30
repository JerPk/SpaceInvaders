package spaceinvaders;

import java.util.Comparator;

/**
 * ScoreComparator is a class that is used mainly in the HighscoreManager class.
 * it is used to compare two score objects.
 * 
 * @author Group 23
 */
public class ScoreComparator implements Comparator<Score> {
       
    /**
     * the compare method of the scorecomparator. if score1 is smaller
     *  than score2 return 1. if score1 is bigger return -1. if the two
     *  scores are equal return 0.
     */
    public int compare(Score score1, Score score2) {

            int sc1 = score1.getScore();
            int sc2 = score2.getScore();

            if (sc1 > sc2){
                return -1;
            }else if (sc1 < sc2){
                return 1;
            }else{
                return 0;
            }
        }
}