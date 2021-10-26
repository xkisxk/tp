package seedu.cardli.flashcard;

import org.junit.jupiter.api.Test;
import seedu.cardli.exceptions.DeckNotExistException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;


class DeckManagerTest {

    @Test
    void prepareToAddDeck_deckAdded_expectOne() throws DeckNotExistException {
        DeckManager deckManager = new DeckManager();
        deckManager.prepareToAddDeck("Test");
        assertEquals(1, deckManager.getDecksSize());
        deckManager.deleteDeck(deckManager.getDeck(0));
    }

    @Test
    void getTestDeck_indexOutOfBounds_expectIndexOutOfBoundsException() {
        DeckManager deckManager = new DeckManager();
        assertThrows(IndexOutOfBoundsException.class, () -> deckManager.getTestDeck(1));
    }

    @Test
    void hasDeck_noDecks_expectFalse() {
        DeckManager deckManager = new DeckManager();
        assertFalse(deckManager.hasDeck(1));
    }

    @Test
    void hasDeck_hasDeck_expectTrue() throws DeckNotExistException {
        DeckManager deckManager = new DeckManager();
        deckManager.prepareToAddDeck("Test");
        assertTrue(deckManager.hasDeck(0));
        deckManager.deleteDeck(deckManager.getDeck(0));
    }

    @Test
    void getTestDeck_twoCards_expectTwoCards() throws DeckNotExistException {
        DeckManager deckManager = new DeckManager();
        deckManager.prepareToAddDeck("Test Deck 1");
        deckManager.prepareToAddDeck("Test Deck 2");
        deckManager.getDeck(0).addFlashCard("test card 1", "test card 1");
        deckManager.getDeck(1).addFlashCard("test card 2", "test card 2");
        assertEquals(2, deckManager.getTestDeck(-1).getDeckSize());
        deckManager.deleteDeck(deckManager.getDeck(0));
        deckManager.deleteDeck(deckManager.getDeck(0));
    }

    @Test
    void deleteDeck_noDecks_expectDeckNotExistException() {
        DeckManager deckManager = new DeckManager();
        Deck deck = new Deck("euyhfdsifnkjadsanauheaiu");
        assertThrows(DeckNotExistException.class, () -> deckManager.deleteDeck(deck));
        assertThrows(DeckNotExistException.class, () -> deckManager.deleteDeck("test"));
    }

    @Test
    void deleteDeck_noDecks_expectIndexOutOfBoundsException() {
        DeckManager deckManager = new DeckManager();
        Deck deck = new Deck("euyhfdsifnkjadsanauheaiu");
        assertThrows(IndexOutOfBoundsException.class, () -> deckManager.deleteDeck(1));
    }
}