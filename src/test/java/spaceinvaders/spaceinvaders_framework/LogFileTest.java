package spaceinvaders.spaceinvaders_framework;

import static org.junit.Assert.*;

import java.io.*;

import org.junit.Test;

import spaceinvaders.LogFile;

public class LogFileTest {

  public static LogFile logfile;

  @Test
  public void testSingleton() {
    logfile = LogFile.getInstance();
    LogFile logfile2 = LogFile.getInstance();

    assertEquals(logfile, logfile2);
  }

  @Test
  public void testOpen() {
    File outfile = new File("logfile.txt");
    logfile = LogFile.getInstance();
    logfile.open();

    assertEquals(outfile.exists(), true);
    assertEquals(logfile.writeString("Test"), true);
  }

  @Test
  public void testClose() {
    File outfile = new File("logfile.txt");
    logfile = LogFile.getInstance();
    logfile.open();

    logfile.close();
    logfile.writeString("Test");

    assertEquals(logfile.writeString("Test"), false);
  }

  @Test
  public void testWriteString() throws IOException {
    File outfile = new File("logfile.txt");
    logfile = LogFile.getInstance();
    logfile.open();
    String msg = "Test message";
    logfile.writeString(msg);
    logfile.close();

    BufferedReader br = new BufferedReader(new FileReader(outfile));
    assertEquals(br.readLine(), msg + ".");
  }

  @Test
  public void testWriteCreate() throws IOException {
    File outfile = new File("logfile.txt");
    logfile = LogFile.getInstance();
    logfile.open();
    String msg = "Obj";
    logfile.writeCreate(msg, 1, 1);
    logfile.close();

    BufferedReader br = new BufferedReader(new FileReader(outfile));
    assertEquals(br.readLine(), msg + " created at x=1.0, y=1.0.");
  }

  @Test
  public void testWriteShoot() throws IOException {
    File outfile = new File("logfile.txt");
    logfile = LogFile.getInstance();
    logfile.open();
    String msg = "Obj";
    logfile.writeShoot(msg, 1, 1);
    logfile.close();

    BufferedReader br = new BufferedReader(new FileReader(outfile));
    assertEquals(br.readLine(), msg + " shot a bullet from x=1.0, y=1.0.");
  }

  @Test
  public void testWriteMove() throws IOException {
    File outfile = new File("logfile.txt");
    logfile = LogFile.getInstance();
    logfile.open();
    String msg = "Obj";
    logfile.writeMove(msg, 1, 1);
    logfile.close();

    BufferedReader br = new BufferedReader(new FileReader(outfile));
    assertEquals(br.readLine(), msg + " moved to x=1.0, y=1.0.");
  }

  @Test
  public void testWriteHit() throws IOException {
    File outfile = new File("logfile.txt");
    logfile = LogFile.getInstance();
    logfile.open();
    String msg = "Obj";
    logfile.writeHit(msg, 1, 1);
    logfile.close();

    BufferedReader br = new BufferedReader(new FileReader(outfile));
    assertEquals(br.readLine(), msg + " at position x=1.0, y=1.0 is hit.");
  }

  @Test
  public void testWriteOffscreen() throws IOException {
    File outfile = new File("logfile.txt");
    logfile = LogFile.getInstance();
    logfile.open();
    String msg = "Obj";
    logfile.writeOffscreen(msg, 1);
    logfile.close();

    BufferedReader br = new BufferedReader(new FileReader(outfile));
    assertEquals(br.readLine(), msg + " bullet went offscreen at x=1.0.");
  }

}
