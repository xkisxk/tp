package seedu.duke.exceptions;

/**
 * Represents exception thrown when user input does not contain '/' character when invoking certain commands.
 */
public class NoSlashException extends CardLiException {
    public NoSlashException() {
        super();
    }

    public NoSlashException(String message) {
        super(message);
    }
}
