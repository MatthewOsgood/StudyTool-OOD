package cs3500.pa02;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SummarizeControllerTest {
  Path notes = Path.of("sampleNotes");
  Path output = Path.of("testFileWrite.md");
  SummarizeController controller = new SummarizeController(notes, SortType.FILENAME, output);
  Scanner mdScanner;
  Scanner srScanner;

  /**
   * initializes scanner before tests
   */
  @BeforeEach
  void init() {
    controller.summarize();
    try {
      mdScanner = new Scanner(output);
      srScanner = new Scanner(Path.of("testFileWrite.sr"));
    } catch (IOException e) {
      fail("could not create scanner for output");
    }
  }

  /**
   * deletes the test output file
   */
  @AfterEach
  void cleanUp() {
    assertTrue(output.toFile().delete());
    assertTrue(new File("testFileWrite.sr").delete());
  }

  @Test
  void testmd() {
    assertEquals("# Java Arrays", mdScanner.nextLine());
    assertEquals("- An **array** is a collection of variables of the same type",
        mdScanner.nextLine());
    assertEquals("", mdScanner.nextLine());
    assertEquals("## Declaring an Array", mdScanner.nextLine());
    assertEquals("- General Form: type[] arrayName;", mdScanner.nextLine());
    assertEquals("- only creates a reference", mdScanner.nextLine());
    assertEquals("- no array has actually been created yet", mdScanner.nextLine());
    assertEquals("", mdScanner.nextLine());
    assertEquals("## Creating an Array (Instantiation)", mdScanner.nextLine());
    assertEquals("- General form:  arrayName = new type[numberOfElements];",
        mdScanner.nextLine());
    assertEquals("- numberOfElements must be a positive Integer.", mdScanner.nextLine());
    assertEquals("- Gotcha: Array size is not modifiable once instantiated.",
        mdScanner.nextLine());
    assertEquals("", mdScanner.nextLine());
    assertEquals("# Vectors", mdScanner.nextLine());
    assertEquals("- Vectors act like resizable arrays", mdScanner.nextLine());
    assertEquals("", mdScanner.nextLine());
    assertEquals("## Declaring a vector", mdScanner.nextLine());
    assertEquals("- General Form: Vector<type> v = new Vector();", mdScanner.nextLine());
    assertEquals("- type needs to be a valid reference type", mdScanner.nextLine());
    assertEquals("", mdScanner.nextLine());
    assertEquals("## Adding an element to a vector", mdScanner.nextLine());
    assertEquals("- v.add(object of type);", mdScanner.nextLine());
  }

  @Test
  void testsr() {
    assertEquals("Example question 1", srScanner.nextLine());
    assertEquals("Example answer 1", srScanner.nextLine());
    assertEquals("hard", srScanner.nextLine());
    assertEquals("This is another question", srScanner.nextLine());
    assertEquals("this is the answer", srScanner.nextLine());
    assertEquals("hard", srScanner.nextLine());
    assertEquals("idek anymore", srScanner.nextLine());
    assertEquals("who would format a QA block like this", srScanner.nextLine());
    assertEquals("hard", srScanner.nextLine());
    assertFalse(srScanner.hasNextLine());
  }

  @Test
  void testErrors() {

  }
}