package level;

import spaceinvaders.spaceinvaders_framework.Game;
import spaceinvaders.spaceinvaders_framework.Spaceship;

public class LevelFactory {
	public static Level createLevel(int number, Game g) {
		Level level;

		if (number <= 15) {
			level = new StandardLevel(number, g);
		} else {
			level = new Level(number, g);
		}

		Game.logfile.writeString("Level " + number + " created");

		return level;
	}
}
