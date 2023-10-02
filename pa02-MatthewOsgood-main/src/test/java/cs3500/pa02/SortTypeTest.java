package cs3500.pa02;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SortTypeTest {

  @Test
  void testCreation() {
    assertEquals(SortType.CREATED, SortType.of("created"));
    assertEquals(SortType.MODIFIED, SortType.of("modified"));
    assertEquals(SortType.FILENAME, SortType.of("filename"));
    assertEquals(SortType.FILENAME, SortType.of("vrnirgsahgsos"));
  }
}