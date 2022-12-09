package view;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import model.IShape;
import model.ISnapshot;

class DrawPanel extends JPanel {
  private ISnapshot currSnapshot;
  public DrawPanel(ISnapshot currSnapshot) {
    this.currSnapshot = currSnapshot;
    setVisible(true);
  }

  // To update snapshot as user toggles between snapshots
  public void setCurrSnapshot(ISnapshot currSnapshot) {
    this.currSnapshot = currSnapshot;
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    setBackground(Color.BLACK);

    System.out.println(currSnapshot.getCanvas().toString());

    ArrayList<IShape> shapeList = currSnapshot.getCanvas().getShapeList();

    System.out.println("shapeList " + shapeList);

    for (IShape shape : shapeList) {
      int posX = (int) Math.round(shape.getPosition().getX());
      int posY = (int) Math.round(shape.getPosition().getY());
      int dim1 = (int) Math.round(shape.getDimension1());
      int dim2 = (int) Math.round(shape.getDimension2());
      Color color = shape.getColor();

      String shapeType = shape.getClass().getSimpleName();

      if (shapeType.equalsIgnoreCase("rectangle")) {
        System.out.println("drawing rect");
        g.setColor(color);
        g.fillRect(posX, posY, dim1, dim2);
      } else if (shapeType.equalsIgnoreCase("oval")) {
        System.out.println("drawing oval");
        g.setColor(color);
        System.out.println("color " + color);
        System.out.println("posX " + posX);

        System.out.println("posY " + posY);
        System.out.println("dim1 " + dim1);
        System.out.println("dim2 " + dim2);
        g.fillOval(posX, posY, dim1, dim2);
      }
    }
  }
}