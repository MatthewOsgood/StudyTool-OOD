package cs3500.pa02;

import java.io.IOException;

/**
 * writes to a given output
 */
public class View {

  /**
   * the output that will be written to
   */
  private final Appendable output;

  /**
   * constructs a View with the given output
   *
   * @param output the output that will be appended to
   */
  public View(Appendable output) {
    this.output = output;
  }

  /**
   * writes the given  message on its own line
   *
   * @param msg the welcome message to be written
   * @throws IOException if the output cannot be written to
   */
  public void write(String msg) throws IOException {
    output.append(msg).append("\n");
  }

  /**
   * writes the input options that a user can input
   *
   * @param seeAnswer whether the user has to option to see answer
   * @throws IOException if the output cannot be written to
   */
  public void writeInputOptions(Boolean seeAnswer) throws IOException {
    output.append("[1] Hard  [2] Easy");
    if (seeAnswer) {
      output.append("  [3] See Answer");
    }
    output.append("\n");
  }

  /**
   * writes the given stats
   * stats must be in the order:
   *  questions answered, marked easy, marked hard, total easy, total hard
   *
   * @param stats the stats that will be written
   */
  public void writeStats(int[] stats) throws IOException {
    this.write("Questions answered: " + stats[0]);
    this.write("Questions marked as easy: " + stats[1]);
    this.write("Questions marked as hard: " + stats[2]);
    this.write("Current number of easy questions: " + stats[3]);
    this.write("Current number of hard questions: " + stats[4]);
  }
}
