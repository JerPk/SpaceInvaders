package level;

import spaceinvaders.spaceinvaders_framework.Game;

public class LevelFactory {
	public static Level createLevel(int number) {
		Level level;

		if (number % 5 == 0) {
			level = new BossLevel(number);
		}else if (number <= 15) {
			level = new StandardLevel(number);
		} else {
			level = new Level(number);
		}

		Game.logfile.writeString("Level " + number + " created");

		return level;
	}
}
