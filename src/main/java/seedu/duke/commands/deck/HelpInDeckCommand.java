package seedu.duke.commands.deck;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.ui.CardLiUi;

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