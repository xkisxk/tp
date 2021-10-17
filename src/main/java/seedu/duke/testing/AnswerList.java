package seedu.duke.testing;

import seedu.duke.flashcard.Deck;
import seedu.duke.flashcard.FlashCard;

import java.util.ArrayList;

public class AnswerList {
    private ArrayList<Answer> answerList;
    private Deck deck;

    public AnswerList(Deck deck) {
        this.answerList = new ArrayList<>();
        this.deck = deck;
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

    public Deck getDeck() {
        return deck;
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

    public int getScore() {
        int score = 0;
        for (Answer response : answerList) {
            int responseNumber = getAnswerIndex(response);
            FlashCard question = deck.getCard(responseNumber);
            String userAnswer = response.getAnswer();
            
            if (response.isCorrect(userAnswer, question)) {
                score++;
            }
        }
        return score;
    }
}
