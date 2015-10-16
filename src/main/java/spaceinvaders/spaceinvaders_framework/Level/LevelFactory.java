package spaceinvaders.spaceinvaders_framework.Level;

import spaceinvaders.spaceinvaders_framework.Game;
import spaceinvaders.spaceinvaders_framework.Spaceship;

public class LevelFactory {
	public static Level createLevel(int number, Spaceship spaceship, Game g) {
		Level level;

		if (number <= 15) {
			level = new StandardLevel(number, spaceship, g);
		} else {
			level = new Level(number, spaceship, g);
		}

		Game.logfile.writeString("Level " + number + " created");

		return level;
	}
}
