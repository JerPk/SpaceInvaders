package level;

import java.util.Vector;

import alien.Alien;
import alien.AlienFactory;
import spaceinvaders.spaceinvaders_framework.Barrier;
import spaceinvaders.spaceinvaders_framework.Screen;

public class StandardLevel extends Level {

	public StandardLevel(int number) {
		super(number);
		levelNumber = number;
	}

	@Override
	public Vector<Alien> createAliens() {
		int startYOffsetAlien = 0;
		int startXOffsetAlien = 75;
		Vector<Alien> tempAliens = new Vector<Alien>(0);

		if (levelNumber > 0 && levelNumber <= 5) {
			alienType = "easy";
		} else if (levelNumber > 5 && levelNumber <= 10) {
			alienType = "normal";
		} else if (levelNumber > 10 && levelNumber <= 15) {
			alienType = "hard";
		}

		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < 2; j++) {
				Alien alien = AlienFactory.getAlien(alienType, startXOffsetAlien + (25 * j) - 3, startYOffsetAlien);
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
			tempBarriers.addElement(new Barrier(Screen.WIDTH / (numberOfBariers + 1) * i - 22, 370));
		}
		
		return tempBarriers;
	}
}
