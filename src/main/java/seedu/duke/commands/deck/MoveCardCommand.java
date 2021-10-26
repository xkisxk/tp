package seedu.duke.commands.deck;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.commands.system.EditDeckCommand;
import seedu.duke.exceptions.CardLiException;
import seedu.duke.exceptions.FieldEmptyException;
import seedu.duke.exceptions.InvalidCommandFormatException;
import seedu.duke.flashcard.Deck;
import seedu.duke.flashcard.DeckManager;
import seedu.duke.parser.system.MoveCardParser;
import seedu.duke.testing.TestManager;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MoveCardCommand extends Command {

    private static final String FIELD_EMPTY_ERROR_MESSAGE = "You cannot leave any field empty! "
            + "Format should be\n move /c <card index/front phrase of card> /d <deck index/name of deck>";
    private static final String WRONG_ORDER_ERROR_MESSAGE = "Incorrect move command! Format should be\n"
            + "move /c <card index/front phrase of card> /d <deck index/name of deck>";
    private static final String SAME_DESTINATION_ERROR_MESSAGE = "Your card is already in the deck specified!";

    private MoveCardParser parser;
    private Deck deck;
    private DeckManager deckManager;
    private static Logger logger = Logger.getLogger(TestManager.class.getName());

    public MoveCardCommand(String arguments, Deck deck, DeckManager deckManager) {
        super("EditCardCommand", arguments);
        this.deck = deck;
        this.deckManager = deckManager;
        this.parser = new MoveCardParser();
    }

    public String[] prepareMoveCommand() throws CardLiException {
        logger.setLevel(Level.WARNING);
        logger.log(Level.INFO, "preparing MoveCommand");
        logger.log(Level.INFO, "Checking if input contains /d and /c");
        if (!arguments.toLowerCase().contains("/c") || !arguments.toLowerCase().contains("/d")) {
            throw new FieldEmptyException(FIELD_EMPTY_ERROR_MESSAGE);
        }
        logger.log(Level.INFO, "Checking if /d and /c are in the right order");
        if (!(arguments.indexOf("/c") < arguments.indexOf("/d"))) {
            throw new InvalidCommandFormatException(WRONG_ORDER_ERROR_MESSAGE);
        }
        logger.log(Level.INFO, "Splitting the input up");
        // "", card, deck
        String[] rawParameters = parser.parseArguments(super.arguments);
        logger.log(Level.INFO, "Checking if there is enough arguments");
        if (rawParameters.length < 3) {
            throw new FieldEmptyException(FIELD_EMPTY_ERROR_MESSAGE);
        }

        String cardInput = rawParameters[1].trim();
        String deckInput = rawParameters[2].trim();

        logger.log(Level.INFO, "Checking if any field is empty");
        if (cardInput.isEmpty() || deckInput.isEmpty()) {
            throw new FieldEmptyException(FIELD_EMPTY_ERROR_MESSAGE);
        }
        logger.log(Level.INFO, "preparing CardIndex and DeckIndex");
        cardInput = EditCardCommand.prepareCardIndex(cardInput, deck);
        deckInput = EditDeckCommand.prepareDeckIndex(deckInput, deckManager);

        logger.log(Level.INFO, "checking if user is already in specified deck");
        if ((Integer.parseInt(deckInput) - 1) == deckManager.getDeckIndex(deck)) {
            throw new CardLiException(SAME_DESTINATION_ERROR_MESSAGE);
        }

        String sourceDeckIndex = String.valueOf(deckManager.getDeckIndex(deck));

        String[] preparedArguments = {sourceDeckIndex, cardInput, deckInput};

        return preparedArguments;
    }

    @Override
    public CommandResult execute() { //move /c <index> /d <index
        CommandResult result;
        try {
            String[] parameters = prepareMoveCommand();
            result = new CommandResult(deckManager.moveCard(parameters));
        } catch (CardLiException e) {
            result = new CommandResult(e.getMessage());
        }
        return result;
    }
}

