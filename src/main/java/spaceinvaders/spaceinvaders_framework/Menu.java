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
    
    public Menu(SpriteSheet ss){
		//Create frame
		//Game game = new Game();
        frame = new JFrame("Space Invaders - Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(width,height));
        frame.setResizable(false);

        menu = ss.grabImage(257, 226, 100, 470);
    }
	
	public void runMenu() {
        // create the main panel
        JPanel panel = new JPanel();

        panel.setLayout(new BorderLayout());
        
        panel.setBackground(Color.black);  
        
        JLabel title = new JLabel(" \n \n \n \n  \n \n \n \n Space Invaders");

        title.setForeground(Color.white);
        title.setFont(new Font("Courier", Font.BOLD, 30));
        
        panel.add(title, BorderLayout.CENTER);
        
        JButton btnNewGame = new JButton("New Game");
        JButton btnStatistics = new JButton("Highscores");
        JButton btnQuit = new JButton("Quit Game");
        
        btnNewGame.setVisible(true);
        btnStatistics.setVisible(true);
        btnQuit.setVisible(true);
        
        btnNewGame.setBounds(205,274,217,30);
        btnStatistics.setBounds(205,304,217,26);
        btnQuit.setBounds(205,330,217,26);
        
        frame.add(btnNewGame);
        frame.add(btnStatistics);
        frame.add(btnQuit);
       
        //Add ActionListener to buttons
        //New game
        btnNewGame.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                newGame();
                frame.setVisible(false);
            }
        });
        //Statistics
        btnStatistics.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                TempShowScores.show();
            }
        });
        //Quit game
        btnQuit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
            	System.exit(1);
            }
        });
        
        // add the panel to the frame.
        frame.add(panel);
        frame.setVisible(true);
    }
	
	//Will be used later to show title
	public void render() {
		Game g = new Game();
		BufferStrategy bs = g.getBufferStrategy();
		
        Graphics graphic = bs.getDrawGraphics();
        graphic.drawImage(menu, 0, 0, width, height, null);
		
	}
    
    private static void newGame(){
      Game game = new Game();
        game.start();
    }
    
}
