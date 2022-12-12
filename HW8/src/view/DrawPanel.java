package view;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import model.IShape;
import model.ISnapshot;

/**
 * Class to draw a panel for graphical view.
 */
class DrawPanel extends JPanel {
  private ISnapshot currSnapshot;

  /**
   * Constructor for DrawPanel.
   */
  public DrawPanel(ISnapshot currSnapshot) {
    this.currSnapshot = currSnapshot;
    this.setPreferredSize(new Dimension(600,600));
  }

  /**
   * paintComponent to be added to frame of graphical view.
   */
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    setBackground(Color.WHITE);

    ArrayList<IShape> shapeList = currSnapshot.getCanvas().getShapeList();

    for (IShape shape : shapeList) {
      int posX = (int) Math.round(shape.getPosition().getX());
      int posY = (int) Math.round(shape.getPosition().getY());
      int dim1 = (int) Math.round(shape.getDimension1());
      int dim2 = (int) Math.round(shape.getDimension2());
      Color color = shape.getColor();

      String shapeType = shape.getClass().getSimpleName();

      if (shapeType.equalsIgnoreCase("rectangle")) {
        g.setColor(color);
        g.fillRect(posX, posY, dim1, dim2);
      } else if (shapeType.equalsIgnoreCase("oval")) {
        g.setColor(color);

        g.fillOval(posX, posY, dim1, dim2);
      }
    }
  }

  /**
   * Method to handle automatic method of redrawing panels.
   */
  public void draw (ISnapshot snap) {
    this.currSnapshot = snap;
    this.repaint();
  }


}