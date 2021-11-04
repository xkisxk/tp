package seedu.cardli.commands.system;


import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.cardli.commands.Command;
import seedu.cardli.commands.CommandResult;
import seedu.cardli.exceptions.CardLiException;
import seedu.cardli.exceptions.DeckNotExistException;
import seedu.cardli.exceptions.FieldEmptyException;
import seedu.cardli.exceptions.InvalidCommandFormatException;
import seedu.cardli.flashcard.DeckManager;
import seedu.cardli.parser.Parser;
import seedu.cardli.parser.system.EditDeckParser;
import seedu.cardli.testing.TestManager;

public class EditDeckCommand extends Command {

    private static final String FIELD_EMPTY_ERROR_MESSAGE = "You cannot leave any field empty! "
            + "Format should be\nedit /d <deck index/name of deck> /n <new name of deck>";
    private static final String WRONG_ORDER_ERROR_MESSAGE = "Incorrect edit command! /n should come after /d. Format "
            + "should be\nedit /d <deck index/name of deck> /n <new name of deck>";
    private static final String INVALID_INDEX_ERROR_MESSAGE = "Incorrect index for deck!";
    private static final String ARGUMENT_TYPE_ERROR_MESSAGE = "You can only input the index of the deck, which is "
            + "a positive integer!";
    private static final String LARGE_INTEGER_ERROR_MESSAGE = "Deck index must be smaller than 2147483647.";
    private static final String INVALID_ARGUMENTS_MESSAGE = "Please use the correct flags and in the correct order! "
            + "\nFormat + should be edit /d <deck index/name of deck> /n <new name of deck>";
    private static final String FLAG_ARGUMENT_ERROR_MESSAGE = "You should not use this command's flag as your argument";
    private static final String MISSING_FLAG_MESSAGE = "You are missing the relevant flag/flags";

    private EditDeckParser parser;
    private DeckManager deckManager;
    private static Logger logger = Logger.getLogger(TestManager.class.getName());

    public EditDeckCommand(String arguments, DeckManager deckManager) {
        super("EditDeckCommand", arguments);
        this.parser = new EditDeckParser();
        this.deckManager = deckManager;
    }

    public static String prepareDeckIndex(String deck, DeckManager deckManager) throws CardLiException,
            NumberFormatException {
        logger.setLevel(Level.WARNING);
        logger.log(Level.INFO, "preparing Deck Index");
        int deckIndex = 0;
        if (Parser.isInteger(deck)) {
            logger.log(Level.INFO, "checking if integer deckIndex is out of bounds");
            //deck is an index
            deckIndex = Integer.parseInt(deck) - 1;
            if (!(deckIndex >= 0 && deckIndex < deckManager.getDecksSize())) {
                throw new DeckNotExistException(INVALID_INDEX_ERROR_MESSAGE);
            }
        } else {

            throw new CardLiException(ARGUMENT_TYPE_ERROR_MESSAGE);
        }

        return deck;
    }

    public String[] prepareEditDeckCommand() throws CardLiException, NumberFormatException {

        logger.setLevel(Level.WARNING);
        logger.log(Level.INFO, "preparing EditDeckCommand");

        if (arguments.isEmpty()) {
            throw new FieldEmptyException(FIELD_EMPTY_ERROR_MESSAGE);
        }


        //maybe remove
        logger.log(Level.INFO, "Checking if input contains /d and /n");
        if (!arguments.contains("/d") || !arguments.contains("/n")) {
            throw new FieldEmptyException(MISSING_FLAG_MESSAGE);
        }
        logger.log(Level.INFO, "Checking if /d and /n are in the right order");
        if (!(arguments.indexOf("/d") < arguments.indexOf("/n"))) {
            throw new InvalidCommandFormatException(WRONG_ORDER_ERROR_MESSAGE);
        }
        logger.log(Level.INFO, "Splitting the input up");
        //"", deck, name // /d, deck, /n, name
        String[] parameters = parser.parseArguments(super.arguments);
        logger.log(Level.INFO, "Checking if there is enough arguments");
        if (parameters.length != 4) {
            throw new FieldEmptyException(FIELD_EMPTY_ERROR_MESSAGE);
        }
        logger.log(Level.INFO, "Checking if /d and /n are in the right order");
        if (!(parameters[0].trim().equals("/d") && parameters[2].trim().equals("/n"))) {
            throw new InvalidCommandFormatException(INVALID_ARGUMENTS_MESSAGE);
        }

        String deck = parameters[1].trim();
        String input = parameters[3].trim();
        //maybe remove
        logger.log(Level.INFO, "Checking if any field is empty");
        if (deck.isEmpty() || input.isEmpty()) {
            throw new FieldEmptyException(FIELD_EMPTY_ERROR_MESSAGE);
        }
        if (input.equalsIgnoreCase("/d") || input.equalsIgnoreCase("/n")) {
            throw new CardLiException(FLAG_ARGUMENT_ERROR_MESSAGE);
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
        } catch (NumberFormatException e) {
            result = new CommandResult(LARGE_INTEGER_ERROR_MESSAGE);
        }
        return result;
    }
}
