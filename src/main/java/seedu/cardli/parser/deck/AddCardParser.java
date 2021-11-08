package seedu.cardli.parser.deck;

import seedu.cardli.parser.CommandArgumentParser;
import seedu.cardli.parser.Parser;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AddCardParser implements CommandArgumentParser {

    private Logger logger;

    public AddCardParser() {
        this.logger = Logger.getLogger(Parser.class.getName());
        logger.setLevel(Level.WARNING);
    }

    @Override
    public String[] parseArguments(String arguments) { //add /f <front> /b <back>
        String[] parameters = arguments.trim().split("/f", 2);
        parameters = parameters[1].split("/b", 2);
        return parameters;
    }
}
