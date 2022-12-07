package model;

import java.util.ArrayList;
import java.util.Objects;


/**
 * Class representing an album of snapshots.
 */
public class Album {
  private ArrayList<ISnapshot> snapshotList;
  private ICanvas canvas;

  /**
   * Constructor for Album.
   *
   */
  public Album() {
    this.snapshotList = new ArrayList<ISnapshot>();
    this.canvas = new CanvasImpl("canvas");
  }

  /**
   * Create a snapshot.
   * Deep copy of canvas is used so that shapes snapshot are not mutable after snapshot is created.
   *
   * @return ISnapshot A snapshot featuring the shapes in the canvas.
   */
  public ISnapshot createSnapshot(String description, ICanvas canvas) {

    ICanvas copyCanvas = this.createCanvas(canvas.getId());
    copyCanvas.setShapeList(canvas.getShapeList());

    ISnapshot newSnap = new SnapshotImpl(description, copyCanvas);

    this.snapshotList.add(newSnap);

    return newSnap;
  };

  /**
   * Get list of snapshots.
   *
   * @return ArrayList<ISnapshot>
   */
  public ArrayList<ISnapshot> getSnapshotList() {
    return this.snapshotList;
  }

  /**
   * Get snapshot based on its id.
   *
   * @return ISnapshot
   */
  public ISnapshot getSnapshot(String id) throws IllegalArgumentException {
    if (id == null || id.equals("")) {
      throw new IllegalArgumentException("Invalid shape id");
    }

    ISnapshot result = null;

    for (int index = 0; index < this.snapshotList.size(); index++) {
      String targetId = this.snapshotList.get(index).getId();
      if (Objects.equals(id, targetId)) {
        result = this.snapshotList.get(index);
        break;
      }
    }
    return result;
  }

  /**
   * Create a canvas with an id.
   *
   * @return ICanvas
   */
  public ICanvas createCanvas(String canvasId) throws IllegalArgumentException {
    if (canvasId == null || canvasId.equals("")) {
      throw new IllegalArgumentException("Id cannot be null or empty");
    };

    ICanvas currCanvas = new CanvasImpl(canvasId);
    this.canvas = currCanvas;
    return currCanvas;
  };

  /**
   * Get canvas.
   *
   * @return ICanvas
   */
  public ICanvas getCanvas() {
    return this.canvas;
  }

  /**
   * Remove canvas.
   *
   */
  public void removeCanvas() {
    this.getCanvas().setShapeList(new ArrayList<>());
  };

  /**
   * Remove album.
   *
   */
  public void removeAlbum() {
    this.snapshotList = new ArrayList<ISnapshot>();
  }

  /**
   * Remove snapshot based on its id.
   *
   */
  public void removeSnapshot(String id) {
    this.snapshotList.remove(getSnapshot(id));
  }

  /**
   * Print out string version of album with the list of snapshots.
   *
   * @return String
   */
  @Override
  public String toString() {
    return "Printing Album\n"
            + "List of Snapshots: \n" + snapshotList + "\n";
  }
}
