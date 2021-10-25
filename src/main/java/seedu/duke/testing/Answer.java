package seedu.duke.testing;

import seedu.duke.flashcard.FlashCard;

/**
 * Represents information about a single user answer when test function is invoked.
 */
public class Answer {

    static final String SEPARATOR = " | ";

    private final String answer;
    private final int questionIndex;

    public Answer(String answer, int questionIndex) {
        this.answer = answer;
        this.questionIndex = questionIndex;
    }

    //getter for front
    public String getAnswer() {
        return this.answer;
    }

    public int getQuestionIndex() {
        return this.questionIndex;
    }

    public boolean isCorrect(String userAnswer, FlashCard question) {
        String lowerCaseUserAnswer = userAnswer.toLowerCase();
        String lowerCaseAnswer = question.getBack().toLowerCase();
        return lowerCaseAnswer.equals(lowerCaseUserAnswer);
    }

    @Override
    public String toString() {
        return getAnswer() + SEPARATOR
                + getQuestionIndex() + '\n';
    }
}
