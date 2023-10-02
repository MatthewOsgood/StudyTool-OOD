package cs3500.pa02;

import java.util.Comparator;

/**
 * used to compare paths by creation date in increasing order
 */
public class CreatedComparator implements Comparator<MarkDownFile> {

  /**
   * compares creation time of o1 with that of o2
   *
   * @param o1 the first object to be compared.
   * @param o2 the second object to be compared.
   * @return negative if o1 comes before o2, 0 if they are equal, and positive if o1 comes after o2
   */
  @Override
  public int compare(MarkDownFile o1, MarkDownFile o2) {
    return o1.getCreationTime().compareTo(o2.getCreationTime());
  }
}
