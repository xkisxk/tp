package seedu.cardli.commands.system;

import seedu.cardli.commands.Command;
import seedu.cardli.commands.CommandResult;

public class TestCommand extends Command {

    public TestCommand(String arguments) {
        super("TestCommand", arguments);
    }

    @Override
    public CommandResult execute() {
        CommandResult result;
        if (arguments.length() > 0) {
            return new CommandResult("There should not be any arguments.");
        }
        result = new CommandResult("Entering test mode...", false, false, true, false);
        return result;
    }
}
