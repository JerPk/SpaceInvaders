package Space_Invaders;

import java.awt.Dimension;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {

	// The width of the screen.
	public static final int WIDTH = 635;
	// The Height of the screen.
	public static final int HEIGHT = 450;
	// the title of the application
	public final String TITLE = "Space Invaders";

	// a boolean that is true when the game is running.
	private boolean running = false;
	// a boolean that is only true if the aliens need to be updated.
	// here it used for moving all the aliens down simultaniously.
	private boolean logicRequiredThisLoop = false;
	// an Array consisting of all the Aliens in the game.
	private ArrayList Aliens = new ArrayList();
	// the main Thread we use for the game.
	private Thread thread;

	//the main BufferedImage of the game class.
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
			BufferedImage.TYPE_INT_RGB);
	
	//the bufferdImage which will be the spritesheet that contains 
	//all the sprites we use.
	private BufferedImage SpriteSheet = null;

	/**
	 * The start method will be called once at the start of the game. it is
	 * mainly used to start up the main thread of our game.
	 */
	private synchronized void start() {
		// an if statement that is to prevent that the start method creates
		// two threads if it accidently called twice.
		if (running) {
			return;
		}

		running = true;
		// create and start the main thread of our game.
		thread = new Thread(this);
		thread.start();

	}

	/**
	 * the stop method of the game. it is called only if we accidently leave the
	 * game loop. if the game has no errors this method will not be called.
	 */
	private synchronized void stop() {
		// returns if for some accident the stop method is called before the
		// game has
		// started.
		if (!running) {
			return;
		}

		running = false;

		// tries to join all the threads together.
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// exits the application.
		System.exit(1);
	}

	/**
	 * the init method initializes all the entities that will appear in the
	 * game.
	 */
	public void init() {
		BuffereImageLoader loader = new BuffereImageLoader();

		// tries to load the spritesheet from the png file.
		try {
			SpriteSheet = loader.LoadImage("res/sprite_sheet.png");
		} catch (IOException e) {
			e.printStackTrace();
		}

		// creates all the aliens and adds them to the Aliens array.
		for (int x = 0; x < 12; x++) {
			Alien alien = new Alien(200 + 20 * x, 200, this);
			Aliens.add(alien);

		}
	}

	/**
	 * the main thread of the application. it creates the jframe and calls the
	 * start method.
	 */
	public static void main(String argv[]) {
		// creates the game object that will be used.
		Game game = new Game();

		game.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		game.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		game.setMinimumSize(new Dimension(WIDTH, HEIGHT));

		// creates the Jframe that will be used.
		JFrame frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		// calls the start method.
		game.start();
	}

	/**
	 * the run method is the method that has the main game loop that will be
	 * called repeatedly when the game is ongoing.
	 */
	public void run() {
		// initialize all the etities
		init();

		// the while loop that will be active once the game is running.
		while (running) {

			doAction();

			// this if statement will only be used if all the aliens need to be
			// updated
			// simultaniously.
			if (logicRequiredThisLoop) {
				// the for loop gets
				for (int i = 0; i < Aliens.size(); i++) {
					Alien a = (Alien) Aliens.get(i);
					a.VMovement();
				}

				logicRequiredThisLoop = false;
			}

			render();

			try {
				Thread.sleep(15);
			} catch (Exception e) {
			}
		}
		// if the loop is ended due to some error the stop method
		// is called immediately.
		stop();
	}

	/**
	 * the method that is used for all the non player entities to perform their
	 * actions
	 */
	private void doAction() {
		for (int i = 0; i < Aliens.size(); i++) {
			Alien a = (Alien) Aliens.get(i);
			a.HMovement();
		}
	}

	/**
	 * the render method is used to project all the objects on the screen for
	 * the player to see.
	 */
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();

		if (bs == null) {

			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		// ///////////////

		// here we draw the black background.
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

		// here we draw all the aliens.
		for (int i = 0; i < Aliens.size(); i++) {
			Alien a = (Alien) Aliens.get(i);
			a.render(g);
		}

		// ///////////////
		g.dispose();
		bs.show();
	}

	/**
	 * get method to get the spritesheet.
	 */
	public BufferedImage getSpriteSheet() {
		return SpriteSheet;
	}

	/**
	 * the method used to put the logicrequirerthisloop boolean to true.
	 */
	public void updateLogic() {
		logicRequiredThisLoop = true;
	}

	/**
	 * the method we use to exit the application. this method is called when the
	 * aliens have reached the bottom of the screen
	 * 
	 */
	public void end() {
		System.exit(0);
	}
}