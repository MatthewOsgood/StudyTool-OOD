package cs3500.pa02;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FileParserTest {
  Path output;
  Path input;
  FileParser fileParser;
  Scanner outputScanner;

  /**
   * to reset all variables
   */
  @BeforeEach
  void init() {
    output = Path.of("testOutput.md");
    input = Path.of("sampleNotes/testNotes1.md");
    fileParser = new FileParser(output);
    try {
      outputScanner = new Scanner(output);
    } catch (IOException e) {
      fail("could not create scanner for the output path");
    }
  }

  /**
   * deletes the contents of the testOutput file
   */
  @AfterEach
  void cleanUp() {
    try {
      new FileWriter(output.toString()).close();
    } catch (IOException e) {
      fail("could not delete contents of testOutput.md");
    }
    try {
      new FileWriter("testOutput.sr").close();
    } catch (IOException e) {
      fail("could not delete contents of testOutput.sr");
    }
  }

  /**
   * to test the parse method
   */
  @Test
  void testParse() {
    try {
      fileParser.parse(input);
      fileParser.parse(Path.of("sampleNotes/moreNotes/testNotes2.md"));
    } catch (IOException e) {
      fail("could not parse files");
    }
    assertEquals("# Java Arrays", outputScanner.nextLine());
    assertEquals("- An **array** is a collection of variables of the same type",
          outputScanner.nextLine());
    assertEquals("", outputScanner.nextLine());
    assertEquals("## Declaring an Array", outputScanner.nextLine());
    assertEquals("- General Form: type[] arrayName;", outputScanner.nextLine());
    assertEquals("- only creates a reference", outputScanner.nextLine());
    assertEquals("- no array has actually been created yet", outputScanner.nextLine());
  }

  /**
   * tests that questions are properly written to the .sr file
   */
  @Test
  void testWriteQuestion() {
    try {
      fileParser.parse(input);
    } catch (IOException e) {
      fail("could not parse " + input.toString());
    }
    try {
      fileParser.parse(Path.of("sampleNotes/moreNotes/testNotes2.md"));
    } catch (IOException e) {
      fail("could not parse " + "sampleNotes/moreNotes/testNotes2.md");
    }
    Scanner srScanner = null;
    try {
      srScanner = new Scanner(Path.of("testOutput.sr"));
    } catch (IOException e) {
      fail("could not scan testOutput.sr");
    }
    assertEquals("Example question 1", srScanner.nextLine());
    assertEquals("Example answer 1", srScanner.nextLine());
    assertEquals("hard", srScanner.nextLine());
    assertEquals("This is another question", srScanner.nextLine());
    assertEquals("this is the answer", srScanner.nextLine());
    assertEquals("hard", srScanner.nextLine());
    assertEquals("idek anymore", srScanner.nextLine());
    assertEquals("who would format a QA block like this", srScanner.nextLine());
    assertEquals("hard", srScanner.nextLine());
  }
}