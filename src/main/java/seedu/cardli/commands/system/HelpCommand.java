//@@author astralum
package seedu.cardli.commands.system;

import seedu.cardli.commands.Command;
import seedu.cardli.commands.CommandResult;
import seedu.cardli.ui.CardLiUi;

public class HelpCommand extends Command {
    public HelpCommand(String arguments) {
        super("HelpCommand", arguments);
    }

    @Override
    public CommandResult execute() {
        if (arguments.length() > 0) {
            return new CommandResult("There should not be any arguments.");
        }
        CommandResult result = new CommandResult(CardLiUi.returnHelpMessage());
        return result;
    }
}