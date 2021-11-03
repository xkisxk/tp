package seedu.cardli.testing;

import org.junit.jupiter.api.Test;
import seedu.cardli.exceptions.DeckNotExistException;
import seedu.cardli.flashcard.Deck;
import seedu.cardli.flashcard.DeckManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestHistoryTest {

    DeckManager deckManager = new DeckManager();

    @Test
    void testPrepareViewTest_validIndex_expectResultMessage() throws DeckNotExistException {
        deckManager.prepareToAddDeck("Test");
        Deck deck = deckManager.getDeck(0);
        deck.addFlashCard("Card1", "Card1");
        deck.addFlashCard("Card2", "Card2");
        AnswerList answerList = new AnswerList(deck);
        answerList.addAnswer("W2", 0);
        answerList.addAnswer("W", 1);
        TestHistory testHistory = new TestHistory(deckManager);
        testHistory.addAnswerList(answerList);
        assertEquals(testHistory.prepareViewTest(0), "For test 1: Test\n"
                + "You scored 0 out of 2\n"
                + "That is 0.00%!");
        deckManager.deleteDeck(deck);
    }

    @Test
    void testPrepareViewTest_all_expectResultMessage() throws DeckNotExistException {
        deckManager.prepareToAddDeck("Test");
        deckManager.prepareToAddDeck("Test2");

        Deck deck = deckManager.getDeck(0);
        deck.addFlashCard("Card1", "Card1");
        Deck deckTwo = deckManager.getDeck(1);
        deckTwo.addFlashCard("Card2", "Card2");

        AnswerList answerList = new AnswerList(deck);
        answerList.addAnswer("W2", 0);
        AnswerList answerListTwo = new AnswerList(deckTwo);
        answerListTwo.addAnswer("W", 0);

        TestHistory testHistory = new TestHistory(deckManager);
        testHistory.addAnswerList(answerList);
        testHistory.addAnswerList(answerListTwo);

        assertEquals("These are your scores:\n" +
                "Score for test 1: Test 0/1 0.00%\n" +
                "Score for test 2: Test2 0/1 0.00%",
                testHistory.prepareViewTest(-1).trim());

        deckManager.deleteDeck(deck);
        deckManager.deleteDeck(deckTwo);
    }

    @Test
    void testViewAllFlashcardStats_testedOnce_expectResultMessage() throws DeckNotExistException {
        deckManager.prepareToAddDeck("Test");
        deckManager.prepareToAddDeck("Test2");

        Deck deck = deckManager.getDeck(0);
        deck.addFlashCard("Card1", "Card1", 0, 1);
        Deck deckTwo = deckManager.getDeck(1);
        deckTwo.addFlashCard("Card2", "Card2", 1, 1);

        AnswerList answerList = new AnswerList(deck);
        answerList.addAnswer("W2", 0);
        AnswerList answerListTwo = new AnswerList(deckTwo);
        answerListTwo.addAnswer("Card2", 0);

        TestHistory testHistory = new TestHistory(deckManager);
        testHistory.addAnswerList(answerList);
        testHistory.addAnswerList(answerListTwo);

        assertEquals("Listing total scores of flashcards for all tests:\n"
                + deck.getCard(0).returnFlashCard()
                + "\nScore: 0 out of 1\n"
                + deckTwo.getCard(0).returnFlashCard()
                + "\nScore: 1 out of 1\n", testHistory.viewAllFlashcardStats());

        deckManager.deleteDeck(deck);
        deckManager.deleteDeck(deckTwo);
    }

    @Test
    void testPrepareViewTest_all_expectDeckNotExistException() {
        TestHistory testHistory = new TestHistory(deckManager);
        assertThrows(DeckNotExistException.class, () -> testHistory.prepareViewTest(-1));
    }

    @Test
    void testPrepareViewTest_noAnswerList_expectOutOfBoundsException() {
        TestHistory testHistory = new TestHistory(deckManager);
        assertThrows(IndexOutOfBoundsException.class, () -> testHistory.prepareViewTest(1));
    }
}
