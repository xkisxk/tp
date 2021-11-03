package seedu.cardli.parser.system;

import seedu.cardli.parser.CommandArgumentParser;

public class AddDeckParser implements CommandArgumentParser {

    public AddDeckParser() {
    }

    @Override
    public String[] parseArguments(String arguments) {
        String[] parameters = new String[1];
        String deckName = arguments;
        parameters[0] = deckName;
        return parameters;
    }
}
