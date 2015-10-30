package level;

import spaceinvaders.Game;
import spaceinvaders.LogFile;

/**
 * Class of the level factory.
 * 
 * @author Group 23 (TI2206)
 */
public class LevelFactory {

	/**
	 * Method to create a level.
	 * 
	 * @param number
	 *            Level number
	 * @return the created level
	 */
	public static Level createLevel(final int number) {
		Level level;

		if (number % 5 == 0) {
			level = new BossLevel(number);
		} else if (number <= 15) {
			level = new StandardLevel(number);
		} else {
			level = new Level(number);
		}

		LogFile.getInstance().writeString("Level " + number + " created");

		return level;
	}
}
