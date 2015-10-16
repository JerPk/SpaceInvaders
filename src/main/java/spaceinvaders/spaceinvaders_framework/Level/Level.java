package spaceinvaders.spaceinvaders_framework.Level;

import spaceinvaders.spaceinvaders_framework.Alien;
import spaceinvaders.spaceinvaders_framework.AlienFactory;
import spaceinvaders.spaceinvaders_framework.Barrier;
import spaceinvaders.spaceinvaders_framework.Game;
import spaceinvaders.spaceinvaders_framework.Spaceship;
import spaceinvaders.spaceinvaders_framework.SpriteSheet;

public class Level {

	Game game;
	int levelNumber;

	/**
	 * 
	 * @param number
	 * @param spaceship
	 * @param g
	 */
	public Level(int number, Spaceship spaceship, Game g) {
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

	/**
	 * creates all the aliens and adds them to the alien vector.
	 * 
	 * @param aliens
	 */
	public void CreateAliens() {

		int startYOffsetAlien = 0;
		int startXOffsetAlien = 75;

		for (int x = 0; x < 18; x++) {
			Alien alien = AlienFactory.getAlien("hard", startXOffsetAlien + (25 * x) - 3, startYOffsetAlien, game);
			game.aliens.addElement(alien);
		}

		for (int x = 0; x < 18; x++) {
			Alien alien = AlienFactory.getAlien("normal", startXOffsetAlien + (25 * x) - 3, startYOffsetAlien + 25,
					game);
			game.aliens.addElement(alien);
		}

		for (int x = 0; x < 18; x++) {
			Alien alien = AlienFactory.getAlien("easy", startXOffsetAlien + (25 * x) - 3, startYOffsetAlien + 50, game);
			game.aliens.addElement(alien);
		}
	}

	/**
	 * creates the four barriers and adds them to the barrier vector.
	 */
	public void CreateBarriers() {
		for (int i = 1; i <= 4; i++) {
			game.barriers.addElement(new Barrier(game.WIDTH / 5 * i - 22, 370, new SpriteSheet(game.getSpriteSheet())));
		}
	}
}
