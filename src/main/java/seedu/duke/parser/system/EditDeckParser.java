package seedu.duke.parser.system;

import seedu.duke.parser.CommandArgumentParser;

public class EditDeckParser implements CommandArgumentParser {

    public EditDeckParser() {
    }

    @Override
    public String[] parseArguments(String arguments) {
        String[] parameters = arguments.trim().split("/d|/n", 3);
        return parameters;
    }
}
