package spaceinvaders.spaceinvaders_framework;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import state.Executor;
import state.MenuState;

public class Menu implements Runnable {
    
    private int width = 603;
    private int height = 447;
  
    private ScoreMenu score_menu; 
    
    private JButton btnNewGame;
    private JButton btnStatistics;
    private JButton btnQuit;
    
    private boolean running;
    private Thread thread;
    private Executor exec;
    private Boolean startedGame = false;
    
    public Menu(Executor ex){
    	running = false;
    	exec = ex;

    	JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		panel.setBackground(Color.black);

		BufferedImage Title = SpriteSheet.getInstance().grabImage(172, 8, 231, 157);

		JLabel title = new JLabel(new ImageIcon(Title));
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 50, 0);
		panel.add(title, c);

		btnNewGame = new JButton("New Game");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 1;
		c.insets = new Insets(0, 50, 5, 50);
		panel.add(btnNewGame, c);

		btnStatistics = new JButton("Highscores");
		c.gridy = 2;
		panel.add(btnStatistics, c);

		btnQuit = new JButton("Quit Game");
		c.gridy = 3;
		panel.add(btnQuit, c);
		
		CardWindow.getInstance().addCard(panel, "MENUCARD");
    }
	
	public void runMenu() {
		running = true;
        startedGame = false;
        
        thread = new Thread(this);
        thread.start();
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
        //Add ActionListener to buttons
        //New game
        btnNewGame.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
            	if (!startedGame) {
            		startedGame = true;
            		running = false;
//            		frame.setVisible(false);
            		exec.start();
            		exec.run();
            	}
            }
        });
        //Statistics
        btnStatistics.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                running = false;
//                frame.setVisible(false);
                exec.scores();
            }
        });
        //Quit game
        btnQuit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                running = false;
//                frame.setVisible(false);
                exec.quit();
            }
        });
	}
	
	public boolean isRunning() {
		return running;
	}
    
    /**
     * the getter method for the ScoreMenu of the menu class.
     * mainly used for testing.
     * @return score_menu
     */
    public ScoreMenu getScoreMenu() {
        return score_menu;
    }
}
