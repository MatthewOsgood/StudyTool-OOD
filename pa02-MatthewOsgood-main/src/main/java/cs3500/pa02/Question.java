package cs3500.pa02;

/**
 * represents a questions and answer phrase
 */
public abstract class Question {
  /**
   * the question
   */
  protected final String question;
  /**
   * the answer
   */
  protected final String answer;

  /**
   * creates a Question with the given question and answer
   *
   * @param question the question
   * @param answer the answer to the question
   */
  public Question(String question, String answer) {
    this.question = question.trim();
    this.answer = answer.trim();
  }

  /**
   * gets the difficulty of this Question as a string
   *
   * @return the difficulty level of this QA as a string
   */
  public abstract String difficulty();

  /**
   * Swaps the difficulty of this QA between easy and hard
   *
   * @return a copy of this Question with the opposite difficulty
   */
  public abstract Question updateDifficulty();

  /**
   * gets the question to this Question
   *
   * @return  the question of this QA
   */
  protected String getQuestion() {
    return this.question;
  }

  /**
   * gets the answer to this Question
   *
   * @return the answer to this QA
   */
  protected String getAnswer() {
    return this.answer;
  }

  /**
   * checks if this is the equal to the given object
   *
   * @param o the reference object with which to compare
   * @return if the given object has the same fields as this object
   */
  @Override
  public abstract boolean equals(Object o);

  /**
   * calculates the hashcode of this Question
   *
   * @return the hashcode of this Question
   */
  @Override
  public int hashCode() {
    return this.question.hashCode()
        + this.answer.hashCode()
        + this.difficulty().hashCode();
  }

  /**
   * turns this question into a formatted string
   *
   * @return the string representation of this Question
   */
  @Override
  public String toString() {
    return this.question + "\n"
        + this.answer + "\n"
        + this.difficulty() + "\n";
  }

}
