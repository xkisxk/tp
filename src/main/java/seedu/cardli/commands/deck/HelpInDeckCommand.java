//@@author astralum

package seedu.cardli.commands.deck;

import seedu.cardli.commands.Command;
import seedu.cardli.commands.CommandResult;
import seedu.cardli.ui.CardLiUi;

/**
 * Implements the HelpInDeck class, which returns a help message with a
 * list of commands the user can input at the deck level.
 */
public class HelpInDeckCommand extends Command {
    public HelpInDeckCommand(String arguments) {
        super("HelpInDeckCommand", arguments);
    }

    @Override
    public CommandResult execute() {
        if (arguments.length() > 0) {
            return new CommandResult("There should not be any arguments.");
        }
        CommandResult result = new CommandResult(CardLiUi.returnHelpInDeckMessage());
        return result;
    }
}