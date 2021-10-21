package seedu.duke.commands.system;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.flashcard.Deck;
import seedu.duke.flashcard.DeckManager;
import seedu.duke.parser.system.DeleteDeckParser;

public class DeleteDeckCommand extends Command {

    private DeleteDeckParser parser;
    private DeckManager deckManager;

    public DeleteDeckCommand(DeckManager deckManager) {
        super("DeleteDeckCommand");
        this.parser = new DeleteDeckParser();
        this.deckManager = deckManager;
    }
    @Override
    public CommandResult execute() {
        String[] parameters = parser.parseArguments(super.arguments);
        String enterInput = parameters[0];
        int deckIndex = Integer.parseInt(enterInput) - 1;

        // TODO: add functionality to delete deck
        // since parameters[0] is a String, can take it as either the
        // deck name or index
        CommandResult result = new CommandResult("");
        return result;
    }
}
