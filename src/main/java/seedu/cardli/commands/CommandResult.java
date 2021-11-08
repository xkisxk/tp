package seedu.cardli.commands;

/**
 * Implements the class CommandResult, which contains the result of the
 * executed command and some flags.
 */
public class CommandResult {

    private final String result;
    private final boolean exit;
    private final boolean enter;

    /**
     * Constructs a {@code CommandResult} with the specified result.
     */
    public CommandResult(String result) {
        this.result = result;
        this.exit = false;
        this.enter = false;
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     * @param result the result of the {@code Command} execution
     * @param exit whether to exit the programme or return to the main menu
     * @param enter whether to enter a deck
     */
    public CommandResult(String result, boolean exit, boolean enter) {
        this.result = result;
        this.exit = exit;
        this.enter = enter;
    }

    /**
     * Getter for result.
     */
    public String getResult() {
        return this.result;
    }

    /**
     * Getter for exit.
     */
    public boolean isExit() {
        return this.exit;
    }

    /**
     * Getter for enter.
     */
    public boolean isEnter() {
        return this.enter;
    }
}
