package spaceinvaders;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Class for the window to add and show cards.
 * 
 * @author Group 23 (TI2206)
 */
public final class CardWindow {
  /**
   * uniqueInstance for singleton.
   */
  private static CardWindow uniqueInstance;

  /**
   * frame for the cardwindow.
   */
  private final transient JFrame frame;

  /**
   * panel for to put the cards in.
   */
  private final transient JPanel cards;

  /**
   * layout in which the cards are placed.
   */
  private final transient CardLayout layout;

  /**
   * width of the frame.
   */
  private static final transient int WIDTH = 650;
  /**
   * height of the frame.
   */
  private static final transient int HEIGHT = 510;

  /**
   * constructor to create a cardwindow.
   */
  private CardWindow() {
    frame = new JFrame("Space Invaders");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(new Dimension(WIDTH, HEIGHT));
    frame.setResizable(false);

    layout = new CardLayout();
    cards = new JPanel(layout);

    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }

  /**
   * method to get the instance of cardlayout.
   * 
   * @return unique CardLayout
   */
  public static CardWindow getInstance() {
    synchronized (CardWindow.class) {
      if (uniqueInstance == null) {
        uniqueInstance = new CardWindow();
      }
      return uniqueInstance;
    }
  }

  /**
   * method to add a card to the frame.
   * 
   * @param card
   *          to add to the frame
   * @param name
   *          of the card to add to the frame
   */
  public void addCard(final JPanel card, final String name) {
    cards.add(card, name);
    frame.getContentPane().add(cards, BorderLayout.CENTER);
  }

  /**
   * method to show designated card.
   * 
   * @param name
   *          of card to be shown
   */
  public void showCard(final String name) {
    layout.show(cards, name);
  }
}
