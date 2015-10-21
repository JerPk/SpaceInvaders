package level;

import java.util.Vector;

import alien.Alien;
import alien.AlienFactory;
import spaceinvaders.spaceinvaders_framework.Barrier;

public class BossLevel extends Level {

	public BossLevel(int number) {
		super(number);
		levelNumber = number;
	}
	
	@Override
	public Vector<Alien> createAliens() {
		int startYOffsetAlien = 0;
		int startXOffsetAlien = 75;
		Vector<Alien> tempAliens = new Vector<Alien>(0);
		
		Alien alien = AlienFactory.getAlien("boss", startXOffsetAlien - 3, startYOffsetAlien);
		alien.setHealth(levelNumber);
		tempAliens.addElement(alien);
		
		return tempAliens;
	}

	@Override
	public Vector<Barrier> createBarriers() {
		return new Vector<Barrier>(0);
	}
}
