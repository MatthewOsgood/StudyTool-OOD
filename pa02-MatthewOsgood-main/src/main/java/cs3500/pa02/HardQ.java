package cs3500.pa02;

/**
 * represents a hard QA
 */
public class HardQ extends Question {

  /**
   * creates a HardQ with the given question and answer
   *
   * @param question the question
   * @param answer the answer to the question
   */
  public HardQ(String question, String answer) {
    super(question, answer);
  }

  /**
   * gets the difficulty as a string
   *
   * @return the difficulty level of this QA as a string
   */
  @Override
  public String difficulty() {
    return "hard";
  }

  /**
   * Swaps the difficulty of this Question between easy and hard
   *
   * @return a copy of this as an EasyQ
   */
  @Override
  public Question updateDifficulty() {
    return new EasyQ(this.question, this.answer);
  }

  /**
   * checks if this is equal to the given object
   *
   * @param o the reference object with which to compare
   * @return if the given object has the same fields as this object
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof HardQ) {
      return this.question.equals(((HardQ) o).question)
          && this.answer.equals(((HardQ) o).answer);
    } else {
      return false;
    }
  }
}
