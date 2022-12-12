package controller;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import model.Album;
import model.ICanvas;
import model.IShape;
import view.GraphicalView;
import view.WebView;

/**
 * Class representing the controller.
 */
public class Controller {
  private static final int DEFAULT_X_HEIGHT = 1000;
  private static final int DEFAULT_Y_WIDTH = 1000;

  private static final int INDEX_ID_NAME = 1;
  private static final int INDEX_SHAPE_TYPE = 2;
  private static final int INDEX_X_POS = 3;
  private static final int INDEX_Y_POS = 4;
  private static final int INDEX_DIM1 = 5;
  private static final int INDEX_DIM2 = 6;
  private static final int INDEX_RED = 7;
  private static final int INDEX_GREEN = 8;
  private static final int INDEX_BLUE = 9;

  private Album album;
  static Scanner scan;

  // Input file
  private File file;

  // Output file
  private File out;

  private String viewType;
  private int maxHeight;
  private int maxWidth;

  public Controller(File file, int maxWidth, int maxHeight, Album album, File out, String viewType)
          throws IllegalArgumentException, FileNotFoundException {

    if (file == null) {
      throw new IllegalArgumentException("Null file not allowed.");
    }

    if (album == null) {
      throw new IllegalArgumentException("Null album not allowed.");
    }

    // Based on prompt: "a default value of 1000 is used for both x (width) and y (height)";
    if (maxWidth <= 0) {
      maxWidth = DEFAULT_Y_WIDTH;
    }
    if (maxHeight <= 0) {
      maxHeight = DEFAULT_X_HEIGHT;
    }
    this.file = file;
    this.album = album;
    this.out = out;
    this.viewType = viewType;
    this.maxHeight = maxHeight;
    this.maxWidth = maxWidth;

  }

public void ParseFile() throws FileNotFoundException {


    scan = new Scanner(this.file);

    while (scan.hasNextLine()) {
      String input = scan.nextLine().trim();
      String[] inputSplit = input.split(" ");
      ICanvas canvas = this.album.getCanvas();


      if (inputSplit[0].equalsIgnoreCase("#")) {
        scan.nextLine();
      };

      if (inputSplit[0].equalsIgnoreCase("shape")) {

        Point2D position = new Point2D.Double(Double.parseDouble(inputSplit[INDEX_X_POS]), Double.parseDouble(inputSplit[INDEX_Y_POS]));
        String idAndName = inputSplit[INDEX_ID_NAME];
        double dim1 = Double.parseDouble(inputSplit[INDEX_DIM1]);
        double dim2 = Double.parseDouble(inputSplit[INDEX_DIM2]);
        int red = Integer.parseInt(inputSplit[INDEX_RED]);
        int green = Integer.parseInt(inputSplit[INDEX_GREEN]);
        int blue = Integer.parseInt(inputSplit[INDEX_BLUE]);
        Color color = new Color(red, green, blue);
        String shapeType = inputSplit[INDEX_SHAPE_TYPE];

        // Call model to create shape
        canvas.createShape(idAndName, idAndName, position, dim1, dim2, color, shapeType);
      };

      if (inputSplit[0].equalsIgnoreCase("move")) {
        String idAndName = inputSplit[INDEX_ID_NAME];
        IShape shape = canvas.getShape(idAndName);
        Point2D position = new Point2D.Double(Double.parseDouble(inputSplit[2]), Double.parseDouble(inputSplit[3]));
        canvas.move(shape, position);
      }

      if (inputSplit[0].equalsIgnoreCase("resize")) {
        String idAndName = inputSplit[INDEX_ID_NAME];
        IShape shape = canvas.getShape(idAndName);

        double dim1 = Double.parseDouble(inputSplit[2]);
        double dim2 = Double.parseDouble(inputSplit[3]);

        canvas.scale(shape, dim1, dim2);
      }

      if (inputSplit[0].equalsIgnoreCase("color")) {
        String idAndName = inputSplit[INDEX_ID_NAME];
        IShape shape = canvas.getShape(idAndName);

        int red = Integer.parseInt(inputSplit[2]);
        int green = Integer.parseInt(inputSplit[3]);
        int blue = Integer.parseInt(inputSplit[4]);
        Color color = new Color(red, green, blue);

        canvas.changeColor(shape, color);
      }

      if (inputSplit[0].equalsIgnoreCase("remove")) {
        String idAndName = inputSplit[INDEX_ID_NAME];
        canvas.removeShape(idAndName);
      };

      if (inputSplit[0].equalsIgnoreCase("snapshot")) {
        String description;
        // Check for empty description
        if(inputSplit.length == 1) {
          description = "";
        } else {
          description = input.substring(1, inputSplit.length - 1);
        }
        // Get all the words after the first one for description
        album.createSnapshot(description, canvas);
      }

    }

    if (viewType.equalsIgnoreCase("web")) {
      new WebView().createWebView(this.album);
    } else if (viewType.equalsIgnoreCase("graphical")) {
      new GraphicalView(this.album);
    }

  }




}
