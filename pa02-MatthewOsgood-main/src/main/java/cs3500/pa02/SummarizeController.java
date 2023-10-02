package cs3500.pa02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * represents the summarizing notes into a study guide
 */
public class SummarizeController {

  /**
   * the notes that will be read
   */
  private final Path notes;
  /**
   * how the study guide will be sorted
   */
  private final SortType ordering;
  /**
   * where the study guide will be outputted to
   */
  private final Path output;

  /**
   * constructor
   *
   * @param notes the input notes to be read
   * @param ordering how the notes should be sorted
   * @param output where to output the summarized study guide
   */
  public SummarizeController(Path notes, SortType ordering, Path output) {
    this.notes = notes;
    this.ordering = ordering;
    this.output = output;
  }

  /**
   * summarizes files in this notes path into a single sorted file at this output path
   */
  public void summarize() {
    FileWalker fileWalker = new FileWalker(this.ordering);
    try {
      Files.walkFileTree(notes, fileWalker);
    } catch (IOException e) {
      throw new IllegalArgumentException("could not access notes files: " + e.getMessage());
    }
    ArrayList<Path> sortedPaths = fileWalker.sort();
    FileParser fileParser = new FileParser(this.output);
    for (Path path : sortedPaths) {
      try {
        fileParser.parse(path);
      } catch (IOException e) {
        throw new IllegalArgumentException("could not write to output file: " + e.getMessage());
      }
    }
  }

}
