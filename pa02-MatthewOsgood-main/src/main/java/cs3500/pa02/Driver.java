package cs3500.pa02;

import java.io.InputStreamReader;
import java.nio.file.Path;

/**
 * This is the main driver of this project.
 */
public class Driver {
  /**
   * Project entry point
   *
   * @param args root of notes folder to be read, ordering flag, output path; or empty for sr mode
   */
  public static void main(String[] args) {
    if (args.length == 3) {
      Path notes = Path.of(args[0]);
      SortType ordering = SortType.of(args[1]);
      Path output = Path.of(args[2]);
      SummarizeController summarize = new SummarizeController(notes, ordering, output);
      summarize.summarize();
    } else if (args.length == 0) {
      InputStreamReader input = new InputStreamReader(System.in);
      View view = new View(System.out);
      StudyController study = new StudyController(input, view);
      study.study();
    } else {
      throw new IllegalArgumentException("invalid number of arguments");
    }

  }
}