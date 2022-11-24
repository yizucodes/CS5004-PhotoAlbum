package model;

import java.sql.Timestamp;
import java.util.ArrayList;

public class SnapshotImpl {
  private String id; // Format of timestamp, example: 2022-03-30T11:51:02.174193
  private Timestamp timestamp;
  private String description;
  private ArrayList<IShape> shapeList;

  public SnapshotImpl(String id, Timestamp timestamp, String description, ArrayList<IShape> shapeList) throws IllegalArgumentException {
    if (id == null || id.equals("")) {
      throw new IllegalArgumentException("Id cannot be null or empty string");
    }

    if (description == null || description.equals("")) {
      throw new IllegalArgumentException("Description cannot be null or empty string");
    }

    if (timestamp == null) {
      throw new IllegalArgumentException("Timestamp cannot be null");
    }

    if (shapeList == null) {
      throw new IllegalArgumentException("List of shapes cannot be null");
    }


    // TODO: Checking whether to validate timestamp for format?

    this.id = id;
    this.timestamp = timestamp;
    this.description = description;
    this.shapeList = shapeList;

  }

  public String getId() {
    return this.id;
  }

  public Timestamp getTimestamp() {
    return this.timestamp;
  }

  public String getDescription() {
    return this.description;
  }

  public ArrayList<IShape> getShapeList() {
    return this.shapeList;
  }

  @Override
  public String toString() {
    return "Printing Snapshots\n"
            + "Snapshot ID: " + this.id + '\n'
            + "Timestamp: " + this.timestamp + '\n'
            + "Description: " + this.description + '\n'
            + "Shape Information\n"
            +  shapeList + "\n";
  }
}
