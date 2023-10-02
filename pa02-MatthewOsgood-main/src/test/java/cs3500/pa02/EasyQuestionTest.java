package cs3500.pa02;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

class EasyQuestionTest {
  EasyQ easyQ = new EasyQ("Test Question", "Test Answer");
  EasyQ easyQ2 = new EasyQ("Test Question", "wrong answer");
  EasyQ easyQ3 = new EasyQ("wrong question", "Test Answer");
  EasyQ easyQ4 = new EasyQ("Test Question", "Test Answer");
  HardQ hardq = new HardQ("asd", "zxcvui");

  @Test
  void testDifficulty() {
    assertEquals("easy", easyQ.difficulty());
  }

  @Test
  void testUpdateDifficulty() {
    assertEquals("hard", easyQ.updateDifficulty().difficulty());
    assertEquals("Test Question", easyQ.updateDifficulty().getQuestion());
    assertEquals("Test Answer", easyQ.updateDifficulty().getAnswer());
  }

  @Test
  void testEquals() {
    assertNotEquals(easyQ, easyQ2);
    assertNotEquals(easyQ, easyQ3);
    assertNotEquals(easyQ, hardq);
    assertEquals(easyQ, easyQ4);
  }
}