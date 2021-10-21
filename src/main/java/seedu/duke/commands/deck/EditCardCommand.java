package seedu.duke.commands.deck;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.exceptions.CardLiException;
import seedu.duke.exceptions.FieldEmptyException;
import seedu.duke.flashcard.Deck;
import seedu.duke.parser.deck.EditCardParser;

public class EditCardCommand extends Command {

    private static final String FIELD_EMPTY_ERROR_MESSAGE = "You cannot leave any field empty! "
            + "Format should be\n edit /card <card index> /side <side> /input <input>";

    private EditCardParser parser;
    private Deck deck;

    public EditCardCommand(Deck deck) {
        super("EditCardCommand");
        this.deck = deck;
        this.parser = new EditCardParser();
    }

    @Override
    public CommandResult execute() { //edit /card <card index> /side <side> /input <input>
        CommandResult result;
        try {
            if (!arguments.toLowerCase().contains("/card") || !arguments.toLowerCase().contains("/side")
                    || !arguments.toLowerCase().contains("/input")) {
                throw new FieldEmptyException(FIELD_EMPTY_ERROR_MESSAGE);
            }
            String[] parameters = parser.parseArguments(super.arguments);
            String card = parameters[0];
            String side = parameters[1];
            String input = parameters[1];
            if (card.isEmpty() || side.isEmpty() || input.isEmpty()) {
                throw new FieldEmptyException(FIELD_EMPTY_ERROR_MESSAGE);
            }

            int cardIndex = Integer.parseInt(card) - 1; // TODO: possibly accept either card name or index

            if (!(cardIndex >= 0 && cardIndex <= this.deck.cards.size())) {
                throw new CardLiException("Incorrect index for Card!");
            }
            if (!(side.equalsIgnoreCase("front") | side.equalsIgnoreCase("back"))) {
                throw new CardLiException("What side is this? Its only either front or back");
            }

            result = new CommandResult(deck.editCard(parameters));
        } catch (CardLiException e) {
            // TODO: FieldEmptyException is subclass of CardLiException, so can't put both in the conditional above
            // TODO: check if want to separate them?
            result = new CommandResult(e.getMessage());
        }
        return result;
    }
}