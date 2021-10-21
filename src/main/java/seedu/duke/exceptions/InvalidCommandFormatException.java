package seedu.duke.exceptions;

/**
 * Represents exception thrown when a field that is expected to be filled is empty.
 */
public class InvalidCommandFormatException extends CardLiException {
    public InvalidCommandFormatException() {
        super();
    }

    public InvalidCommandFormatException(String message) {
        super(message);
    }
}
