package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.FieldEmptyException;
import seedu.duke.parser.TestParser;
import seedu.duke.testing.TestManager;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestManagerTest {

    @Test
    public void parseUserResponse_noInput_expectFieldEmptyException() {
        String input = "";
        assertThrows(FieldEmptyException.class, () -> TestParser.parseUserResponse(input));
    }
}
