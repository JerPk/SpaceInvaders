package Space_Invaders;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	// the bufferedImage used by the SpriteSheet class.
	private BufferedImage image;
	
	/**
	 * the constructor method of the SpriteSheet class.
	 * 
	 * @param BufferedImage ss
	 */
	public SpriteSheet(BufferedImage ss){
		this.image = ss;
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
