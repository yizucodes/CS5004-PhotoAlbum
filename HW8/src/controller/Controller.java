package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import model.Album;

public class Controller {

  private static final int DEFAULT_X_HEIGHT = 1000;
  private static final int DEFAULT_Y_WIDTH = 1000;
  File file;

  // Instantiate model


  private int maxHeight;
  private int maxWidth;

  Album album;


  public Controller(File file, int maxHeight, int maxWidth) throws IllegalArgumentException, FileNotFoundException {
    if (file == null) {
      throw new IllegalArgumentException("File cannot be null");
    }

    if (maxHeight <= 0) {
      maxHeight = DEFAULT_X_HEIGHT;
    }
    if (maxWidth <= 0) {
      maxWidth = DEFAULT_Y_WIDTH;
    }
    this.file = file;
    this.maxHeight = maxHeight;
    this.maxWidth = maxWidth;
    album = new Album();
  }


  // Scanner scans input file
  Scanner scan = new Scanner(System.in);
//  scan.nextLine()

  // nextLine[0] == "shape"
  // Then go to next line

  //  nextLine[0] == "shape"
  // album.getCanvas().createShape(...args)


  //  nextLine[0] == "snapshot"
//  createSnapshot(String description, ICanvas canvas): ISnapshot
//  album.createSnapshot(String description, ICanvas canvas);

}
