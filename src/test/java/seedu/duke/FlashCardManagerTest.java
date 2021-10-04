package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.FieldEmptyException;
import seedu.duke.exceptions.NoSlashException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FlashCardManagerTest {

    @Test
    public void trimStrings_hasFrontHasBack_expectTwoParts() throws NoSlashException, FieldEmptyException {
        String input = "add good morning /def ohayou";
        assertEquals(2, FlashCardManager.trimStrings(input).length);
    }

    @Test
    public void trimStrings_hasNoSlash_expectNoSlashException() {
        String input = "add good morning ohayou";
        assertThrows(NoSlashException.class, ()-> FlashCardManager.trimStrings(input));
    }

    @Test
    public void trimStrings_emptyFront_expectFieldEmptyException() {
        String input = "add /def ohayou";
        assertThrows(FieldEmptyException.class, ()-> FlashCardManager.trimStrings(input));
    }

    @Test
    public void trimStrings_emptyBack_expectFieldEmptyException() {
        String input = "add good morning /def";
        assertThrows(FieldEmptyException.class, ()-> FlashCardManager.trimStrings(input));
    }
}