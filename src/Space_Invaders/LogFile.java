package Space_Invaders;

import java.io.*;

public class LogFile {
	
	private BufferedWriter outbuf;
	
	/**
	 * method to open logfile
	 * 
	 * @return success of opening logfile
	 */
	public boolean open() {
		try {
			File outfile = new File("logfile.txt");

			if (!outfile.exists()) {
				outfile.createNewFile();
			}

			FileWriter fw = new FileWriter(outfile.getAbsoluteFile());
			outbuf = new BufferedWriter(fw);
			
			return true;
		} catch(IOException e) {
			return false;
		}
	}
	
	/**
	 * method to close logfile
	 * 
	 * @return success of closing logfile
	 */
	public boolean close() {
		try {
			outbuf.close();
			return true;
		} catch(IOException e) {
			return false;
		}
	}
	
	/**
	 * method to write string to logfile
	 * 
	 * @param message string to write
	 * @return success of writing to logfile
	 */
	public boolean writeString(String message) {
		try {
			outbuf.write(message + ".\n");
			return true;
		} catch(IOException e) {
			return false;
		}
	}
	
	/**
	 * method to write created object to logfile
	 * 
	 * @param object which is created
	 * @param x position of the object
	 * @param y position of the object
	 * @return success of writing to logfile 
	 */
	public boolean writeCreate(String object, double x, double y) {
		String out = object + " created at x=" + String.valueOf(x) + ", y=" + String.valueOf(y);
		if (writeString(out) == true) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * method to write object which shot to logfile
	 * 
	 * @param object which shot
	 * @param x position of the object
	 * @param y position of the object
	 * @return success of writing to logfile
	 */
	public boolean writeShoot(String object, double x, double y) {
		String out = object + " shot a bullet from x=" + String.valueOf(x) + ", y=" + String.valueOf(y);
		if (writeString(out) == true) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * method to write moved object to logfile
	 * 
	 * @param object which has moved
	 * @param x position of the object
	 * @param y position of the object
	 * @return success of writing to logfile
	 */
	public boolean writeMove(String object, double x, double y) {
		String out = object + " moved to x=" + String.valueOf(x) + ", y=" + String.valueOf(y);
		if (writeString(out) == true) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * method to write hit object to logfile
	 * 
	 * @param object which is hit
	 * @param x position of the object
	 * @param y position of the object
	 * @return success of writing to logfile
	 */
	public boolean writeHit(String object, double x, double y) {
		String out = object + " at position x=" + String.valueOf(x) + ", y=" + String.valueOf(y) + " is hit";
		if (writeString(out) == true) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * method to write object which went offscreen to logfile
	 * 
	 * @param object which went off the screen
	 * @param x position of the object
	 * @return success of writing to logfile
	 */
	public boolean writeOffscreen(String object, double x) {
		String out = object + " bullet went offscreen at x=" + String.valueOf(x);
		if (writeString(out) == true) {
			return true;
		}
		else {
			return false;
		}
	}
	
}
