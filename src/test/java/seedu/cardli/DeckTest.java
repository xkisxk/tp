package seedu.cardli;

import org.junit.jupiter.api.Test;
import seedu.cardli.exceptions.CardLiException;
import seedu.cardli.flashcard.FlashCard;
import seedu.cardli.flashcard.Deck;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeckTest {

    /**
     * Tests toString() and Deck() constructor
     */
    @Test
    public void testToString() {
        Deck deck = new Deck();
        deck.addFlashCard("testFront1", "testBack1");
        assertEquals("Untitled\n1\n"
                + "testFront1 | testBack1 | 0 | 0\n", deck.toString());
    }

    /**
     * Tests toJSONObject(), Deck(deckName) constructor
     * and all 3 implementations of addFlashCard()
     */
    @Test
    public void testToJsonObject() {
        FlashCard flashCard = new FlashCard("testFront3", "testBack3", 1, 2);
        Deck deck = new Deck("testDeck");
        deck.addFlashCard("testFront1", "testBack1");
        deck.addFlashCard("testFront2", "testBack2", 2, 3);
        deck.addFlashCard(flashCard);
        assertEquals("{\"cards\":["
                + "{\"back\":\"testBack1\",\"front\":\"testFront1\",\"userScore\":0,\"totalScore\":0},"
                + "{\"back\":\"testBack2\",\"front\":\"testFront2\",\"userScore\":2,\"totalScore\":3},"
                + "{\"back\":\"testBack3\",\"front\":\"testFront3\",\"userScore\":1,\"totalScore\":2}],"
                + "\"deckName\":\"testDeck\"}", deck.toJsonObject().toJSONString());
    }

    @Test
    public void testDeleteFlashCard() throws CardLiException {
        Deck deck = new Deck("testDeck");
        deck.addFlashCard("testFront1", "testBack1");
        deck.addFlashCard("testFront2", "testBack2", 2, 3);
        deck.deleteFlashCard("1");
        assertEquals("testDeck\n1\n"
                + "testFront2 | testBack2 | 0 | 0\n", deck.toString());
    }

    @Test
    public void testEditFlashCardFront() {
        Deck deck = new Deck("testDeck");
        deck.addFlashCard("testFront1", "testBack1");
        deck.editCard(new String[]{"1", "front", "testEdit1"});
        assertEquals("testDeck\n1\n"
                + "testEdit1 | testBack1 | 0 | 0\n", deck.toString());
    }

    @Test
    public void testEditFlashCardBack() {
        Deck deck = new Deck("testDeck");
        deck.addFlashCard("testFront1", "testBack1");
        deck.editCard(new String[]{"1", "back", "testEdit1"});
        assertEquals("testDeck\n1\n"
                + "testFront1 | testEdit1 | 0 | 0\n", deck.toString());
    }
}
