package level;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import alien.Alien;
import alien.AlienFactory;
import spaceinvaders.spaceinvaders_framework.Barrier;
import spaceinvaders.spaceinvaders_framework.Screen;
import spaceinvaders.spaceinvaders_framework.SpriteSheet;

public class Level {

	int levelNumber;

	String alienType = "";

	/**
	 * constructor of Level class
	 * 
	 * @param number
	 *            Levelnumber
	 * @param g
	 *            The game
	 */
	public Level(int number) {
		levelNumber = number;
	}

	/**
	 * creates the aliens and returns them.
	 * 
	 * @return the created aliens
	 */
	public Vector<Alien> createAliens() {

		int startYOffsetAlien = 0;
		int startXOffsetAlien = 75;
		Vector<Alien> tempAliens = new Vector<Alien>(0);

		for (int x = 0; x < 18; x++) {
			Alien alien = AlienFactory.getAlien("hard", startXOffsetAlien + (25 * x) - 3, startYOffsetAlien);
			tempAliens.addElement(alien);
		}

		for (int x = 0; x < 18; x++) {
			Alien alien = AlienFactory.getAlien("normal", startXOffsetAlien + (25 * x) - 3, startYOffsetAlien + 25);
			tempAliens.addElement(alien);
		}

		for (int x = 0; x < 18; x++) {
			Alien alien = AlienFactory.getAlien("easy", startXOffsetAlien + (25 * x) - 3, startYOffsetAlien + 50);
			tempAliens.addElement(alien);
		}
		// this is how you add a boss alien.
		/**
		 * bossLevel = true; Alien alien = AlienFactory.getAlien("boss",
		 * startXOffsetAlien, startYOffsetAlien, this);
		 * 
		 * aliens.addElement(alien);
		 */

		return tempAliens;
	}

	/**
	 * creates the four barriers and returns them.
	 * 
	 * @return the created barriers
	 */
	public Vector<Barrier> createBarriers() {
		Vector<Barrier> tempBarriers = new Vector<Barrier>(0);

		for (int i = 1; i <= 4; i++) {
			tempBarriers.addElement(new Barrier(Screen.WIDTH / 5 * i - 22, 370));
		}

		return tempBarriers;
	}

	/**
	 * creates the Transition screen between levels.
	 * 
	 * @return panel the transition panel
	 */
	public JPanel createTransitionPanel() {
		JPanel panel = new JPanel();

		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		panel.setBackground(Color.black);

		JLabel levelNumberLabel = new JLabel("Level " + levelNumber);
		levelNumberLabel.setForeground(Color.white);
		levelNumberLabel.setFont(new Font("Courier", Font.BOLD, 30));
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.insets = new Insets(0, 0, 30, 0);
		panel.add(levelNumberLabel, c);

		JLabel alienTypeLabel = new JLabel("Type of aliens: ");
		alienTypeLabel.setForeground(Color.white);
		alienTypeLabel.setFont(new Font("Courier", Font.PLAIN, 15));
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.LINE_START;
		c.insets = new Insets(0, 0, 0, 0);
		panel.add(alienTypeLabel, c);

		BufferedImage alienTypeImage = null;
		int alienLives = 0;
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

		JLabel alienTypeImageLabel = new JLabel(new ImageIcon(alienTypeImage));
		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(0, 0, 0, 0);
		panel.add(alienTypeImageLabel, c);

		JLabel alienLivesLabel = new JLabel("Lives: " + alienLives);
		alienLivesLabel.setForeground(Color.white);
		alienLivesLabel.setFont(new Font("Courier", Font.PLAIN, 15));
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		c.insets = new Insets(0, 0, 0, 0);
		panel.add(alienLivesLabel, c);

		JLabel ScoreLabel = new JLabel("Score: ");
		ScoreLabel.setForeground(Color.white);
		ScoreLabel.setFont(new Font("Courier", Font.PLAIN, 15));
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2;
		c.insets = new Insets(0, 0, 0, 0);
		panel.add(ScoreLabel, c);

		return panel;
	}
}
