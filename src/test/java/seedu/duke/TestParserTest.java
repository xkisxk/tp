package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.FieldEmptyException;
import seedu.duke.parser.TestParser;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestParserTest {
    @Test
    public void parseUserResponse_noInput_expectFieldEmptyException() {
        String input = "";
        assertThrows(FieldEmptyException.class, () -> TestParser.parseUserResponse(input));
    }

    @Test
    public void toInt_invalidInput_expectNumberFormatException() {
        String input = "a";
        assertThrows(NumberFormatException.class, () -> TestParser.toInt(input));
    }
}
