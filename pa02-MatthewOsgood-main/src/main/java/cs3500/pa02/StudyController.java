package cs3500.pa02;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * controls the spaced repetition study session functionality
 */
public class StudyController {

  /**
   * takes in user input
   */
  private final Scanner input;
  /**
   * writes to some output
   */
  private final View view;
  /**
   * keeps track of the study session state
   */
  private StudySession model;
  /**
   * the Question that is currently being worked with
   */
  private Question current;

  /**
   * constructor
   *
   * @param input the input that will be scanned for interaction
   * @param view the view that will be used for output
   */
  public StudyController(Readable input, View view) {
    this.input = new Scanner(input);
    this.view = view;
  }

  /**
   * runs the study session
   */
  public void study() {
    try {
      this.view.write("Welcome! Please give the path to the .sr file you would like to study.");
      String srFile = this.input.next();
      Path srPath = Path.of(srFile);
      this.view.write("How many questions would you like to be asked?");
      int numOfQuestions = input.nextInt();
      this.model = new StudySession(numOfQuestions);

      this.model.parseSr(srPath);

      this.askQuestions();

      int[] stats = this.model.stats();
      this.view.writeStats(stats);
      this.model.write(srPath);
    } catch (IOException e) {
      throw new IllegalArgumentException("study session failed: " + e.getMessage());
    }
  }

  /**
   * asks all the questions in for this study session
   *
   * @throws IOException in the case of an input/output exception
   */
  private void askQuestions() throws IOException {
    while (this.model.hasNext()) {
      this.current = model.next();
      this.view.write(current.getQuestion());
      this.view.writeInputOptions(true);
      this.handlePlayerResponse();
    }
  }

  /**
   * handles the input options that the user has while answering questions
   *
   * @throws IOException when the output cannot be written to
   */
  private void handlePlayerResponse() throws IOException {
    int response = input.nextInt();
    switch (response) {
      case 1 -> {
        if (this.current.difficulty().equals("easy")) {
          this.model.updateDifficulty(this.current);
        }
      }
      case 2 -> {
        if (this.current.difficulty().equals("hard")) {
          this.model.updateDifficulty(this.current);
        }
      }
      case 3 -> {
        this.view.write(this.current.getAnswer());
        this.view.writeInputOptions(false);
        this.handlePlayerResponse();
      }
      default -> this.handlePlayerResponse();
    }
  }
}
