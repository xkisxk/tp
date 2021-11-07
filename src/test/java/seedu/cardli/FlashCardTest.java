package seedu.cardli;

import org.junit.jupiter.api.Test;
import seedu.cardli.flashcard.FlashCard;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlashCardTest {

    private FlashCard flashCard = new FlashCard("testFront", "testBack");

    @Test
    public void testGetFront() {
        assertEquals("testFront", flashCard.getFront());
    }

    @Test
    public void testGetBack() {
        assertEquals("testBack", flashCard.getBack());
    }

    //@@author xRossKoh
    @Test
    public void testToString() {
        assertEquals("testFront | testBack | 0 | 0\n", flashCard.toString());
    }

    @Test
    public void testToJsonObject() {
        assertEquals("{\"back\":\"testBack\",\"front\":\"testFront\",\"userScore\":0,\"totalScore\":0}",
                flashCard.toJsonObject().toJSONString());
    }
}
