package seedu.duke.testing;

import seedu.duke.flashcard.Deck;
import seedu.duke.flashcard.DeckList;
import seedu.duke.flashcard.FlashCard;

import java.util.ArrayList;

/**
 * TestHistory contains the answerResponses from all previous tests
 */
public class TestHistory {
    private static ArrayList<AnswerList> testHistory = new ArrayList<>();

    public static void addAnswerList(AnswerList answerList) {
        testHistory.add(answerList);
    }

    public static AnswerList getTestByIndex(int index) {
        return testHistory.get(index);
    }

    /**
     * View overall result statistics of all tests and individual flashcards.
     * Invoked by the user command "stats".
     */
    public static void viewOverallFlashcardStats() {
        assert DeckList.getDeckList().size() > 0 : "deckList must not be empty";
        System.out.println("Listing total scores of flashcards for all tests");
        for (Deck deck : DeckList.getDeckList()) {
            for (FlashCard card : deck.cards) {
                card.viewFlashCard();
                System.out.println("Score: " + card.getUserScore() + "out of " + card.getTotalScore());
            }
        }
    }

    public static void viewTestByIndex(int index) {
        AnswerList answerList = testHistory.get(index);
        int score = answerList.getScore();
        int totalScore = answerList.getSize();
        System.out.println("You scored " + score + " out of " + totalScore + " for this test.");
        System.out.println("That is " + score / totalScore * 100 + "%!");
    }

}
