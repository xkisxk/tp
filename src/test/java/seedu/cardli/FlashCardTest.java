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
}
