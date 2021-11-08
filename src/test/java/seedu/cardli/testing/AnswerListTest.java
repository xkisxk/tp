package seedu.cardli.testing;

import org.junit.jupiter.api.Test;
import seedu.cardli.flashcard.Deck;
import seedu.cardli.flashcard.DeckManager;
import seedu.cardli.flashcard.FlashCard;
import seedu.cardli.testing.Answer;
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

    //@@author xRossKoh
    @Test
    public void testToString() {
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
        assertEquals("Untitled\n"
                + "2\n"
                + "card | card | 1 | 1\n"
                + "card2 | card2 | 0 | 1\n"
                + "2\n"
                + "card | 1\n"
                + "card | 2\n"
                + "1", answerList.toString());
    }

    @Test
    public void testToJsonObject() {
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
        assertEquals("{\"answerList\":"
                + "[{\"questionIndex\":1,\"answer\":\"card\"},"
                + "{\"questionIndex\":2,\"answer\":\"card\"}],"
                + "\"deck\":{\"cards\":"
                + "[{\"back\":\"card\",\"front\":\"card\",\"userScore\":1,\"totalScore\":1},"
                + "{\"back\":\"card2\",\"front\":\"card2\",\"userScore\":0,\"totalScore\":1}],"
                + "\"deckName\":\"Untitled\"},\"userScore\":1}",
                answerList.toJsonObject().toJSONString());
    }
}
