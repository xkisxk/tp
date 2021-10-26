package seedu.cardli.commands.system;

import seedu.cardli.commands.Command;
import seedu.cardli.commands.CommandResult;
import seedu.cardli.flashcard.DeckManager;

public class ViewDecksCommand extends Command {

    private DeckManager deckManager;

    public ViewDecksCommand(DeckManager deckManager) {
        super("ViewDecksCommand");
        this.deckManager = deckManager;
    }

    @Override
    public CommandResult execute() {
        CommandResult result = new CommandResult(this.deckManager.viewDecks());
        return result;
    }
}
