package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.flashcard.Deck;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryManagerTest {

    @Test
    public void trimToPass_enterValidAddLine_success() {
        String input = "1 /fro good morning /bac ohayo";
        assertEquals("good morning /bac ohayo", Deck.DeckList.trimToPass(input, "/fro"));

    }
}