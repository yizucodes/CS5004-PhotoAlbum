import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.awt.Color;
import java.awt.geom.Point2D;

import model.Album;
import model.ICanvas;
import model.IShape;
import model.ISnapshot;

/**
 * JUnit test class for the Album class.
 */
public class AlbumTest {

  // Instance vars to test
  Album album;
  ICanvas canvas1;
  IShape rect1;
  IShape oval1;

  String idExp;
  String timestampExp;

  ISnapshot canvas1snap;

  /**
   * Setup objects to be tested.
   */
  @org.junit.Before
  public void setUp() {
    album = new Album();
    // Init objects with values
    canvas1 = album.createCanvas("canvas1");

    oval1 = canvas1.createShape("10", "oval1", new Point2D.Double(0, 0),
            2, 2, new Color(1,1,1), "oval");

    rect1 = canvas1.createShape("3", "rect1", new Point2D.Double(0, 0),
            2, 2, new Color(1,1,1), "rectangle");

    idExp = "2022-11-30T21:04:22.000275";
    timestampExp = "2022-11-30 21:04:22";

    canvas1snap = album.createSnapshot("Test for canvas1", canvas1);

    canvas1snap.setId(idExp);
    canvas1snap.setTimestamp(timestampExp);

    // Create multiple snapshots
    album.createSnapshot("Test for canvas2", canvas1);

    album.createSnapshot("Test for canvas3", canvas1);

  }

  /**
   * Testing for invalid cases of createSnapshot.
   */
  @org.junit.Test
  public void testBadConstructor() {

    int illegalTestsPassed = 0;

    try {
      album.createSnapshot(null, canvas1);

    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }

    try {
      album.createSnapshot("hello world", null);
    }
    catch (NullPointerException e) {
      illegalTestsPassed += 1;
    }

    assertEquals(2, illegalTestsPassed);
  }

  /**
   * Test getSnapshotList method. The timestamp is generated by the constructor and is immutable.
   * The unit test is marked wrong because time changes.
   */
  @Test
  public void testGetSnapshotList() {
    album.getSnapshotList();
    assertEquals("[Printing Snapshots\n" +
            "Snapshot ID: 2022-11-30T20:27:59.000822\n" +
            "Timestamp: 2022-11-30 20:27:59\n" +
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
            "]\n" +
            "]", album.getSnapshotList().toString());
  }

  /**
   * Test getSnapshotIds method.
   */
  @Test
  public void testGetSnapshotIds() {
    assertEquals("[2022-11-30T21:04:22.000275, 2022-12-09T21:17:13.000841, 2022-12-09T21:17:13.000841]",   album.getSnapshotIds(album.getSnapshotList()));
  }

  /**
   * Test createSnapshot method.
   * The unit test is marked wrong because time changes.
   * The purpose of this test is to check if multiple snapshots can be added to the Album's snapshotList
   * and that to verify their presence.
   */
  @Test
  public void testCreateSnapshot() {
    album.createSnapshot("Copy of - Test for canvas1", canvas1);

    ICanvas canvas2 = album.createCanvas("canvas2");

    canvas2.createShape(canvas2.createShape("3", "canvas2rect3", new Point2D.Double(0, 0),
            2, 10, new Color(1,1,1), "rectangle"));

    ISnapshot snap2 = album.createSnapshot("canvas2 snap", canvas2);

    assertEquals("[Printing Snapshots\n" +
            "Snapshot ID: 2022-11-30T22:14:43.000085\n" +
            "Timestamp: 2022-11-30 22:14:43\n" +
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
            "]\n" +
            ", Printing Snapshots\n" +
            "Snapshot ID: 2022-11-30T22:14:43.000143\n" +
            "Timestamp: 2022-11-30 22:14:43\n" +
            "Description: Copy of - Test for canvas1\n" +
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
            "]\n" +
            ", Printing Snapshots\n" +
            "Snapshot ID: 2022-11-30T22:14:43.000143\n" +
            "Timestamp: 2022-11-30 22:14:43\n" +
            "Description: canvas2 snap\n" +
            "Shape Information\n" +
            "[Name: canvas2rect3\n" +
            "Type: rectangle\n" +
            "Min corner: (0.0,0.0)\n" +
            "Width: 2.0\n" +
            "Height: 10.0\n" +
            "Color: (1,1,1)\n" +
            "]\n" +
            "]", album.getSnapshotList());

  }

