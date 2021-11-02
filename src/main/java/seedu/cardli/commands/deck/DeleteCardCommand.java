package seedu.cardli.commands.deck;

import seedu.cardli.commands.Command;
import seedu.cardli.commands.CommandResult;
import seedu.cardli.exceptions.CardLiException;
import seedu.cardli.exceptions.FieldEmptyException;
import seedu.cardli.flashcard.Deck;
import seedu.cardli.parser.Parser;
import seedu.cardli.parser.deck.DeleteCardParser;

public class DeleteCardCommand extends Command {

    private static final String FIELD_EMPTY_ERROR_MESSAGE = "You cannot leave any field empty! "
            + "Format should be\n delete <index>";
    public static final String CARD_INDEX_TOO_BIG_MESSAGE = "Card index must be smaller than 2147483647.";

    private DeleteCardParser parser;
    private Deck deck;

    public DeleteCardCommand(String arguments, Deck deck) {
        super("DeleteCardCommand", arguments);
        this.parser = new DeleteCardParser();
        this.deck = deck;
    }

    @Override
    public CommandResult execute() {
        CommandResult result;

        try {
            String[] parameters = parser.parseArguments(super.arguments);
            String enterInput = parameters[0];
            if (enterInput.isEmpty()) {
                throw new FieldEmptyException(FIELD_EMPTY_ERROR_MESSAGE);
            }

            if (Parser.isInteger(enterInput)) {
                result = new CommandResult(deck.deleteFlashCardByIndex(enterInput));
            } else {
                throw new CardLiException("Please enter an integer.");
            }
        } catch (CardLiException e) {
            result = new CommandResult(e.getMessage());
        } catch (NumberFormatException e) {
            result = new CommandResult(CARD_INDEX_TOO_BIG_MESSAGE);
        }
        return result;
    }
}
