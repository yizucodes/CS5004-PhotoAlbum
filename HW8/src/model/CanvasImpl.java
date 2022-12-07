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
   * Set list of shapes to passed list.
   *
   * @param shapeList
   */
  @Override
  public void setShapeList(ArrayList<IShape> shapeList) {
    this.shapeList = shapeList;
  }

  /**
   * Get shape.
   *
   * @return IShape
   */
  @Override
  public IShape getShape(String shapeId) throws IllegalArgumentException {

    if (shapeId == null || shapeId.equals("")) {
      throw new IllegalArgumentException("Invalid shape id");
    }

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
   * Add shape to list. Prevent from adding a shape with same id values as ids are unique to each shape.
   *
   */
  @Override
  public void createShape(IShape shape) throws IllegalArgumentException {

    if (shape == null) {
      throw new IllegalArgumentException("Shape cannot be null");
    }

    String shapeId = shape.getId();

    for (int index = 0; index < this.shapeList.size(); index++) {
      String targetId = this.shapeList.get(index).getId();
      if (Objects.equals(shapeId, targetId)) {
        throw new IllegalArgumentException(shapeId + " already exists in the list of shapes.");
      }
    }

    this.shapeList.add(shape);
  }

  /**
   * Remove a shape from canvas.
   *
   */
  @Override
  public void removeShape(String id) {
    if (id == null || id.equals("")) {
      throw new IllegalArgumentException("Id cannot be null or empty string.");
    }

    this.shapeList.remove(getShape(id));
  }

  /**
   * Moves a shape to a target position.
   * Shapes can overlap each other so if there is an existing shape
   * at the coordinates of targetPosition, that is allowed.
   *
   */
  @Override
  public void move(IShape shape, Point2D targetPosition)
          throws IllegalArgumentException {

    shape.setPosition(targetPosition);
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
  public void scale(IShape shape, double dimension1, double dimension2)
          throws IllegalArgumentException {

    shape.setDimension1(dimension1);
    shape.setDimension2(dimension2);
  }

  @Override
  public String toString() {
    return "Printing Canvas" + '\n' +
    "Canvas id: " + id + '\n' +
            "List of shapes : " + shapeList + '\n';
  }
}
