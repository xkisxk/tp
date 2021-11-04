package seedu.cardli.commands.system;

import seedu.cardli.commands.Command;
import seedu.cardli.commands.CommandResult;
import seedu.cardli.exceptions.DeckNotExistException;
import seedu.cardli.parser.TestParser;
import seedu.cardli.testing.TestHistory;

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
            result = new CommandResult("Input a positive integer or \"all\" after viewtest.");
        } catch (IndexOutOfBoundsException e) {
            result = new CommandResult("There is no test at that index.");
        } catch (DeckNotExistException e) {
            result = new CommandResult("You have not taken any tests.");
        }
        return result;
    }
}
