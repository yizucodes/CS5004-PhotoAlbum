package model;

import java.awt.Color;
import java.awt.geom.Point2D;

/**
 * Rectangle class to represent a 2D shaped rectangle.
 */
public class Rectangle extends AbstractShape {

  /**
   * Constructor for Rectangle.
   * @param id String - unique identifier.
   * @param name String - arbitrary name of the shape, does not have to be unique.
   * @param minCorner Point2D - refers to the x and y coordinates of the lower-left corner of the shape.
   * @param width double - horizontal width
   * @param height double - vertical height
   * @param color Color - RGB color of the shape.
   *
   */
  public Rectangle(String id, String name, Point2D minCorner, double width, double height, Color color) throws IllegalArgumentException {
    super(id, name, minCorner, width, height, color);
  }

  /**
   *
   * Print out string version of rectangle with its values. For rectangle, position is min corner which is bottom left corner of the shape,
   * dimension 1 is width, dimension 2 is height.
   *
   * @return String
   */
  @Override
  public String toString() {
    return "Name: " + this.getName() + "\n"
            + "Type: " + this.getClass().getSimpleName().toLowerCase() + "\n"
            + "Min corner: (" + this.getPosition().getX() + "," + this.getPosition().getY() + ")" + "\n"
            + "Width: " + this.getDimension1() + "\n"
            + "Height: " + this.getDimension2() + "\n"
            + "Color: (" + this.getColor().getRed() + "," + this.getColor().getGreen() + ","
            + this.getColor().getBlue() + ")" + "\n";
  }

  /**
   * Deep copy of shape.
   */
  @Override
  public Rectangle copy() {
    return new Rectangle(this.id, this.name, this.position, this.dimension1, this.dimension2, this.color);
  }

}



