package view;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import model.Album;
import model.ICanvas;
import model.IShape;
import model.ISnapshot;

public class WebView {
  public static void main(String[] args) {

    Album album = new Album();
    System.out.println("album: " + album);

    ICanvas canvas = album.getCanvas();


    Point2D position = new Point2D.Double(0, 0);

    Point2D position2 = new Point2D.Double(30, 30);

    album.getCanvas().createShape("oval1", "oval1", position,50,50,
            new Color(10, 8, 8),"oval");


    album.getCanvas().createShape("rect1", "rect1",position2,50,50,
            new Color(255, 8, 8),"rectangle");

    album.createSnapshot("snap 1", album.getCanvas());

    // Delay the timing of the snapshot so that it can be saved
    try {
      Thread.sleep(8);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    album.createSnapshot("snap 2", album.getCanvas());

    WebView view = new WebView();
    view.createWebView(album);

  }
  public WebView() {
  }

  public void createWebView(Album album) {

    File file = new File("./resources/buildingsOut.html");

    String htmlBody = "<html>\n" +
            "    <head>\n" +
            "        <title>Web View for Shapes</title>\n" +
            "        <style>\n" +
            "            .snapshot {\n" +
            "                border: 5px outset black;\n" +
            "                background-color: white;\n" +
              "              padding: 10px;\n" +
            "            }\n" +
            "        </style>\n" +
            "    </head>\n" +
            "    <body>\n";

    ArrayList<ISnapshot> snapList = album.getSnapshotList();

    for(ISnapshot snap : snapList) {
      htmlBody = htmlBody + "\t\t<div class=" + "\"snapshot\">\n" +
              "\t\t\t<h2>"+ snap.getDescription() +"</h2>\n" +
              "\t\t\t<h2>"+ snap.getId() + "</h2>\n" +
              "\t\t\t\t<svg width=\"1000\" height=\"1000\">\n";

      ArrayList<IShape> shapeList = album.getCanvas().getShapeList();

      System.out.println("shapeList: " + shapeList);

      for(IShape shape : shapeList) {
        System.out.println("in for each for shapeList");
        String shapeType = shape.getClass().getSimpleName();

        String id = shape.getId();
        String name = shape.getName();

        double posX = shape.getPosition().getX();
        double posY = shape.getPosition().getY();
        double dim1 = shape.getDimension1();
        double dim2 = shape.getDimension2();
        int red = shape.getColor().getRed();
        int green = shape.getColor().getGreen();
        int blue = shape.getColor().getBlue();

        if(shapeType.equalsIgnoreCase("rectangle")) {
          htmlBody = htmlBody + "\t\t\t\t\t<rect id = \"" + id +
                  "\" name=\"" + name +
                  "\" x=\"" + posX + "\" y=\"" + posY +
                  "\" width= \"" + dim1 + "\" height= \"" + dim2 +
                  "\" fill= \" rgb(" + red + ", " +
                  green + ", " + blue + ")\" ></rect>\n";

        } else if (shapeType.equalsIgnoreCase("oval")) {
          htmlBody = htmlBody + "\t\t\t\t\t<ellipse id = \"" + id +
                  "\" name=\"" + name +
                  "\" cx=\"" + posX + "\" cy=\"" + posY +
                  "\" rx= \"" + dim1 + "\" ry = \"" + dim2 +
                  "\" fill= \" rgb(" + red + ", " +
                  green + ", " + blue + ")\" ></ellipse>\n";
        }
      }
      htmlBody = htmlBody + "\t\t\t\t</svg>\n" +
              "\t\t\t</div>\n" +
              "\t\t<p></p>\n";
    }
    htmlBody = htmlBody + "\t</body>\n" +
            "</html>";

    try {
      BufferedWriter buffWriter = new BufferedWriter(new FileWriter(file));
      buffWriter.write(htmlBody);
      buffWriter.close();
    } catch (IOException error) {
      throw new RuntimeException(error);
    }

  }
}
