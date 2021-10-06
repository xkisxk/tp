package seedu.duke;

/**
 * Represents information about a single user answer when test function is invoked.
 */
public class Answer {
    private String answer;
    private int questionIndex;

    public Answer(String answer, int questionIndex) {
        this.answer = answer;
        this.questionIndex = questionIndex;
    }

    //getter for front
    public String getAnswer() {
        return this.answer;
    }


}
