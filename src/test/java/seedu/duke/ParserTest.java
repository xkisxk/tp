package seedu.duke;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void programLogic_hasFrontWhitespace_success() {
        Parser parser = new Parser();

        String input = " bye\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setIn(in);
        parser.programLogic();
        assertEquals("", out.toString());
    }
}
