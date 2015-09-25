package spaceinvaders.spaceinvaders_framework;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
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

        // set layout manager
        panel.setLayout(new BorderLayout());
        // set background colour.
        panel.setBackground(Color.black);     
        
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
//                game.end();
            	System.exit(1);
            }
        });
        
        
        // add the panel to the frame.
        frame.add(panel);
        frame.setVisible(true);
    }
	
	public void render(BufferStrategy bs) {

        Graphics graphic = bs.getDrawGraphics();
        Game game = new Game();

        graphic.drawImage(menu, 0, 0, width, height, game);
		
	}
    
    private static void newGame(){
      Game game = new Game();
        game.start();
    }
    
    //public static void main(){
      //  Game game = new Game();
       // game.init();
       // createMenu();
   // }
}
