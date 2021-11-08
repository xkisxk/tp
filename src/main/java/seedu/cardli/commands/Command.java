package seedu.cardli.commands;

/**
 * Implements the Command class, which contains the
 * type of command and the arguments to be executed.
 */
public abstract class Command {
    protected String name;
    protected String arguments;

    /**
     * Constructs a {@code Command} with the specified fields.
     */
    public Command(String name, String arguments) {
        this.name = name;
        this.arguments = arguments;
    }

    /**
     * Constructs a {@code Command} with the given name and no arguments.
     * @param name name of command
     */
    public Command(String name) {
        this.name = name;
        this.arguments = null;
    }

    /**
     * Executes the command and returns the result as a {@code CommandResult}.
     * @return a {@code CommandResult} containing the result of the executed command
     */
    public abstract CommandResult execute();

    @Override
    public String toString() {
        return this.name;
    }
}
