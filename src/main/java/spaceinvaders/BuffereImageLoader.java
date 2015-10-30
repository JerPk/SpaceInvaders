package spaceinvaders;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BuffereImageLoader {

  // the bufferedImage used by the loadImage method.
  private BufferedImage image;

  /**
   * the method used to create an bufferedImage from the file.
   * 
   * @return BufferedImage that can be used to draw the graphics
   * @throws IOException    throws exception if it fails to load
   */
  public BufferedImage loadImage(String path) throws IOException {

    image = ImageIO.read(new FileInputStream(path));
    return image;
  }

}
