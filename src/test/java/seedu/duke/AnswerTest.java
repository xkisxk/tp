package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnswerTest {

    @Test
    public void testGetAnswer() {
        assertEquals("testAnswer",
                new Answer("testAnswer", 1).getAnswer());
    }

}
