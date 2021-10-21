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
        String[] parameters = new String[2];
        logger.log(Level.INFO, "splitting input");
        int frontIndex = arguments.indexOf("/fro");
        int backIndex = arguments.indexOf("/bac");

        parameters[0] = arguments.substring(frontIndex + 4, backIndex).trim();
        parameters[1] = arguments.substring(backIndex + 4).trim();

        return parameters;
    }
}
