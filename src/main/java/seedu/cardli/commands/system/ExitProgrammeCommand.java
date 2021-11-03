package seedu.cardli.commands.system;

import seedu.cardli.commands.Command;
import seedu.cardli.commands.CommandResult;

public class ExitProgrammeCommand extends Command {
    public ExitProgrammeCommand(String arguments) {
        super("ExitProgrammeCommand", arguments);
    }

    @Override
    public CommandResult execute() {
        if (arguments.length() > 0) {
            return new CommandResult("There should not be any arguments.");
        }
        CommandResult result = new CommandResult("Exiting", true, false);
        return result;
    }
}
