package seedu.duke.commands.deck;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.exceptions.FieldEmptyException;
import seedu.duke.flashcard.Deck;
import seedu.duke.parser.deck.AddCardParser;

import java.util.Locale;

public class AddCardCommand extends Command {

    private static final String FIELD_EMPTY_ERROR_MESSAGE = "You cannot leave any field empty! "
            + "Format should be\n add /fro <words on front> /bac <words on back>";

    private AddCardParser parser;
    private Deck deck;

    public AddCardCommand(String arguments, Deck deck) {
        super("AddCardCommand", arguments);
        this.deck = deck;
        this.parser = new AddCardParser();
    }

    @Override
    public CommandResult execute() {
        CommandResult result;
        try {
            if (!arguments.toLowerCase().contains("/f") || !arguments.toLowerCase().contains("/b")) {
                throw new FieldEmptyException(FIELD_EMPTY_ERROR_MESSAGE);
            }
            String[] parameters = parser.parseArguments(super.arguments);
            String front = parameters[0];
            String back = parameters[1];
            if (front.isEmpty() || back.isEmpty()) {
                throw new FieldEmptyException(FIELD_EMPTY_ERROR_MESSAGE);
            }
            result = new CommandResult(deck.prepareToAddFlashCard(parameters));
        } catch (FieldEmptyException e) {
            result = new CommandResult(e.getMessage());
        }
        return result;
    }
}
