package seedu.cardli.flashcard;

import org.junit.jupiter.api.Test;
import seedu.cardli.commands.Command;
import seedu.cardli.commands.CommandResult;
import seedu.cardli.commands.deck.EditCardCommand;
import seedu.cardli.parser.Parser;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    @Test
    public void hasCardWithSameName_matchesCardExactly_returnTrue() {
        Deck deck = new Deck();
        deck.addFlashCard("card", "card");
        assertEquals(true, deck.hasCardWithSameName("card"));

    }

    @Test
    public void hasCardWithSameName_hasTrailingSpaces_returnTrue() {
        Deck deck = new Deck();
        deck.addFlashCard("card", "card");
        assertEquals(true, deck.hasCardWithSameName("card   "));

    }

    @Test
    public void hasCardWithSameName_differenceInCase_returnFalse() {
        Deck deck = new Deck();
        deck.addFlashCard("card", "card");
        assertEquals(false, deck.hasCardWithSameName("cArd"));

    }

    @Test
    public void hasCardWithSameName_completelyDifferentQuery_returnFalse() {
        Deck deck = new Deck();
        deck.addFlashCard("card", "card");
        assertEquals(false, deck.hasCardWithSameName("ohno"));

    }

}