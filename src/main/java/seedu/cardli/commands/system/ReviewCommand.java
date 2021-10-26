package seedu.cardli.commands.system;

import seedu.cardli.commands.Command;
import seedu.cardli.commands.CommandResult;

public class ReviewCommand extends Command {

    public ReviewCommand() {
        super("ReviewCommand");
    }

    @Override
    public CommandResult execute() {
        CommandResult result;
        result = new CommandResult("Entering review mode...", false, false, false, true);
        return result;
    }
}
