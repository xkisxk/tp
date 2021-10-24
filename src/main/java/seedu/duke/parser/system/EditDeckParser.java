package seedu.duke.parser.system;

import seedu.duke.exceptions.DeckNotExistException;
import seedu.duke.exceptions.FieldEmptyException;
import seedu.duke.exceptions.InvalidCommandFormatException;
import seedu.duke.parser.CommandArgumentParser;

import java.util.logging.Level;

public class EditDeckParser implements CommandArgumentParser {

    public EditDeckParser() {}

    @Override
    public String[] parseArguments(String arguments) {
        String[] parameters = arguments.trim().split(" ", 4);
        return parameters;
    }
}