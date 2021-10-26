package seedu.duke.parser.deck;

import seedu.duke.parser.CommandArgumentParser;

public class EditCardParser implements CommandArgumentParser {

    public EditCardParser() {
    }

    @Override
    public String[] parseArguments(String arguments) { //edit /c <index> /s <side> /i <input>
        String[] parameters = arguments.trim().split("/c|/s|/i", 4);
        return parameters;
    }
}
