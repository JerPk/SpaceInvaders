package spaceinvaders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class ScoreTest {

  /**
   * test method for the constructor of score.
   */
  @Test
  public void testScoreConstructor() {
    Score score1 = new Score("name", 90);
    assertNotNull(score1);
    assertEquals(score1.getName(), "name");
    assertEquals(score1.getScore(), 90);
  }

  /**
   * test method for the getname method.
   */
  @Test
  public void testgetName() {
    Score score1 = new Score("name", 90);
    assertEquals(score1.getName(), "name");
  }

  /**
   * test method for the getscore method.
   */
  @Test
  public void testgetScore() {
    Score score1 = new Score("name", 90);
    assertEquals(score1.getScore(), 90);
  }
}
