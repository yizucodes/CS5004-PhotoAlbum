//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.Objects;
//import java.util.Scanner;
//
//import controller.IController;
//import model.Album;
//
//public class PhotoAlbumMain {
//
////  MyProgram -in buildings.txt -out myWeb.html  -v web
////
////  MyProgram -in buildings.txt -v graphical 800 800 --> passed to the controller
////
////  Characteristics of a valid input are:
////
////  Each pair of arguments (-in "input-file", -out "output-file", etc.) may appear in any order (e.g. the -view pair can appear first, followed by -in and so on)
////
////  Each pair of arguments are ordered. That is, if the user types -in then the next input must be the name of an input file, and so on.
////
////  Providing an input file (the -in pair) and a view (the -view pair) are mandatory. If the output set is not specified and the view needs it, gracefully exit the program with an error message indicating why the program did not run..
//
//  public static void main(String[] args) {
//    // You read terminal inputs
//
//    // if file
//
//    // Create controller
//
//    // Create model
//
//    // Create view
//
//
//  }
//}
//
//package controller;
//
//
//        import java.io.IOException;
//        import java.io.InputStreamReader;
//        import java.rmi.server.ExportException;
//        import java.util.Objects;
//        import java.util.Scanner;
//
//        import model.Album;
//
//public class Controller implements IController {
//
//  final Readable in;
//  final Appendable out;
//
//  private static final int DEFAULT_X_HEIGHT = 1000;
//  private static final int DEFAULT_Y_WIDTH = 1000;
//
//  private static final int PAIR = 2;
//
//  public Controller(Readable in, Appendable out) {
//    this.in = in;
//    this.out = out;
//  }
//
//  /**
//   * Parse input file.
//   */
//  @Override
//  public void parseFile(Album album) throws IOException {
//    Objects.requireNonNull(album);
//    String token = "";
//    int pairCount = 0;
//
//    boolean hasInputFile = false;
//    boolean hasOutputView = false;
//
//    Scanner scan = new Scanner(this.in);
//
//    // Continue until terminal cmd is ended
//    while(scan.hasNext()) {
//      token = scan.next();
//
//      if (token.equalsIgnoreCase("-in")) {
//
//        // Check if filename before contains .txt, check if there is a char before .txt
//        if (scan.next().contains(".txt")) {
//          // TODO: Read file
//          System.out.println("Reading text file");
//          hasInputFile = true;
//        }
//
//      }
//
//      // Webview
//      if (token.equalsIgnoreCase("-out")) {
//        // Check if filename before contains .html, check if there is a char before .html
//        if (scan.next().contains(".html")) {
//          System.out.println("Display web view");
//          hasOutputView = true;
//        }
//
//      }
//
//      // GUI
//      if (token.equalsIgnoreCase("-v") || token.equalsIgnoreCase("-view") ) {
//        if (scan.next().equalsIgnoreCase("graphical")) {
//          System.out.println("Display java swing view");
//        }
//      }
//
//    }
//
//    // If output set not occurring --> error message
//    if (!(hasInputFile && hasOutputView)) {
//      throw new IOException("Please provide both input text file and view type.");
//    }
//
//    scan.close();
//
//  }
//
//  public static void main(String[] args) {
//    try {
//      new controller.Controller(new InputStreamReader(System.in), System.out).parseFile(new Album());
//
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//  }
//}
