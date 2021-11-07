package seedu.cardli.parser.deck;

import seedu.cardli.parser.CommandArgumentParser;

public class EditCardParser implements CommandArgumentParser {

    public EditCardParser() {
    }

    @Override
    public String[] parseArguments(String arguments) {
        String[] parameters = arguments.trim().split(" ", 6);
        return parameters;
    }
}
