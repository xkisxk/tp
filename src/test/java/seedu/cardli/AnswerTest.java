package seedu.cardli;

import org.junit.jupiter.api.Test;
import seedu.cardli.testing.Answer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnswerTest {

    @Test
    public void testGetAnswer_sameAnswer_expectCorrectAnswer() {
        assertEquals("testAnswer",
                new Answer("testAnswer", 1).getAnswer());
    }

    @Test
    public void testGetQuestionIndex_questionOne_expectOne() {
        Answer answer = new Answer("testAnswer", 1,true);
        assertEquals(1, answer.getQuestionIndex());
    }

    @Test
    public void testIsAnswered_true_expectTrue() {
        Answer answer = new Answer("testAnswer", 1,false);
        answer.setIsAnswered();
        assertEquals(true, answer.isAnswered());
    }

    @Test
    public void testSetAnswer_sameAnswer_expectCorrectAnswer() {
        Answer answer = new Answer("", 1,true);
        answer.setAnswer("testAnswer");
        assertEquals("testAnswer", answer.getAnswer());
    }

    //@@author xRossKoh
    @Test
    public void testToString() {
        Answer answer = new Answer("testAnswer", 1,true);
        assertEquals("testAnswer | 1\n", answer.toString());
    }

    @Test
    public void testToJsonObject() {
        Answer answer = new Answer("testAnswer", 1,true);
        assertEquals("{\"questionIndex\":1,\"answer\":\"testAnswer\"}",
                answer.toJsonObject().toJSONString());
    }

}
