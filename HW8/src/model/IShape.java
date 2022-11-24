package model;

import java.awt.Color;
import java.awt.geom.Point2D;

public interface IShape {

  public String getId();

  public String getName();

  public Point2D getPosition();

  public double getDimension1();

  public double getDimension2();

  public Color getColor();

  public String toString();
}
