package seedu.duke.commands.deck;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;

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