package model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Snapshot implementation of the ISnapshot interface.
 */
public class SnapshotImpl implements ISnapshot {
  private static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss";
  private static final String TIMESTAMP_ID_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS";
  private String id; // Format of timestamp, example: 2022-03-30T11:51:02.174193
  private String timestamp;
  private String description;

  private ICanvas canvas;

  /**
   * Constructor for SnapshotImp.
   * @param description String - description
   * @param canvas ICanvas - canvas that will be taken as a snapshot.
   *
   */
  public SnapshotImpl(String description, ICanvas canvas) throws IllegalArgumentException {

    if (description == null) {
      throw new IllegalArgumentException("Description cannot be null");
    }

    if (canvas == null) {
      throw new IllegalArgumentException("Canvas cannot be null");
    }

    Timestamp currTimestamp = new Timestamp(System.currentTimeMillis());

    this.id = new SimpleDateFormat(TIMESTAMP_ID_FORMAT).format(currTimestamp);
    this.timestamp = new SimpleDateFormat(TIMESTAMP_FORMAT).format(currTimestamp);
    this.description = description;
    this.canvas = canvas;

  }

  /**
   * Get id of snapshot.
   *
   * @return String id
   */
  public String getId() {
    return this.id;
  }

  /**
   * Set id of snapshot.
   * For testing purposes as the timestamp changes which marks the assertEquals method wrong.
   */
  @Override
  public void setId(String id) {
    this.id = id;
  }

  /**
   * Get timestamp of snapshot.
   *
   * @return Timestamp timestamp
   */

  public String getTimestamp() {
    return this.timestamp;
  }

  /**
   * Set timestamp of snapshot.
   * For testing purposes as the timestamp changes which marks the assertEquals method wrong.
   */
  @Override
  public void setTimestamp(String ts) {
    this.timestamp = timestamp;
  }

  /**
   * Get description of snapshot.
   *
   * @return String description
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Get canvas associated with snapshot.
   *
   * @return String id
   */
  @Override
  public ICanvas getCanvas() {
    return this.canvas;
  }

  /**
   * Print out string version of snapshot with its values.
   *
   * @return String
   */
  @Override
  public String toString() {
    return "Printing Snapshots\n"
            + "Snapshot ID: " + this.id + '\n'
            + "Timestamp: " + this.timestamp + '\n'
            + "Description: " + this.description + '\n'
            + "Shape Information\n"
            +  this.canvas.getShapeList() + "\n";
  }
}
