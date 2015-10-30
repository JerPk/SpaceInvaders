package spaceinvaders;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteSheet {

  // the bufferedImage used by the SpriteSheet class.
  private BufferedImage image;

  private static volatile SpriteSheet uniqueInstance;

  /**
   * constructor which loads the spritesheet from the png file.
   */
  private SpriteSheet() {
    BuffereImageLoader loader = new BuffereImageLoader();
    try {
      image = loader.loadImage("res/sprite_sheet.png");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * method to get the instance of spritesheet.
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

  /**
   * uses the coordinates specified to grab the part of the sprite sheet. that
   * will be used by the object.
   * 
   * @param col of the piece of image we need
   * @param row of the piece of image we need
   * @param width of the piece of image we need
   * @param height of the piece of image we need
   * @return BufferedImage
   */
  public BufferedImage grabImage(int col, int row, int width, int height) {

    BufferedImage img = image.getSubimage(col, row, width, height);
    return img;
  }
}
