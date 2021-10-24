package seedu.duke.parser.deck;

import seedu.duke.exceptions.CardLiException;
import seedu.duke.exceptions.FieldEmptyException;
import seedu.duke.exceptions.InvalidCommandFormatException;
import seedu.duke.parser.CommandArgumentParser;

import java.util.logging.Level;

public class EditCardParser implements CommandArgumentParser {

    public EditCardParser() {}

    @Override
    public String[] parseArguments(String arguments) { //edit /card <card index> /side <side> /input <input>
        String[] parameters = arguments.trim().split(" ", 6);
        return parameters;
    }
}
