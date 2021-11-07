//@@author ThaddeusLim99

package seedu.cardli.commands.deck;

import java.util.logging.Level;
import java.util.logging.Logger;
import seedu.cardli.commands.Command;
import seedu.cardli.commands.CommandResult;
import seedu.cardli.exceptions.CardLiException;
import seedu.cardli.exceptions.FieldEmptyException;
import seedu.cardli.exceptions.InvalidCommandFormatException;
import seedu.cardli.flashcard.Deck;
import seedu.cardli.parser.Parser;
import seedu.cardli.parser.deck.EditCardParser;
import seedu.cardli.testing.TestManager;

public class EditCardCommand extends Command {

    private static final String FIELD_EMPTY_ERROR_MESSAGE = "You cannot leave any field empty! "
            + "Format should be\nedit /c <card index> /s <side> /i <input>";
    private static final String WRONG_ORDER_ERROR_MESSAGE = "/c should come before /s, which should come before /i!"
            + " Format should be\nedit /c <card index> /s <side> /i <input>";
    private static final String INVALID_INDEX_ERROR_MESSAGE = "Incorrect index for Card!";
    private static final String INVALID_SIDE_ERROR_MESSAGE = "What side is this? It's only either front or back";
    private static final String LARGE_INTEGER_ERROR_MESSAGE = "Card index must be smaller than 2147483647.";
    private static final String ARGUMENT_TYPE_ERROR_MESSAGE = "You can only input the index of the card, which is "
            + "a positive integer!";
    private static final String INVALID_ARGUMENTS_MESSAGE = "Please use the correct flags and in the correct order! "
            + "\nFormat should be edit /c <card index> /s <side> /i <input>";
    private static final String FLAG_ARGUMENT_ERROR_MESSAGE = "You should not use this command's flag as your argument";
    private static final String MISSING_FLAG_MESSAGE = "You are missing the relevant flag/flags";

    private EditCardParser parser;
    private Deck deck;
    private static Logger logger = Logger.getLogger(TestManager.class.getName());

    public EditCardCommand(String arguments, Deck deck) {
        super("EditCardCommand", arguments);
        this.deck = deck;
        this.parser = new EditCardParser();
    }

    public static String prepareCardIndex(String card, Deck deck) throws CardLiException, NumberFormatException {
        logger.setLevel(Level.WARNING);
        logger.log(Level.INFO, "preparing Card Index");
        int cardIndex = 0;
        if (Parser.isInteger(card)) {
            logger.log(Level.INFO, "checking if integer cardIndex is out of bounds");
            //card is an index
            cardIndex = Integer.parseInt(card) - 1;
            if (!(cardIndex >= 0 && cardIndex < deck.getCards().size())) {
                throw new CardLiException(INVALID_INDEX_ERROR_MESSAGE);
            }
        } else {
            throw new CardLiException(ARGUMENT_TYPE_ERROR_MESSAGE);
        }

        return card;
    }

    public String[] prepareEditCardCommand() throws CardLiException, NumberFormatException {
        logger.setLevel(Level.WARNING);
        logger.log(Level.INFO, "preparing EditCardCommand");

        if (arguments.isEmpty()) {
            throw new FieldEmptyException(FIELD_EMPTY_ERROR_MESSAGE);
        }

        logger.log(Level.INFO, "Checking if input contains /c, /s and /i");
        if (!arguments.contains("/c") || !arguments.contains("/s")
                || !arguments.contains("/i")) {
            throw new FieldEmptyException(MISSING_FLAG_MESSAGE);
        }

        logger.log(Level.INFO, "Checking if /c,/s and /i are in the right order");
        if (!(arguments.indexOf("/c") < arguments.indexOf("/s")
                && arguments.indexOf("/s") < arguments.indexOf("/i"))) {
            throw new InvalidCommandFormatException(WRONG_ORDER_ERROR_MESSAGE);
        }

        logger.log(Level.INFO, "Splitting the input up");
        // "", card, side, input ///c <> /s <> /i <>
        String[] rawParameters = parser.parseArguments(super.arguments);

        logger.log(Level.INFO, "Checking if there is enough arguments");
        if (rawParameters.length != 6) {
            throw new FieldEmptyException(FIELD_EMPTY_ERROR_MESSAGE);
        }

        logger.log(Level.INFO, "Checking if /c,/s and /i are in the right order");
        if (!(rawParameters[0].trim().equals("/c") && rawParameters[2].trim().equals("/s")
                && rawParameters[4].trim().equals("/i"))) {
            throw new InvalidCommandFormatException(INVALID_ARGUMENTS_MESSAGE);
        }

        String card = rawParameters[1].trim();
        String side = rawParameters[3].trim();
        String input = rawParameters[5].trim();

        logger.log(Level.INFO, "Checking if any field is empty");
        if (card.isEmpty() || side.isEmpty() || input.isEmpty()) {
            throw new FieldEmptyException(FIELD_EMPTY_ERROR_MESSAGE);
        }

        if (input.equalsIgnoreCase("/c") || input.equalsIgnoreCase("/s") || input.equalsIgnoreCase("/i")) {
            throw new CardLiException(FLAG_ARGUMENT_ERROR_MESSAGE);
        }

        logger.log(Level.INFO, "checking only front and back inputted to /s");
        if (!(side.equalsIgnoreCase("front") | side.equalsIgnoreCase("back"))) {
            throw new CardLiException(INVALID_SIDE_ERROR_MESSAGE);
        }

        logger.log(Level.INFO, "preparing CardIndex");
        card = prepareCardIndex(card, deck);

        String[] preparedArguments = {card, side, input};
        return preparedArguments;
    }

    @Override
    public CommandResult execute() { //edit /c <index> /s <side> /i <input>
        CommandResult result;
        try {
            String[] parameters = prepareEditCardCommand();
            result = new CommandResult(deck.editCard(parameters));
        } catch (CardLiException e) {
            result = new CommandResult(e.getMessage());
        } catch (NumberFormatException e) {
            result = new CommandResult(LARGE_INTEGER_ERROR_MESSAGE);
        }
        return result;
    }
}
