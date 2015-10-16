package level;

import java.util.Vector;

import Alien.Alien;
import Alien.AlienFactory;
import spaceinvaders.spaceinvaders_framework.Barrier;
import spaceinvaders.spaceinvaders_framework.Game;

public class BossLevel extends Level {

	public BossLevel(int number, Game g) {
		super(number, g);

		game = g;
		levelNumber = number;
	}
	
	@Override
	public Vector<Alien> createAliens() {
		int startYOffsetAlien = 0;
		int startXOffsetAlien = 75;
		Vector<Alien> tempAliens = new Vector<Alien>(0);
		
		Alien alien = AlienFactory.getAlien("boss", startXOffsetAlien - 3,
	               startYOffsetAlien, game);
		tempAliens.addElement(alien);
		
		return tempAliens;
	}

	@Override
	public Vector<Barrier> createBarriers() {
		return new Vector<Barrier>(0);
	}
}
