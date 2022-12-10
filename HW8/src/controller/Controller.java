package controller;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import model.Album;
import model.ICanvas;
import model.IShape;

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

  static Scanner scan = new Scanner(System.in);
  File file;
  private int maxHeight;
  private int maxWidth;

  // Instantiate model
  Album album = new Album();
  ICanvas canvas = album.createCanvas("canvas");


  public Controller(File file, int maxHeight, int maxWidth) throws IllegalArgumentException, FileNotFoundException {

    if (file == null) {
      throw new IllegalArgumentException("File cannot be null");
    }

    // Default if inputs are not valid based on prompt: "a default value of 1000 is used for both x (width) and y (height)";
    if (maxHeight <= 0) {
      maxHeight = DEFAULT_X_HEIGHT;
    }
    if (maxWidth <= 0) {
      maxWidth = DEFAULT_Y_WIDTH;
    }
    this.file = file;
    this.maxHeight = maxHeight;

  }

  // id and name are the same for this purpose
  // TODO: Check why not having static is ok
//  public static void main(String[] args) {

  // TODO: Make it read a file
public void Control(String[] args) {

    // Find current directory and pass that
    File file = new File("filename.txt");
    String input = scan.nextLine().trim();
    String[] inputSplit = input.split(" ");

    // TODO: Check if it works - Ignore first line
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
      IShape newShape = canvas.createShape(idAndName, idAndName, position, dim1, dim2, color, shapeType);
      canvas.createShape(newShape);
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

      // Get all the words after the first one for description
      String description = input.substring(1, inputSplit.length - 1);
      album.createSnapshot(description, canvas);

  }

  // TODO: At the end of the file, init the web view OR GUI based on user input


  }

//  public static void main(String[] args) {
//    Controller controller = new Controller()
//  }






}
