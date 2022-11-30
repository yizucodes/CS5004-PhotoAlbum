package model;

import java.sql.Timestamp;

/**
 * Interface for a snapshot.
 */
public interface ISnapshot {
  /**
   * Get id of snapshot.
   *
   * @return String id
   */
  public String getId();
  /**
   * Get timestamp of snapshot.
   *
   * @return Timestamp timestamp
   */
  public Timestamp getTimestamp();

  /**
   * Get description of snapshot.
   *
   * @return String description
   */
  public String getDescription();

  /**
   * Get canvas associated with snapshot.
   *
   * @return String id
   */
  public ICanvas getCanvas();
  /**
   * Print out string version of snapshot with its values.
   *
   * @return String
   */
  public String toString();
}
