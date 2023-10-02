package cs3500.pa02;

/**
 * represents all possible ways that the files could be sorted
 */

public enum SortType {
  /**
   * represents sorting files by name
   */
  FILENAME,
  /**
   * represents sorting files by creation date
   */
  CREATED,
  /**
   * represents sorting files by last modified date
   */
  MODIFIED;

  /**
   * creates a SortType from the given string
   * returns FILENAME by default
   *
   * @param type sort type
   * @return sort type
   */
  public static SortType of(String type) {
    switch (type) {
      case "created" -> {
        return SortType.CREATED;
      }
      case "modified" -> {
        return SortType.MODIFIED;
      }
      default -> {
        return SortType.FILENAME;
      }
    }
  }
}
