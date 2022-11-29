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
