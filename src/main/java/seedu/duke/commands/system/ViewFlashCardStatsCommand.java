package seedu.duke.commands.system;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.testing.TestHistory;

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
