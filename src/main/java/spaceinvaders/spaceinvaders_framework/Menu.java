package spaceinvaders.spaceinvaders_framework;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import state.Executor;
import state.MenuState;

public class Menu implements Runnable {
    
    private int width = 603;
    private int height = 447;

    private static JFrame frame = null;  
    private ScoreMenu score_menu; 
    private JPanel panel;
    
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

        frame = new JFrame("Space Invaders - Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(width,height));
        frame.setResizable(false);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.black);  
        
        btnNewGame = new JButton("New Game");
        btnStatistics = new JButton("Highscores");
        btnQuit = new JButton("Quit Game");
        
        JLabel title = new JLabel(" \n \n \n \n  \n \n \n \n Space Invaders");
        title.setForeground(Color.white);
        title.setFont(new Font("Courier", Font.BOLD, 30));
        
        panel.add(title, BorderLayout.CENTER);
        
        btnNewGame.setVisible(true);
        btnStatistics.setVisible(true);
        btnQuit.setVisible(true);
        btnNewGame.setBounds(205,274,217,30);
        btnStatistics.setBounds(205,304,217,26);
        btnQuit.setBounds(205,330,217,26);
        
        frame.add(btnNewGame);
        frame.add(btnStatistics);
        frame.add(btnQuit);
        
        // add the panel to the frame.
        frame.add(panel);
    }
	
	public void runMenu() {
		running = true;
        frame.setVisible(true);
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
            		frame.setVisible(false);
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
                frame.setVisible(false);
                exec.scores();
            }
        });
        //Quit game
        btnQuit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                running = false;
                frame.setVisible(false);
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