  /**
   * Test getSnapshot.
   */
  @org.junit.Test
  public void testGetSnapshot() {
    assertEquals(canvas1snap, album.getSnapshot("2022-11-30T22:23:30.000536"));
  }

  /**
   * Testing for invalid cases of getSnapshot method.
   */
  @org.junit.Test
  public void testBadGetSnapshot() {

    int illegalTestsPassed = 0;

    try {
      album.getSnapshot("");
    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }

    try {
      album.getSnapshot(null);
    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }

    assertEquals(2, illegalTestsPassed);
  }

  /**
   * Test createCanvas.
   */
  @org.junit.Test
  public void testCreateCanvas() {
    ICanvas testCreateCanvas = album.createCanvas("testCreateCanvas");

    IShape shapeAdd = testCreateCanvas.createShape("2", "rect1", new Point2D.Double(0, 0),
            2, 2, new Color(1,1,1), "rectangle");

    testCreateCanvas.createShape(shapeAdd);

    assertEquals("Printing Canvas\n" +
            "Canvas id: testCreateCanvas\n" +
            "List of shapes : [Name: rect1\n" +
            "Type: rectangle\n" +
            "Min corner: (0.0,0.0)\n" +
            "Width: 2.0\n" +
            "Height: 2.0\n" +
            "Color: (1,1,1)\n" +
            "]\n", album.getCanvas().toString());

  }

  /**
   * Testing for invalid cases of createCanvas method.
   */
  @org.junit.Test
  public void testBadCreateCanvas() {

    int illegalTestsPassed = 0;

    try {
      album.createCanvas("");
    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }

    try {
      album.createCanvas(null);
    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }

    assertEquals(2, illegalTestsPassed);
  }

  /**
   * Test getCanvas.
   */
  @org.junit.Test
  public void testGetCanvas() {
    album.getCanvas();

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
            "]\n", album.getCanvas().toString());


    ICanvas testCreateCanvas = album.createCanvas("testCreateCanvas");

    IShape shapeAdd = testCreateCanvas.createShape("2", "rect1", new Point2D.Double(0, 0),
            2, 2, new Color(1,1,1), "rectangle");

    testCreateCanvas.createShape(shapeAdd);

    assertEquals("Printing Canvas\n" +
            "Canvas id: testCreateCanvas\n" +
            "List of shapes : [Name: rect1\n" +
            "Type: rectangle\n" +
            "Min corner: (0.0,0.0)\n" +
            "Width: 2.0\n" +
            "Height: 2.0\n" +
            "Color: (1,1,1)\n" +
            "]\n", album.getCanvas().toString());

  }

  /**
   * Test removeCanvas.
   */
  @org.junit.Test
  public void testRemoveCanvas() {
   album.removeCanvas();

   assertEquals("Printing Canvas\n" +
           "Canvas id: canvas1\n" +
           "List of shapes : []\n", album.getCanvas().toString());
  }

  /**
   * Test removeAlbum.
   */
  @org.junit.Test
  public void testRemoveAlbum() {
    album.removeAlbum();
    assertEquals("[]", album.getSnapshotList().toString());
  }


  /**
   * Test removeSnapshot.
   */
  @org.junit.Test
  public void testRemoveSnapshot() {
    album.removeSnapshot(idExp);
    assertEquals("", album.getSnapshotList().toString());

    album.createSnapshot("remaining snap", canvas1);
    assertEquals("[Printing Snapshots\n" +
            "Snapshot ID: 2022-11-30T22:56:38.000740\n" +
            "Timestamp: 2022-11-30 22:56:38\n" +
            "Description: remaining snap\n" +
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
            "]\n" +
            "]", album.getSnapshotList().toString());
  }

  /**
   * Test toString method.
   */
  @Test
  public void testToString() {
    assertEquals("Printing Album\n" +
            "List of Snapshots: \n" +
            "[Printing Snapshots\n" +
            "Snapshot ID: 2022-11-30T21:04:22.000275\n" +
            "Timestamp: 2022-11-30 23:01:35\n" +
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
            "]\n" +
            "]\n", album.toString());

  }


}
