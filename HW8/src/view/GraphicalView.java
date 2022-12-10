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
import model.ISnapshot;

public class GraphicalView extends JFrame {
  private static final int WIDTH = 1000;
  private static final int HEIGHT = 1000;
  private ISnapshot snap;
  private Album album;
  private int currSnapshotIndex = 0; // Counter pointing to current index of snapshot displayed in view

  // TODO: Get timestamp from controller
  private DrawPanel currPanel;
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

    shapesPanel.setBorder(BorderFactory.createEmptyBorder(100, 10, 10, 10));

    buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

//    panel.setLayout(new GridLayout(10, 10));

    // Top panel label for timestamp
    topPanel.add(tsLabel);
    // Center panel label for shapes
//    shapesPanel.add(shapesLabel);
    shapesPanel.setBackground(new Color(135, 206, 235));

    // Button panel
    buttonPanel.add(nextBtn);
    nextBtn.addActionListener(new NextSnapListener());
    buttonPanel.add(selectBtn);
    selectBtn.addActionListener(new DropSelectSnapListener());
    buttonPanel.add(prevBtn);
    prevBtn.addActionListener(new PrevSnapListener());
    buttonPanel.add(quitBtn);
    quitBtn.addActionListener(new QuitListener());

    // Setup frame
    // 1. Add panel to the frame
    frame.add(topPanel, BorderLayout.NORTH);
    frame.add(shapesPanel, BorderLayout.CENTER);
    frame.add(buttonPanel, BorderLayout.SOUTH);
    // 2. Set behavior on frame closing
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // 3. Set title to window
    frame.setTitle("CS5004 Shapes Photo Album Viewer");


    // Drawing shapes
    currPanel = new DrawPanel(this.snap);
    shapesPanel.add(currPanel);

    // 4. Set window to certain size
    // Always set visible last to show everything
    frame.setVisible(true);

  }

  private class NextSnapListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      currSnapshotIndex++;

      shapesPanel.setVisible(false);
      // Pass list of snapshots
      if (currSnapshotIndex == album.getSnapshotList().size()) {
        JOptionPane.showMessageDialog(frame, "Reached end of photo album");
        return;
      }
      showSnapshot();
      updateLabel();

    }
  }


  private class PrevSnapListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      currSnapshotIndex--;

      shapesPanel.setVisible(false);
      // Pass list of snapshots
      if (currSnapshotIndex < 0) {
        JOptionPane.showMessageDialog(frame, "No more snapshots before this one.");
        return;
      }
      showSnapshot();
      updateLabel();

    }

  }

  // TODO
  private class DropSelectSnapListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
//      currSnapshotIndex++;
//
//      shapesPanel.setVisible(false);
//      // Pass list of snapshots
//      if (currSnapshotIndex == album.getSnapshotList().size()) {
//        JOptionPane.showMessageDialog(frame, "Reached end of photo album");
//      }
      showSnapshot();
      updateLabel();

    }
  }

  private class QuitListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      System.exit(0);
    }

  }

  // TODO: Add this method to interface
  public void showSnapshot() {
    ArrayList<ISnapshot> snapList = album.getSnapshotList();

    // Getting snapshot at index of currSnapshotIndex
    shapesPanel = new DrawPanel(snapList.get(currSnapshotIndex));
    add(shapesLabel);
    this.setVisible(true);
  }

  // Update label when user toggles between snapshots
  private void updateLabel() {
    ISnapshot selectedSnap = album.getSnapshotList().get(currSnapshotIndex);
    shapesLabel.setText("");
    String descText = selectedSnap.getId() + " " + selectedSnap.getDescription();
    shapesLabel.setText(descText);
    shapesLabel.setFont(new Font("SANS_SERIF", 1, 16));
  }

  public static void main(String[] args) {

    Album album = new Album();
    ICanvas canvas = album.getCanvas();

    canvas.createShape("1", "oval1", new Point2D.Double(200, 200),
            100, 100, new Color(200,200,1), "oval");

//    canvas.createShape("2", "rect1", new Point2D.Double(0, 0),
//            1000, 1000, new Color(255,1,1), "rectangle");

//    canvas.createShape("2", "oval2", new Point2D.Double(20, 100),
//            100, 100, Color.RED, "oval");

//    canvas.createShape("2", "rect1", new Point2D.Double(100, 0),
//            100, 100, new Color(1,1,1), "rectangle");
    ISnapshot testSnap = album.createSnapshot("Test for canvas1", canvas);
    new GraphicalView(album, testSnap);

  }
}
