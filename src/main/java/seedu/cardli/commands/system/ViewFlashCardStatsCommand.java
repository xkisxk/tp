package seedu.cardli.commands.system;

import seedu.cardli.commands.Command;
import seedu.cardli.commands.CommandResult;
import seedu.cardli.testing.TestHistory;

public class ViewFlashCardStatsCommand extends Command {

    private final TestHistory testHistory;

    public ViewFlashCardStatsCommand(String arguments, TestHistory testHistory) {
        super("ViewFlashCardStatsCommand", arguments);
        this.testHistory = testHistory;
    }

    @Override
    public CommandResult execute() {
        if (arguments.length() > 0) {
            return new CommandResult("There should not be any arguments.");
        }
        return new CommandResult(testHistory.viewAllFlashcardStats());
    }
}
