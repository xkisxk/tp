package seedu.cardli.testing;

import org.junit.jupiter.api.Test;
import seedu.cardli.exceptions.EmptyDeckException;
import seedu.cardli.flashcard.Deck;
import seedu.cardli.flashcard.DeckManager;

import static org.junit.jupiter.api.Assertions.assertThrows;

class TestManagerTest {
    @Test
    void testAllCardsShuffled_emptyDeck_expectEmptyDeckException() {
        Deck deck = new Deck("Test");
        DeckManager deckManager = new DeckManager();
        TestHistory testHistory = new TestHistory(deckManager);
        TestManager testManager = new TestManager(testHistory, deckManager);
        AnswerList answerList = new AnswerList(deck);
        assertThrows(EmptyDeckException.class, () -> testManager.testAllCardsShuffled(answerList));
    }

    @Test
    void testReviewCards_emptyDeck_expectEmptyDeckException() {
        Deck deck = new Deck("Test");
        DeckManager deckManager = new DeckManager();
        TestHistory testHistory = new TestHistory(deckManager);
        TestManager testManager = new TestManager(testHistory, deckManager);
        assertThrows(EmptyDeckException.class, () -> testManager.reviewCards(deck));
    }
}