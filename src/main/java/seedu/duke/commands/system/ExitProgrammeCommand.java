package seedu.duke.commands.system;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;

public class ExitProgrammeCommand extends Command {
    public ExitProgrammeCommand() {
        super("ExitProgrammeCommand");
    }

    @Override
    public CommandResult execute() {
        CommandResult result = new CommandResult("Exiting", true, false);
        return result;
    }
}
