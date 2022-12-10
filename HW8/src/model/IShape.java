package model;

import java.awt.Color;
import java.awt.geom.Point2D;

/**
 * Interface for a 2D shape.
 */
public interface IShape {

  /**
   * Get id of shape.
   *
   * @return String id
   */
  public String getId();

  /**
   * Get name of shape.
   *
   * @return String name
   */
  public String getName();

  /**
   * Get Point2D of shape.
   *
   * @return String position
   */
  public Point2D getPosition();

  /**
   * Setter for position
   *
   */
  public void setPosition(Point2D pos);

  /**
   * Get dimension1 of shape.
   *
   * @return double dimension1
   */
  public double getDimension1();

  /**
   * Setter for dimension1
   *
   */
  public void setDimension1(double dim1);

  /**
   * Get dimension2 of shape.
   *
   * @return double dimension2
   */
  public double getDimension2();

  /**
   * Setter for dimension2
   *
   */
  public void setDimension2(double dim2);

  /**
   * Get color of shape.
   *
   * @return Color color
   */
  public Color getColor();

  /**
   * Setter for color.
   *
   */
  public void setColor(Color color);

  /**
   * Print out string version of shape with its values.
   *
   * @return String
   */
  public String toString();

  /**
   * Equals method to check for deep equality when testing shapes.
   *
   */
  public boolean equals(Object o);

  /**
   * Hashcode method to check for deep equality when testing shapes.
   *
   */
  public int hashCode();


  /**
   * Deep copy of shape.
   *
   */
  public IShape copy();
}
