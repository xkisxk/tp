package seedu.cardli;

import org.junit.jupiter.api.Test;
import seedu.cardli.exceptions.FieldEmptyException;
import seedu.cardli.parser.TestParser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestParserTest {
    @Test
    public void parseUserResponse_noInput_expectFieldEmptyException() {
        String input = "";
        assertThrows(FieldEmptyException.class, () -> TestParser.parseUserResponse(input));
    }

    @Test
    public void parseUserResponse_trailingSpaces_expectTrimmedInput() throws FieldEmptyException {
        String input = "s       ";
        assertEquals("s", TestParser.parseUserResponse(input));
    }

    @Test
    public void toInt_invalidInput_expectNumberFormatException() {
        String input = "call";
        assertThrows(NumberFormatException.class, () -> TestParser.toInt(input));
    }

    @Test
    public void toInt_all_expectMinusOne() {
        String input = "all";
        assertEquals(-1, TestParser.toInt("all"));
    }
}
