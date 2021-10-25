package seedu.duke.commands.system;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.exceptions.CardLiException;
import seedu.duke.exceptions.DeckNotExistException;
import seedu.duke.exceptions.FieldEmptyException;
import seedu.duke.exceptions.InvalidCommandFormatException;
import seedu.duke.flashcard.Deck;
import seedu.duke.flashcard.DeckManager;
import seedu.duke.flashcard.FlashCard;
import seedu.duke.parser.Parser;
import seedu.duke.parser.system.EditDeckParser;

public class EditDeckCommand extends Command {

    private static final String FIELD_EMPTY_ERROR_MESSAGE = "You cannot leave any field empty! "
            + "Format should be\n edit /deck <cat index> /input <input>";
    private static final String WRONG_ORDER_ERROR_MESSAGE = "Incorrect edit command! Format should be\n"
            + "editdeck /deck <deck index> /input <input>";
    private static final String INVALID_INDEX_ERROR_MESSAGE = "Incorrect index for deck!";
    private static final String NO_SUCH_DECK_ERROR_MESSAGE = "No deck goes by that name!";

    private EditDeckParser parser;
    private DeckManager deckManager;

    public EditDeckCommand(String arguments, DeckManager deckManager) {
        super("EditDeckCommand", arguments);
        this.parser = new EditDeckParser();
        this.deckManager = deckManager;
    }

    @Override
    public CommandResult execute() { //edit /d <deck index> /n <name>
        CommandResult result;
        try {
            if (!arguments.toLowerCase().contains("/d") || !arguments.toLowerCase().contains("/n")) {
                throw new FieldEmptyException(FIELD_EMPTY_ERROR_MESSAGE);
            }

            if (!(arguments.indexOf("/d") < arguments.indexOf("/n"))) {
                throw new InvalidCommandFormatException(WRONG_ORDER_ERROR_MESSAGE);
            }

            //"", deck, name
            String[] parameters = parser.parseArguments(super.arguments);

            String deck = parameters[1].trim();
            String input = parameters[2].trim();
            if (deck.isEmpty() || input.isEmpty()) {
                throw new FieldEmptyException(FIELD_EMPTY_ERROR_MESSAGE);
            }

            int deckIndex = 0;
            if (Parser.isInteger(deck)) {
                //deck is an index
                deckIndex = Integer.parseInt(deck) - 1;
                if (!(deckIndex >= 0 && deckIndex <= this.deckManager.getDecksSize())) {
                    throw new DeckNotExistException(INVALID_INDEX_ERROR_MESSAGE);
                }
            } else {
                //deck is a string input corresponding to name of the deck
                boolean deckFound = false;
                for (Deck d : deckManager.getDecks()) {
                    if (d.getName().equalsIgnoreCase(deck)) {
                        //card now is a string type containing index of card to be edited
                        //assume no duplicate cards
                        deck = String.valueOf(deckManager.getDeckIndex(d) + 1);
                        deckFound = true;
                    }
                }
                if (!deckFound) {
                    throw new CardLiException(NO_SUCH_DECK_ERROR_MESSAGE);
                }
            }

            String[] editedParameters = {deck, input};

            result = new CommandResult(this.deckManager.editDeck(editedParameters));
        } catch (CardLiException e) {
            result = new CommandResult(e.getMessage());
        }
        return result;
    }
}
