package seedu.duke.commands.system;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.flashcard.DeckManager;

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
