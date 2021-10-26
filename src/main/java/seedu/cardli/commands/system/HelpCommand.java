package seedu.cardli.commands.system;

import seedu.cardli.commands.Command;
import seedu.cardli.commands.CommandResult;
import seedu.cardli.ui.CardLiUi;

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