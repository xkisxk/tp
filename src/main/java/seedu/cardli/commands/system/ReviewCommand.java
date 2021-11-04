package seedu.cardli.commands.system;

import seedu.cardli.commands.Command;
import seedu.cardli.commands.CommandResult;
import seedu.cardli.testing.TestManager;

public class ReviewCommand extends Command {

    private final TestManager testManager;

    public ReviewCommand(String arguments, TestManager testManager) {
        super("ReviewCommand", arguments);
        this.testManager = testManager;
    }

    @Override
    public CommandResult execute() {
        CommandResult result;
        if (arguments.length() > 0) {
            return new CommandResult("There should not be any arguments.");
        }
        result = new CommandResult(testManager.startReview(), false, false);
        return result;
    }
}
