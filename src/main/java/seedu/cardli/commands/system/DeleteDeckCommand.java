package seedu.cardli.commands.system;

import seedu.cardli.commands.Command;
import seedu.cardli.commands.CommandResult;
import seedu.cardli.exceptions.DeckNotExistException;
import seedu.cardli.flashcard.DeckManager;
import seedu.cardli.parser.Parser;
import seedu.cardli.parser.system.DeleteDeckParser;

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

        try {
            if (Parser.isInteger(enterInput)) {
                int deckIndex = Integer.parseInt(enterInput) - 1;
                return new CommandResult(deckManager.deleteDeck(deckIndex));
            } else {
                throw new DeckNotExistException("Please input a positive integer.");
            }

        } catch (IndexOutOfBoundsException e) {
            result = new CommandResult("This deck does not exist.");

        } catch (DeckNotExistException e) {
            result = new CommandResult(e.getMessage());
        } catch (NumberFormatException e) {
            result = new CommandResult("Deck index must be smaller than 2147483647.");
        }
        return result;
    }
}
