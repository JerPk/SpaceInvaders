package spaceinvaders.spaceinvaders_framework;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Vector;

public class Barrier {
    
    private double xpos;
    private double ypos;
    private int state;
    private SpriteSheet sprite_sheet;
    
    private BufferedImage[] barrier = new BufferedImage[5];
    
    public Barrier(double x, double y){
        this.xpos = x;
        this.ypos = y;
        this.state = 0;
        
        BuffereImageLoader loader = new BuffereImageLoader();
        BufferedImage BImg = null;
        try {
            BImg = loader.LoadImage("res/sprite_sheet.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SpriteSheet sprite_sheet = new SpriteSheet(BImg);
        
        Game.logfile.writeCreate("Barrier", xpos, ypos);

        barrier[0] = sprite_sheet.grabImage(316, 213, 44, 32);
        barrier[1] = sprite_sheet.grabImage(480, 210, 44, 32);
        barrier[2] = sprite_sheet.grabImage(480, 265, 44, 32);
        barrier[3] = sprite_sheet.grabImage(373, 211, 44, 32);
        barrier[4] = sprite_sheet.grabImage(428, 210, 44, 32);
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
        Game.logfile.writeHit("Barrier", xpos, ypos);
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
