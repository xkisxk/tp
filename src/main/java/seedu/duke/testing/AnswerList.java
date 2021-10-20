package seedu.duke.testing;

import seedu.duke.flashcard.Deck;
import seedu.duke.flashcard.FlashCard;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Contains all the user's answers to the tested deck.
 */
public class AnswerList {
    private final ArrayList<Answer> answerList;
    private final Deck deck;
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
    }

    /**
     * Gets the index of the answer in the list.
     *
     * @param answer answer query
     * @return       index of the answer
     */
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

    /**
     * Gets the score of a particular test.
     *
     * @return score of the test
     */
    public int getScore() {
        logger.setLevel(Level.WARNING);
        logger.log(Level.INFO, "Starting getScore process");
        int score = 0;
        for (Answer response : answerList) {
            int responseNumber = getAnswerIndex(response);
            FlashCard question = deck.getCard(responseNumber);
            String userAnswer = response.getAnswer();

            if (response.isCorrect(userAnswer, question)) {
                logger.log(Level.INFO, "Answer is correct");
                score++;
            }
        }
        return score;
    }
}
