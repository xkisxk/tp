package seedu.duke.testing;

import seedu.duke.flashcard.Deck;
import seedu.duke.flashcard.DeckManager;
import seedu.duke.flashcard.FlashCard;
import seedu.duke.ui.TestUi;

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

        String result = "Listing total scores of flashcards for all tests: \n";
        for (Deck deck : deckManager.getDecks()) {
            for (FlashCard card : deck.getCards()) {
                result = result.concat(card.returnFlashCard() + "\nScore: "
                        + card.getUserScore() + " out of " + card.getTotalScore()
                        + "\n");
            }
        }
        return result;
    }

    public String prepareViewTest(int index) {
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
        String result = "You scored " + score + " out of " + totalScore + " for test " + (index + 1)
                + "\nThat is " + (double) score / totalScore * 100 + "%!";
        return result;
    }

    /**
     * Views the results of the tests in order of all the tests taken.
     * Gives the raw score, followed by the percentage.
     *
     * @return all the tests as a string
     */
    public String viewTests() {
        String result = "";
        if (testHistory.size() <= 0) {
            return "You have not taken any tests.";
        }
        int index = 1;
        result = result.concat("These are your scores: " + System.lineSeparator());
        for (AnswerList answerList : testHistory) {
            int score = answerList.getUserScore();
            int totalScore = answerList.getSize();
            result = result.concat(
                    "Score for test " + index + ": " + answerList.getDeck().getName()
                            + " " + score + "/" + totalScore
                            + " " + (double) score / totalScore * 100 + "%\n");
            index++;
        }
        return result;
    }

    /**
     * Gets all the low scoring cards and put them into a deck.
     *
     * @return deck of low scoring cards
     */
    private Deck getLowScoringCardsFromAllDecks() {
        logger.setLevel(Level.WARNING);
        logger.log(Level.INFO, "Collecting low scoring cards");
        Deck reviewDeck = new Deck("Review");
        for (Deck deck : deckManager.getDecks()) {
            for (FlashCard card : deck.getCards()) {
                if (isLowScoring(card)) {
                    reviewDeck.addFlashCard(card);
                    logger.log(Level.INFO, "Added a low scoring card");
                }
            }
        }
        return reviewDeck;
    }

    /**
     * Gets all the low scoring cards from a deck and put them into a deck.
     *
     * @return deck of low scoring cards
     */
    private Deck getLowScoringCardsFromADeck(Deck deck) {
        logger.setLevel(Level.WARNING);
        logger.log(Level.INFO, "Collecting low scoring cards");
        Deck reviewDeck = new Deck("Review");
        for (FlashCard card : deck.getCards()) {
            if (isLowScoring(card)) {
                reviewDeck.addFlashCard(card);
                logger.log(Level.INFO, "Added a low scoring card");
            }
        }
        return reviewDeck;
    }

    /**
     * Gets all the low scoring cards and put them into a deck.
     * If index is -1, get low scaring cards from all decks.
     * Else get low scoring cards from the deck from that index.
     * The cards that are put into the deck are the same cards objects, in other
     * words they are not new FlashCard objects.
     *
     * @return deck of low scoring cards
     */
    public Deck getLowScoringCards(int index) {
        if (index == -1) {
            return getLowScoringCardsFromAllDecks();
        }
        if (deckManager.hasDeck(index)) {
            return getLowScoringCardsFromADeck(deckManager.getDeck(index));
        }
        throw new IndexOutOfBoundsException("This deck does not exist.");
    }

    /**
     * A card is low scoring if its accumulated user score is less than 50% of
     * the total score.
     *
     * @return true if card is low scoring, false otherwise
     */
    private boolean isLowScoring(FlashCard card) {
        return (double) card.getUserScore() * 100 / card.getTotalScore() < 50;
    }

}