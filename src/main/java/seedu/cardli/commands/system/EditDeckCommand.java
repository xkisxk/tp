package seedu.cardli.commands.system;


import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.cardli.commands.Command;
import seedu.cardli.commands.CommandResult;
import seedu.cardli.exceptions.CardLiException;
import seedu.cardli.exceptions.DeckNotExistException;
import seedu.cardli.exceptions.FieldEmptyException;
import seedu.cardli.exceptions.InvalidCommandFormatException;
import seedu.cardli.flashcard.Deck;
import seedu.cardli.flashcard.DeckManager;
import seedu.cardli.parser.Parser;
import seedu.cardli.parser.system.EditDeckParser;
import seedu.cardli.testing.TestManager;


public class EditDeckCommand extends Command {

    private static final String FIELD_EMPTY_ERROR_MESSAGE = "You cannot leave any field empty! "
            + "Format should be\n edit /d <deck index/name of deck> /n <new name of deck>";
    private static final String WRONG_ORDER_ERROR_MESSAGE = "Incorrect edit command! Format should be\n"
            + "edit /d <deck index/name of deck> /n <new name of deck>";
    private static final String INVALID_INDEX_ERROR_MESSAGE = "Incorrect index for deck!";
    private static final String NO_SUCH_DECK_ERROR_MESSAGE = "No deck goes by that name!";

    private EditDeckParser parser;
    private DeckManager deckManager;
    private static Logger logger = Logger.getLogger(TestManager.class.getName());

    public EditDeckCommand(String arguments, DeckManager deckManager) {
        super("EditDeckCommand", arguments);
        this.parser = new EditDeckParser();
        this.deckManager = deckManager;
    }

    public static String prepareDeckIndex(String deck, DeckManager deckManager) throws CardLiException {
        logger.setLevel(Level.WARNING);
        logger.log(Level.INFO, "preparing Deck Index");
        int deckIndex = 0;
        if (Parser.isInteger(deck)) {
            logger.log(Level.INFO, "checking if integer deckIndex is out of bounds");
            //deck is an index
            deckIndex = Integer.parseInt(deck) - 1;
            if (!(deckIndex >= 0 && deckIndex <= deckManager.getDecksSize())) {
                throw new DeckNotExistException(INVALID_INDEX_ERROR_MESSAGE);
            }
        } else {
            logger.log(Level.INFO, "Checking if String deckIndex exists in deck");
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

        return deck;
    }

    public String[] prepareEditDeckCommand() throws CardLiException {
        logger.setLevel(Level.WARNING);
        logger.log(Level.INFO, "preparing EditDeckCommand");
        logger.log(Level.INFO, "Checking if input contains /d and /n");
        if (!arguments.toLowerCase().contains("/d") || !arguments.toLowerCase().contains("/n")) {
            throw new FieldEmptyException(FIELD_EMPTY_ERROR_MESSAGE);
        }
        logger.log(Level.INFO, "Checking if /d and /n are in the right order");
        if (!(arguments.indexOf("/d") < arguments.indexOf("/n"))) {
            throw new InvalidCommandFormatException(WRONG_ORDER_ERROR_MESSAGE);
        }
        logger.log(Level.INFO, "Splitting the input up");
        //"", deck, name
        String[] parameters = parser.parseArguments(super.arguments);
        logger.log(Level.INFO, "Checking if there is enough arguments");
        if (parameters.length < 3) {
            throw new FieldEmptyException(FIELD_EMPTY_ERROR_MESSAGE);
        }

        String deck = parameters[1].trim();
        String input = parameters[2].trim();

        logger.log(Level.INFO, "Checking if any field is empty");
        if (deck.isEmpty() || input.isEmpty()) {
            throw new FieldEmptyException(FIELD_EMPTY_ERROR_MESSAGE);
        }
        logger.log(Level.INFO, "preparing deckIndex");
        deck = prepareDeckIndex(deck, deckManager);

        String[] preparedArguments = {deck, input};
        return preparedArguments;
    }

    @Override
    public CommandResult execute() { //edit /d <deck index> /n <name>
        CommandResult result;
        try {
            String[] parameters = prepareEditDeckCommand();
            result = new CommandResult(this.deckManager.editDeck(parameters));
        } catch (CardLiException e) {
            result = new CommandResult(e.getMessage());
        }
        return result;
    }
}
