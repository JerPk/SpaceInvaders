package Space_Invaders_Test;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import Space_Invaders.BuffereImageLoader;
import Space_Invaders.Game;
import Space_Invaders.Spaceship;
import Space_Invaders.SpriteSheet;

public class GameTest {
    
    private Game g;

    @Before
    public void setup(){
        g = new Game();
    }
    
    /**
     * the JUnit test of the constructor method of Game
     */
    @Test
    public void TestConstructor(){
        Game ngame = new Game();
        
        assertEquals(0,ngame.getcounter());
        assertNotSame(ngame,null);
        
    }
    
    /**
     * the JUnit test of the init method. This particular test case
     * tests whether or not the spritesheet was successfully loaded from the png file.
     */
    @Test
    public void TestInitSpriteSheet(){
        
        assertSame(g.getSpriteSheet(),null);

        g.init();
        
        BuffereImageLoader loader = new BuffereImageLoader();
        BufferedImage testSprite = null;
        
        try {
            testSprite = loader.LoadImage("res/sprite_sheet.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        assertTrue(g.compareImages(g.getSpriteSheet(),testSprite));
        
        assertNotSame(g.getSpriteSheet(),null);
    }
    
    /**
     * The JUnit test of the init method. This particual test case tests wheter or not the
     * spaceship was successfully created.
     */
    @Test
    public void TestInitSpaceship(){
        assertSame(g.getSpaceship(),null);
        
        g.init();
        Spaceship shipTest = new Spaceship(g);

        assertNotSame(g.getSpaceship(),null);
        assertEquals(g.getSpaceship(),shipTest);
        assertNotSame(g.getSpaceship(),shipTest);
    
    }
}
