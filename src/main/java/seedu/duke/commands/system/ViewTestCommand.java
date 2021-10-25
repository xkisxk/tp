package seedu.duke.commands.system;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.parser.TestParser;
import seedu.duke.testing.TestHistory;

public class ViewTestCommand extends Command {

    private final TestHistory testHistory;

    public ViewTestCommand(String arguments, TestHistory testHistory) {
        super("ViewTestCommand", arguments);
        this.testHistory = testHistory;
    }

    @Override
    public CommandResult execute() {
        CommandResult result;
        try {
            int index = TestParser.toInt(super.arguments.trim());
            result = new CommandResult(testHistory.prepareViewTest(index));
        } catch (NumberFormatException e) {
            result = new CommandResult("Input a number.");
        }
        return result;
    }
}
