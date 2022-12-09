package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Objects;

import javax.swing.*;

import model.Album;
import model.ICanvas;
import model.IShape;
import model.ISnapshot;

public class GraphicalView extends JFrame {

  private static final int WIDTH = 1000;
  private static final int HEIGHT = 1000;
  private ISnapshot snap;
  private Album album;
  private int counter = 0; // Counter pointing to current index of snapshot displayed in view

  // TODO: Get timestamp from controller
  private JLabel tsLabel = new JLabel("Timestamp here");

  private JLabel shapesLabel = new JLabel("TODO: Shapes here");
  private JButton nextBtn = new JButton("Next Snapshot");

  private JButton prevBtn = new JButton("Prev Snapshot");

  private JButton selectBtn = new JButton("Select Snapshot (Dropdown)");

  private JButton quitBtn = new JButton("Quit");
  private JFrame frame = new JFrame();
  private JPanel buttonPanel = new JPanel();
  private JPanel shapesPanel = new JPanel();
  private JPanel topPanel = new JPanel();

  public GraphicalView(Album album, ISnapshot snap) {
    Objects.requireNonNull(snap); // require non null object
    this.album = album;
    this.snap = snap;

    frame.setSize(WIDTH, HEIGHT);

    topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    shapesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//    panel.setLayout(new GridLayout(10, 10));

    // Top panel for timestamp
    topPanel.add(tsLabel);
    // Center panel label for shapes
//    shapesPanel.add(shapesLabel);
    shapesPanel.setBackground(new Color(135, 206, 235));

    // Button panel

    buttonPanel.add(nextBtn);
    buttonPanel.add(selectBtn);
    buttonPanel.add(prevBtn);
    buttonPanel.add(quitBtn);

    nextBtn.addActionListener(this);

    // Setup frame
    // 1. Add panel to the frame
    frame.add(topPanel, BorderLayout.NORTH);
    frame.add(shapesPanel, BorderLayout.CENTER);
    frame.add(buttonPanel, BorderLayout.SOUTH);

    // 2. Set behavior on frame closing
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // 3. Set title to window
    frame.setTitle("CS5004 Shapes Photo Album Viewer");
    // 4. Set window to certain size
    frame.setVisible(true);

    // TODO: Add shape to the views
    DrawPanel drawPanel = new DrawPanel();
    shapesPanel.add(drawPanel);

  }

  class DrawPanel extends JPanel {
    private ISnapshot dpSnap;
    public DrawPanel(ISnapshot dpSnap) {
      this.dpSnap = dpSnap;
    }
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      setBackground(Color.BLACK);

      ArrayList<IShape> shapeList = snap.getCanvas().getShapeList();

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
          g.fillRect(posX, posY, dim1, dim2);
        }
      }
    }
  }

  private class NextSnapListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      counter++;

      // Pass list of snapshots
      if (counter == album.getSnapshotList().size()) {
        JOptionPane.showMessageDialog(frame, "Reached end of photo album");
      }
      showSnapshot();
      updateLabel();

    }
  }

  // TODO: Add this method to interface
  public void showSnapshot() {
    ArrayList<ISnapshot> snapList = album.getSnapshotList();

    // Getting snapshot at index of counter
    shapesPanel = new DrawPanel(snapList.get(counter));
    add(shapesLabel);
    this.setVisible(true);
  }

  // Update label when user toggles between snapshots
  private void updateLabel() {
    ISnapshot selectedSnap = album.getSnapshotList().get(counter);
    shapesLabel.setText("");
    String descText = selectedSnap.getId() + " " + selectedSnap.getDescription();
    shapesLabel.setText(descText);
    shapesLabel.setFont(new Font("SANS_SERIF", 1, 16));
  }
  public static void main(String[] args) {

    Album album = new Album();
    ICanvas canvas = album.getCanvas();
    canvas.createShape("1", "oval1", new Point2D.Double(0, 0),
            100, 100, new Color(1,1,1), "oval");

    canvas.createShape("2", "rect1", new Point2D.Double(100, 0),
            100, 100, new Color(1,1,1), "rectangle");
    ISnapshot testSnap = album.createSnapshot("Test for canvas1", canvas);
    new GraphicalView(album, testSnap);

  }
}
