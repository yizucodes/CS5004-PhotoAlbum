package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GraphicalView implements ActionListener {

  private static final int WIDTH = 1000;
  private static final int HEIGHT = 1000;

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

  public GraphicalView() {


    frame.setSize(WIDTH, HEIGHT);

    topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    shapesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//    panel.setLayout(new GridLayout(10, 10));

    // Top panel for timestamp
    topPanel.add(tsLabel);

    // Center panel for shapes
    shapesPanel.add(shapesLabel);

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

  }



  public static void main(String[] args) {
    new GraphicalView();
  }
  /**
   * Invoked when an action occurs.
   *
   * @param e the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    System.out.println("next page");
  }
}
