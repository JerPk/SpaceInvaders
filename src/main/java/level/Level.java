package level;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import alien.Alien;
import alien.AlienFactory;
import spaceinvaders.Barrier;
import spaceinvaders.Screen;
import spaceinvaders.SpriteSheet;

/**
 * Class of the level.
 * 
 * @author Group 23 (TI2206)
 */
public class Level {

	/**
	 * Integer with the number of the level.
	 */
	protected transient int levelNumber;

	/**
	 * String with the type of alien in the level.
	 */
	protected String alienType = "";

	/**
	 * BufferedImage with the Image of the alien (for the transition screen).
	 */
	private transient BufferedImage alienTypeImage;

	/**
	 * Integer with the lives of the alien (for the transition screen).
	 */
	private transient int alienLives;

	/**
	 * Constructor of Level class.
	 * 
	 * @param number
	 *            Level number
	 */
	public Level(final int number) {
		levelNumber = number;
	}

	/**
	 * Creates the aliens and returns them.
	 * 
	 * @return the created aliens
	 */
	public Vector<Alien> createAliens() {

		final int offsetY = 0;
		final int offsetX = 75;
		final int horizontalAliens = 18;
		Vector<Alien> tempAliens = new Vector<Alien>(0);

		for (int x = 0; x < horizontalAliens; x++) {
			final Alien alien = AlienFactory.getAlien("hard",offsetX + (25 * x) - 3,
					offsetY);
			tempAliens.addElement(alien);
		}

		for (int x = 0; x < horizontalAliens; x++) {
			final Alien alien = AlienFactory.getAlien("normal", offsetX + (25 * x) - 3, offsetY + 25);
			tempAliens.addElement(alien);
		}

		for (int x = 0; x < horizontalAliens; x++) {
			final Alien alien = AlienFactory.getAlien("easy", offsetX + (25 * x) - 3, offsetY + 50);
			tempAliens.addElement(alien);
		}

		return tempAliens;
	}

	/**
	 * Creates the four barriers and returns them.
	 * 
	 * @return the created barriers
	 */
	public Vector<Barrier> createBarriers() {
		final Vector<Barrier> tempBarriers = new Vector<Barrier>(0);
		final int numberOfBarriers = 4;

		for (int i = 1; i <= numberOfBarriers; i++) {
			final Barrier barrier = new Barrier(Screen.WIDTH / 5 * i - 22, 370);
			tempBarriers.addElement(barrier);
		}

		return tempBarriers;
	}

	/**
	 * creates the Transition screen between levels.
	 * 
	 * @return panel the transition panel
	 */
	public final JPanel createTransitionPanel() {
		final JPanel panel = new JPanel();
		final Font labelFont = new Font("Courier", Font.PLAIN, 15);
		final Color labelColor = Color.white;

		panel.setLayout(new GridBagLayout());
		final GridBagConstraints constraints = new GridBagConstraints();
		panel.setBackground(Color.black);

		final JLabel levelNumberLabel = new JLabel("Level " + levelNumber);
		levelNumberLabel.setForeground(labelColor);
		levelNumberLabel.setFont(new Font("Courier", Font.BOLD, 30));
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		constraints.insets = new Insets(0, 0, 30, 0);
		panel.add(levelNumberLabel, constraints);

		final JLabel alienTypeLabel = new JLabel("Type of aliens: ");
		alienTypeLabel.setForeground(labelColor);
		alienTypeLabel.setFont(labelFont);
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.anchor = GridBagConstraints.LINE_START;
		constraints.insets = new Insets(0, 0, 0, 0);
		panel.add(alienTypeLabel, constraints);

		determineAlienType();

		final JLabel alienImageLabel = new JLabel(new ImageIcon(alienTypeImage));
		constraints.gridx = 1;
		panel.add(alienImageLabel, constraints);

		final JLabel alienLivesLabel = new JLabel("Alien lives: " + alienLives);
		alienLivesLabel.setForeground(labelColor);
		alienLivesLabel.setFont(labelFont);
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 2;
		panel.add(alienLivesLabel, constraints);

		final JLabel ScoreLabel = new JLabel("Score: ");
		ScoreLabel.setForeground(labelColor);
		ScoreLabel.setFont(labelFont);
		constraints.gridy = 3;
		panel.add(ScoreLabel, constraints);

		return panel;
	}

	/**
	 * Method to determine AlienType in the level.
	 */
	private void determineAlienType() {
		if (levelNumber % 5 == 0) {
			alienTypeImage = SpriteSheet.getInstance().grabImage(215, 225, 50, 20);
			alienLives = levelNumber;
		} else if (levelNumber > 0 && levelNumber <= 5) {
			alienTypeImage = SpriteSheet.getInstance().grabImage(7, 225, 16, 16);
			alienLives = 1;
		} else if (levelNumber > 5 && levelNumber <= 10) {
			alienTypeImage = SpriteSheet.getInstance().grabImage(40, 225, 16, 16);
			alienLives = 2;
		} else if (levelNumber > 10 && levelNumber <= 15) {
			alienTypeImage = SpriteSheet.getInstance().grabImage(74, 225, 22, 16);
			alienLives = 3;
		}
	}
}
