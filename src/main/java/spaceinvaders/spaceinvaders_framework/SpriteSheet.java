package spaceinvaders.spaceinvaders_framework;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.IOException;

public class SpriteSheet {

    // the bufferedImage used by the SpriteSheet class.
    private BufferedImage image;
    
    private volatile static SpriteSheet uniqueInstance;
    
    private SpriteSheet() {}
    
    /**
     * method to get the instance of spritesheet
     * 
     * @return unique SpriteSheet
     */
    public static SpriteSheet getInstance() {
    	if (uniqueInstance == null) {
    		synchronized (SpriteSheet.class) {
    			if (uniqueInstance == null) {
    				uniqueInstance = new SpriteSheet();
    			}
    		}
    	}
    	return uniqueInstance;
    }
    
//    /**
//     * the constructor method of the SpriteSheet class.
//     * 
//     * @param BufferedImage ss
//     */
//    public SpriteSheet(BufferedImage ss){
//        this.image = ss;
//    }
    
    public void init() {
    	BuffereImageLoader loader = new BuffereImageLoader();
        try {
        	image = loader.LoadImage("res/sprite_sheet.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * uses the coordinates specified to grab the part of the sprite sheet.
     * that will be used by the object.
     * 
     * @param int col
     * @param int row
     * @param int width
     * @param int height
     * @return BufferedImage
     */
    public BufferedImage grabImage(int col, int row, int width, int height){
        
        BufferedImage img = image.getSubimage(col, row, width, height);
        return img;
    }
}
