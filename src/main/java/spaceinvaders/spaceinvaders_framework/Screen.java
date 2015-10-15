package spaceinvaders.spaceinvaders_framework;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JFrame;

public class Screen extends Canvas {
	
    private static JFrame frame = null;
    /**
     * The Widht of the screen.
     */
    public static final int WIDTH = 635;

    /**
     * The Height of the screen.
     */
    public static final int HEIGHT = 470;
    /**
     * The title of the application.
     */
    public final String TITLE = "Space Invaders";
    /**
     * the main BufferedImage of the game class.
     */
    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
            BufferedImage.TYPE_INT_RGB);
    
    private Game game;
    
    /**
     * the bufferdImage which will be the spritesheet that contains
     *
     * all the sprites we use.
     */
    private BufferedImage SpriteSheet = null;
	
	public Screen() {
		setFocusable(true);
		
        BuffereImageLoader loader = new BuffereImageLoader();

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setMaximumSize(new Dimension(WIDTH, HEIGHT));
        setMinimumSize(new Dimension(WIDTH, HEIGHT));

        // creates the JFrame that will be used.
        frame = new JFrame(TITLE);
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        // tries to load the spritesheet from the png file.
        try {
            SpriteSheet = loader.LoadImage("res/sprite_sheet.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        frame.setVisible(true);
	}
	
	   /**
     * the render method is used to project all the objects on the screen for
     * the player to see.
     */
    public void render(Game g) {
    	game = g;
        BufferStrategy buff_strat = this.getBufferStrategy();

        if (buff_strat == null) {
            createBufferStrategy(4);
            return;
        }

        Graphics graphic = buff_strat.getDrawGraphics();

        // here we draw the black background.
        graphic.drawImage(image, 0, 0, getWidth(), getHeight(), this);

        // here we draw all the aliens.
        for (int i = 0; i < game.getAlienVector().size(); i++) {
            Alien alien_obj = (Alien) game.getAlienVector().get(i);
            alien_obj.render(graphic);
        }

        game.getSpaceship().render(graphic);

        for (int i = 0; i < game.getBarriers().size(); i++) {
            game.getBarriers().get(i).render(graphic);
        }

        renderScore(game.getScore());
        renderHighScore(game.getHSManager());

        // Draw the bullet for the Aliens
        renderBulletShip(game.getShipBullets());
        renderBulletAlien(game.getAlienBullets());

        graphic.dispose();
        buff_strat.show();

    }
    
    public void renderBulletShip(Vector<Bullet> shipBullets) {

        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {

            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        for (int i = 0; i < shipBullets.size(); i++) {
            Bullet c = (Bullet) shipBullets.get(i);
            c.render(g);
            c.moveUp();
        }
    }

    /**
     * Temporal: Method to render the bullet for the alien
     */
    public void renderBulletAlien(Vector<Bullet> alienBullets) {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        for (int i = 0; i < alienBullets.size(); i++) {
            Bullet b = (Bullet) alienBullets.get(i);
            b.render(g);
            b.moveDown();
        }
    }
    
    /**
     * renders the highscore on the screen.
     */
    public void renderHighScore(HighscoreManager hm) {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.white);
        ArrayList<Score> scores = hm.getScores();
        Score topscore = scores.get(0);

        g.drawString("Highscore : " + topscore.getScore(), 550, 470);
    }

    /**
     * renders the players current score on the screen.
     */
    public void renderScore(int score) {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.white);
        g.drawString("Score : " + score, 550, 455);
    }
    
    /**
     * get method to get the spritesheet.
     */
    public BufferedImage getSpriteSheet() {
        return SpriteSheet;
    }

    
    /**
     * The setter method for the sprite sheet from the path that is written as a
     * string.
     * 
     * @param s
     */
    public void setSpriteSheet(String s) {
        BuffereImageLoader loader = new BuffereImageLoader();
        // tries to load the spritesheet from the string.
        try {
            SpriteSheet = loader.LoadImage(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void close() {
        frame.setVisible(false);
    }
}
