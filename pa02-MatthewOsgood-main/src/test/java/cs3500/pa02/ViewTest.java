package cs3500.pa02;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ViewTest {

  View view;
  StringBuilder testOutput;
  int[] stats;
  String statsString;

  @BeforeEach
  void init() {
    testOutput = new StringBuilder();
    view = new View(testOutput);
    stats = new int[] {1, 2, 3, 4, 5};
    statsString = """
        Questions answered: 1
        Questions marked as easy: 2
        Questions marked as hard: 3
        Current number of easy questions: 4
        Current number of hard questions: 5
        """;
  }

  @Test
  void testWrite() {
    try {
      view.write("WELCOME");
      assertEquals("WELCOME\n", testOutput.toString());
      view.write("another welcome");
      assertEquals("WELCOME\nanother welcome\n", testOutput.toString());
    } catch (IOException e) {
      fail("failed to write to stringBuilder");
    }
  }

  @Test
  void testWriteInputOptions() {
    try {
      view.writeInputOptions(false);
      assertEquals("[1] Hard  [2] Easy\n", testOutput.toString());
      view.writeInputOptions(true);
      assertEquals("[1] Hard  [2] Easy\n[1] Hard  [2] Easy  [3] See Answer\n",
          testOutput.toString());
    } catch (IOException e) {
      fail("could not write to stringBuilder");
    }
  }

  @Test
  void testWriteStats() {
    try {
      view.writeStats(stats);
    } catch (IOException e) {
      fail("could not write stats");
    }
    assertEquals(statsString, testOutput.toString());
  }
}