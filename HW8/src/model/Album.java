package model;

import java.util.ArrayList;
import java.util.Objects;

public class Album {
  private String id;
  private ArrayList<ISnapshot> snapshotList;
  private ICanvas canvas;

  public Album() {
    this.snapshotList = new ArrayList<ISnapshot>();
    this.canvas = null;
  }

  // TODO
  public ISnapshot createSnapshot(ICanvas canvas) {
    return null;
  };

  public ArrayList<ISnapshot> getSnapshotList() {
    return this.snapshotList;
  }

  public ISnapshot getSnapshot(String id) {
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

  public ICanvas createCanvas(String canvasId) {
    ICanvas currCanvas = new CanvasImpl(canvasId);
    this.canvas = currCanvas;
    return currCanvas;
  };

  public ICanvas getCanvas() {
    return this.canvas;
  }

  /**
   * Remove canvas.
   *
   */
  public void removeCanvas() {
    this.getCanvas().resetShapeList();
  };

  public void removeAlbum() {
    this.snapshotList = new ArrayList<ISnapshot>();
  }

  public void removeSnapshot(String id) {
    this.snapshotList.remove(getSnapshot(id));
  }


}
