package level;

import java.util.Vector;

import Alien.Alien;
import Alien.AlienFactory;
import spaceinvaders.spaceinvaders_framework.Barrier;
import spaceinvaders.spaceinvaders_framework.Game;
import spaceinvaders.spaceinvaders_framework.SpriteSheet;

public class StandardLevel extends Level {

	public StandardLevel(int number, Game g) {
		super(number, g);

		game = g;
		levelNumber = number;
	}

	@Override
	public Vector<Alien> createAliens() {
		int startYOffsetAlien = 0;
		int startXOffsetAlien = 75;
		Vector<Alien> tempAliens = new Vector<Alien>(0);
		String alienType = "";

		if (levelNumber > 0 && levelNumber <= 5) {
			alienType = "easy";
		} else if (levelNumber > 5 && levelNumber <= 10) {
			alienType = "normal";
		} else if (levelNumber > 10 && levelNumber <= 15) {
			alienType = "hard";
		}

		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < 1; j++) {
				Alien alien = AlienFactory.getAlien(alienType, startXOffsetAlien + (25 * j) - 3, startYOffsetAlien,
						game);
				tempAliens.addElement(alien);
			}
			startYOffsetAlien += 25;
		}
		
		return tempAliens;
	}

	@Override
	public Vector<Barrier> createBarriers() {
		Vector<Barrier> tempBarriers = new Vector<Barrier>(0);
		int numberOfBariers = 0;

		if (levelNumber % 5 != 0) {
			numberOfBariers = 5 - (levelNumber % 5);
		}

		for (int i = 1; i <= numberOfBariers; i++) {
			tempBarriers.addElement(new Barrier(game.WIDTH / (numberOfBariers + 1) * i - 22, 370,
					new SpriteSheet(game.getSpriteSheet())));
		}
		
		return tempBarriers;
	}
}
