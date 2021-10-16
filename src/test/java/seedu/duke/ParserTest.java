package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.parser.Parser;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void parseCommand_hasFrontWhitespace_success() {
        String input = " bye\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setIn(in);
        Parser.parseCommand(input);
        assertEquals("", out.toString());
    }

    @Test
    public void removeCommandWord_provideValidInputForAdd_success() {
        String input = "add to pay /def haraimasu";
        assertEquals("to pay /def haraimasu", Parser.removeCommandWord(input, 3));
    }

    @Test
    public void removeCommandWord_provideValidInputForDelete_success() {
        String input = "delete 1";
        assertEquals("1", Parser.removeCommandWord(input, 6));
    }
}
