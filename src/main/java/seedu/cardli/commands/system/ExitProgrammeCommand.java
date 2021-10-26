package seedu.cardli.commands.system;

import seedu.cardli.commands.Command;
import seedu.cardli.commands.CommandResult;

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
