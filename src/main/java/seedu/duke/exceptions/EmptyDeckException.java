package seedu.duke.exceptions;

public class EmptyDeckException extends CardLiException {
    public EmptyDeckException() {
        super();
    }

    public EmptyDeckException(String message) {
        super(message);
    }
}
