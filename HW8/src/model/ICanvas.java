package model;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public interface ICanvas {

  // Get canvasId
  public String getId();

  public ArrayList<IShape> getShapeList();

  public IShape getShape(String shapeId) throws IllegalArgumentException;

  public IShape createShape(String id, String name, Point2D position,
                            double dimension1, double position2, Color color, String shapeType) throws IllegalArgumentException;

  public void removeCanvas();

  public void removeShape(String id);

  public IShape move(IShape shape, Point2D targetPosition)
          throws IllegalArgumentException;

  public IShape changeColor(IShape shape, Color targetColor)
          throws IllegalArgumentException;;

  public IShape scale(IShape shape, double targetWidth, double targetHeight)
          throws IllegalArgumentException;

  public String toString();




}
