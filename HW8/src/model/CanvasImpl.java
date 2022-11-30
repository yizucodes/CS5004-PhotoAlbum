package model;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Canvas implementation.
 */
public class CanvasImpl implements ICanvas {
  private String id;
  private ArrayList<IShape> shapeList;

  /**
   * Canvas implementation representing instance before deciding to take a snapshot.
   */
  public CanvasImpl(String id) throws IllegalArgumentException {
    if (id == null || id.equals("")) {
      throw new IllegalArgumentException("Id cannot be null or empty string.");
    }
    this.id = id;
    this.shapeList = new ArrayList<IShape>();
  }

  /**
   * Get id of canvas.
   *
   * @return String id
   */
  @Override
  public String getId() {
    return this.id;
  }

  /**
   * Get id the list of shapes.
   *
   * @return ArrayList<IShape>
   */
  @Override
  public ArrayList<IShape> getShapeList() {
    return this.shapeList;
  }

  /**
   * Get shape.
   *
   * @return IShape
   */
  @Override
  public IShape getShape(String shapeId) {

    IShape result = null;

    for (int index = 0; index < this.shapeList.size(); index++) {
      String targetId = this.shapeList.get(index).getId();
      if (Objects.equals(shapeId, targetId)) {
        result = this.shapeList.get(index);
        break;
      }
    }

    return result;
  }

  /**
   * Create a shape.
   *
   * @return IShape
   */
  @Override
  public IShape createShape(String id, String name, Point2D position,
                            double dimension1, double dimension2, Color color, String shapeType) throws IllegalArgumentException {

    if (shapeType.equalsIgnoreCase("rectangle")) {
      return new Rectangle(id, name, position, dimension1, dimension2, color);
    }
    else if(shapeType.equalsIgnoreCase("oval")) {
      return new Oval(id, name, position, dimension1, dimension2, color);
    }
    else {
      throw new IllegalArgumentException(shapeType + " does not exist");
    }
  }

  /**
   * Moves a shape to a target position.
   *
   */
  @Override
  public void move(IShape shape, Point2D targetPosition)
          throws IllegalArgumentException {
  }

  /**
   * Change color a shape.
   *
   */
  @Override
  public void changeColor(IShape shape, Color targetColor)
          throws IllegalArgumentException {
    shape.setColor(targetColor);
  }

  /**
   * Scale a shape to bigger or smaller size.
   *
   */
  @Override
  public void scale(IShape shape, double targetWidth, double targetHeight)
          throws IllegalArgumentException {

    shape.setDimension1(targetWidth);
    shape.setDimension2(targetHeight);
  }

  /**
   * Remove canvas.
   *
   */
  @Override
  public void removeCanvas() {
    this.shapeList = new ArrayList<IShape>();
  }

  /**
   * Remove a shape from canvas.
   *
   */
  @Override
  public void removeShape(String id) {
    this.shapeList.remove(getShape(id));
  }

}
