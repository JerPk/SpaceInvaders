package spaceinvaders;

import state.Executor;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

public class ScoreMenu implements Runnable {

  private HighscoreManager highscoremanager;
  private ArrayList<Score> allscores;
  private JTable table;

  // the returns and quit button of the highscores page.
  private JButton btnReturn;
  private JButton btnReset;
  private JButton btnQuit;

  private Boolean running;
  private Thread thread;
  private Executor exec;
  
  private GridBagConstraints gbc;
  private JPanel panel;

  /**
   * The constructor of the game class.
   * 
   * @param ex
   *          executor for the state pattern
   */
  public ScoreMenu(Executor ex) {
    exec = ex;
    running = false;

    setup();
  }

  private void setup() {
    panel = new JPanel();
    panel.setLayout(new GridBagLayout());
    gbc = new GridBagConstraints();
    panel.setBackground(Color.black);

    JLabel title = createTitle();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 3;
    gbc.insets = new Insets(0, 0, 20, 0);
    panel.add(title, gbc);

    String[] columnNames = { "Number", "Name", "Score" };
    Object[][] data = createData();

    // a table containing the top 10 scores of the game space invaders.
    table = new JTable(data, columnNames);
    table.setBackground(Color.black);
    table.setForeground(Color.white);
    table.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
    table.setGridColor(Color.gray);

    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.insets = new Insets(0, 0, 20, 0);
    panel.add(table, gbc);

    addButtons();

    CardWindow.getInstance().addCard(panel, "SCORECARD");
  }

  private JLabel createTitle() {
    JLabel title = new JLabel("Highscores");
    title.setForeground(Color.white);
    title.setFont(new Font("Calibri", Font.PLAIN, 30));

    return title;
  }

  private void addButtons() {
    btnReturn = new JButton("Return");
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.gridwidth = 1;
    gbc.insets = new Insets(0, 5, 0, 5);
    panel.add(btnReturn, gbc);

    btnReset = new JButton("Reset");
    gbc.gridx = 1;
    gbc.gridy = 2;
    panel.add(btnReset, gbc);

    btnQuit = new JButton("Quit");
    gbc.gridx = 2;
    gbc.gridy = 2;
    panel.add(btnQuit, gbc);
  }

  public void show() {
    setup();
    running = true;
    thread = new Thread(this);
    thread.start();
    CardWindow.getInstance().showCard("SCORECARD");
  }

  /**
   * Standard method to run the menu.
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
   * Method to handle when a button is pressed.
   */
  public void listenForActions() {
    btnReturn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent ae) {
        running = false;
        exec.returning();
      }
    });

    btnReset.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent ae) {
        highscoremanager.clear();
      }
    });

    // method that makes sure that when we press quit the application will
    // end.
    btnQuit.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent ae) {
        running = false;
        exec.quit();
      }
    });
  }

  /**
   * Method to create data for the table.
   * 
   * @return data to fill the table with scores
   */
  public Object[][] createData() {
    allscores = HighscoreManager.getInstance().getScores();
    
    Score score1 = allscores.get(0);
    Score score2 = allscores.get(1);
    Score score3 = allscores.get(2);
    Score score4 = allscores.get(3);
    Score score5 = allscores.get(4);
    Score score6 = allscores.get(5);
    Score score7 = allscores.get(6);
    Score score8 = allscores.get(7);
    Score score9 = allscores.get(8);
    Score score10 = allscores.get(9);

    Object[][] data = { { "1. ", score1.getName(), score1.getScore() },
        { "2. ", score2.getName(), score2.getScore() },
        { "3. ", score3.getName(), score3.getScore() },
        { "4. ", score4.getName(), score4.getScore() },
        { "5. ", score5.getName(), score5.getScore() },
        { "6. ", score6.getName(), score6.getScore() },
        { "7. ", score7.getName(), score7.getScore() },
        { "8. ", score8.getName(), score8.getScore() },
        { "9. ", score9.getName(), score9.getScore() },
        { "10. ", score10.getName(), score10.getScore() }, };

    return data;
  }

  /**
   * This method return the JTable used by the scoreMenu. this method is mainly
   * used for testing.
   * 
   * @return table.
   */
  public JTable getTable() {
    return table;
  }
}
