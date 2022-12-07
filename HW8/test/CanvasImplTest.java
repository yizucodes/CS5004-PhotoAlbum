import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import model.Album;
import model.ICanvas;
import model.IShape;
import model.Rectangle;
import model.Oval;

/**
 * JUnit test class for the CanvasImpl class.
 */
public class CanvasImplTest {

  // Instance vars to test
  Album album;
  ICanvas canvas1;
  IShape rect1;
  IShape oval1;

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

    canvas1.createShape(oval1);
    canvas1.createShape(rect1);

  }

  /**
   * Test getShapeList.
   */
  @org.junit.Test
  public void testGetShapeList() {
    ArrayList<IShape> expected = new ArrayList<IShape>();

    Oval ovalExp = new Oval("1", "oval1", new Point2D.Double(0, 0),
            2, 2, new Color(1,1,1));
    expected.add(ovalExp);

    Rectangle rectExp = new Rectangle("2", "rect1", new Point2D.Double(0, 0),
            2, 2, new Color(1,1,1));

    expected.add(rectExp);

    assertEquals(expected, canvas1.getShapeList());
  }

  /**
   * Test getShape.
   */
  @org.junit.Test
  public void testGetShape() {

    Oval ovalExp = new Oval("1", "oval1", new Point2D.Double(0, 0),
            2, 2, new Color(1,1,1));

    assertEquals(ovalExp, canvas1.getShape("1"));
  }

  /**
   * Testing for invalid cases of getShape method.
   */
  @org.junit.Test
  public void testBadGetShape() {

    int illegalTestsPassed = 0;

    try {
      canvas1.getShape("");
    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }

    try {
      canvas1.getShape(null);
    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }

    assertEquals(2, illegalTestsPassed);
  }

  /**
   * Test createShape.
   */
  @org.junit.Test
  public void testCreateShape() {

    Oval ovalExpected = new Oval("oval3", "ovalExpected", new Point2D.Double(0, 0),
            2, 2, new Color(1,1,1));
    IShape ovalActual = canvas1.createShape("oval3", "ovalExpected", new Point2D.Double(0, 0),
            2, 2, new Color(1,1,1), "OVAL");
    assertEquals(ovalExpected, ovalActual);

    Rectangle rectExp = new Rectangle("2", "rect1", new Point2D.Double(0, 0),
            2, 2, new Color(1,1,1));

    IShape rectActual = canvas1.createShape("2", "rect1", new Point2D.Double(0, 0),
            2, 2, new Color(1,1,1), "RECTANGLE");

    assertEquals(rectExp, rectActual);
  }

  /**
   * Testing for invalid cases of createShape method.
   */
  @org.junit.Test
  public void testBadCreateShape() {

    int illegalTestsPassed = 0;

    try {
      canvas1.createShape("2", "rect1", new Point2D.Double(0, 0),
              2, 2, new Color(1,1,1), "");
    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }

    try {
      canvas1.createShape("2", "rect1", new Point2D.Double(0, 0),
              2, 2, new Color(1,1,1), null);
    }
    catch (NullPointerException e) {
      illegalTestsPassed += 1;
    }

    try {
      canvas1.createShape("2", "rect1", new Point2D.Double(0, 0),
              2, 2, new Color(1,1,1), "square");
    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }

    assertEquals(3, illegalTestsPassed);
  }

  /**
   * Test addShape.
   */
  @org.junit.Test
  public void testAddShape() {

    IShape ovalActual = canvas1.createShape("oval3", "ovalExpected", new Point2D.Double(0, 0),
            2, 2, new Color(1,1,1), "OVAL");

    IShape rectActual = canvas1.createShape("rect3", "rect1", new Point2D.Double(0, 0),
            2, 2, new Color(1,1,1), "RECTANGLE");
    canvas1.createShape(ovalActual);
    canvas1.createShape(rectActual);

    String expectedOut = "[Name: oval1\n" +
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
            ", Name: ovalExpected\n" +
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
            "]";
    assertEquals(expectedOut, canvas1.getShapeList().toString());
  }

  /**
   * Testing for invalid cases of addShape method.
   */
  @org.junit.Test
  public void testBadAddShape() {

    int illegalTestsPassed = 0;

    // Existing id
    try {
      IShape ovalActual = canvas1.createShape("1", "ovalExpected", new Point2D.Double(0, 0),
              2, 2, new Color(1,1,1), "OVAL");
      canvas1.createShape(ovalActual);

    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }

    try {
      IShape rectActual = canvas1.createShape("2", "rect1", new Point2D.Double(0, 0),
              2, 2, new Color(1,1,1), "RECTANGLE");

      canvas1.createShape(rectActual);
    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }

    try {
      canvas1.createShape(null);
    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }

    assertEquals(3, illegalTestsPassed);
  }

  /**
   * Test removeShape.
   */
  @org.junit.Test
  public void testRemoveShape() {
    canvas1.removeShape("1");

    assertEquals("[Name: rect1\n" +
            "Type: rectangle\n" +
            "Min corner: (0.0,0.0)\n" +
            "Width: 2.0\n" +
            "Height: 2.0\n" +
            "Color: (1,1,1)\n" +
            "]", canvas1.getShapeList().toString());

    canvas1.removeShape("2");
    assertEquals("[]", canvas1.getShapeList().toString());

    // Non-matching id - nothing happens as expected
    canvas1.removeShape("23");
    assertEquals("[]", canvas1.getShapeList().toString());
  }

  /**
   * Testing for invalid cases of removeShape method.
   */
  @org.junit.Test
  public void testBadRemoveShape() {

    int illegalTestsPassed = 0;

    try {
      canvas1.removeShape(null);

    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }

    try {
      canvas1.removeShape("");

    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }

    assertEquals(2, illegalTestsPassed);
  }

  /**
   * Test changeColor.
   */
  @org.junit.Test
  public void testChangeColor() {

    canvas1.changeColor(canvas1.getShape("1"), new Color(10, 10, 10));

    assertEquals("[Name: oval1\n" +
            "Type: oval\n" +
            "Center: (0.0,0.0)\n" +
            "X radius: 2.0\n" +
            "Y radius: 2.0\n" +
            "Color: (10,10,10)\n" +
            ", Name: rect1\n" +
            "Type: rectangle\n" +
            "Min corner: (0.0,0.0)\n" +
            "Width: 2.0\n" +
            "Height: 2.0\n" +
            "Color: (1,1,1)\n" +
            "]", canvas1.getShapeList().toString());

    canvas1.changeColor(canvas1.getShape("2"), new Color(100, 100, 100));

    assertEquals("[Name: oval1\n" +
            "Type: oval\n" +
            "Center: (0.0,0.0)\n" +
            "X radius: 2.0\n" +
            "Y radius: 2.0\n" +
            "Color: (10,10,10)\n" +
            ", Name: rect1\n" +
            "Type: rectangle\n" +
            "Min corner: (0.0,0.0)\n" +
            "Width: 2.0\n" +
            "Height: 2.0\n" +
            "Color: (100,100,100)\n" +
            "]", canvas1.getShapeList().toString());

  }

  /**
   * Testing for invalid cases of changeColor method.
   */
  @org.junit.Test
  public void testBadChangeColor() {

    int illegalTestsPassed = 0;

    try {
      canvas1.changeColor(canvas1.getShape("2"), new Color(300, 1000, 1000));

    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }

    try {
      canvas1.changeColor(canvas1.getShape("2"), new Color(-100, -1000, -1000));

    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }

    assertEquals(2, illegalTestsPassed);
  }

  /**
   * Test move.
   */
  @org.junit.Test
  public void testMove() {

    Point2D targetPos = new Point2D.Double(2, 2);
    canvas1.move(canvas1.getShape("1"), targetPos);

    assertEquals("[Name: oval1\n" +
            "Type: oval\n" +
            "Center: (2.0,2.0)\n" +
            "X radius: 2.0\n" +
            "Y radius: 2.0\n" +
            "Color: (1,1,1)\n" +
            ", Name: rect1\n" +
            "Type: rectangle\n" +
            "Min corner: (0.0,0.0)\n" +
            "Width: 2.0\n" +
            "Height: 2.0\n" +
            "Color: (1,1,1)\n" +
            "]", canvas1.getShapeList().toString());

    Point2D targetPos2 = new Point2D.Double(20, 20);

    canvas1.move(canvas1.getShape("2"), targetPos2);

    assertEquals("[Name: oval1\n" +
            "Type: oval\n" +
            "Center: (2.0,2.0)\n" +
            "X radius: 2.0\n" +
            "Y radius: 2.0\n" +
            "Color: (1,1,1)\n" +
            ", Name: rect1\n" +
            "Type: rectangle\n" +
            "Min corner: (20.0,20.0)\n" +
            "Width: 2.0\n" +
            "Height: 2.0\n" +
            "Color: (1,1,1)\n" +
            "]", canvas1.getShapeList().toString());

    // Move to same position as shape with id "1"
    canvas1.move(canvas1.getShape("2"), targetPos);


    assertEquals("[Name: oval1\n" +
            "Type: oval\n" +
            "Center: (2.0,2.0)\n" +
            "X radius: 2.0\n" +
            "Y radius: 2.0\n" +
            "Color: (1,1,1)\n" +
            ", Name: rect1\n" +
            "Type: rectangle\n" +
            "Min corner: (2.0,2.0)\n" +
            "Width: 2.0\n" +
            "Height: 2.0\n" +
            "Color: (1,1,1)\n" +
            "]", canvas1.getShapeList().toString());
  }

  /**
   * Testing for invalid cases of move method.
   */
  @org.junit.Test
  public void testBadMove() {

    int illegalTestsPassed = 0;

    try {
      canvas1.move(canvas1.getShape("2"), new Point2D.Double(-100, -100));

    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }

    assertEquals(1, illegalTestsPassed);
  }

  /**
   * Test scale.
   */
  @org.junit.Test
  public void testScale() {

    // Scale bigger
    canvas1.scale(canvas1.getShape("1"), 10, 10);

    assertEquals("[Name: oval1\n" +
            "Type: oval\n" +
            "Center: (0.0,0.0)\n" +
            "X radius: 10.0\n" +
            "Y radius: 10.0\n" +
            "Color: (1,1,1)\n" +
            ", Name: rect1\n" +
            "Type: rectangle\n" +
            "Min corner: (0.0,0.0)\n" +
            "Width: 2.0\n" +
            "Height: 2.0\n" +
            "Color: (1,1,1)\n" +
            "]", canvas1.getShapeList().toString());

    // Scale smaller
    canvas1.scale(canvas1.getShape("1"), 5, 5);

    assertEquals("[Name: oval1\n" +
            "Type: oval\n" +
            "Center: (0.0,0.0)\n" +
            "X radius: 5.0\n" +
            "Y radius: 5.0\n" +
            "Color: (1,1,1)\n" +
            ", Name: rect1\n" +
            "Type: rectangle\n" +
            "Min corner: (0.0,0.0)\n" +
            "Width: 2.0\n" +
            "Height: 2.0\n" +
            "Color: (1,1,1)\n" +
            "]", canvas1.getShapeList().toString());
  }

  /**
   * Testing for invalid cases of scale method.
   */
  @org.junit.Test
  public void testBadScale() {

    int illegalTestsPassed = 0;

    try {
      canvas1.scale(canvas1.getShape("1"), 0, 0);

    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }

    try {
      canvas1.scale(canvas1.getShape("1"), -5, -5);

    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }

    assertEquals(2, illegalTestsPassed);
  }

  /**
   * Test toString method.
   */
  @Test
  public void testToString() {
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
            "]\n", canvas1.toString());

  }

}
