package seedu.duke.exceptions;

/**
 * Represents exception thrown when a field that is expected to be filled is empty.
 */
public class FieldEmptyException extends CardLiException {
    public FieldEmptyException() {
        super();
    }

    public FieldEmptyException(String message) {
        super(message);
    }
}
