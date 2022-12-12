package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

import javax.swing.*;

import model.Album;
import model.ISnapshot;

public class GraphicalView extends JFrame implements IView {
  private static final int WIDTH = 1000;
  private static final int HEIGHT = 1000;
  private ISnapshot currSnapshot;
  private Album album;
  private int currSnapshotIndex = 0; // Counter pointing to current index of snapshot displayed in view

  private JComboBox dropdown;

  private DrawPanel currPanel;
  private JLabel tsLabel = new JLabel("");
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
    renderView();

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

//      shapesPanel.setVisible(false);
      // get current snapshot
      currSnapshot = album.getSnapshotList().get(currSnapshotIndex);
      showSnapshot();
      updateTsLabel();

    }
  }



  private class QuitListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      System.exit(0);
    }
  }

  public void showSnapshot() {

    currPanel.draw(currSnapshot);
    shapesPanel.add(currPanel);
    shapesPanel.setVisible(true);
  }

  // Update label when user toggles between snapshots
  private void updateTsLabel() {
    ISnapshot selectedSnap = album.getSnapshotList().get(currSnapshotIndex);
    tsLabel.setText("");
    String descText = selectedSnap.getId() + " " + selectedSnap.getDescription();
    tsLabel.setText(descText);
    tsLabel.setFont(new Font("SANS_SERIF", 1, 16));
  }

//  // TODO: Dropdown Listener
  private class DropdownListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      currSnapshotIndex = dropdownMenu().getSelectedIndex();

      String id = album.getSnapshotIds(album.getSnapshotList()).get(currSnapshotIndex);

      currSnapshot = album.getSnapshot(id);
      showSnapshot();
      updateTsLabel();

    }
  }

  private JComboBox dropdownMenu() {
    ArrayList<String> snapIds = album.getSnapshotIds(album.getSnapshotList());

    JComboBox menu = new JComboBox(snapIds.toArray());
    menu.setPreferredSize(new Dimension(200, 80));
    menu.addActionListener(new DropdownListener());
    return menu;
  };

  @Override
  public void renderView() {
    this.currSnapshot = album.getSnapshotList().get(currSnapshotIndex);

    frame.setSize(WIDTH, HEIGHT);
    frame.setLayout(new BorderLayout());
    topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    shapesPanel.setBorder(BorderFactory.createEmptyBorder(100, 10, 10, 10));

    buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    // Top panel label for timestamp
    topPanel.add(tsLabel);
    updateTsLabel();
    // Center panel label for shapes
    shapesPanel.setBackground(new Color(135, 206, 235));

    // Button panel
    buttonPanel.add(prevBtn);
    prevBtn.addActionListener(new PrevSnapListener());

    // Dropdown
    this.dropdown = dropdownMenu();
    buttonPanel.add(this.dropdown);


    buttonPanel.add(nextBtn);
    nextBtn.addActionListener(new NextSnapListener());

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

    currPanel = new DrawPanel(this.currSnapshot);
    currPanel.setSize(100, 100);
    currPanel.setBackground(Color.WHITE);
//    currPanel.setSize(new Dimension(100, 100));
//    currPanel.
    shapesPanel.add(currPanel, BorderLayout.CENTER);

    // 4. Set window to certain size
    // Always set visible last to show everything
    frame.setVisible(true);

  }

}
