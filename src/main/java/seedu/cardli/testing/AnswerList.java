package seedu.cardli.testing;

import seedu.cardli.flashcard.Deck;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Contains all the user's answers to the tested deck and his score.
 */
public class AnswerList {
    private final ArrayList<Answer> answerList;
    private final Deck deck;
    private int userScore;
    private static final Logger logger = Logger.getLogger(Deck.class.getName());

    /**
     * AnswerList contains the list of answers from a test and the deck
     * the questions came from.
     *
     * @param deck tested deck
     */
    public AnswerList(Deck deck) {
        this.answerList = new ArrayList<>();
        this.deck = deck;
        this.userScore = 0;
    }

    /**
     * Gets the index of the answer in the list.
     *
     * @param answer answer query
     * @return index of the answer
     */
    public int getAnswerIndex(Answer answer) {
        return answerList.indexOf(answer);
    }

    public ArrayList<Answer> getAnswerList() {
        return answerList;
    }

    public int getUserScore() {
        return userScore;
    }

    public void setUserScore(int userScore) {
        this.userScore = userScore;
    }

    public void incrementUserScore() {
        userScore++;
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
        logger.setLevel(Level.WARNING);
        logger.log(Level.INFO, "Adding card");
        answerList.add(new Answer(answer, questionIndex));
    }

    @Override
    public String toString() {
        String answersString = "";
        int answersCount = getSize();

        for (int i = 0; i < answersCount; i++) {
            answersString += answerList.get(i);
        }

        return getDeck().toString()
                + answersCount + '\n'
                + answersString
                + getUserScore();
    }
}
