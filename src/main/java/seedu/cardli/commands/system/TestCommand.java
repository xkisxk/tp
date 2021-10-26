package seedu.cardli.commands.system;

import seedu.cardli.commands.Command;
import seedu.cardli.commands.CommandResult;

public class TestCommand extends Command {

    public TestCommand() {
        super("TestCommand");
    }

    @Override
    public CommandResult execute() {
        CommandResult result;
        result = new CommandResult("Entering test mode...", false, false, true, false);
        return result;
    }
}
