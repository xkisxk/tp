package seedu.duke;

/**
 * Represents information within a single flashcard
 */
public class FlashCard {
    private String front;
    private String back;

    public FlashCard(String front, String back) {
        this.front = front;
        this.back = back;
    }

    /**
     * Getter for String on front of flashcard.
     *
     * @return  String on front of flashcard
     */
    public String getFront() {
        return this.front;
    }

    /**
     * Getter for String on back of flashcard.
     *
     * @return String on back of flashcard
     */
    public String getBack() {
        return this.back;
    }
}
