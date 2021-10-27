package seedu.duke.commands.system;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;

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
