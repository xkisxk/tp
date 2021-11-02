package seedu.cardli;

import org.junit.jupiter.api.Test;
import seedu.cardli.flashcard.Deck;
import seedu.cardli.flashcard.DeckManager;
import seedu.cardli.testing.AnswerList;
import seedu.cardli.testing.TestHistory;
import seedu.cardli.testing.TestManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnswerListTest {
    @Test
    public void getScore_noAnswers_expectZero() {
        Deck deck = new Deck();
        AnswerList answerList = new AnswerList(deck);
        assertEquals(0, answerList.getUserScore());
    }

    @Test
    public void getScore_oneCorrectAnswer_expectOne() {
        Deck deck = new Deck();
        deck.addFlashCard("card", "card");
        deck.addFlashCard("card2", "card2");
        AnswerList answerList = new AnswerList(deck);
        answerList.addAnswer("card", 1);
        answerList.addAnswer("card", 2);
        DeckManager deckManager = new DeckManager();
        TestHistory testHistory = new TestHistory(deckManager);
        TestManager testManager = new TestManager(testHistory, deckManager);
        testManager.markTest(answerList);
        assertEquals(1, answerList.getUserScore());
    }
}
