package cs3500.pa02;

import java.nio.file.Path;
import java.util.Comparator;

/**
 * used to compare files by file name in increasing alphabetical order
 */
public class FileNameComparator implements Comparator<Path> {

  /**
   * compares the given paths
   *
   * @param o1 the first object to be compared.
   * @param o2 the second object to be compared.
   * @return negative if o1 comes before o2, 0 if they are equal, and positive if o1 comes after o2
   */
  @Override
  public int compare(Path o1, Path o2) {
    return o1.getFileName().toString().compareTo(o2.getFileName().toString());
  }
}
