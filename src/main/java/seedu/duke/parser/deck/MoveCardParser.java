package seedu.duke.parser.system;

import seedu.duke.parser.CommandArgumentParser;

public class MoveCardParser implements CommandArgumentParser {

    public MoveCardParser() {
    }

    @Override
    public String[] parseArguments(String arguments) {
        String[] parameters = arguments.trim().split("/d|/c", 3);
        return parameters;
    }
}