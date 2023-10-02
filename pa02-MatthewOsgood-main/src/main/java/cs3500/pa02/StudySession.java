package cs3500.pa02;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

/**
 * used to read and make .sr files
 */
public class StudySession implements Iterator<Question> {

  /**
   * represents all questions in a given sr file
   * items are never removed from this list
   */
  private final ArrayList<Question> allQuestions;
  /**
   * represents the total number of questions that will be answered
   */
  private final int total;
  /**
   * represents the number of questions that have been answered
   */
  private int current;
  /**
   * keeps track of how many questions have changed from hard to easy
   */
  private int newEasy;
  /**
   * keeps track of how many questions have changed from easy to hard
   */
  private int newHard;

  /**
   * creates a SRParser with all default values
   */
  public StudySession(int numOfQuestions) {
    this.allQuestions = new ArrayList<>();
    this.total = numOfQuestions;
    this.current = 0;
    this.newHard = 0;
    this.newEasy = 0;
  }

  /**
   * Returns {@code true} if the iteration has more elements.
   * (In other words, returns {@code true} if {@link #next} would
   * return an element rather than throwing an exception.)
   *
   * @return {@code true} if the iteration has more elements
   */
  @Override
  public boolean hasNext() {
    return this.current < this.total
        && this.current < this.allQuestions.size();
  }

  /**
   * Returns the next element in the iteration.
   * hard questions always come before easy questions
   *
   * @return the next element in the iteration
   */
  @Override
  public Question next() {
    Question ans = this.allQuestions.get(this.current);
    current++;
    return ans;
  }

  /**
   * parses a given .sr file and updates this class to keep track of the questions
   * the order that the questions are placed in the remaining questions are randomized
   *
   * @param path the .sr file to the read
   * @throws IOException if the file cannot be accessed or is not formatted properly
   */
  public void parseSr(Path path) throws IOException {
    Scanner scanner = new Scanner(path);
    ArrayList<Question> easyQs = new ArrayList<>();
    ArrayList<Question> hardQs = new ArrayList<>();
    while (scanner.hasNextLine()) {
      String question = scanner.nextLine();
      String answer = scanner.nextLine();
      String difficulty = scanner.nextLine();
      if (difficulty.equals("easy")) {
        EasyQ qa = new EasyQ(question, answer);
        easyQs.add(qa);
      } else if (difficulty.equals("hard")) {
        HardQ qa = new HardQ(question, answer);
        hardQs.add(qa);
      } else {
        throw new IOException(path + ": has not been formatted properly");
      }
    }
    Collections.shuffle(easyQs);
    Collections.shuffle(hardQs);
    this.allQuestions.addAll(hardQs);
    this.allQuestions.addAll(easyQs);
  }

  /**
   * writes the questions held by this to the given path
   *
   * @param path the path that will be written to
   */
  public void write(Path path) throws IOException {
    try (FileWriter writer = new FileWriter(path.toFile())) {
      for (Question q : this.allQuestions) {
        writer.write(q.toString());
      }
    }
  }

  /**
   * updates the difficulty of the given question
   *
   * @param q the question of whose difficulty will be updated
   */
  public void updateDifficulty(Question q) {
    int index = this.allQuestions.indexOf(q);
    this.allQuestions.set(index, q.updateDifficulty());
    if (q.difficulty().equals("easy")) {
      this.newHard++;
    } else {
      this.newEasy++;
    }
  }

  /**
   * calculates the stats of this study session and packages them in an array
   * in order: questions answered, marked easy, marked hard, total easy, total hard
   *
   * @return the stats of this study session
   */
  public int[] stats() {
    return new int[] {
        this.current,
        this.newEasy,
        this.newHard,
        this.countEasy(),
        this.countHard()
    };
  }

  /**
   * counts how many easy questions there currently are
   *
   * @return the number of easy questions there currently are
   */
  private int countEasy() {
    int ans = 0;
    for (Question q : this.allQuestions) {
      if (q.difficulty().equals("easy")) {
        ans++;
      }
    }
    return ans;
  }

  /**
   * counts how many hard questions there currently are
   *
   * @return the number of hard questions there currently are
   */
  private int countHard() {
    int ans = 0;
    for (Question q : this.allQuestions) {
      if (q.difficulty().equals("hard")) {
        ans++;
      }
    }
    return ans;
  }

}
