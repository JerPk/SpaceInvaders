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

public class ScoreMenu {

	private JFrame frame;
    private HighscoreManager highscoremanager;
    private ArrayList<Score> allscores;
    private JPanel panel;
    private JTable table;
    
    // the returns and quit button of the highscores page.
    private JButton returns;
    private JButton quit;
    private JButton reset;
	
	public ScoreMenu() {
		
		highscoremanager = new HighscoreManager();
		allscores = highscoremanager.getScores();
		
        // create the frame.
        frame = new JFrame("Highscores");
        frame.setVisible(true);
        frame.setSize(new Dimension(635, 470));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // create the main panel
        panel = new JPanel();
        // set layout manager
        panel.setLayout(new BorderLayout());
        // set background colour.
        panel.setBackground(Color.black);
        
        // the returns and quit button of the highscores page.
        returns = new JButton("return");
        quit = new JButton("quit");
        reset = new JButton("reset");
	}
	
	public void show() {

        // the large text saying highscores add the top.
        JLabel top = new JLabel("                                    Highscores");

        top.setForeground(Color.white);
        top.setFont(new Font("Calibri", Font.PLAIN, 30));

        
        String[] columnNames = { "Number", "Name", "Score" };
        Object[][] data = createData();

        // a table containing the top 10 scores of the game space invaders.
        table = new JTable(data, columnNames);
        table.setBackground(Color.black);
        table.setForeground(Color.white);
        table.setEnabled(false);

        JPanel southpanel = new JPanel();
        southpanel.add(returns);
        southpanel.add(reset);
        southpanel.add(quit);
        
        listenForActions();

        // add all the elements to the panel
        panel.add(top, BorderLayout.NORTH);
        panel.add(table, BorderLayout.CENTER);
        panel.add(southpanel, BorderLayout.SOUTH);

        // add the panel to the frame.
        frame.add(panel);
        frame.setVisible(true);
    }

    
    public void listenForActions() {
        returns.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});

        reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				highscoremanager.clear();
			}
		});
        
        // method that makes sure that when we press quit the application will
        // end.
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });
    }
    
    public Object[][] createData() {
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
        
        return data;
    }
}
