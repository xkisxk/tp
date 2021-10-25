package seedu.duke.parser.deck;

import seedu.duke.parser.CommandArgumentParser;

public class EditCardParser implements CommandArgumentParser {

    public EditCardParser() {
    }

    @Override
    public String[] parseArguments(String arguments) { //edit /card <card index> /side <side> /input <input>
        String[] parameters = arguments.trim().split(" ", 6);
        return parameters;
    }
}
