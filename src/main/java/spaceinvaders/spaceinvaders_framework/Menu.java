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

public class Menu {

    private BufferedImage menu;
    
    private int width = 603;
    private int height = 447;
  
    private ScoreMenu score_menu; 
    
    private JButton btnNewGame;
    private JButton btnStatistics;
    private JButton btnQuit;
    
    private boolean running;
    
    public Menu(){
    	running = false;

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
        
        runMenu();
    }
	
	public void runMenu() {
		running = true;
//        frame.setVisible(true);
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
//			frame.setVisible(false);
		}
	}
	
	public void listenForActions() {
        //Add ActionListener to buttons
        //New game
        btnNewGame.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                running = false;
//                frame.setVisible(false);
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
