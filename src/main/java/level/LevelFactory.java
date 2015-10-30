package level;

import spaceinvaders.Game;

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

		Game.logfile.writeString("Level " + number + " created");

		return level;
	}
}
