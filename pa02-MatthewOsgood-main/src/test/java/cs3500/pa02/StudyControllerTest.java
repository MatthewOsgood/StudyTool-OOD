package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.StringReader;
import org.junit.jupiter.api.Test;

/**
 * tests StudyController class
 */
class StudyControllerTest {

  String testInput1 = """
      testStudyController.sr
      2
      3
      4
      1
      2
      """;

  String testInput2 = """
      testStudyController2.sr
      3
      2
      3
      1
      """;

  String expectedOutput = """
            Welcome! Please give the path to the .sr file you would like to study.
            How many questions would you like to be asked?
            foo?
            [1] Hard  [2] Easy  [3] See Answer
            bar
            [1] Hard  [2] Easy
            What color is the sky?
            [1] Hard  [2] Easy  [3] See Answer
            Questions answered: 2
            Questions marked as easy: 0
            Questions marked as hard: 0
            Current number of easy questions: 1
            Current number of hard questions: 1
            """;
  StringReader input1 = new StringReader(testInput1);
  StringReader input2 = new StringReader(testInput2);
  StringBuilder output1 = new StringBuilder();
  StringBuilder output2 = new StringBuilder();
  View view1 = new View(output1);
  View view2 = new View(output2);
  StudyController controller1 = new StudyController(input1, view1);
  StudyController controller2 = new StudyController(input2, view2);

  /**
   * tests study method
   */
  @Test
  void testStudy() {
    controller1.study();
    assertEquals(expectedOutput, output1.toString());
    controller2.study();
    assertTrue(output2.toString().contains("Questions marked as easy: 1"));
    assertTrue(output2.toString().contains("Questions marked as hard: 1"));
  }
}