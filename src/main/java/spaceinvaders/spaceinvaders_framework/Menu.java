package spaceinvaders.spaceinvaders_framework;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Menu {

    private BufferedImage menu;
    //Bullet coordinates
    private double xpos,ypos;
    private static JFrame frame = null;
   
    
    public Menu(double x, double y, SpriteSheet ss){
        this.xpos = x;
        this.ypos = y;

        Game.logfile.writeCreate("Menu", xpos, ypos);
        menu = ss.grabImage(257, 226, 635, 470);
    }
	
	public static void createMenu() {
		//Create frame
		Game game = new Game();
        frame = new JFrame(game.TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(603,447);
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
