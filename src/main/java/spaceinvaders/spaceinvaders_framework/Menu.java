package spaceinvaders.spaceinvaders_framework;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Menu {

    private BufferedImage menu;
    
    private int width = 603;
    private int height = 447;

    private static JFrame frame = null;
   
    
    public Menu(SpriteSheet ss){

        menu = ss.grabImage(257, 226, 100, 470);
    }
	
	public void runMenu() {
		//Create frame
		Game game = new Game();
        frame = new JFrame(game.TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(width,height);
        frame.setResizable(false);
        frame.setLayout(null);
     
        
        //Temporal: Add buttons
        JButton btnNewGame = new JButton("New Game");
        JButton btnStatistics = new JButton("Statistics");
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
            }
        });
        //Statistics
        btnStatistics.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(frame, "Here must be the statistics");
            }
        });
        //Quit game
        btnQuit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                game.end();
            }
        });
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
