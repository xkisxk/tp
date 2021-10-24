package seedu.duke.commands.system;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.exceptions.DeckNotExistException;
import seedu.duke.flashcard.DeckManager;
import seedu.duke.parser.Parser;
import seedu.duke.parser.system.DeleteDeckParser;

public class DeleteDeckCommand extends Command {

    private DeleteDeckParser parser;
    private DeckManager deckManager;

    public DeleteDeckCommand(String arguments, DeckManager deckManager) {
        super("DeleteDeckCommand", arguments);
        this.parser = new DeleteDeckParser();
        this.deckManager = deckManager;
    }

    @Override
    public CommandResult execute() {
        String[] parameters = parser.parseArguments(super.arguments);
        String enterInput = parameters[0];

        CommandResult result;

        if (Parser.isInteger(enterInput)) {
            int deckIndex = Integer.parseInt(enterInput) - 1;
            return new CommandResult(deckManager.deleteDeck(deckIndex));
        }

        try {
            result = new CommandResult(deckManager.deleteDeck(enterInput));
        } catch (DeckNotExistException e) {
            result = new CommandResult(e.getMessage());
        }
        return result;
    }
}
