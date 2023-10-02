package cs3500.pa02;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * tests the driver class
 */
class DriverTest {
  Path notes = Path.of("sampleNotes");
  String fileNameSort = "filename";
  Path output = Path.of("testFileWrite.md");
  Scanner mdScanner;

  /**
   * initializes scanner before tests
   */
  @BeforeEach
  void init() {
    Driver.main(new String[] {notes.toString(), fileNameSort, output.toString()});
    try {
      mdScanner = new Scanner(output);
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
  public void testSummary() {
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
  }

  /**
   * tests that the program fails if an incorrect number of arguments are given
   */
  @Test
  public void testError() {
    assertThrows(IllegalArgumentException.class,
        () -> Driver.main(new String[] {"cannot have 1 argument"})
    );
  }

}