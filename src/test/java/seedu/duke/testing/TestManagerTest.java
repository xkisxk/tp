package seedu.duke.testing;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.EmptyDeckException;
import seedu.duke.flashcard.Deck;
import seedu.duke.flashcard.DeckManager;

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
}