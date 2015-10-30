package spaceinvaders.spaceinvaders_framework;

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

import state.Executor;

public class ScoreMenu implements Runnable{

	//private JFrame frame;

    private ArrayList<Score> allscores;
    private JTable table;
    
    // the returns and quit button of the highscores page.
    private JButton btnReturn;
    private JButton btnReset;
    private JButton btnQuit;
    
    private Boolean running;
    private Thread thread;
    private Executor exec;
    private GridBagConstraints c;
    private JPanel panel;
	
	public ScoreMenu(Executor ex) {
		exec = ex;
		running = false;
		
		setup();
	}
	
	private void setup() {
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		panel.setBackground(Color.black);

		JLabel title = createTitle();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		c.insets = new Insets(0, 0, 20, 0);
		panel.add(title, c);
		
		String[] columnNames = { "Number", "Name", "Score" };
        Object[][] data = createData();
        
        // a table containing the top 10 scores of the game space invaders.
        table = new JTable(data, columnNames);
        table.setBackground(Color.black);
        table.setForeground(Color.white);
        table.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        table.setGridColor(Color.gray);
        
        c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(0, 0, 20, 0);
        panel.add(table, c);
		
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
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.insets = new Insets(0, 5, 0, 5);
		panel.add(btnReturn, c);

		btnReset = new JButton("Reset");
		c.gridx = 1;
		c.gridy = 2;
		panel.add(btnReset, c);

		btnQuit = new JButton("Quit");
		c.gridx = 2;
		c.gridy = 2;
		panel.add(btnQuit, c);
	}
	
	public void show() {
		setup();
		running = true;
        thread = new Thread(this);
        thread.start();
        CardWindow.getInstance().showCard("SCORECARD");
    }
	
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

    
    public void listenForActions() {
    	btnReturn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                running = false;
                exec.returning();
			}
		});

    	btnReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				HighscoreManager.getInstance().clear();
			}
		});
        
        // method that makes sure that when we press quit the application will
        // end.
    	btnQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                running = false;
                exec.quit();
            }
        });
    }
    
    public Object[][] createData() {
    	allscores = HighscoreManager.getInstance().getScores();

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
    
    /**
     * This method return the JTable used by the scoreMenu.
     * this method is mainly used for testing.
     * @return table.
     */
    public JTable getTable(){
        return table;
    }
}
