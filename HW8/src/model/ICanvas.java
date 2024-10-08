package model;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * Interface for a canvas.
 */
public interface ICanvas {

  /**
   * Get id of canvas.
   *
   * @return String id
   */
  public String getId();

  /**
   * Get list of shapes.
   *
   * @return ArrayList<IShape>
   */
  public ArrayList<IShape> getShapeList();

  /**
   * Set list of shapes to passed list.
   *
   */
  public void setShapeList(ArrayList<IShape> shapeList);

  /**
   * Get shape.
   *
   * @return IShape
   */
  public IShape getShape(String shapeId) throws IllegalArgumentException;

  /**
   * Create a shape.
   *
   * @return IShape
   */
  public IShape createShape(String id, String name, Point2D position,
                            double dimension1, double dimension2, Color color, String shapeType) throws IllegalArgumentException;

  /**
   * Add shape to list.
   *
   */
  public void createShape(IShape shape);

  /**
   * Remove a shape from canvas.
   *
   */
  public void removeShape(String id);

  /**
   * Moves a shape to a target position.
   *
   */
  public void move(IShape shape, Point2D targetPosition)
          throws IllegalArgumentException;

  /**
   * Change color a shape.
   *
   */
  public void changeColor(IShape shape, Color targetColor)
          throws IllegalArgumentException;

  /**
   * Scale a shape to bigger or smaller size.
   *
   */
  public void scale(IShape shape, double targetWidth, double targetHeight)
          throws IllegalArgumentException;

  /**
   * Print out string version of canvas with its values.
   *
   * @return String
   */
  public String toString();




}
