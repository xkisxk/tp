package seedu.duke.commands.system;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.exceptions.CardLiException;
import seedu.duke.exceptions.DeckNotExistException;
import seedu.duke.exceptions.FieldEmptyException;
import seedu.duke.exceptions.InvalidCommandFormatException;
import seedu.duke.flashcard.DeckManager;
import seedu.duke.parser.system.EditDeckParser;

public class EditDeckCommand extends Command {

    private static final String FIELD_EMPTY_ERROR_MESSAGE = "You cannot leave any field empty! "
            + "Format should be\n edit /deck <cat index> /input <input>";
    private static final String WRONG_ORDER_ERROR_MESSAGE = "Incorrect edit command! Format should be\n"
            + "editdeck /deck <deck index> /input <input>";
    private static final String INVALID_INDEX_ERROR_MESSAGE = "Incorrect index for deck!";

    private EditDeckParser parser;
    private DeckManager deckManager;

    public EditDeckCommand(String arguments, DeckManager deckManager) {
        super("EditDeckCommand", arguments);
        this.parser = new EditDeckParser();
        this.deckManager = deckManager;
    }

    @Override
    public CommandResult execute() { //edit /deck <cat index> /input <input>
        CommandResult result;
        try {
            if (!arguments.toLowerCase().contains("/deck") || !arguments.toLowerCase().contains("/input")) {
                throw new FieldEmptyException(FIELD_EMPTY_ERROR_MESSAGE);
            }
            String[] parameters = parser.parseArguments(super.arguments);

            if (!parameters[0].equalsIgnoreCase("/deck") | !parameters[2].equalsIgnoreCase("/input")) {
                throw new InvalidCommandFormatException(WRONG_ORDER_ERROR_MESSAGE);
            }

            String deck = parameters[1];
            String input = parameters[3];
            if (deck.isEmpty() || input.isEmpty()) {
                throw new FieldEmptyException(FIELD_EMPTY_ERROR_MESSAGE);
            }

            int deckIndex = Integer.parseInt(deck) - 1; // TODO: possibly accept either card name or index

            if (!(deckIndex >= 0 && deckIndex <= this.deckManager.getDecksSize())) {
                throw new DeckNotExistException(INVALID_INDEX_ERROR_MESSAGE);
            }

            String[] editedParameters = {deck, input};

            result = new CommandResult(this.deckManager.editDeck(editedParameters));
        } catch (CardLiException e) {
            // TODO: FieldEmptyException is subclass of CardLiException, so can't put both in the conditional above
            // TODO: check if want to separate them?
            result = new CommandResult(e.getMessage());
        }
        return result;
    }
}
