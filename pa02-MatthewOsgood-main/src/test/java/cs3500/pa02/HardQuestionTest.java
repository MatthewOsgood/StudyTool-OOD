package cs3500.pa02;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

class HardQuestionTest {
  HardQ hardQ = new HardQ("Test Question", "Test Answer");
  HardQ hardQ2 = new HardQ("Test Question", "wrong answer");
  HardQ hardQ3 = new HardQ("wrong question", "Test Answer");
  HardQ hardQ4 = new HardQ("Test Question", "Test Answer");
  EasyQ easyQ = new EasyQ("asf", "asd");

  @Test
  void testDifficulty() {
    assertEquals("hard", hardQ.difficulty());
  }

  @Test
  void testUpdateDifficulty() {
    assertEquals("easy", hardQ.updateDifficulty().difficulty());
    assertEquals("Test Question", hardQ.updateDifficulty().getQuestion());
    assertEquals("Test Answer", hardQ.updateDifficulty().getAnswer());
  }

  @Test
  void testEquals() {
    assertEquals(hardQ, hardQ4);
    assertNotEquals(hardQ, hardQ2);
    assertNotEquals(hardQ, hardQ3);
    assertNotEquals(hardQ, easyQ);
  }
}