package model;

import java.awt.*;
import java.awt.geom.Point2D;

public class AbstractShape implements IShape {

  protected static final int MIN_COLOR_VAL = 0;
  protected static final int MAX_COLOR_VAL = 255;

  protected String id;
  protected String name;
  protected Point2D position;

  protected double dimension1;
  protected double dimension2;
  protected Color color;

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

  @Override
  public String getId() {
    return this.id;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public Point2D getPosition() {
    return this.position;
  }

  @Override
  public double getDimension1() {
    return this.dimension1;
  }

  @Override
  public double getDimension2() {
    return this.dimension2;
  }

  @Override
  public Color getColor() {
    return this.color;
  }

  // Will need to be overridden as Position, Dimension1 and Dimension2 have different names
  // depending on shape
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
