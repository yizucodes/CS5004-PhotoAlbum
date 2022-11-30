package model;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.Objects;

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

    isValidId(id);
    isValidName(name);
    isValidPosition(position);
    isValidDimension(dimension1);
    isValidDimension(dimension2);
    isValidColor(color);

    this.id = id;
    this.name = name;
    this.position = position;

    this.dimension1 = dimension1;
    this.dimension2 = dimension2;
    this.color = color;
  }


  /**
   * Check for id.
   *
   * @return boolean true if valid
   */
  private static boolean isValidId(String id) throws IllegalArgumentException {
    if (id == null || id.equals("")) {
      throw new IllegalArgumentException("Id cannot be null or empty string");
    }
    return true;
  }

  /**
   * Check for name.
   *
   * @return boolean true if valid
   */
  private static boolean isValidName(String name) throws IllegalArgumentException {
    if (name == null || name.equals("")) {
      throw new IllegalArgumentException("Name cannot be null or empty string");
    }
    return true;
  }

  /**
   * Check for position.
   *
   * @return boolean true if valid
   */
  private static boolean isValidPosition(Point2D position) throws IllegalArgumentException {
    if (position.getX() < 0 || position.getY() < 0) {
      throw new IllegalArgumentException("X and/or Y value cannot be lower than 0");
    }
    return true;
  }

  /**
   * Check for dimension.
   *
   * @return boolean true if valid
   */
  private static boolean isValidDimension(double dim) throws IllegalArgumentException {
    if (dim <= 0) {
      throw new IllegalArgumentException("Dimension cannot be 0 or lower");
    }
    return true;
  }

  /**
   * Check for color.
   *
   * @return boolean true if valid
   */
  private static boolean isValidColor(Color color) throws IllegalArgumentException {
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

    return true;
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

  @Override
  public void setPosition(Point2D pos) {
    isValidPosition(pos);
    this.position = pos;
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

  @Override
  public void setDimension1(double dim1) {
    isValidDimension(dim1);
    this.dimension1 = dim1;
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

  @Override
  public void setDimension2(double dim2) {
    isValidDimension(dim2);
    this.dimension2 = dim2;
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

  @Override
  public void setColor(Color color) {
    isValidColor(color);
    this.color = color;
  }

//  // Will be overriden at concrete-class level.
//  @Override
//  public IShape copy() {
//    return null;
//  }

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof AbstractShape that)) return false;
    return Double.compare(that.getDimension1(), getDimension1()) == 0 && Double.compare(that.getDimension2(), getDimension2()) == 0 && Objects.equals(getId(), that.getId()) && Objects.equals(getName(), that.getName()) && Objects.equals(getPosition(), that.getPosition()) && Objects.equals(getColor(), that.getColor());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getName(), getPosition(), getDimension1(), getDimension2(), getColor());
  }
}
