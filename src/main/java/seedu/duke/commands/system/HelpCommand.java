package seedu.duke.commands.system;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.ui.CardLiUi;

public class HelpCommand extends Command {
    public HelpCommand() {
        super("HelpCommand");
    }

    @Override
    public CommandResult execute() {
        CommandResult result = new CommandResult(CardLiUi.returnHelpMessage());
        return result;
    }
}