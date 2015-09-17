package Space_Invaders;

import java.io.*;

public class LogFile {
	
	private BufferedWriter outbuf;
	
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
	
	public boolean close() {
		try {
			outbuf.close();
			return true;
		} catch(IOException e) {
			return false;
		}
	}
	
	public boolean writeString(String message) {
		try {
			outbuf.write(message);
			return true;
		} catch(IOException e) {
			return false;
		}
	}
	
	public boolean writeCreate(String object, double x, double y) {
		String out = object + " created at x=" + String.valueOf(x) + ", y=" + String.valueOf(y) + ".\n";
		if (writeString(out) == true) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean writeShoot(String object, double x, double y) {
		String out = object + " shot a bullet from x=" + String.valueOf(x) + ", y=" + String.valueOf(y) + ".\n";
		if (writeString(out) == true) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean writeMove(String object, double x, double y) {
		String out = object + " moved to x=" + String.valueOf(x) + ", y=" + String.valueOf(y) + ".\n";
		if (writeString(out) == true) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean writeHit(String object, double x, double y) {
		String out = object + " is hit at x=" + String.valueOf(x) + ", y=" + String.valueOf(y) + ".\n";
		if (writeString(out) == true) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean writeOffscreen(String object, double y) {
		String out = object + " went offscreen at y=" + String.valueOf(y) + ".\n";
		if (writeString(out) == true) {
			return true;
		}
		else {
			return false;
		}
	}
	
}
