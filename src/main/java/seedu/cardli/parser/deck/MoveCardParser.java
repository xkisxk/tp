package seedu.cardli.parser.deck;

import seedu.cardli.parser.CommandArgumentParser;

public class MoveCardParser implements CommandArgumentParser {

    public MoveCardParser() {
    }

    @Override
    public String[] parseArguments(String arguments) {
        String[] parameters = arguments.trim().split(" ", 4);
        return parameters;
    }
}