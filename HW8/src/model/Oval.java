package model;

import java.awt.Color;
import java.awt.geom.Point2D;

public class Oval extends AbstractShape {
  public Oval(String id, String name, Point2D center, double xRadius, double yRadius, Color color) throws IllegalArgumentException {
    super(id, name, center, xRadius, yRadius, color);
  }

  @Override
  public String toString() {
    return "Name: " + this.getName() + "\n"
            + "Type: " + this.getClass().getSimpleName().toLowerCase() + "\n"
            + "Center: (" + this.getPosition().getX() + "," + this.getPosition().getY() + ")" + "\n"
            + "X radius: " + this.getDimension1() + "\n"
            + "Y radius: " + this.getDimension2() + "\n"
            + "Color: (" + this.getColor().getRed() + "," + this.getColor().getGreen() + ","
            + this.getColor().getBlue() + ")" + "\n";
  }
}



