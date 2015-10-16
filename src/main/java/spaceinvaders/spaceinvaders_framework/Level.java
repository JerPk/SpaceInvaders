package spaceinvaders.spaceinvaders_framework;

import java.util.Vector;

public class Level {
	
	Game game;
	
//	private Vector<Alien> aliens;
//    private Vector<Bullet> alienBullets;
//    private Vector<Bullet> shipBullets;
//    private Vector<Barrier> barriers;
	
    /**
     * 
     * @param number
     * @param spaceship
     * @param g
     */
	public Level(int number, Spaceship spaceship, Game g) {
		game=g;
		game.aliens = new Vector<Alien>(0);
        game.alienBullets = new Vector<Bullet>(0);
        game.shipBullets = new Vector<Bullet>(0);
        game.barriers = new Vector<Barrier>(0);
        
        CreateAliens();
        
     // creates all barriers and adds them to the barrier vector
        for (int i = 1; i <= 4; i++) {
            game.barriers.addElement(new Barrier(game.WIDTH / 5 * i - 22, 370,
                    new SpriteSheet(game.getSpriteSheet())));
        }
	}
	
	/**
     * creates all the aliens and adds them to the alien vector.
     * 
     * @param aliens
     */
    public void CreateAliens() {

        int startYOffsetAlien = 0;
        int startXOffsetAlien = 75;

//        for (int x = 0; x < 18; x++) {
//            Alien alien = AlienFactory.getAlien("hard", startXOffsetAlien
//                    + (25 * x) - 3, startYOffsetAlien, game);
//            game.aliens.addElement(alien);
//        }
//
//        for (int x = 0; x < 18; x++) {
//            Alien alien = AlienFactory.getAlien("normal", startXOffsetAlien
//                    + (25 * x) - 3, startYOffsetAlien + 25, game);
//            game.aliens.addElement(alien);
//        }

        for (int x = 0; x < 18; x++) {
            Alien alien = AlienFactory.getAlien("easy", startXOffsetAlien
                    + (25 * x) - 3, startYOffsetAlien + 50, game);
            game.aliens.addElement(alien);
        }
    }
}
