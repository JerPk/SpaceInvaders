package spaceinvaders.spaceinvaders_framework;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

import javax.swing.JFrame;

import org.junit.Test;

/**
 * MenuTest tests all the methods in the Menu class.
 * 
 * @author Group 23
 */
public class MenuTest {

    /**
     * the JUnit test of the constructor method of Menu.
     */
    @Test
    public void testMenuConstructor() {
        Menu menunull = null;
                
        assertNull(menunull);
        
        menunull = new Menu();
        Menu menu2 = new Menu();

        assertNotEquals(menunull, null);
        assertSame(menunull,menunull);
        assertNotSame(menunull, menu2);
        
    }
    
    /**
     * the JUnit test of the run method of Menu.
     */
    @Test
    public void testMenuRun() throws AWTException {
        Menu menu2 = new Menu();
        menu2.runMenu();
        
        assertNull(menu2.getScoreMenu());
        
        Robot bot = new Robot();
        bot.mouseMove(300,350);
        bot.mousePress(InputEvent.BUTTON1_MASK);
      
        //we add time between the press and release of the input event
        // otherwise system may not think it is a click.
        try{Thread.sleep(75);}catch(InterruptedException e){}
        
        bot.mouseRelease(InputEvent.BUTTON1_MASK);        
        assertNotNull(menu2.getScoreMenu());
    }
}
