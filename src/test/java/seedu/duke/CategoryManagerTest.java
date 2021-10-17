package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.flashcard.DeckList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryManagerTest {

    @Test
    public void trimToPass_enterValidAddLine_success() {
        String input = "1 /fro good morning /bac ohayo";
        assertEquals("good morning /bac ohayo", DeckList.trimToPass(input, "/fro"));

    }
}