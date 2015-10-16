package spaceinvaders.spaceinvaders_framework.Level;

import java.util.Vector;

import Alien.Alien;
import Alien.AlienFactory;
import spaceinvaders.spaceinvaders_framework.Barrier;
import Bullet.Bullet;
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

//		game.aliens.clear();
//		game.barriers.clear();
//		game.alienBullets.clear();
//		game.shipBullets.clear();
		spaceship.resetPosition();

//		game.aliens = createAliens();
//		game.barriers = createBarriers();
	}

	/**
	 * creates all the aliens and adds them to the alien vector.
	 * 
	 * @param aliens
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
		//this is how you add a boss alien.
        /**
       bossLevel = true;
       Alien alien = AlienFactory.getAlien("boss", startXOffsetAlien,
               startYOffsetAlien, this);
       
       aliens.addElement(alien);
  */
		
		return tempAliens;
	}

	/**
	 * creates the four barriers and adds them to the barrier vector.
	 */
	public Vector<Barrier> createBarriers() {
		Vector<Barrier> tempBarriers = new Vector<Barrier>(0);
		
		for (int i = 1; i <= 4; i++) {
			tempBarriers.addElement(new Barrier(game.WIDTH / 5 * i - 22, 370, new SpriteSheet(game.getSpriteSheet())));
		}
		
		return tempBarriers;
	}
}
