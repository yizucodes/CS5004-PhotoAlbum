package model;

import java.awt.Color;
import java.awt.geom.Point2D;

public interface IShape {

  public String getId();

  public String getName();

  public Point2D getPosition();
  public void setPosition(Point2D pos);

  public double getDimension1();
  public void setDimension1(double dim1);

  public double getDimension2();

  public void setDimension2(double dim2);

  public Color getColor();
  public void setColor(Color color);
  public IShape copy();

  public String toString();
}
