package spaceinvaders.spaceinvaders_framework.Level;

import spaceinvaders.spaceinvaders_framework.Alien;
import spaceinvaders.spaceinvaders_framework.AlienFactory;
import spaceinvaders.spaceinvaders_framework.Barrier;
import spaceinvaders.spaceinvaders_framework.Game;
import spaceinvaders.spaceinvaders_framework.Spaceship;
import spaceinvaders.spaceinvaders_framework.SpriteSheet;

public class StandardLevel extends Level {

	public StandardLevel(int number, Spaceship spaceship, Game g) {
		super(number, spaceship, g);

		game = g;
		levelNumber = number;

		game.aliens.clear();
		game.barriers.clear();
		game.alienBullets.clear();
		game.shipBullets.clear();
		spaceship.resetPosition();

		CreateAliens();
		CreateBarriers();
	}

	@Override
	public void CreateAliens() {
		int startYOffsetAlien = 0;
		int startXOffsetAlien = 75;
		String alienType = "";

		if (levelNumber > 0 && levelNumber <= 5) {
			alienType = "easy";
		} else if (levelNumber > 5 && levelNumber <= 10) {
			alienType = "normal";
		} else if (levelNumber > 10 && levelNumber <= 15) {
			alienType = "hard";
		}

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 18; j++) {
				Alien alien = AlienFactory.getAlien(alienType, startXOffsetAlien + (25 * j) - 3, startYOffsetAlien,
						game);
				game.aliens.addElement(alien);
			}
			startYOffsetAlien += 25;
		}
	}

	@Override
	public void CreateBarriers() {
		int numberOfBariers = 0;

		if (levelNumber % 5 != 0) {
			numberOfBariers = 5 - (levelNumber % 5);
		}

		for (int i = 1; i <= numberOfBariers; i++) {
			game.barriers.addElement(new Barrier(game.WIDTH / (numberOfBariers + 1) * i - 22, 370,
					new SpriteSheet(game.getSpriteSheet())));
		}
	}
}
