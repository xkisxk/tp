package seedu.duke.commands.system;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;

/**
 * Command that lists all the decks, i.e. their name and stuff.
 */
public class ListDecksCommand extends Command {
    public ListDecksCommand() {
        super("ListDecksCommand");
    }

    @Override
    public CommandResult execute() {
        return null;
    }
}
