package cs3500.pa02;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudySessionTest {
  StudySession studySession;
  StudySession badStudySession;
  StudySession empty;
  Path sample;
  Question q1;
  Question q2;
  ArrayList<Question> questions;
  Scanner scanner;
  Path output;
  int[] stats1;
  int[] stats2;
  int[] stats3;

  /**
   * initializes tests
   */
  @BeforeEach
  void init() {
    empty = new StudySession(0);
    studySession = new StudySession(3);
    badStudySession = new StudySession(10);
    sample = Path.of("Sample.sr");
    try {
      studySession.parseSr(sample);
    } catch (IOException e) {
      fail("could not parse Sample.sr file");
    }
    q1 = new HardQ(
        "This is a question?", "This is the answer to the above question.");
    q2 = new EasyQ("What color is the sky?", "blue");
    questions = new ArrayList<>(Arrays.asList(q1, q2));
    output = Path.of("testOutput.sr");
    stats1 = new int[] {0, 0, 0, 1, 1};
    stats2 = new int[] {1, 1, 0, 2, 0};
    stats3 = new int[] {2, 1, 1, 1, 1};
  }

  /**
   * deletes contents of output file
   */
  @AfterEach
  void clean() {
    try (FileWriter deleter = new FileWriter(output.toFile())) {
      deleter.write("");
    } catch (IOException e) {
      fail("could not delete contents of testOutput.sr");
    }
  }


  /**
   * tests hasNext and next methods
   */
  @Test
  void testIteration() {
    assertFalse(empty.hasNext());
    assertThrows(
        IndexOutOfBoundsException.class,
        () -> empty.next()
    );
    assertTrue(studySession.hasNext());
    assertTrue(questions.contains(studySession.next()));
    assertTrue(studySession.hasNext());
    assertTrue(questions.contains(studySession.next()));
  }

  /**
   * tests that parse throws an error if the .sr file is malformed
   */
  @Test
  void testParseError() {
    assertThrows(
        IOException.class,
        () -> badStudySession.parseSr(Path.of("badSample.sr"))
    );
  }

  /**
   * tests the write method
   */
  @Test
  void testWrite() {
    try {
      studySession.write(output);
    } catch (IOException e) {
      fail("could not write to testOutput.sr");
    }
    try {
      scanner = new Scanner(output);
    } catch (IOException e) {
      fail("testOutput.sr was not created");
    }
    assertEquals("This is a question?", scanner.nextLine());
    assertEquals("This is the answer to the above question.", scanner.nextLine());
    assertEquals("hard", scanner.nextLine());
    assertEquals("What color is the sky?", scanner.nextLine());
    assertEquals("blue", scanner.nextLine());
    assertEquals("easy", scanner.nextLine());
  }

  /**
   * tests the stats method
   */
  @Test
  void testStats() {
    assertEquals(stats1[0], studySession.stats()[0]);
    assertEquals(stats1[1], studySession.stats()[1]);
    assertEquals(stats1[2], studySession.stats()[2]);
    assertEquals(stats1[3], studySession.stats()[3]);
    assertEquals(stats1[4], studySession.stats()[4]);
    studySession.next();
    studySession.updateDifficulty(q1);
    assertEquals(stats2[0], studySession.stats()[0]);
    assertEquals(stats2[1], studySession.stats()[1]);
    assertEquals(stats2[2], studySession.stats()[2]);
    assertEquals(stats2[3], studySession.stats()[3]);
    assertEquals(stats2[4], studySession.stats()[4]);
    studySession.next();
    studySession.updateDifficulty(q2);
    assertEquals(stats3[0], studySession.stats()[0]);
    assertEquals(stats3[1], studySession.stats()[1]);
    assertEquals(stats3[2], studySession.stats()[2]);
    assertEquals(stats3[3], studySession.stats()[3]);
    assertEquals(stats3[4], studySession.stats()[4]);
  }
}