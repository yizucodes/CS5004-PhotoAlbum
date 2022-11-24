package model;

import java.awt.Color;
import java.awt.geom.Point2D;

public class Rectangle extends AbstractShape {
  public Rectangle(String id, String name, Point2D minCorner, double width, double height, Color color) throws IllegalArgumentException {
    super(id, name, minCorner, width, height, color);
  }

  @Override
  public String toString() {
    return "Name: " + this.getName() + "\n"
            + "Type: " + this.getClass().getSimpleName().toLowerCase() + "\n"
            + "Min corner: " + this.getPosition() + "\n"
            + "Width: " + this.getDimension1() + "\n"
            + "Height: " + this.getDimension2() + "\n"
            + "Color: (" + this.getColor().getRed() + "," + this.getColor().getGreen() + ","
            + this.getColor().getBlue() + "," + "\n";
  }
}



