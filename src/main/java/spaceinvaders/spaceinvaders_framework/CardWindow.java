package spaceinvaders.spaceinvaders_framework;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class CardWindow {
	private volatile static CardWindow uniqueInstance;
	
	JFrame frame = null;
	
	private JPanel cards;
	
	private CardLayout layout;
	
	private int width = 635;
	private int height = 470;
	
	private CardWindow() {
		frame = new JFrame("Space Invaders");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(width, height+50));
		frame.setResizable(false);

		layout = new CardLayout();
		cards = new JPanel(layout);

		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	/**
	 * method to get the instance of cardlayout
	 * 
	 * @return unique CardLayout
	 */
	public static CardWindow getInstance() {
		if (uniqueInstance == null) {
			synchronized (CardWindow.class) {
				if (uniqueInstance == null) {
					uniqueInstance = new CardWindow();
				}
			}
		}
		return uniqueInstance;
	}
	
	public void addCard(JPanel card, String name) {
		cards.add(card, name);
		frame.getContentPane().add(cards, BorderLayout.CENTER);
	}
	
	public void showCard(String name) {
		layout.show(cards, name);
	}
}
