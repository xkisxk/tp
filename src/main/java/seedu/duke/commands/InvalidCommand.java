package seedu.duke.commands;

public class InvalidCommand extends Command {

    public InvalidCommand() {
        super("InvalidCommand");
    }

    @Override
    public CommandResult execute() {
        return new CommandResult("\tThat's not a command.");
    }
}
