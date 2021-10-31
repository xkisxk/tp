package seedu.cardli.testing;

import org.json.simple.JSONObject;
import seedu.cardli.flashcard.FlashCard;

/**
 * Represents information about a single user answer when test function is invoked.
 */
public class Answer {

    private String answer;

    static final String SEPARATOR = " | ";


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

    @Override
    public String toString() {
        return getAnswer() + SEPARATOR
                + getQuestionIndex() + '\n';
    }

    public JSONObject toJSONObject(){
        JSONObject jsonAnswer = new JSONObject();

        jsonAnswer.put("answer", getAnswer());
        jsonAnswer.put("questionIndex", getQuestionIndex());

        return jsonAnswer;
    }
}
