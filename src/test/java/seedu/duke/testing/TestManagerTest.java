package seedu.duke.testing;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.EmptyDeckException;
import seedu.duke.flashcard.Deck;

import static org.junit.jupiter.api.Assertions.assertThrows;

class TestManagerTest {
    @Test
    void testAllCardsShuffled_emptyDeck_expectEmptyDeckException() {
        Deck deck = new Deck("Test");
        AnswerList answerList = new AnswerList(deck);
        assertThrows(EmptyDeckException.class, () -> TestManager.testAllCardsShuffled(answerList));
    }
}