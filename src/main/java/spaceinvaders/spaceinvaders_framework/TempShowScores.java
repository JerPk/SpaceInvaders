package spaceinvaders.spaceinvaders_framework;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

/**
 * This class is temporary it creates the highscore table for the player to see.
 * normally this table would be able to view from the main menu. but as off
 * writting this the main menu hasn't been implemented yet. So instead you can
 * look up the table through this seperate class. Once the main menu has been
 * implemented methods from this class will be implemented in the game class so
 * you can view the highscores in the game itself.
 * 
 * @author Harvey van Veltom
 *
 */
public class TempShowScores {

    private JFrame frame;
    private JPanel p;
    private JLabel top;

    public TempShowScores() {
        gui();
    }

    private void gui() {

        HighscoreManager highscoremanager = new HighscoreManager();
        ArrayList<Score> allscores = highscoremanager.getScores();

        // create the frame.
        frame = new JFrame("Highscores");
        frame.setVisible(true);
        frame.setSize(new Dimension(635, 470));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create the main panel
        p = new JPanel();

        // set layout manager
        p.setLayout(new BorderLayout());
        // set background colour.
        p.setBackground(Color.black);

        
        Score Score1 = allscores.get(0);
        Score Score2 = allscores.get(1);
        Score Score3 = allscores.get(2);
        Score Score4 = allscores.get(3);
        Score Score5 = allscores.get(4);
        Score Score6 = allscores.get(5);
        Score Score7 = allscores.get(6);
        Score Score8 = allscores.get(7);
        Score Score9 = allscores.get(8);
        Score Score10 = allscores.get(9);

        // the large text saying highscores add the top.
        top = new JLabel("                                    Highscores");

        top.setForeground(Color.white);
        top.setFont(new Font("Calibri", Font.PLAIN, 30));

        
        String[] columnNames = { "Number", "Name", "Score" };

        Object[][] data = { { "1. ", Score1.getName(), Score1.getScore() },
                { "2. ", Score2.getName(), Score2.getScore() },
                { "3. ", Score3.getName(), Score3.getScore() },
                { "4. ", Score4.getName(), Score4.getScore() },
                { "5. ", Score5.getName(), Score5.getScore() },
                { "6. ", Score6.getName(), Score6.getScore() },
                { "7. ", Score7.getName(), Score7.getScore() },
                { "8. ", Score8.getName(), Score8.getScore() },
                { "9. ", Score9.getName(), Score9.getScore() },
                { "10. ", Score10.getName(), Score10.getScore() },

        };

        // a table containing the top 10 scores of the game space invaders.
        JTable table = new JTable(data, columnNames);
        table.setBackground(Color.black);
        table.setForeground(Color.white);
        table.setEnabled(false);

        // the returns and quit button of the highscores page.
        JButton returns = new JButton("return");
        JButton quit = new JButton("quit");

        JPanel southpanel = new JPanel();

        southpanel.add(returns);
        southpanel.add(quit);

        // method that makes sure that when we press quit the application will
        // end.
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });

        // add all the elements to the panel
        p.add(top, BorderLayout.NORTH);
        p.add(table, BorderLayout.CENTER);
        p.add(southpanel, BorderLayout.SOUTH);

        // add the panel to the frame.
        frame.add(p);
        
    }

    public static void main(String argv[]) {
        new TempShowScores();
    }

}
