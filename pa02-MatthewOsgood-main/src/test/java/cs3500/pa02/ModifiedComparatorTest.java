package cs3500.pa02;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * tests ModifiedComparator class
 */
class ModifiedComparatorTest {

  ModifiedComparator modifiedComparator = new ModifiedComparator();
  Path path1 = Path.of("sampleNotes/testNotes1.md");
  File file1 = path1.toFile();
  Path path2 = Path.of("sampleNotes/moreNotes/testNotes2.md");
  File file2 = path2.toFile();
  Path pathBad = Path.of("InvalidPath");

  /**
   * to initialize the tests
   */
  @BeforeEach
  void init() {
    assertTrue(file1.setLastModified(87654321));
    assertTrue(file2.setLastModified(12345678));
  }

  /**
   * tests compare method
   */
  @Test
  void testCompare() {
    assertEquals(1, modifiedComparator.compare(path1, path2));
    assertEquals(-1, modifiedComparator.compare(path2, path1));
    assertEquals(0,
        modifiedComparator.compare(path1,
            Path.of("sampleNotes/testNotes1.md")));
    assertThrows(
        IllegalArgumentException.class,
        () -> modifiedComparator.compare(pathBad, path2)
    );
    assertThrows(
        IllegalArgumentException.class,
        () -> modifiedComparator.compare(path1, pathBad)
    );
  }
}