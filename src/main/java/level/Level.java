package level;

import java.util.Vector;

import spaceinvaders.spaceinvaders_framework.Alien;
import spaceinvaders.spaceinvaders_framework.AlienFactory;
import spaceinvaders.spaceinvaders_framework.Barrier;
import spaceinvaders.spaceinvaders_framework.Game;
import spaceinvaders.spaceinvaders_framework.SpriteSheet;

public class Level {

	Game game;
	int levelNumber;

	/**
	 * constructor of Level class
	 * 
	 * @param number Levelnumber
	 * @param g The game
	 */
	public Level(int number, Game g) {
		game = g;
		levelNumber = number;
	}

	/**
	 * creates the aliens and returns them.
	 * 
	 * @return the created aliens
	 */
	public Vector<Alien> createAliens() {

		int startYOffsetAlien = 0;
		int startXOffsetAlien = 75;
		Vector<Alien> tempAliens = new Vector<Alien>(0);

		for (int x = 0; x < 18; x++) {
			Alien alien = AlienFactory.getAlien("hard", startXOffsetAlien + (25 * x) - 3, startYOffsetAlien, game);
			tempAliens.addElement(alien);
		}

		for (int x = 0; x < 18; x++) {
			Alien alien = AlienFactory.getAlien("normal", startXOffsetAlien + (25 * x) - 3, startYOffsetAlien + 25,
					game);
			tempAliens.addElement(alien);
		}

		for (int x = 0; x < 18; x++) {
			Alien alien = AlienFactory.getAlien("easy", startXOffsetAlien + (25 * x) - 3, startYOffsetAlien + 50, game);
			tempAliens.addElement(alien);
		}
		
		return tempAliens;
	}
	
	/**
	 * creates the four barriers and returns them.
	 * 
	 * @return the created barriers
	 */
	public Vector<Barrier> createBarriers() {
		Vector<Barrier> tempBarriers = new Vector<Barrier>(0);
		
		for (int i = 1; i <= 4; i++) {
			tempBarriers.addElement(new Barrier(game.WIDTH / 5 * i - 22, 370, new SpriteSheet(game.getSpriteSheet())));
		}
		
		return tempBarriers;
	}
}
