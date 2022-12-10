import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Objects;

import model.Rectangle;

/**
 * JUnit test class for the Rectangle class.
 */
public class RectangleTest {

  // Instance vars to test
  Rectangle rect1;
  Rectangle rect2;


  /**
   * Setup objects to be tested.
   */
  @org.junit.Before
  public void setUp() {

    // Init objects with values
    rect1 = new Rectangle("1", "rect1", new Point2D.Double(0, 0),
            2, 2, new Color(1,1,1));

    rect2 = new Rectangle("2", "rect2", new Point2D.Double(10, 22),
            2, 2, new Color(100,12,100));

  }

  /**
   * Test for IllegalArgumentException for invalid id and/or name via constructor.
   */
  @Test
  public void testBadIdAndName() {

    int illegalTestsPassed = 0;

    try {
      Rectangle rectEmptyStrId = new Rectangle("", "hello", new Point2D.Double(0, 0),
              2, 2, new Color(1,1,1));
    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }

    try {
      Rectangle rectEmptyStrName = new Rectangle("2", "", new Point2D.Double(0, 0),
              2, 2, new Color(1,1,1));
    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }

    try {
      Rectangle rectNullId = new Rectangle(null, "hello", new Point2D.Double(0, 0),
              2, 2, new Color(1,1,1));
    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }

    try {
      Rectangle rectNullName = new Rectangle("2", null, new Point2D.Double(0, 0),
              2, 2, new Color(1,1,1));
    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }

    try {
      Rectangle rectEmptyStrIdAndName = new Rectangle("", "", new Point2D.Double(0, 0),
              2, 2, new Color(1,1,1));
    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }

    try {
      Rectangle rectNullIdAndName = new Rectangle(null, null, new Point2D.Double(0, 0),
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
      Rectangle rectInvalidX = new Rectangle("1", "hello", new Point2D.Double(-1, 0),
              2, 2, new Color(1,1,1));
    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }

    try {
      Rectangle rectInvalidY = new Rectangle("1", "hello", new Point2D.Double(1, -1),
              2, 2, new Color(1,1,1));
    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }

    try {
      Rectangle rectInvalidXandY = new Rectangle("1", "hello", new Point2D.Double(-1, -2),
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
      Rectangle rectInvalidWidth = new Rectangle("1", "hello", new Point2D.Double(1, 0),
              -1, 2, new Color(1,1,1));
    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }

    try {
      Rectangle rectInvalidHeight = new Rectangle("1", "hello", new Point2D.Double(1, 0),
              2, -2, new Color(1,1,1));
    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }

    try {
      Rectangle rectInvalidWidthAndHeight = new Rectangle("1", "hello", new Point2D.Double(1, 0),
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
      Rectangle rectInvalidLowerRed = new Rectangle("1", "hello", new Point2D.Double(1, 0),
              1, 2, new Color(-100,1,1));
    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }

    try {
      Rectangle rectInvalidUpperRed = new Rectangle("1", "hello", new Point2D.Double(1, 0),
              1, 2, new Color(1000,1,1));
    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }

    try {
      Rectangle rectInvalidLowerGreen = new Rectangle("1", "hello", new Point2D.Double(1, 0),
              1, 2, new Color(100,-222,1));
    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }

    try {
      Rectangle rectInvalidUpperGreen = new Rectangle("1", "hello", new Point2D.Double(1, 0),
              1, 2, new Color(100,1222,1));
    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }

    try {
      Rectangle rectInvalidLowerBlue = new Rectangle("1", "hello", new Point2D.Double(1, 0),
              1, 2, new Color(100,222,-212));
    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }

    try {
      Rectangle rectInvalidUpperBlue = new Rectangle("1", "hello", new Point2D.Double(1, 0),
              1, 2, new Color(100,222,1000));
    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }

    try {
      Rectangle rectInvalidLowerAllColors = new Rectangle("1", "hello", new Point2D.Double(1, 0),
              1, 2, new Color(-100,-222,-1000));
    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }

    try {
      Rectangle rectInvalidUpperAllColors = new Rectangle("1", "hello", new Point2D.Double(1, 0),
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
    assertEquals("1", rect1.getId());
  }


  /**
   * Test getName method.
   */
  @Test
  public void testGetName() {
    assertEquals("rect1", rect1.getName());
  }

  /**
   * Test getMinCorner method.
   */
  @Test
  public void testGetMinCorner() {
    assertEquals(new Point2D.Double(0, 0), rect1.getPosition());
  }


  /**
   * Test setMinCorner method.
   */
  @Test
  public void testSetMinCorner() {
    rect1.setPosition(new Point2D.Double(1, 1));
    assertEquals(new Point2D.Double(1, 1), rect1.getPosition());
  }

  /**
   * Test for IllegalArgumentException for invalid min corner values.
   */
  @Test
  public void testBadSetMinCorner() {

    int illegalTestsPassed = 0;

    try {
      rect1.setPosition(new Point2D.Double(-10, -10));
    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }

    assertEquals(1, illegalTestsPassed);
  }

  /**
   * Test getWidth method.
   */
  @Test
  public void testGetWidth() {
    assertEquals(2, rect1.getDimension1(), 0.001);
  }

  /**
   * Test setWidth method.
   */
  @Test
  public void testSetWidth() {
    rect1.setDimension1(5);
    assertEquals(5, rect1.getDimension1(), 0.001);
  }

  /**
   * Test for IllegalArgumentException for invalid width.
   */
  @Test
  public void testBadSetWidth() {

    int illegalTestsPassed = 0;

    try {
      rect1.setDimension1(0);
    } catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }
    try {
      rect1.setDimension1(-110);
    } catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }

    assertEquals(2, illegalTestsPassed);
  }

    /**
     * Test getHeight method.
     */
  @Test
  public void testGetHeight() {
    assertEquals(2, rect1.getDimension2(), 0.001);
  }

  /**
   * Test setWidth method.
   */
  @Test
  public void testSetHeight() {
    rect1.setDimension2(100);
    assertEquals(100, rect1.getDimension2(), 0.001);
  }

  /**
   * Test for IllegalArgumentException for invalid height.
   */
  @Test
  public void testBadSetHeight() {

    int illegalTestsPassed = 0;

    try {
      rect1.setDimension2(0);
    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }
    try {
      rect1.setDimension2(-110);
    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }

    assertEquals(2, illegalTestsPassed);
  }

  /**
   * Test getColor method.
   */
  @Test
  public void testGetColor() {
    assertEquals(new Color(1,1,1), rect1.getColor());
  }

  /**
   * Test copy method and protected setter methods.
   */
  @Test
  public void testCopy() {

    Rectangle copyRect = rect1.copy();
    assertTrue(Objects.deepEquals(copyRect, rect1));

    assertFalse(Objects.deepEquals(rect1, rect2));
  }

  /**
   * Test toString method.
   */
  @Test
  public void testToString() {
    assertEquals("Name: rect1\n" +
            "Type: rectangle\n" +
            "Min corner: (0.0,0.0)\n" +
            "Width: 2.0\n" +
            "Height: 2.0\n" +
            "Color: (1,1,1)\n", rect1.toString());

    assertEquals("Name: rect2\n" +
            "Type: rectangle\n" +
            "Min corner: (10.0,22.0)\n" +
            "Width: 2.0\n" +
            "Height: 2.0\n" +
            "Color: (100,12,100)\n", rect2.toString());
  }

}
