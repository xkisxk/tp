package seedu.cardli.testing;


import seedu.cardli.exceptions.DeckNotExistException;
import seedu.cardli.flashcard.Deck;
import seedu.cardli.flashcard.DeckManager;
import seedu.cardli.flashcard.FlashCard;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * TestHistory keeps track of all answerResponses from previous tests.
 */
public class TestHistory {
    private final ArrayList<AnswerList> testHistory;
    private final Logger logger = Logger.getLogger(TestManager.class.getName());

    private final DeckManager deckManager;

    public TestHistory(DeckManager deckManager) {
        this.testHistory = new ArrayList<>();
        this.deckManager = deckManager;
    }

    public TestHistory(DeckManager deckManager, ArrayList<AnswerList> testHistory) {
        this.testHistory = testHistory;
        this.deckManager = deckManager;
    }

    public ArrayList<AnswerList> getTestHistory() {
        return testHistory;
    }

    public void addAnswerList(AnswerList answerList) {
        testHistory.add(answerList);
    }

    /**
     * View overall result statistics of all or a single flashcards.
     *
     * @return FlashCardStats as a string
     */
    public String viewAllFlashcardStats() {
        logger.setLevel(Level.WARNING);
        logger.log(Level.INFO, "listing all flashcard stats");

        assert deckManager.getDecks().size() > 0 : "deckList must not be empty";

        String result = "Listing total scores of flashcards for all tests:\n";
        for (Deck deck : deckManager.getDecks()) {
            for (FlashCard card : deck.getCards()) {
                result = result.concat(card.returnFlashCard() + "\nScore: " + card.getUserScore()
                        + " out of " + card.getTotalScore()
                        + "\n");
            }
        }
        return result;
    }

    public String prepareViewTest(int index) throws DeckNotExistException {
        if (index == -1) {
            return viewTests();
        } else {
            return viewTestByIndex(index);
        }
    }

    /**
     * Views the results of a test given the index.
     * Gives the raw score, followed by the percentage.
     *
     * @param index index of the test
     * @return that test as string
     */
    public String viewTestByIndex(int index) {
        AnswerList answerList = testHistory.get(index);
        int score = answerList.getUserScore();
        int totalScore = answerList.getSize();

        double percentage = (double) score / totalScore * 100;
        String percentageAsString = String.format("%.2f", percentage);
        String result = "For test " + (index + 1) + ": " + answerList.getDeck().getName()
                + "\nYou scored " + score + " out of " + totalScore
                + "\nThat is " + percentageAsString + "%!";

        return result;
    }

    /**
     * Views the results of the tests in order of all the tests taken.
     * Gives the raw score, followed by the percentage.
     *
     * @return all the tests as a string
     */
    public String viewTests() throws DeckNotExistException {
        String result = "";
        if (testHistory.size() <= 0) {
            throw new DeckNotExistException("You have not taken any tests.");
        }
        int index = 1;
        result = result.concat("These are your scores:\n");
        for (AnswerList answerList : testHistory) {
            int score = answerList.getUserScore();
            int totalScore = answerList.getSize();
            double percentage = (double) score / totalScore * 100;
            String percentageAsString = String.format("%.2f", percentage);
            result = result.concat(
                    "Score for test " + index + ": " + answerList.getDeck().getName()
                            + " " + score + "/" + totalScore
                            + " " + percentageAsString + "%\n");
            index++;
        }
        return result;
    }
}
