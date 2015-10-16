package spaceinvaders.spaceinvaders_framework;


public class LevelFactory {
	public static Level createLevel(int number, Spaceship spaceship, Game g) {
		Level level;
		
		level = new Level(number, spaceship, g);
		Game.logfile.writeString("Level " + number + " created");
		
		return level;
	}
}
