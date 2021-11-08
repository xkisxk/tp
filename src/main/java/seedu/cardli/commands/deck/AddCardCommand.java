package seedu.cardli.commands.deck;

import seedu.cardli.commands.Command;
import seedu.cardli.commands.CommandResult;
import seedu.cardli.exceptions.CardLiException;
import seedu.cardli.exceptions.FieldEmptyException;
import seedu.cardli.flashcard.Deck;
import seedu.cardli.flashcard.DeckManager;
import seedu.cardli.parser.deck.AddCardParser;

/**
 * Implements the AddCardCommand class, which adds a card with the
 * specified front and back to the given deck.
 */
public class AddCardCommand extends Command {

    private static final String FIELD_EMPTY_ERROR_MESSAGE = "You cannot leave any field empty! "
            + "Format should be\n add /f <words on front> /b <words on back>";
    private static final String WRONG_ORDER_ERROR_MESSAGE = "Incorrect add command! Format should be\n"
            + "add /f <front> /b <back>";

    private AddCardParser parser;
    private Deck deck;
    private DeckManager deckManager;

    public AddCardCommand(String arguments, Deck deck, DeckManager deckManager) {
        super("AddCardCommand", arguments);
        this.deck = deck;
        this.deckManager = deckManager;
        this.parser = new AddCardParser();
    }

    //@@author JWweiyin
    @Override
    public CommandResult execute() {
        CommandResult result;
        try {
            if (!arguments.toLowerCase().contains("/f") || !arguments.toLowerCase().contains("/b")) {
                throw new FieldEmptyException(FIELD_EMPTY_ERROR_MESSAGE);
            }
            String[] rawParameters = parser.parseArguments(super.arguments);

            if (rawParameters.length < 2) {
                throw new FieldEmptyException(FIELD_EMPTY_ERROR_MESSAGE);
            }

            String front = rawParameters[0].trim();
            String back = rawParameters[1].trim();

            if (front.isEmpty() || back.isEmpty()) {
                throw new FieldEmptyException(FIELD_EMPTY_ERROR_MESSAGE);
            }
            String deckWithSameNameCard = deckManager.cardHasSameName(front);
            if (!deckWithSameNameCard.isEmpty()) {
                throw new CardLiException("There is already a card with " + front + " on the front in deck "
                        + deckWithSameNameCard + ".");

            }

            String[] parameters = {front, back};
            result = new CommandResult(deck.prepareToAddFlashCard(parameters));
        } catch (CardLiException e) {
            result = new CommandResult(e.getMessage());
        }
        return result;
    }
}
