package seedu.cardli.commands.system;

import seedu.cardli.commands.Command;
import seedu.cardli.commands.CommandResult;
import seedu.cardli.flashcard.DeckManager;

public class ViewDecksCommand extends Command {

    private DeckManager deckManager;

    public ViewDecksCommand(DeckManager deckManager, String arguments) {
        super("ViewDecksCommand", arguments);
        this.deckManager = deckManager;
    }

    @Override
    public CommandResult execute() {
        if (arguments.length() > 0) {
            return new CommandResult("There should not be any arguments.");
        }
        CommandResult result = new CommandResult(this.deckManager.viewDecks());
        return result;
    }
}
