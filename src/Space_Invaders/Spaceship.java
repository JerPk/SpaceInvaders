package Space_Invaders;

public class Spaceship {

	// the x and y coordinates of the Spaceship.
	private int x;
  private static int y;

  /**
   * this method makes the ship move left by 1
   * as long as it hasn't reached the border
   *
   */
  public void moveLeft() {
    if (x > 10) {
      x-= 1;
    }
  }

  /**
   * this method makes the ship move right by 1
   * as long as it hasn't reached the border
   *
   */
  public void moveRight() {
    if (x < 590) {
      x += 1;
    }
  }

	/**
	 * the method that returns the x position
	 *
	 * @return x position
	 */
	public int getPosX(){
		return x;
	}

	/**
	 * the method that returns the y position
	 *
	 * @return y position
	 */
	public int getPosY(){
		return y;
	}
}
