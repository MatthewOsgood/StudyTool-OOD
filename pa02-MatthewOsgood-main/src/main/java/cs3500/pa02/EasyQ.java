package cs3500.pa02;

/**
 * represents an easy QA
 */
public class EasyQ extends Question {

  /**
   * creates an EasyQ with the given question and answer
   *
   * @param question the questions
   * @param answer the answer to the question
   */
  public EasyQ(String question, String answer) {
    super(question, answer);
  }

  /**
   * gets the difficulty of this as a string
   *
   * @return the difficulty level of this QA as a string
   */
  @Override
  public String difficulty() {
    return "easy";
  }

  /**
   * Swaps the difficulty of this Question between easy and hard
   *
   * @return a copy of this as a HardQ
   */
  @Override
  public Question updateDifficulty() {
    return new HardQ(this.question, this.answer);
  }

  /**
   * checks if this is equal to the given object
   *
   * @param o the reference object with which to compare
   * @return if the given object has the same fields as this object
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof EasyQ) {
      return this.question.equals(((EasyQ) o).question)
          && this.answer.equals(((EasyQ) o).answer);
    } else {
      return false;
    }
  }
}
