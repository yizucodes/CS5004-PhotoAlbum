package view;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import model.Album;
import model.IShape;
import model.ISnapshot;

public class WebView implements IView {

  public WebView() {
  }

  public void createWebView(Album album) {
    Objects.requireNonNull(album);
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

      for(IShape shape : shapeList) {
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

  @Override
  public void renderView() {
    return;
  }
}
