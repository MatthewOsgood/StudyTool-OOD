package cs3500.pa02;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

class QuestionTest {
  EasyQ easyQ = new EasyQ("Test question", "Test answer");
  EasyQ easyQ2 = new EasyQ("Test question", "Test answer");
  EasyQ easyQ3 = new EasyQ("asfd", "Tasdf");
  HardQ hardQ = new HardQ("Test question", "Test answer");
  HardQ hardQ2 = new HardQ("Test question", "Test answer");
  HardQ hardQ3 = new HardQ("dfghj", "tydhj");

  @Test
  void getQuestion() {
    assertEquals("Test question", easyQ.getQuestion());
    assertEquals("Test question", hardQ.getQuestion());
  }

  @Test
  void getAnswer() {
    assertEquals("Test answer", easyQ.getAnswer());
    assertEquals("Test answer", hardQ.getAnswer());
  }

  @Test
  void testHashCode() {
    assertEquals(easyQ.hashCode(), easyQ2.hashCode());
    assertEquals(hardQ.hashCode(), hardQ2.hashCode());
    assertNotEquals(easyQ.hashCode(), easyQ3.hashCode());
    assertNotEquals(hardQ.hashCode(), hardQ3.hashCode());
    assertNotEquals(hardQ.hashCode(), easyQ.hashCode());
    assertNotEquals(hardQ.hashCode(), easyQ3.hashCode());
  }

  @Test
  void testToString() {
    String str = "Test question\nTest answer\neasy\n";
    assertEquals(str, easyQ.toString());
  }
}