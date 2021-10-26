package seedu.duke.commands.system;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.ui.DukeUi;

public class HelpCommand extends Command {
    public HelpCommand() {
        super("HelpCommand");
    }

    @Override
    public CommandResult execute() {
        CommandResult result = new CommandResult(DukeUi.returnHelpMessage());
        return result;
    }
}