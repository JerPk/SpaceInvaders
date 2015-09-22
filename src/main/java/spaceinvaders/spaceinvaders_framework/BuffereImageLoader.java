package spaceinvaders.spaceinvaders_framework;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BuffereImageLoader {
    
    //the bufferedImage used by the loadImage method.
    private BufferedImage image;
    
    /**
     * the method used to create an bufferedImage from the file.
     * 
     * @param String Path
     * @return BufferedImage
     * @throws IOException
     */
    public BufferedImage LoadImage(String Path) throws IOException{
        
        image = ImageIO.read(new FileInputStream(Path));
        return image;
    }

}
