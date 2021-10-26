package seedu.cardli.commands.deck;

import seedu.cardli.commands.Command;
import seedu.cardli.commands.CommandResult;

public class ExitDeckCommand extends Command {
    public ExitDeckCommand() {
        super("ExitDeckCommand");
    }

    @Override
    public CommandResult execute() {
        CommandResult result = new CommandResult("Exiting to main menu.", true, false);
        return result;
    }
}