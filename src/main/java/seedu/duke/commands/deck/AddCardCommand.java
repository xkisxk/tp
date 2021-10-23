package seedu.duke.commands.deck;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.exceptions.FieldEmptyException;
import seedu.duke.exceptions.InvalidCommandFormatException;
import seedu.duke.flashcard.Deck;
import seedu.duke.parser.deck.AddCardParser;

import java.util.Locale;

public class AddCardCommand extends Command {

    private static final String FIELD_EMPTY_ERROR_MESSAGE = "You cannot leave any field empty! "
            + "Format should be\n add /f <words on front> /b <words on back>";
    private static final String WRONG_ORDER_ERROR_MESSAGE = "Incorrect add command! Format should be\n"
            + "add /f <front> /b <back>";

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
            String[] rawParameters = parser.parseArguments(super.arguments);

            if (rawParameters.length < 4) {
                throw new FieldEmptyException(FIELD_EMPTY_ERROR_MESSAGE);
            }

            if (!rawParameters[0].equalsIgnoreCase("/f") || !rawParameters[2].equalsIgnoreCase("/b")) {
                throw new InvalidCommandFormatException(WRONG_ORDER_ERROR_MESSAGE);
            }
            String front = rawParameters[1];
            String back = rawParameters[3];
            if (front.isEmpty() || back.isEmpty()) {
                throw new FieldEmptyException(FIELD_EMPTY_ERROR_MESSAGE);
            }

            String[] parameters = {front, back};
            result = new CommandResult(deck.prepareToAddFlashCard(parameters));
        } catch (FieldEmptyException | InvalidCommandFormatException e) {
            result = new CommandResult(e.getMessage());
        }
        return result;
    }
}
