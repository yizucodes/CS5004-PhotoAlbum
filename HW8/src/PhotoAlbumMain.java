import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import model.Album;
import controller.Controller;


/**
 * The type Main.
 */
public class PhotoAlbumMain {

  public static final int REQUIRED_WEB_VIEW_ARGS = 6;
  public static final int REQUIRED_GRAPHICAL_VIEW_ARGS = 4;
  public static final int ERROR_STATUS = 2;
  public static final int INDEX_DISTANCE_XMAX = 2;
  public static final int INDEX_DISTANCE_YMAX = 3;

  /**
   * Entry point of application.
   *
   * @param args input arguments from command line.
   * @throws FileNotFoundException File not found exception.
   */
  public static void main(String[] args) throws FileNotFoundException {
    // Using ArrayList to hold string arguments as terminal command input's size varies

    ArrayList<String> arguments = (ArrayList<String>) Arrays.stream(args).collect(Collectors.toList());
    File inFile = null;
    File out = null;
    String viewType = "";
    int xMax = -1;
    int yMax = -1;
    Album album = new Album();

    if((!arguments.contains("-v") && !arguments.contains("-view") || !arguments.contains("-in"))) {
      System.exit(ERROR_STATUS);
    }

    if(arguments.contains("-in")) {
      int index = 0;
      for(;index < args.length; index++) {
        if(arguments.get(index).equalsIgnoreCase("-in")) {
          break;
        }
      }
      inFile = new File(arguments.get(index + 1));
    }

    if(arguments.contains("-out")) {
      int index = 0;
      for(;index < args.length; index++) {
        if(arguments.get(index).equalsIgnoreCase("-out")) {
          break;
        }
      }
      out = new File(arguments.get(index + 1));
    }

    if(arguments.contains("-v") || arguments.contains("-view")) {
      int index = 0;
      for(;index < args.length; index++) {
        if(arguments.get(index).equalsIgnoreCase("-v") || arguments.get(index).equalsIgnoreCase("-view")) {
          break;
        }
      }
      int viewInd = index;
      viewType = arguments.get(index + 1);

      if(viewType.equalsIgnoreCase("web")) {
        if(!arguments.contains("-out")){
          System.exit(ERROR_STATUS);
        }
        for(;index < args.length; index++) {
          if (arguments.get(index).equalsIgnoreCase("-out")) {
            break;
          }

        }
        out = new File(arguments.get(index + 1));
        if(arguments.size() > REQUIRED_WEB_VIEW_ARGS) {
          // Adding indices to get respected maximum size of window (x and y)
          xMax = Integer.parseInt(arguments.get(viewInd + INDEX_DISTANCE_XMAX));
          yMax = Integer.parseInt(arguments.get(viewInd + INDEX_DISTANCE_YMAX));
        }
      } else if(viewType.equalsIgnoreCase("graphical")) {
        if(arguments.size() > REQUIRED_GRAPHICAL_VIEW_ARGS) {
          // Adding indices to get respected maximum size of window (x and y)
          xMax = Integer.parseInt(arguments.get(viewInd + INDEX_DISTANCE_XMAX));
          yMax = Integer.parseInt(arguments.get(viewInd + INDEX_DISTANCE_YMAX));
        }
      }
    }

    new Controller(inFile, xMax, yMax, album, out, viewType).ParseFile();

  }
}
