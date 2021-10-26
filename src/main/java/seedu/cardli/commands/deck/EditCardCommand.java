package seedu.cardli.commands.deck;

import seedu.cardli.commands.Command;
import seedu.cardli.commands.CommandResult;
import seedu.cardli.exceptions.CardLiException;
import seedu.cardli.exceptions.FieldEmptyException;
import seedu.cardli.exceptions.InvalidCommandFormatException;
import seedu.cardli.flashcard.Deck;
import seedu.cardli.parser.Parser;
import seedu.cardli.parser.deck.EditCardParser;

public class EditCardCommand extends Command {

    private static final String FIELD_EMPTY_ERROR_MESSAGE = "You cannot leave any field empty! "
            + "Format should be\n edit /card <card index> /side <side> /input <input>";
    private static final String WRONG_ORDER_ERROR_MESSAGE = "Incorrect edit command! Format should be\n"
            + "edit /card <card index> /side <side> /input <input>";
    private static final String INVALID_INDEX_ERROR_MESSAGE = "Incorrect index for Card!";
    private static final String INVALID_SIDE_ERROR_MESSAGE = "What side is this? It's only either front or back.";

    private EditCardParser parser;
    private Deck deck;

    public EditCardCommand(String arguments, Deck deck) {
        super("EditCardCommand", arguments);
        this.deck = deck;
        this.parser = new EditCardParser();
    }

    @Override
    public CommandResult execute() { //edit /c <index> /s <side> /i <input>
        CommandResult result;
        try {
            if (!arguments.toLowerCase().contains("/c") || !arguments.toLowerCase().contains("/s")
                    || !arguments.toLowerCase().contains("/i")) {
                throw new FieldEmptyException(FIELD_EMPTY_ERROR_MESSAGE);
            }
            String[] rawParameters = parser.parseArguments(super.arguments);

            if (rawParameters.length < 6) {
                throw new FieldEmptyException(FIELD_EMPTY_ERROR_MESSAGE);
            }

            if (!rawParameters[0].equalsIgnoreCase("/c")
                    | !rawParameters[2].equalsIgnoreCase("/s")
                    | !rawParameters[4].equalsIgnoreCase("/i")) {
                throw new InvalidCommandFormatException(WRONG_ORDER_ERROR_MESSAGE);
            }

            String card = rawParameters[1];
            String side = rawParameters[3];
            String input = rawParameters[5];
            if (card.isEmpty() || side.isEmpty() || input.isEmpty()) {
                throw new FieldEmptyException(FIELD_EMPTY_ERROR_MESSAGE);
            }

            if (!Parser.isInteger(card)) {
                throw new CardLiException(INVALID_INDEX_ERROR_MESSAGE);
            }
            int cardIndex = Integer.parseInt(card) - 1;

            if (!(cardIndex >= 0 && cardIndex <= this.deck.cards.size())) {
                throw new CardLiException(INVALID_INDEX_ERROR_MESSAGE);
            }
            if (!(side.equalsIgnoreCase("front") | side.equalsIgnoreCase("back"))) {
                throw new CardLiException(INVALID_SIDE_ERROR_MESSAGE);
            }

            String[] parameters = {card, side, input};

            result = new CommandResult(deck.editCard(parameters));
        } catch (CardLiException e) {
            result = new CommandResult(e.getMessage());
        }
        return result;
    }
}
