package model;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public interface ICanvas {
  public String getId(); // Get canvasId

  public ArrayList<IShape> getShapeList();

  public IShape getShape(String shapeId) throws IllegalArgumentException;

  public IShape createShape(IShape shape) throws IllegalArgumentException;;
  public String toString();

  public IShape move(IShape shape, Point2D targetPosition)
          throws IllegalArgumentException;

  public IShape changeColor(IShape shape, Color targetColor)
          throws IllegalArgumentException;;

  public IShape scale(IShape shape, double targetWidth, double targetHeight)
          throws IllegalArgumentException;

}
