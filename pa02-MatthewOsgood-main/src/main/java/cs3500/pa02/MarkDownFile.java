package cs3500.pa02;

import java.nio.file.Path;
import java.nio.file.attribute.FileTime;

/**
 * represents a Markdown File with its path and creation time
 */
public class MarkDownFile {

  /**
   * represents the path of the file
   */
  private final Path path;
  /**
   * represents the time the file was created
   */
  private final FileTime creationTime;

  /**
   * creates a MarkDownFile with the given path and time
   *
   * @param path path to the file
   * @param creationTime time the file was created
   */
  public MarkDownFile(Path path, FileTime creationTime) {
    this.path = path;
    this.creationTime = creationTime;
  }

  /**
   * returns the path of this MarkDownFile
   *
   * @return the path of this MarkDownFile
   */
  public Path getPath() {
    return this.path;
  }

  /**
   * returns the time this MarkDownFile was created
   *
   * @return the time this File was created
   */
  public FileTime getCreationTime() {
    return this.creationTime;
  }
}
