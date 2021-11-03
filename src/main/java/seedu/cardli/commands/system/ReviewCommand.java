package seedu.cardli.commands.system;

import seedu.cardli.commands.Command;
import seedu.cardli.commands.CommandResult;

public class ReviewCommand extends Command {

    public ReviewCommand(String arguments) {
        super("ReviewCommand", arguments);
    }

    @Override
    public CommandResult execute() {
        CommandResult result;
        if (arguments.length() > 0) {
            return new CommandResult("There should not be any arguments.");
        }
        result = new CommandResult("Entering review mode...", false, false, false, true);
        return result;
    }
}
