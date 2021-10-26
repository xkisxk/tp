package seedu.cardli.exceptions;

public class DeckNotExistException extends CardLiException {
    public DeckNotExistException() {
        super();
    }

    public DeckNotExistException(String message) {
        super(message);
    }
}
