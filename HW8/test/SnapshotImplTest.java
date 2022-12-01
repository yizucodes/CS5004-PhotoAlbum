import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import model.Album;
import model.ICanvas;
import model.IShape;
import model.ISnapshot;

/**
 * JUnit test class for the Album class.
 */
public class SnapshotImplTest {

  private static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss";
  private static final String TIMESTAMP_ID_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS";
  private static final Timestamp TIME_NOW = new Timestamp(System.currentTimeMillis());

  // Instance vars to test
  Album album;
  ICanvas canvas1;
  IShape rect1;
  IShape oval1;
  ISnapshot canvas1snap;

  String idExp;
  String timestampExp;

  /**
   * Setup objects to be tested.
   */
  @org.junit.Before
  public void setUp() {
    album = new Album();
    // Init objects with values
    canvas1 = album.createCanvas("canvas1");

    oval1 = canvas1.createShape("1", "oval1", new Point2D.Double(0, 0),
            2, 2, new Color(1,1,1), "oval");

    rect1 = canvas1.createShape("2", "rect1", new Point2D.Double(0, 0),
            2, 2, new Color(1,1,1), "rectangle");

    canvas1.addShape(oval1);
    canvas1.addShape(rect1);

    canvas1snap = album.createSnapshot("Test for canvas1", canvas1);

    idExp = "2022-11-30T21:04:22.000275";
    timestampExp = "2022-11-30 21:04:22";

    canvas1snap.setId("2022-11-30T21:04:22.000275");
    canvas1snap.setTimestamp("2022-11-30 21:04:22");
  }

  /**
   * Test getId method.
   */
  @Test
  public void testGetId() {
    assertEquals(idExp, canvas1snap.getId());
  }


  /**
   * Test getTimestamp method.
   */
  @Test
  public void testGetTimestamp() {
    String tsNow = new SimpleDateFormat(TIMESTAMP_FORMAT).format(TIME_NOW);
    assertEquals(tsNow, canvas1snap.getTimestamp());
  }

  /**
   * Test getDescription method.
   */
  @Test
  public void testGetDescription() {
    assertEquals("Test for canvas1", canvas1snap.getDescription());
  }

  /**
   * Test getCanvas method.
   */
  @Test
  public void testGetCanvas() {

    assertEquals("Printing Canvas\n" +
            "Canvas id: canvas1\n" +
            "List of shapes : [Name: oval1\n" +
            "Type: oval\n" +
            "Center: (0.0,0.0)\n" +
            "X radius: 2.0\n" +
            "Y radius: 2.0\n" +
            "Color: (1,1,1)\n" +
            ", Name: rect1\n" +
            "Type: rectangle\n" +
            "Min corner: (0.0,0.0)\n" +
            "Width: 2.0\n" +
            "Height: 2.0\n" +
            "Color: (1,1,1)\n" +
            "]\n", canvas1snap.getCanvas().toString());
  }

  /**
   * Test toString method.
   */
  @Test
  public void testToString() {

    canvas1snap.setId("2022-11-30T21:04:22.000275");
    canvas1snap.setTimestamp("2022-11-30 21:04:22");

    assertEquals("Printing Snapshots\n" +
            "Snapshot ID: 2022-11-30T21:04:22.000275\n" +
            "Timestamp: 2022-11-30 21:04:22\n" +
            "Description: Test for canvas1\n" +
            "Shape Information\n" +
            "[Name: oval1\n" +
            "Type: oval\n" +
            "Center: (0.0,0.0)\n" +
            "X radius: 2.0\n" +
            "Y radius: 2.0\n" +
            "Color: (1,1,1)\n" +
            ", Name: rect1\n" +
            "Type: rectangle\n" +
            "Min corner: (0.0,0.0)\n" +
            "Width: 2.0\n" +
            "Height: 2.0\n" +
            "Color: (1,1,1)\n" +
            "]\n", canvas1snap.toString());

  }
  
}
