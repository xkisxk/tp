package seedu.cardli;

import org.junit.jupiter.api.Test;
import seedu.cardli.parser.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void parseCommand_hasFrontWhitespace_success() {
        String input = " bye\n";
        assertEquals("bye", Parser.getCommandType(input));
    }

}
