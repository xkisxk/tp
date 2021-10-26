package seedu.duke.testing;

import seedu.duke.flashcard.FlashCard;

/**
 * Represents information about a single user answer when test function is invoked.
 */
public class Answer {
    private String answer;
    private final int questionIndex;
    private boolean isAnswered;

    public Answer(String answer, int questionIndex, Boolean isAnswered) {
        this.answer = answer;
        this.questionIndex = questionIndex;
        this.isAnswered = isAnswered;
    }

    public Answer(String answer, int questionIndex) {
        this.answer = answer;
        this.questionIndex = questionIndex;
        this.isAnswered = false;
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

    public void setIsAnswered() {
        this.isAnswered = true;
    }

    public Boolean isAnswered() {
        return isAnswered;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
