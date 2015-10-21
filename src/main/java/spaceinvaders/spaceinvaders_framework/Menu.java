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

public class Menu {

    private BufferedImage menu;
    
    private int width = 603;
    private int height = 447;

    private static JFrame frame = null;  
    private ScoreMenu score_menu; 
    private JPanel panel;
    
    private JButton btnNewGame;
    private JButton btnStatistics;
    private JButton btnQuit;
    
    private boolean running;
    
    public Menu(){
    	running = false;

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
        
        runMenu();
    }
	
	public void runMenu() {
		running = true;
        frame.setVisible(true);
        listenForActions();
    }
	
	//Will be used later to show title
	public void render() {
		Game g = new Game();
		BufferStrategy bs = g.getBufferStrategy();
		
        Graphics graphic = bs.getDrawGraphics();
        graphic.drawImage(menu, 0, 0, width, height, null);
		
	}
	
	//This method checks if the frame really disappeared when the new game button is pressed. Sometimes this is not the case. 
	public void check() {
		if (!running) {
			frame.setVisible(false);
		}
	}
	
	public void listenForActions() {
        //Add ActionListener to buttons
        //New game
        btnNewGame.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                running = false;
                frame.setVisible(false);
            }
        });
        //Statistics
        btnStatistics.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                //score_menu = new ScoreMenu();
                //score_menu.show();
            }
        });
        //Quit game
        btnQuit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
            	System.exit(1);
            }
        });
	}
	
	public boolean isRunning() {
		return running;
	}
    
    private static void newGame(){
      Game game = new Game();
        game.start();
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
