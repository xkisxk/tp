package seedu.duke;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents information within a single flashcard.
 */
public class FlashCard {

    private static final Logger logger = Logger.getLogger("Card");
    private String front;
    private String back;

    public FlashCard(String front, String back) {
        assert front != null;
        assert back != null;
        this.front = front;
        this.back = back;
        logger.log(Level.INFO, "Card front: " + front);
        logger.log(Level.INFO, "Card back: " + back);
    }

    /**
     * Getter for String on front of flashcard.
     *
     * @return String on front of flashcard
     */
    public String getFront() {
        assert this.front != null;
        logger.log(Level.INFO, "Getting card front: " + front);
        return this.front;
    }

    /**
     * Getter for String on back of flashcard.
     *
     * @return String on back of flashcard
     */
    public String getBack() {
        assert this.back != null;
        logger.log(Level.INFO, "Getting card back: " + back);
        return this.back;
    }
}