package seedu.cardli.testing;

import org.junit.jupiter.api.Test;
import seedu.cardli.exceptions.DeckNotExistException;
import seedu.cardli.exceptions.EmptyDeckException;
import seedu.cardli.flashcard.Deck;
import seedu.cardli.flashcard.DeckManager;
import seedu.cardli.ui.TestUi;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.cardli.ui.TestUi.DECK_NOT_EXIST_MESSAGE;
import static seedu.cardli.ui.TestUi.END_REVIEW_MESSAGE;
import static seedu.cardli.ui.TestUi.END_TEST_MESSAGE;
import static seedu.cardli.ui.TestUi.INCORRECT_INPUT_FORMAT_MESSAGE;
import static seedu.cardli.ui.TestUi.NO_CARDS_TO_REVIEW_MESSAGE;
import static seedu.cardli.ui.TestUi.NO_CARDS_TO_TEST_MESSAGE;

class TestManagerTest {

    DeckManager deckManager = new DeckManager();
    TestHistory testHistory = new TestHistory(deckManager);

    @Test
    void testAllCardsShuffled_emptyDeck_expectEmptyDeckException() {
        Deck deck = new Deck("Test");
        AnswerList answerList = new AnswerList(deck);
        TestManager testManager = new TestManager(testHistory, deckManager);
        assertThrows(EmptyDeckException.class, () -> testManager.testAllCardsShuffled(answerList));
    }

    @Test
    void testStartReview_emptyDeck_expectCaughtEmptyDeckMessage() {
        deckManager.prepareToAddDeck("Test");

        ByteArrayInputStream inputStream = new ByteArrayInputStream("0".getBytes());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        TestUi testUi = new TestUi(inputStream, ps);

        TestManager testManager = new TestManager(testHistory, deckManager, testUi);

        testManager.startReview();

        String outputText = byteArrayOutputStream.toString().trim();
        String actual = outputText.substring(outputText.length() - NO_CARDS_TO_REVIEW_MESSAGE.length()).trim();
        assertEquals(actual, NO_CARDS_TO_REVIEW_MESSAGE);
        deckManager.deleteDeck(0);
    }

    @Test
    void testStartReview_noDeck_expectCaughtIndexOutOfBoundsMessage() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("110852".getBytes());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        TestUi testUi = new TestUi(inputStream, ps);

        TestManager testManager = new TestManager(testHistory, deckManager, testUi);

        testManager.startReview();

        String outputText = byteArrayOutputStream.toString().trim();
        String actual = outputText.substring(outputText.length() - DECK_NOT_EXIST_MESSAGE.length()).trim();
        assertEquals(actual, DECK_NOT_EXIST_MESSAGE);
    }

    @Test
    void testStartReview_invalidInputFormat_expectCaughtInvalidFormatMessage() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("asjdaldkallaskdj".getBytes());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        TestUi testUi = new TestUi(inputStream, ps);

        TestManager testManager = new TestManager(testHistory, deckManager, testUi);

        testManager.startReview();

        String outputText = byteArrayOutputStream.toString().trim();
        String actual = outputText.substring(outputText.length() - INCORRECT_INPUT_FORMAT_MESSAGE.length()).trim();
        assertEquals(actual, INCORRECT_INPUT_FORMAT_MESSAGE);
    }

    @Test
    void testStartReview_CorrectIncorrectAndNoAnswers_expectEndTestMessage() throws DeckNotExistException {
        deckManager.prepareToAddDeck("Test");
        Deck deck = deckManager.getDeck(0);
        deck.addFlashCard("Card1", "Card1", 3, 5);
        deck.addFlashCard("Card2", "Card2", 2, 5);
        deck.addFlashCard("Card3", "Card3", 0, 5);

        ByteArrayInputStream inputStream = new ByteArrayInputStream(
                "1\n/next\n/back\nCard2\n/back\nWrong".getBytes());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        TestUi testUi = new TestUi(inputStream, ps);

        TestManager testManager = new TestManager(testHistory, deckManager, testUi);

        String outputText = testManager.startReview().trim();
        String actual = outputText.substring(outputText.length() - END_REVIEW_MESSAGE.length()).trim();
        assertEquals(actual, END_REVIEW_MESSAGE);
        deckManager.deleteDeck(deck);
    }

    @Test
    void testStartTest_emptyDeck_expectCaughtEmptyDeckMessage() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("0".getBytes());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        TestUi testUi = new TestUi(inputStream, ps);

        TestManager testManager = new TestManager(testHistory, deckManager, testUi);

        testManager.startTest();

        String outputText = byteArrayOutputStream.toString().trim();
        String actual = outputText.substring(outputText.length() - NO_CARDS_TO_TEST_MESSAGE.length()).trim();
        assertEquals(actual, NO_CARDS_TO_TEST_MESSAGE);
    }

    @Test
    void testStartTest_noDeck_expectCaughtIndexOutOfBoundsMessage() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("110852".getBytes());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        TestUi testUi = new TestUi(inputStream, ps);

        TestManager testManager = new TestManager(testHistory, deckManager, testUi);

        testManager.startTest();

        String outputText = byteArrayOutputStream.toString().trim();
        String actual = outputText.substring(outputText.length() - DECK_NOT_EXIST_MESSAGE.length()).trim();
        assertEquals(actual, DECK_NOT_EXIST_MESSAGE);
    }

    @Test
    void testStartTest_invalidInputFormat_expectCaughtInvalidFormatMessage() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("call".getBytes());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        TestUi testUi = new TestUi(inputStream, ps);

        TestManager testManager = new TestManager(testHistory, deckManager, testUi);

        testManager.startTest();

        String outputText = byteArrayOutputStream.toString().trim();
        String actual = outputText.substring(outputText.length() - INCORRECT_INPUT_FORMAT_MESSAGE.length()).trim();
        assertEquals(actual, INCORRECT_INPUT_FORMAT_MESSAGE);
    }

    @Test
    void testStartTest_CorrectIncorrectAndNoAnswers_expectEndTestMessage() throws DeckNotExistException {
        deckManager.prepareToAddDeck("Test");
        Deck deck = deckManager.getDeck(0);
        deck.addFlashCard("Card1", "Card1");
        deck.addFlashCard("Card2", "Card2");
        deck.addFlashCard("Card3", "Card3");

        ByteArrayInputStream inputStream = new ByteArrayInputStream(
                "1\n/next\n/back\nCard1\n/back\nWrong\n\n".getBytes());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        TestUi testUi = new TestUi(inputStream, ps);

        TestManager testManager = new TestManager(testHistory, deckManager, testUi);

        String outputText = testManager.startTest();
        String actual = outputText.substring(outputText.length() - END_TEST_MESSAGE.length()).trim();
        assertEquals(actual, END_TEST_MESSAGE);
        deckManager.deleteDeck(deck);
    }
}