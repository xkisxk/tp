package seedu.cardli.commands.system;

import seedu.cardli.commands.Command;
import seedu.cardli.commands.CommandResult;
import seedu.cardli.testing.TestHistory;

public class ViewFlashCardStatsCommand extends Command {

    private final TestHistory testHistory;

    public ViewFlashCardStatsCommand(TestHistory testHistory) {
        super("ViewFlashCardStatsCommand");
        this.testHistory = testHistory;
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(testHistory.viewAllFlashcardStats());
    }
}
