package seedu.cardli.commands.system;

import seedu.cardli.commands.Command;
import seedu.cardli.commands.CommandResult;
import seedu.cardli.testing.TestManager;

public class TestCommand extends Command {

    private final TestManager testManager;

    public TestCommand(String arguments, TestManager testManager) {
        super("TestCommand", arguments);
        this.testManager = testManager;
    }

    @Override
    public CommandResult execute() {
        CommandResult result;
        if (arguments.length() > 0) {
            return new CommandResult("There should not be any arguments.");
        }
        result = new CommandResult(testManager.startTest(), false, false);
        return result;
    }
}
