package cs3500.pa02;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import org.junit.jupiter.api.Test;

/**
 * tests FileNameComparator class
 */
class FileNameComparatorTest {

  FileNameComparator fileNameComparator = new FileNameComparator();
  Path path1 = Path.of("aaa");
  Path path2 = Path.of("zzz");

  /**
   * tests compare method
   */
  @Test
  void testCompare() {
    assertEquals(-1, Math.signum(fileNameComparator.compare(path1, path2)));
    assertEquals(1, Math.signum(fileNameComparator.compare(path2, path1)));
    assertEquals(0, Math.signum(fileNameComparator.compare(path1, Path.of("aaa"))));
  }
}