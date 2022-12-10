package model;

import java.awt.Color;
import java.awt.geom.Point2D;

/**
 * Oval class to represent a 2D shaped oval.
 */
public class Oval extends AbstractShape {

  /**
   * Constructor for Rectangle.
   * @param id String - unique identifier.
   * @param name String - arbitrary name of the shape, does not have to be unique.
   * @param center Point2D - refers to the x and y coordinates of the center point of the oval shape.
   * @param xRadius double - horizontal radius length.
   * @param yRadius double - vertical radius length.
   * @param color Color - RGB color of the shape.
   *
   */
  public Oval(String id, String name, Point2D center, double xRadius, double yRadius, Color color) throws IllegalArgumentException {
    super(id, name, center, xRadius, yRadius, color);
  }

  /**
   * Print out string version of oval with its values. For oval, position is center,
   * dimension 1 is X radius, dimension 2 is Y radius.
   *
   * @return String
   */
  @Override
  public String toString() {
    return  "Name: " + this.getName() + "\n"
            + "Type: " + this.getClass().getSimpleName().toLowerCase() + "\n"
            + "Center: (" + this.getPosition().getX() + "," + this.getPosition().getY() + ")" + "\n"
            + "X radius: " + this.getDimension1() + "\n"
            + "Y radius: " + this.getDimension2() + "\n"
            + "Color: (" + this.getColor().getRed() + "," + this.getColor().getGreen() + ","
            + this.getColor().getBlue() + ")" + "\n";
  }

  /**
   * Deep copy of shape.
   */
  @Override
  public Oval copy() {
    return new Oval(this.id, this.name, this.position, this.dimension1, this.dimension2, this.color);
  }

}



