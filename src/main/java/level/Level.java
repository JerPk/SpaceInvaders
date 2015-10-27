package level;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Vector;

import javax.swing.BoxLayout;
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
	 * @param number Levelnumber
	 * @param g The game
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
	 * 
	 */
	public JPanel createTransitionPanel() {
		JPanel panel = new JPanel();
		
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
        panel.setBackground(Color.black);
        
        
        
        JLabel title = new JLabel("Level " + levelNumber);
        title.setForeground(Color.white);
        title.setFont(new Font("Courier", Font.BOLD, 30));
        c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 50, 0);
		panel.add(title, c);
		
		JLabel alienTypeLabel = new JLabel("Type of aliens:");
		alienTypeLabel.setForeground(Color.white);
		alienTypeLabel.setFont(new Font("Courier", Font.BOLD, 20));
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(0, 0, 50, 0);
		panel.add(alienTypeLabel, c);
        
        
		
		return panel;
	}
}
