package seedu.duke.commands.system;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;

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
