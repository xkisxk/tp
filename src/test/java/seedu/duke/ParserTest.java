package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.CardLiException;
import seedu.duke.exceptions.FieldEmptyException;
import seedu.duke.parser.Parser;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    @Test
    public void parseCommand_hasFrontWhitespace_success() {
        String input = " bye\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setIn(in);
        try {
            Parser.parseCommand(input);
        } catch (CardLiException e) {
            e.printStackTrace();
        }
        assertEquals("", out.toString());
    }

    @Test
    public void parseEditCardCommand_noArguments_expectFieldEmptyException() {
        String input = "editcard ";
        assertThrows(FieldEmptyException.class, () -> Parser.parseEditCardCommand(input));
    }

    @Test
    public void parseEditDeckCommand_noArguments_expectFieldEmptyException() {
        String input = "editdeck ";
        assertThrows(FieldEmptyException.class, () -> Parser.parseEditDeckCommand(input));
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
