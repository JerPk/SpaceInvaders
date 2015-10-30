package spaceinvaders;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

/**
 * LogFileTest tests all the methods in the logfile class.
 * 
 * @author Group 23
 */
public class LogFileTest {

  /**
   * The JUnit test to test the LogFile singleton.
   */
  @Test
  public final void testSingleton() {
    LogFile logfile = LogFile.getInstance();
    LogFile logfile2 = LogFile.getInstance();

    assertEquals(logfile, logfile2);
  }

  /**
   * The JUnit test to test the open method.
   */
  @Test
  public final void testOpen() {
    File outfile = new File("logfile.txt");
    LogFile logfile = LogFile.getInstance();
    logfile.open();
    
    assertEquals(outfile.exists(), true);
    assertEquals(logfile.writeString("Test"), true);
    
    logfile.close();
  }

  /**
   * The JUnit test to test the close method.
   */
  @Test
  public final void testClose() {
    LogFile logfile = LogFile.getInstance();
    logfile.open();

    logfile.close();
    logfile.writeString("Test");

    assertEquals(logfile.writeString("Test"), false);
  }

  /**
   * The JUnit test to test the writeString method.
   * 
   * @throws IOException BufferedReader error
   */
  @Test
  public final void testWriteString() throws IOException {
    File outfile = new File("logfile.txt");
    LogFile logfile = LogFile.getInstance();
    logfile.open();
    String msg = "Test message";
    logfile.writeString(msg);
    logfile.close();

    BufferedReader bReader = new BufferedReader(new FileReader(outfile));
    assertEquals(bReader.readLine(), msg + ".");
    bReader.close();
  }

  /**
   * The JUnit test to test the writeCreate method.
   * 
   * @throws IOException BufferedReader error
   */
  @Test
  public final void testWriteCreate() throws IOException {
    File outfile = new File("logfile.txt");
    LogFile logfile = LogFile.getInstance();
    logfile.open();
    String msg = "Obj";
    logfile.writeCreate(msg, 1, 1);
    logfile.close();

    BufferedReader bReader = new BufferedReader(new FileReader(outfile));
    assertEquals(bReader.readLine(), msg + " created at x=1.0, y=1.0.");
    bReader.close();
  }

  /**
   * The JUnit test to test the writeShoot method.
   * 
   * @throws IOException BufferedReader error
   */
  @Test
  public final void testWriteShoot() throws IOException {
    File outfile = new File("logfile.txt");
    LogFile logfile = LogFile.getInstance();
    logfile.open();
    String msg = "Obj";
    logfile.writeShoot(msg, 1, 1);
    logfile.close();

    BufferedReader bReader = new BufferedReader(new FileReader(outfile));
    assertEquals(bReader.readLine(), msg + " shot a bullet from x=1.0, y=1.0.");
    bReader.close();
  }

  /**
   * The JUnit test to test the writeMove method.
   * 
   * @throws IOException BufferedReader error
   */
  @Test
  public final void testWriteMove() throws IOException {
    File outfile = new File("logfile.txt");
    LogFile logfile = LogFile.getInstance();
    logfile.open();
    String msg = "Obj";
    logfile.writeMove(msg, 1, 1);
    logfile.close();

    BufferedReader bReader = new BufferedReader(new FileReader(outfile));
    assertEquals(bReader.readLine(), msg + " moved to x=1.0, y=1.0.");
    bReader.close();
  }

  /**
   * The JUnit test to test the writeHit method.
   * 
   * @throws IOException BufferedReader error
   */
  @Test
  public final void testWriteHit() throws IOException {
    File outfile = new File("logfile.txt");
    LogFile logfile = LogFile.getInstance();
    logfile.open();
    String msg = "Obj";
    logfile.writeHit(msg, 1, 1);
    logfile.close();

    BufferedReader bReader = new BufferedReader(new FileReader(outfile));
    assertEquals(bReader.readLine(), msg + " at position x=1.0, y=1.0 is hit.");
    bReader.close();
  }

  /**
   * The JUnit test to test the writeOffScreen method.
   * 
   * @throws IOException BufferedReader error
   */
  @Test
  public final void testWriteOffscreen() throws IOException {
    File outfile = new File("logfile.txt");
    LogFile logfile = LogFile.getInstance();
    logfile.open();
    String msg = "Obj";
    logfile.writeOffscreen(msg, 1);
    logfile.close();

    BufferedReader bReader = new BufferedReader(new FileReader(outfile));
    assertEquals(bReader.readLine(), msg + " bullet went offscreen at x=1.0.");
    bReader.close();
  }

}
