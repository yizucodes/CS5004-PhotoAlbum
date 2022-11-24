package model;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Abstract class to represent a 2D shape.
 */
public class AbstractShape implements IShape {

  protected static final int MIN_COLOR_VAL = 0;
  protected static final int MAX_COLOR_VAL = 255;

  protected String id;
  protected String name;
  protected Point2D position;

  protected double dimension1;
  protected double dimension2;
  protected Color color;

  /**
   * Constructor to check for invalid values and to initialize values.
   * @param id String - unique identifier.
   * @param name String - arbitrary name of the shape, does not have to be unique.
   * @param position Point2D - x and y coordinates serving as point of reference to a part of a shape
   *                 For example, for Rectangle, it refers the lower-left corner of the shape.
   * @param dimension1 double - first dimension of 2D shape.
   * @param dimension2 double - second dimension of 2D shape.
   * @param color Color - RGB color of the shape.
   *
   */
  public AbstractShape(String id, String name, Point2D position, double dimension1, double dimension2,
                   Color color) throws IllegalArgumentException {


    if (id == null || id.equals("")) {
      throw new IllegalArgumentException("Id cannot be null or empty string");
    }

    if (name == null || name.equals("")) {
      throw new IllegalArgumentException("Name cannot be null or empty string");
    }

    if (position.getX() < 0 || position.getY() < 0) {
      throw new IllegalArgumentException("X and/or Y value cannot be lower than 0");
    }

    if (dimension1 < 0 || dimension2 < 0) {
      throw new IllegalArgumentException("Width or dimension2 cannot be lower than 0");
    }

    if (color.getRed() < MIN_COLOR_VAL || color.getRed() > MAX_COLOR_VAL) {
      throw new IllegalArgumentException("Red color cannot be lower than " + MIN_COLOR_VAL
              + " or greater than " + MAX_COLOR_VAL);
    }

    if (color.getGreen() < MIN_COLOR_VAL || color.getGreen() > MAX_COLOR_VAL) {
      throw new IllegalArgumentException("Green color cannot be lower than " + MIN_COLOR_VAL
              + " or greater than " + MAX_COLOR_VAL);
    }

    if (color.getBlue() < MIN_COLOR_VAL || color.getBlue() > MAX_COLOR_VAL) {
      throw new IllegalArgumentException("Blue color cannot be lower than " + MIN_COLOR_VAL
              + " or greater than " + MAX_COLOR_VAL);
    }

    this.id = id;
    this.name = name;
    this.position = position;

    this.dimension1 = dimension1;
    this.dimension2 = dimension2;
    this.color = color;
  }

  /**
   * Get id of shape.
   *
   * @return String id
   */
  @Override
  public String getId() {
    return this.id;
  }

  /**
   * Get name of shape.
   *
   * @return String name
   */
  @Override
  public String getName() {
    return this.name;
  }


  /**
   * Gets position of shape.
   *
   * @return Point2D position
   */
  @Override
  public Point2D getPosition() {
    return this.position;
  }

  /**
   * Gets first dimension of shape.
   *
   * @return double dimension1
   */
  @Override
  public double getDimension1() {
    return this.dimension1;
  }

  /**
   * Gets second dimension of shape.
   *
   * @return double dimension2
   */
  @Override
  public double getDimension2() {
    return this.dimension2;
  }

  /**
   * Gets color of shape.
   *
   * @return Color color
   */
  @Override
  public Color getColor() {
    return this.color;
  }

  // Will need to be overridden as Position, Dimension1 and Dimension2 have different names
  // depending on shape
  /**
   * Print out string version of shape with its values.
   *
   * @return String
   */
  public String toString() {
    return "Name: " + this.getName() + "\n"
            + "Type: " + this.getClass().getSimpleName().toLowerCase() + "\n"
            + "Position: (" + this.getPosition().getX() + "," + this.getPosition().getY() + ")" + "\n"
            + "Dimension1: " + this.getDimension1() + "\n"
            + "Dimension2: " + this.getDimension2() + "\n"
            + "Color: (" + this.getColor().getRed() + "," + this.getColor().getGreen() + ","
            + this.getColor().getBlue() + ")" + "\n";
  }
}
