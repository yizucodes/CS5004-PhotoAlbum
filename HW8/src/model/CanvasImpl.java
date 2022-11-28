package model;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Objects;

public class CanvasImpl implements ICanvas {
  private String id;
  private ArrayList<IShape> shapeList;

  @Override
  public String getId() {
    return this.id;
  }

  @Override
  public ArrayList<IShape> getShapeList() {
    return this.shapeList;
  }

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

  @Override
  public IShape createShape(IShape shape) throws IllegalArgumentException{

    if (shape == null) {
      throw new IllegalArgumentException("Shape cannot be null");
    }

    String shapeName = shape.getClass().getName();
    String id = shape.getId();
    String name = shape.getName();
    Point2D position = shape.getPosition();
    double dim1 = shape.getDimension1();
    double dim2 = shape.getDimension2();
    Color color = shape.getColor();

    switch (shapeName) {
      case "Rectangle":
        new Rectangle(id, name, position, dim1, dim2, color);

      case "Oval":
        new Oval(id, name, position, dim1, dim2, color);

      default:
        throw new IllegalArgumentException(shape + " does not exist");
    }
  }

  @Override
  public IShape move(IShape shape, Point2D targetPosition)
          throws IllegalArgumentException {
    return null;
  }

  @Override
  public IShape changeColor(IShape shape, Color targetColor)
          throws IllegalArgumentException {
    return null;
  }

  @Override
  public IShape scale(IShape shape, double targetWidth, double targetHeight)
          throws IllegalArgumentException {
    return null;
  }
}
