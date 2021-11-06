//@@author JWweiyin
package seedu.cardli.commands.system;

import seedu.cardli.commands.Command;
import seedu.cardli.commands.CommandResult;
import seedu.cardli.exceptions.FieldEmptyException;
import seedu.cardli.flashcard.DeckManager;
import seedu.cardli.parser.system.AddDeckParser;

public class AddDeckCommand extends Command {

    public static final String FIELD_EMPTY_ERROR_MESSAGE = "What is the name of the deck?";

    private AddDeckParser parser;
    private DeckManager deckManager;

    public AddDeckCommand(String arguments, DeckManager deckManager) {
        super("AddDeckCommand", arguments);
        this.parser = new AddDeckParser();
        this.deckManager = deckManager;
    }

    @Override
    public CommandResult execute() {
        CommandResult result;
        try {
            String[] parameters = parser.parseArguments(super.arguments);
            String deckName = parameters[0];

            if (deckName.isEmpty()) {
                throw new FieldEmptyException(FIELD_EMPTY_ERROR_MESSAGE);
            }
            result = new CommandResult(deckManager.prepareToAddDeck(deckName));
        } catch (FieldEmptyException e) {
            result = new CommandResult(e.getMessage());
        }
        return result;
    }
}
