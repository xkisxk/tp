package seedu.cardli.commands.deck;

import seedu.cardli.commands.Command;
import seedu.cardli.commands.CommandResult;
import seedu.cardli.ui.CardLiUi;

public class HelpInDeckCommand extends Command {
    public HelpInDeckCommand() {
        super("HelpInDeckCommand");
    }

    @Override
    public CommandResult execute() {
        CommandResult result = new CommandResult(CardLiUi.returnHelpInDeckMessage());
        return result;
    }
}