package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.parser.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void parseCommand_hasFrontWhitespace_success() {
        String input = " bye\n";
        assertEquals("bye", Parser.getCommandType(input));
    }

}
