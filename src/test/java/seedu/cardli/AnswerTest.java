package seedu.cardli;

import org.junit.jupiter.api.Test;
import seedu.cardli.testing.Answer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnswerTest {

    @Test
    public void testGetAnswer() {
        assertEquals("testAnswer",
                new Answer("testAnswer", 1).getAnswer());
    }

}
