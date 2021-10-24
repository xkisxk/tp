package seedu.duke.testing;

import seedu.duke.flashcard.Deck;
import seedu.duke.flashcard.DeckManager;
import seedu.duke.flashcard.FlashCard;
import seedu.duke.parser.TestParser;
import seedu.duke.ui.TestUi;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * TestHistory keeps track of all answerResponses from previous tests.
 */
public class TestHistory {
    private final ArrayList<AnswerList> testHistory = new ArrayList<>();
    private final Logger logger = Logger.getLogger(TestManager.class.getName());
    private final TestUi ui = new TestUi();

    private final DeckManager deckManager;

    public TestHistory(DeckManager deckManager) {
        this.deckManager = deckManager;
    }

    public void addAnswerList(AnswerList answerList) {
        testHistory.add(answerList);
    }

    /**
     * View overall result statistics of all tests and individual flashcards.
     * Invoked by the user command "viewtests".
     */
    public void viewOverallFlashcardStats() {
        logger.setLevel(Level.WARNING);
        logger.log(Level.INFO, "listing all flashcard stats");

        assert deckManager.getDecks().size() > 0 : "deckList must not be empty";
        System.out.println("Listing total scores of flashcards for all tests");
        for (Deck deck : deckManager.getDecks()) {
            for (FlashCard card : deck.getCards()) {
                ui.printScoreWithCard(card);
            }
        }
    }

    public void prepareToViewTest(String input) {
        try {
            int index = TestParser.toInt(input);
            viewTestByIndex(index);
        } catch (NumberFormatException e) {
            System.out.println("That's not a number.");
        }
    }

    /**
     * Views the results of a test given the index.
     * Gives the raw score, followed by the percentage.
     *
     * @param index index of the test
     */
    public void viewTestByIndex(int index) {
        AnswerList answerList = testHistory.get(index);
        int score = answerList.getUserScore();
        int totalScore = answerList.getSize();
        ui.printScore(index, score, totalScore);
    }

    /**
     * Views the results of the tests in order of all the tests taken.
     * Gives the raw score, followed by the percentage.
     *
     */
    public void viewTests() {
        int index = 1;
        for (AnswerList answerList : testHistory) {
            ui.printTest(index, answerList);
            index++;
        }
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