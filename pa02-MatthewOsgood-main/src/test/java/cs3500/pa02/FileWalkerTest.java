package cs3500.pa02;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FileWalkerTest {
  FileWalker fileWalkerName;
  FileWalker fileWalkerCreated;
  FileWalker fileWalkerModified;
  Path notesPath;
  Path testNotes1;
  Path testNotes2;
  Path badPath;
  FileTime time1;
  FileTime time2;
  MarkDownFile mdFile1;
  MarkDownFile mdFile2;


  /**
   * initializes all variables
   */
  @BeforeEach
  void init() {
    notesPath = Path.of("sampleNotes");
    testNotes1 = Path.of("sampleNotes/testNotes1.md");
    assertTrue(testNotes1.toFile().setLastModified(200000000));
    testNotes2 = Path.of("sampleNotes/moreNotes/testNotes2.md");
    assertTrue(testNotes2.toFile().setLastModified(100000000));
    badPath = Path.of("Invalid Path for testing");
    time1 = FileTime.from(Instant.parse("2022-05-12T17:35:42Z"));
    time2 = FileTime.from(Instant.parse("2023-05-12T17:35:42Z"));
    mdFile1 = new MarkDownFile(testNotes1, time1);
    mdFile2 = new MarkDownFile(testNotes2, time2);
    fileWalkerName = new FileWalker(SortType.FILENAME);
    fileWalkerModified = new FileWalker(SortType.MODIFIED);
    fileWalkerCreated = new FileWalker(SortType.CREATED);
    fileWalkerCreated.mdFiles.add(mdFile1);
    fileWalkerCreated.mdFiles.add(mdFile2);
  }


  /**
   * tests a successful case of fileWalker
   */
  @Test
  void testFileWalker() {
    try {
      Files.walkFileTree(notesPath, fileWalkerName);
    } catch (IOException e) {
      fail("fileWalker failed. Path may be invalid");
    }
    assertEquals(2, fileWalkerName.getFilePaths().size());
    assertEquals(testNotes2, fileWalkerName.getFilePaths().get(0));
    assertEquals(testNotes1, fileWalkerName.getFilePaths().get(1));
  }

  /**
   * tests that if a file fails to visit the visitFile method returns CONTINUE
   */
  @Test
  void testHandleVisitFileFailed() {
    assertEquals(FileVisitResult.CONTINUE,
        fileWalkerCreated.visitFileFailed(badPath, new IOException("TEST")));
    assertTrue(fileWalkerCreated.handleVisitFileFailed(badPath, new IOException()));
  }

  /**
   * tests the sort method
   */
  @Test
  void testSort() {
    try {
      Files.walkFileTree(notesPath, fileWalkerName);
      Files.walkFileTree(notesPath, fileWalkerModified);
    } catch (IOException e) {
      fail("walkFileTree failed. Path may be invalid");
    }

    ArrayList<Path> sortedByName = new ArrayList<>(Arrays.asList(testNotes1, testNotes2));
    assertEquals(sortedByName, fileWalkerName.sort());

    ArrayList<Path> sortedByCreated = new ArrayList<>(Arrays.asList(testNotes1, testNotes2));
    assertEquals(sortedByCreated, fileWalkerCreated.sort());

    ArrayList<Path> sortedByModified = new ArrayList<>(Arrays.asList(testNotes2, testNotes1));
    assertEquals(sortedByModified, fileWalkerModified.sort());
  }
}