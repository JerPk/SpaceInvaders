package Space_Invaders;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Vector;

public class Barrier {
	
	private double xpos;
	private double ypos;
	private int state;
	
	private BufferedImage[] barrier = new BufferedImage[5];
	
	public Barrier(double x, double y, SpriteSheet ss){
		this.xpos = x;
		this.ypos = y;
		this.state = 0;

		barrier[0] = ss.grabImage(316, 213, 44, 32);
		barrier[1] = ss.grabImage(480, 210, 44, 32);
		barrier[2] = ss.grabImage(480, 265, 44, 32);
		barrier[3] = ss.grabImage(373, 211, 44, 32);
		barrier[4] = ss.grabImage(428, 210, 44, 32);
	}

	public int ifHit(Vector<Bullet> alienBullets) {
		for (int i = 0; i < alienBullets.size(); i++) {
			if (alienBullets.get(i).getX() >= xpos && alienBullets.get(i).getX() <= xpos+44) {
				if (alienBullets.get(i).getY() >= ypos && alienBullets.get(i).getY() <= ypos+32) {
					return i;
				}
			}
		}
		return -1;
	}
	
	/**
	 * the method that returns the state
	 */
	public void decreaseState() {
		state++;
	}
	
	/**
	 * the method that returns the state
	 */
	public int getState() {
		return state;
	}
	
	/**
	 * the method that returns the x position
	 *
	 * @return x position
	 */
	public double getPosX() {
		return xpos;
	}

	/**
	 * the method that returns the y position
	 *
	 * @return y position
	 */
	public double getPosY(){
		return ypos;
	}
	
	public void render(Graphics g){
		g.drawImage(barrier[state],(int) xpos,(int) ypos, null);
	}
}
