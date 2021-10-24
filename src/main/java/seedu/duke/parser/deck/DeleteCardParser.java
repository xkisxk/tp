package seedu.duke.parser.deck;

import seedu.duke.parser.CommandArgumentParser;
import seedu.duke.parser.Parser;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteCardParser implements CommandArgumentParser {

    private Logger logger;

    public DeleteCardParser() {
        this.logger = Logger.getLogger(Parser.class.getName()); // TODO: idk? change name?
        logger.setLevel(Level.WARNING);
    }

    @Override
    public String[] parseArguments(String arguments) {
        String[] parameters = new String[1];
        String deckName = arguments;
        parameters[0] = deckName;
        return parameters;
    }
}
