package seedu.duke.flashcard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;


class DeckManagerTest {

    @Test
    void prepareToAddDeck_deckAdded_expectOne() {
        DeckManager.prepareToAddDeck("Test");
        assertEquals(1, DeckManager.getDecksSize());
        DeckManager.deleteDeck(DeckManager.getDeck(0));
    }

    @Test
    void getTestDeck_indexOutOfBounds_expectIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> DeckManager.getTestDeck(1));
    }

    @Test
    void hasDeck_noDecks_expectFalse() {
        assertFalse(DeckManager.hasDeck(1));
    }

    @Test
    void hasDeck_hasDeck_expectTrue() {
        DeckManager.prepareToAddDeck("Test");
        assertTrue(DeckManager.hasDeck(0));
        DeckManager.deleteDeck(DeckManager.getDeck(0));
    }

    @Test
    void getTestDeck_twoCards_expectTwoCards() {
        DeckManager.prepareToAddDeck("Test Deck 1");
        DeckManager.prepareToAddDeck("Test Deck 2");
        DeckManager.getDeck(0).addFlashCard("test card 1", "test card 1");
        DeckManager.getDeck(1).addFlashCard("test card 2", "test card 2");
        assertEquals(2, DeckManager.getTestDeck(-1).getDeckSize());
        DeckManager.deleteDeck(DeckManager.getDeck(0));
        DeckManager.deleteDeck(DeckManager.getDeck(0));
    }
}