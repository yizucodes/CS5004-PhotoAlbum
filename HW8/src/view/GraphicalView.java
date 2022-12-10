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
  private ISnapshot currSnapshot;
  private Album album;
  private int currSnapshotIndex = 0; // Counter pointing to current index of snapshot displayed in view

  private JComboBox dropdown;

  private DrawPanel currPanel;
  private JLabel tsLabel = new JLabel("");

  private JLabel shapesLabel = new JLabel("TODO: Shapes here");
  private JButton nextBtn = new JButton("Next Snapshot");

  private JButton prevBtn = new JButton("Prev Snapshot");


  private JLabel dropdownLabel = new JLabel("Select your snapshot");
  private JButton dropdownBtn = new JButton("Select Snapshot (Dropdown)");

  private JButton quitBtn = new JButton("Quit");
  private JFrame frame = new JFrame();
  private JPanel buttonPanel = new JPanel();
  private JPanel shapesPanel = new JPanel();
  private JPanel topPanel = new JPanel();

  public GraphicalView(Album album) {
    Objects.requireNonNull(album); // require non null object
    this.album = album;

    System.out.println(album.toString());
    this.currSnapshot = album.getSnapshotList().get(currSnapshotIndex);

    frame.setSize(WIDTH, HEIGHT);
    frame.setLayout(new BorderLayout());
    // Borderlayout
    topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    shapesPanel.setBorder(BorderFactory.createEmptyBorder(100, 10, 10, 10));

    buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    // Top panel label for timestamp
    topPanel.add(tsLabel);
    // Center panel label for shapes
    shapesPanel.setBackground(new Color(135, 206, 235));

    // Button panel
    buttonPanel.add(nextBtn);
    nextBtn.addActionListener(new NextSnapListener());

    // Dropdown
//    buttonPanel.add(dropdownBtn);
//    dropdownBtn.addActionListener(new DropdownListener());
    this.dropdown = dropdownMenu();
    buttonPanel.add(dropdown);

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
    currPanel = new DrawPanel(this.currSnapshot);
    shapesPanel.add(currPanel);

    // 4. Set window to certain size
    // Always set visible last to show everything
    frame.setVisible(true);

  }

  private class NextSnapListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      // Pass list of snapshots
      if (currSnapshotIndex == album.getSnapshotList().size() - 1) {
        JOptionPane.showMessageDialog(frame, "Reached end of photo album");
        return;
      }

      currSnapshotIndex++;

//      shapesPanel.setVisible(false);
      currSnapshot = album.getSnapshotList().get(currSnapshotIndex);
      showSnapshot();
      updateTsLabel();
    }
  }


  private class PrevSnapListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      // Pass list of snapshots
      if (currSnapshotIndex == 0) {
        JOptionPane.showMessageDialog(frame, "No more snapshots before this one.");
        return;
      }

      currSnapshotIndex--;

      shapesPanel.setVisible(false);
      // get current snapshot
      currSnapshot = album.getSnapshotList().get(currSnapshotIndex);
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

    // Getting snapshot at index of currSnapshotIndex
    currPanel = new DrawPanel(currSnapshot);
    shapesPanel.add(currPanel);
    shapesPanel.setVisible(true);
  }

  // Update label when user toggles between snapshots
  private void updateLabel() {
    ISnapshot selectedSnap = album.getSnapshotList().get(currSnapshotIndex);
    shapesLabel.setText("");
    String descText = selectedSnap.getId() + " " + selectedSnap.getDescription();
    shapesLabel.setText(descText);
    shapesLabel.setFont(new Font("SANS_SERIF", 1, 16));
  }

  // TODO: Dropdown Listener
  private class DropdownListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {

      System.out.println("Showing dropdown");
      currSnapshotIndex = dropdownMenu().getSelectedIndex();

      shapesPanel.setVisible(false);
      System.out.println("currSnapshotIndex " + currSnapshotIndex);
      String id = album.getSnapshotIds(album.getSnapshotList()).get(currSnapshotIndex);
      System.out.println("id " + id);

      currSnapshot = album.getSnapshot(id);
      showSnapshot();
      updateLabel();

    }
  }

  private JComboBox dropdownMenu() {
    ArrayList<String> snapIds = album.getSnapshotIds(album.getSnapshotList());
    JComboBox menu = new JComboBox(snapIds.toArray());
    menu.setPreferredSize(new Dimension(200, 80));
    menu.addActionListener(new DropdownListener());
    return menu;
  };

  public static void main(String[] args) {

    Album album = new Album();
    ICanvas canvas = album.getCanvas();

    Point2D position = new Point2D.Double(100, 0);

    Point2D position2 = new Point2D.Double(30, 30);

    canvas.createShape("1", "oval1", position,
            100, 100, new Color(200,200,1), "oval");

    album.createSnapshot("First snap", canvas);

    canvas.createShape("2", "rect1", position2,
            300, 300, new Color(1,200,1), "rectangle");

    // Delay the timing of the snapshot so that it can be saved
    try {
      Thread.sleep(8);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    album.createSnapshot("Second snap", canvas);

    new GraphicalView(album);

  }
}
