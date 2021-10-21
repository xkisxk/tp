package seedu.duke.parser.deck;

import seedu.duke.parser.CommandArgumentParser;
import seedu.duke.parser.Parser;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AddCardParser implements CommandArgumentParser {

    private Logger logger;

    public AddCardParser() {
        this.logger = Logger.getLogger(Parser.class.getName()); // TODO: idk? change name?
        logger.setLevel(Level.WARNING);
    }

    @Override
    public String[] parseArguments(String arguments) { //add /f <front> /b <back>
        String[] parameters = arguments.trim().split(" ", 4);
        return parameters;
    }
}
