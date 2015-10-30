package spaceinvaders;

import state.Executor;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu implements Runnable {

  private JButton btnNewGame;
  private JButton btnStatistics;
  private JButton btnQuit;

  private boolean running;
  private Thread thread;
  private Executor exec;
  private Boolean startedGame = false;

  /**
   * the constructor for the Menu class.
   * 
   * @param ex
   *          executor for the state pattern
   */
  public Menu(Executor ex) {
    running = false;
    exec = ex;

    JPanel panel = new JPanel();
    panel.setLayout(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();
    panel.setBackground(Color.black);

    constraints.gridx = 0;
    constraints.gridy = 0;
    constraints.insets = new Insets(0, 0, 50, 0);

    BufferedImage biTitle = SpriteSheet.getInstance().grabImage(172, 8, 231,
        157);
    JLabel title = new JLabel(new ImageIcon(biTitle));
    panel.add(title, constraints);

    btnNewGame = new JButton("New Game");
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.gridy = 1;
    constraints.insets = new Insets(0, 50, 5, 50);
    panel.add(btnNewGame, constraints);

    btnStatistics = new JButton("Highscores");
    constraints.gridy = 2;
    panel.add(btnStatistics, constraints);

    btnQuit = new JButton("Quit Game");
    constraints.gridy = 3;
    panel.add(btnQuit, constraints);

    CardWindow.getInstance().addCard(panel, "MENUCARD");
  }

  /**
   * method that makes the menu ready & starts a thread to run it.
   */
  public void runMenu() {
    running = true;
    startedGame = false;

    thread = new Thread(this);
    thread.start();
    CardWindow.getInstance().showCard("MENUCARD");
  }

  /**
   * standard run method to run the menu.
   */
  public void run() {
    while (running) {
      listenForActions();

      try {
        Thread.sleep(1000);
      } catch (Exception e) {
        // Catch if needed
      }
    }
  }

  /**
   * Method to handle the actions when a button is pressed.
   */
  public void listenForActions() {
    btnNewGame.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent ae) {
        if (!startedGame) {
          startedGame = true;
          running = false;
          exec.start();
          exec.run();
        }
      }
    });
    btnStatistics.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent ae) {
        running = false;
        exec.scores();
        CardWindow.getInstance().showCard("SCORECARD");
      }
    });
    btnQuit.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent ae) {
        running = false;
        exec.quit();
      }
    });
  }

  public boolean isRunning() {
    return running;
  }
}
