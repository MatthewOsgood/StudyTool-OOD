package cs3500.pa02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Comparator;

/**
 * used to compare paths by their last modified date
 */
public class ModifiedComparator implements Comparator<Path> {

  /**
   * compares the first object with the second object to order them
   *
   * @param o1 the first object to be compared.
   * @param o2 the second object to be compared.
   * @return negative if o1 comes before o2, 0 if they are equal, and positive if o1 comes after o2
   */
  @Override
  public int compare(Path o1, Path o2) {
    BasicFileAttributes attrs1;
    BasicFileAttributes attrs2;
    try {
      attrs1 = Files.readAttributes(o1, BasicFileAttributes.class);
    } catch (IOException e) {
      throw new IllegalArgumentException(o1 + " has no BasicFileAttributes");
    }
    try {
      attrs2 = Files.readAttributes(o2, BasicFileAttributes.class);
    } catch (IOException e) {
      throw new IllegalArgumentException(o2 + " has no BasicFileAttributes");
    }
    return attrs1.lastModifiedTime().compareTo(attrs2.lastModifiedTime());
  }
}
