import org.junit.Test;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Objects;

import model.Oval;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
   * Test setCenter method.
   */
  @Test
  public void testSetCenter() {
    oval1.setPosition(new Point2D.Double(1, 1));
    assertEquals(new Point2D.Double(1, 1), oval1.getPosition());
  }

  /**
   * Test for IllegalArgumentException for invalid center values.
   */
  @Test
  public void testBadSetCenter() {

    int illegalTestsPassed = 0;

    try {
      oval1.setPosition(new Point2D.Double(-10, -10));
    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }

    assertEquals(1, illegalTestsPassed);
  }

  /**
   * Test getXRadius method via abstract class' getDimension1.
   */
  @Test
  public void testGetXRadius() {
    assertEquals(2, oval1.getDimension1(), 0.001);
  }


  /**
   * Test setXRadius method via abstract class' setDimension1.
   */
  @Test
  public void testSetXRadius() {
    oval1.setDimension1(5);
    assertEquals(5, oval1.getDimension1(), 0.001);
  }

  /**
   * Test for IllegalArgumentException for invalid x radius.
   */
  @Test
  public void testBadXRadius() {

    int illegalTestsPassed = 0;

    try {
      oval1.setDimension1(0);
    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }
    try {
      oval1.setDimension1(-110);
    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }

    assertEquals(2, illegalTestsPassed);
  }

  /**
   * Test getYRadius method via abstract class' getDimension2.
   */
  @Test
  public void testGetHeight() {
    assertEquals(2, oval1.getDimension2(), 0.001);
  }

  /**
   * Test setYRadius method via abstract class' setDimension2.
   */
  @Test
  public void testSetYRadius() {
    oval1.setDimension2(100);
    assertEquals(100, oval1.getDimension2(), 0.001);
  }

  /**
   * Test for IllegalArgumentException for invalid height.
   */
  @Test
  public void testBadSetYRadius() {

    int illegalTestsPassed = 0;

    try {
      oval1.setDimension2(0);
    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }
    try {
      oval1.setDimension2(-110);
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
    assertEquals(new Color(1,1,1), oval1.getColor());
  }

  /**
   * Test setColor method.
   */
  @Test
  public void testSetColor() {
    oval1.setColor(new Color(2,2,2));
    assertEquals(new Color(2,2,2), oval1.getColor());
  }

  /**
   * Test for IllegalArgumentException for invalid color values.
   */
  @Test
  public void testBadSetColor() {

    int illegalTestsPassed = 0;

    try {
      oval1.setColor(new Color(-2,-2,-2));
    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }
    try {
      oval1.setColor(new Color(300,300,300));
    }
    catch (IllegalArgumentException e) {
      illegalTestsPassed += 1;
    }

    assertEquals(2, illegalTestsPassed);
  }
//
//  /**
//   * Test copy method and protected setter methods.
//   */
//  @Test
//  public void testCopy() {
//    Oval test = new Oval("1", "test", new Point2D.Double(0, 0),
//            2, 2, new Color(1,1,1));
//    Oval copyTest = test.copy();
//    Oval copyOval1 = oval1.copy();
//    assertTrue(Objects.deepEquals(copyTest, test));
////    assertTrue(Objects.deepEquals(copyOval1, oval1));
//
//    assertFalse(Objects.deepEquals(oval2, copyOval1));
//  }


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
