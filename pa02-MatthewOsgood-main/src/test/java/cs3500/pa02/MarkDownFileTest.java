package cs3500.pa02;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MarkDownFileTest {
  Path path;
  FileTime time;
  MarkDownFile mdFile;
  FileTime time2;

  @BeforeEach
  void init() {
    path = Path.of("sampleNotes/testNotes1.md");
    time = FileTime.from(Instant.parse("2023-05-12T17:35:42Z"));
    mdFile = new MarkDownFile(path, time);
    time2 = FileTime.from(Instant.parse("2022-05-12T17:35:42Z"));
  }

  @Test
  void testGetPath() {
    assertEquals(path, mdFile.getPath());
  }

  @Test
  void testGetCreationTime() {
    assertEquals(time, mdFile.getCreationTime());
  }
}