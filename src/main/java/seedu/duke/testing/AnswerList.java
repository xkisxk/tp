package seedu.duke.testing;

import seedu.duke.flashcard.FlashCard;

import java.util.ArrayList;

public class AnswerList {
    private ArrayList<Answer> answerList;

    public AnswerList() {
        this.answerList = new ArrayList<>();
    }

    public int getAnswerIndex(Answer answer) {
        return answerList.indexOf(answer);
    }

    public ArrayList<Answer> getAnswerList() {
        return answerList;
    }

    public Boolean isEmpty() {
        return answerList.isEmpty();
    }

    public int getSize() {
        return answerList.size();
    }

    /**
     * Saves a new user answer to the current list of user answers.
     *
     * @param answer        String representation of user's answer
     * @param questionIndex Question number for the question that the answer answers
     */
    public void addAnswer(String answer, int questionIndex) {
        answerList.add(new Answer(answer, questionIndex));
    }

    public String getUserAnswer(int responseNumber) {
        return answerList.get(responseNumber).getAnswer();
    }

    public boolean isUserAnswerCorrect(String userAnswer, FlashCard question) {
        return question.getBack().equals(userAnswer);
    }
}
