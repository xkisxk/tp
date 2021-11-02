package seedu.cardli.commands.deck;

import seedu.cardli.commands.Command;
import seedu.cardli.commands.CommandResult;

public class ExitDeckCommand extends Command {
    public ExitDeckCommand(String arguments) {
        super("ExitDeckCommand", arguments);
    }

    @Override
    public CommandResult execute() {
        if (arguments.length() > 0) {
            return new CommandResult("There should not be any arguments.");
        }
        CommandResult result = new CommandResult("Exiting to main menu.", true, false);
        return result;
    }
}