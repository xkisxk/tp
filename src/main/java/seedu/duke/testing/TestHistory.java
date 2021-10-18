package seedu.duke.testing;

import seedu.duke.flashcard.Deck;
import seedu.duke.flashcard.DeckList;
import seedu.duke.flashcard.FlashCard;
import seedu.duke.parser.TestParser;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * TestHistory keeps track of all answerResponses from previous tests.
 */
public class TestHistory {
    private static final ArrayList<AnswerList> testHistory = new ArrayList<>();
    private static final Logger logger = Logger.getLogger(TestManager.class.getName());

    public static void addAnswerList(AnswerList answerList) {
        testHistory.add(answerList);
    }

    /**
     * View overall result statistics of all tests and individual flashcards.
     * Invoked by the user command "viewtests".
     */
    public static void viewOverallFlashcardStats() {
        logger.setLevel(Level.WARNING);
        logger.log(Level.INFO, "listing all flashcard stats");

        assert DeckList.getDeckList().size() > 0 : "deckList must not be empty";
        System.out.println("Listing total scores of flashcards for all tests");
        for (Deck deck : DeckList.getDeckList()) {
            for (FlashCard card : deck.cards) {
                card.viewFlashCard();
                System.out.println("Score: " + card.getUserScore() + " out of " + card.getTotalScore());
            }
        }
    }

    public static void prepareToViewTest(String input) {
        try {
            int index = TestParser.toInt(input);
            viewTestByIndex(index);
        } catch (NumberFormatException e) {
            System.out.println("That's not a number.");
        }
    }

    public static void viewTestByIndex(int index) {
        AnswerList answerList = testHistory.get(index);
        int score = answerList.getScore();
        int totalScore = answerList.getSize();
        System.out.println("You scored " + score + " out of " + totalScore + " for test " + (index + 1));
        System.out.println("That is " + score / totalScore * 100 + "%!");
    }

    public static void viewTests() {
        int index = 1;
        for (AnswerList answerList : testHistory) {
            int score = answerList.getScore();
            int totalScore = answerList.getSize();
            System.out.println(
                    "Score for " + index + " " + answerList.getDeck().getName()
                            + " " + score + "/" + totalScore
                            + " " + score / totalScore * 100 + "%");
            index++;
        }
    }

    /**
     * Gets all the low scoring cards and put them into a deck.
     *
     * @return deck of low scoring cards
     */
    public static Deck getLowScoringCards() {
        Deck reviewDeck = new Deck("Review");
        for (Deck deck : DeckList.getDeckList()) {
            for (FlashCard card : deck.cards) {
                if (isLowScoring(card)) {
                    reviewDeck.addFlashCard(card);
                }
            }
        }
        return reviewDeck;
    }

    /**
     * A card is low scoring if its accumulated user score is less than 50% of
     * the total score.
     *
     * @return true if card is low scoring, false otherwise
     */
    private static boolean isLowScoring(FlashCard card) {
        return (double) card.getUserScore() * 100 / card.getTotalScore() < 50;
    }
}
