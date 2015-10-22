package spaceinvaders.spaceinvaders_framework;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

public class HighscoreManagerTest {

//    /**
//     * tests the add score method.
//     */
//    @Test
//    public void testaddScore(){
//        HighscoreManager manager = new HighscoreManager();
//        manager.addScore("name", 0);
//        assertNotNull(manager);
//    }
    
    /**
     * tests the loadscoremethod while the outputstram is in use thus causing an error.
     *
    @Test
    public void testloadScoreFileWhileOutputstreamisInuse() throws FileNotFoundException, IOException{
        HighscoreManager manager = new HighscoreManager();
        manager.setOutputstream();
        manager.loadScoreFile();
        assertNotNull(manager);
    }
    
//    /**
//     * tests the loadscore method. if everything goes well.
//     */
//    @Test
//    public void testloadScoreFile() throws FileNotFoundException, IOException{
//        HighscoreManager manager = new HighscoreManager();
//        manager.loadScoreFile();
//        assertNotNull(manager);
//    }
  
    /**
     * tests the updatescore method.
     *
    @Test
    public void testupdateScoreFile() throws FileNotFoundException, IOException{
        HighscoreManager manager = new HighscoreManager();
        manager.updateScoreFile();
        assertNotNull(manager);
    }
    */
    
}
