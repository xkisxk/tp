package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.flashcard.Deck;
import seedu.duke.testing.AnswerList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnswerListTest {
    @Test
    public void getScore_noAnswers_expectZero() {
        Deck deck = new Deck();
        AnswerList answerList = new AnswerList(deck);
        assertEquals(0, answerList.getScore());
    }

    @Test
    public void getScore_oneCorrectAnswer_expectOne() {
        Deck deck = new Deck();
        deck.addFlashCard("card", "card");
        AnswerList answerList = new AnswerList(deck);
        answerList.addAnswer("card", 1);
        assertEquals(1, answerList.getScore());
    }
}
