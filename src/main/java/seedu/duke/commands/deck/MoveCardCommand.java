package seedu.duke.commands.deck;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.exceptions.CardLiException;
import seedu.duke.exceptions.FieldEmptyException;
import seedu.duke.exceptions.InvalidCommandFormatException;
import seedu.duke.flashcard.Deck;
import seedu.duke.flashcard.DeckManager;
import seedu.duke.flashcard.FlashCard;
import seedu.duke.parser.Parser;
import seedu.duke.parser.system.MoveCardParser;

public class MoveCardCommand extends Command {

    private static final String FIELD_EMPTY_ERROR_MESSAGE = "You cannot leave any field empty! "
            + "Format should be\n move /c <card index/front phrase of card> /d <deck index/name of deck>";
    private static final String WRONG_ORDER_ERROR_MESSAGE = "Incorrect move command! Format should be\n"
            + "move /c <card index/front phrase of card> /d <deck index/name of deck>";
    private static final String INVALID_CARD_INDEX_ERROR_MESSAGE = "Incorrect index for Card!";
    private static final String NO_SUCH_CARD_ERROR_MESSAGE = "No such card of that description exist!";
    private static final String SAME_DESTINATION_ERROR_MESSAGE = "Your card is already in the deck specified!";
    private static final String INVALID_DECK_INDEX_ERROR_MESSAGE = "Incorrect index for deck!";
    private static final String NO_SUCH_DECK_ERROR_MESSAGE = "No deck goes by that name!";

    private MoveCardParser parser;
    private Deck deck;
    private DeckManager deckManager;

    public MoveCardCommand(String arguments, Deck deck, DeckManager deckManager) {
        super("EditCardCommand", arguments);
        this.deck = deck;
        this.deckManager = deckManager;
        this.parser = new MoveCardParser();
    }

    @Override
    public CommandResult execute() { //move /c <index> /d <index
        CommandResult result;
        try {
            if (!arguments.toLowerCase().contains("/c") || !arguments.toLowerCase().contains("/d")) {
                throw new FieldEmptyException(FIELD_EMPTY_ERROR_MESSAGE);
            }
            if (!(arguments.indexOf("/c") < arguments.indexOf("/d"))) {
                throw new InvalidCommandFormatException(WRONG_ORDER_ERROR_MESSAGE);
            }

            // "", card, deck
            String[] rawParameters = parser.parseArguments(super.arguments);

            if (rawParameters.length < 3) {
                throw new FieldEmptyException(FIELD_EMPTY_ERROR_MESSAGE);
            }

            String cardInput = rawParameters[1].trim();
            String deckInput = rawParameters[2].trim();

            if (cardInput.isEmpty() || deckInput.isEmpty()) {
                throw new FieldEmptyException(FIELD_EMPTY_ERROR_MESSAGE);
            }
            //checks if card to be moved is legit
            int cardIndex = 0;
            if (Parser.isInteger(cardInput)) {
                //card is an index
                cardIndex = Integer.parseInt(cardInput) - 1;
                if (!(cardIndex >= 0 && cardIndex <= this.deck.getCards().size())) {
                    throw new CardLiException(INVALID_CARD_INDEX_ERROR_MESSAGE);
                }
            } else {
                //card is a string input corresponding to front of the flashcard
                boolean cardFound = false;
                for (FlashCard c: deck.getCards()) {
                    if (c.getFront().equalsIgnoreCase(cardInput)) {
                        //card now is a string type containing index of card to be edited
                        //assume no duplicate cards
                        cardInput = String.valueOf(deck.getCardIndex(c) + 1);
                        cardFound = true;
                    }
                }
                if (!cardFound) {
                    throw new CardLiException(NO_SUCH_CARD_ERROR_MESSAGE);
                }
            }
            //checks if deck to move card to is legit
            int destinationDeckIndex = 0;
            if (Parser.isInteger(deckInput)) {
                //deckInput is an index
                destinationDeckIndex = Integer.parseInt(deckInput) - 1;
                if (!(destinationDeckIndex >= 0 && destinationDeckIndex <= this.deckManager.getDecksSize())) {
                    throw new CardLiException(INVALID_DECK_INDEX_ERROR_MESSAGE);
                }
            } else {
                //deckInput is a string input corresponding to name of the deck
                boolean deckFound = false;
                for (Deck d: deckManager.getDecks()) {
                    if (d.getName().equalsIgnoreCase(deckInput)) {
                        //deck now is a string type containing index of deck for card to be sent to
                        //assume no duplicate decks
                        deckInput = String.valueOf(deckManager.getDeckIndex(d) + 1);
                        deckFound = true;
                    }
                }
                if (!deckFound) {
                    throw new CardLiException(NO_SUCH_DECK_ERROR_MESSAGE);
                }
            }

            if ((Integer.parseInt(deckInput) - 1) == deckManager.getDeckIndex(deck)) {
               throw new CardLiException(SAME_DESTINATION_ERROR_MESSAGE);
            }


            String sourceDeckIndex = String.valueOf(deckManager.getDeckIndex(deck));

            String[] parameters = {sourceDeckIndex, cardInput, deckInput};

            result = new CommandResult(deckManager.moveCard(parameters));
        } catch (CardLiException e) {
            result = new CommandResult(e.getMessage());
        }
        return result;
    }
}

