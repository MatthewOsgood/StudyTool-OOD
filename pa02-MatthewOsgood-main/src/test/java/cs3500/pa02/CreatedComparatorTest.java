package cs3500.pa02;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import org.junit.jupiter.api.Test;

/**
 * tests CreatedComparator class
 */
class CreatedComparatorTest {

  CreatedComparator createdComparator = new CreatedComparator();
  Path path1 = Path.of("sampleNotes/testNotes1.md");
  FileTime time1 = FileTime.from(Instant.parse("2023-05-12T17:35:42Z"));
  MarkDownFile mdFile1 = new MarkDownFile(path1, time1);
  Path path2 = Path.of("sampleNotes/moreNotes/testNotes2.md");
  FileTime time2 = FileTime.from(Instant.parse("2023-05-13T17:37:11Z"));
  MarkDownFile mdFile2 = new MarkDownFile(path2, time2);


  /**
   * tests compare method
   */
  @Test
  void testCompare() {
    assertEquals(-1, createdComparator.compare(mdFile1, mdFile2));
    assertEquals(1, createdComparator.compare(mdFile2, mdFile1));
    assertEquals(0, createdComparator.compare(mdFile1, new MarkDownFile(path1, time1)));
  }
}