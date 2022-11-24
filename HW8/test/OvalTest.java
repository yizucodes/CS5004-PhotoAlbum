import org.junit.Test;

import java.awt.*;
import java.awt.geom.Point2D;

import model.Oval;

import static org.junit.Assert.assertEquals;

/**
 * JUnit test class for the Oval class.
 */
public class OvalTest {

  // Instance vars to test
  Oval oval1;
  Oval oval2;


  /**
   * Setup objects to be tested.
   */
  @org.junit.Before
  public void setUp() {

    // Init objects with values
    oval1 = new Oval("1", "oval1", new Point2D.Double(0, 0),
            2, 2, new Color(1,1,1));

    oval2 = new Oval("2", "oval2", new Point2D.Double(10, 22),
            2, 2, new Color(100,12,100));

  }

  /**
   * Test for IllegalArgumentException for invalid id and/or name via constructor.
   */
  @Test
  public void testBadIdAndName() {

    int illegalTestsPassed = 0;

    try {
      Oval ovalEmptyStrId = new Oval("", "hello", new Point2D.Double(0, 0),
              2, 2, new Color(1,1,1));
    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }

    try {
      Oval ovalEmptyStrName = new Oval("2", "", new Point2D.Double(0, 0),
              2, 2, new Color(1,1,1));
    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }

    try {
      Oval ovalNullId = new Oval(null, "hello", new Point2D.Double(0, 0),
              2, 2, new Color(1,1,1));
    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }

    try {
      Oval ovalNullName = new Oval("2", null, new Point2D.Double(0, 0),
              2, 2, new Color(1,1,1));
    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }

    try {
      Oval ovalEmptyStrIdAndName = new Oval("", "", new Point2D.Double(0, 0),
              2, 2, new Color(1,1,1));
    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }

    try {
      Oval ovalNullIdAndName = new Oval(null, null, new Point2D.Double(0, 0),
              2, 2, new Color(1,1,1));
    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }

    assertEquals(illegalTestsPassed, 6);
  }

  /**
   * Test for IllegalArgumentException for invalid minimum corner via constructor.
   */
  @Test
  public void testMinCorner() {

    int illegalTestsPassed = 0;

    try {
      Oval rectInvalidX = new Oval("1", "hello", new Point2D.Double(-1, 0),
              2, 2, new Color(1,1,1));
    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }

    try {
      Oval rectInvalidY = new Oval("1", "hello", new Point2D.Double(1, -1),
              2, 2, new Color(1,1,1));
    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }

    try {
      Oval rectInvalidXandY = new Oval("1", "hello", new Point2D.Double(-1, -2),
              2, 2, new Color(1,1,1));
    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }

    assertEquals(illegalTestsPassed, 3);
  }

  /**
   * Test for IllegalArgumentException for invalid width and height via constructor.
   */
  @Test
  public void testBadWidthAndHeight() {

    int illegalTestsPassed = 0;

    try {
      Oval ovalInvalidWidth = new Oval("1", "hello", new Point2D.Double(1, 0),
              -1, 2, new Color(1,1,1));
    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }

    try {
      Oval ovalInvalidHeight = new Oval("1", "hello", new Point2D.Double(1, 0),
              2, -2, new Color(1,1,1));
    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }

    try {
      Oval ovalInvalidWidthAndHeight = new Oval("1", "hello", new Point2D.Double(1, 0),
              -2, -2, new Color(1,1,1));
    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }

    assertEquals(illegalTestsPassed, 3);
  }

  /**
   * Test for IllegalArgumentException for color values below allowed values of RGB (0 to 255).
   */
  @Test
  public void testBadColor() {

    int illegalTestsPassed = 0;

    try {
      Oval ovalInvalidLowerRed = new Oval("1", "hello", new Point2D.Double(1, 0),
              1, 2, new Color(-100,1,1));
    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }

    try {
      Oval ovalInvalidUpperRed = new Oval("1", "hello", new Point2D.Double(1, 0),
              1, 2, new Color(1000,1,1));
    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }

    try {
      Oval ovalInvalidLowerGreen = new Oval("1", "hello", new Point2D.Double(1, 0),
              1, 2, new Color(100,-222,1));
    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }

    try {
      Oval ovalInvalidUpperGreen = new Oval("1", "hello", new Point2D.Double(1, 0),
              1, 2, new Color(100,1222,1));
    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }

    try {
      Oval ovalInvalidLowerBlue = new Oval("1", "hello", new Point2D.Double(1, 0),
              1, 2, new Color(100,222,-212));
    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }

    try {
      Oval ovalInvalidUpperBlue = new Oval("1", "hello", new Point2D.Double(1, 0),
              1, 2, new Color(100,222,1000));
    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }

    try {
      Oval ovalInvalidLowerAllColors = new Oval("1", "hello", new Point2D.Double(1, 0),
              1, 2, new Color(-100,-222,-1000));
    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }

    try {
      Oval ovalInvalidUpperAllColors = new Oval("1", "hello", new Point2D.Double(1, 0),
              1, 2, new Color(1200,1222,1000));
    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }
    
    assertEquals(illegalTestsPassed, 8);
  }

  /**
   * Test getId method.
   */
  @Test
  public void testGetId() {
    assertEquals("1", oval1.getId());
  }


  /**
   * Test getName method.
   */
  @Test
  public void testGetName() {
    assertEquals("oval1", oval1.getName());
  }

  /**
   * Test getMinCorner method.
   */
  @Test
  public void testGetMinCorner() {
    assertEquals(new Point2D.Double(0, 0), oval1.getPosition());
  }

  /**
   * Test getWidth method.
   */
  @Test
  public void testGetWidth() {
    assertEquals(2, oval1.getDimension1(), 0.001);
  }

  /**
   * Test getHeight method.
   */
  @Test
  public void testGetHeight() {
    assertEquals(2, oval1.getDimension2(), 0.001);
  }

  /**
   * Test getColor method.
   */
  @Test
  public void testGetColor() {
    assertEquals(new Color(1,1,1), oval1.getColor());
  }

  /**
   * Test toString method.
   */
  @Test
  public void testToString() {
    assertEquals("Name: oval1\n" +
            "Type: oval\n" +
            "Center: (0.0,0.0)\n" +
            "X radius: 2.0\n" +
            "Y radius: 2.0\n" +
            "Color: (1,1,1)\n", oval1.toString());

    assertEquals("Name: oval2\n" +
            "Type: oval\n" +
            "Center: (10.0,22.0)\n" +
            "X radius: 2.0\n" +
            "Y radius: 2.0\n" +
            "Color: (100,12,100)\n", oval2.toString());
  }

}
