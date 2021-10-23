package seedu.duke.parser.deck;

import seedu.duke.parser.CommandArgumentParser;

public class TestParser implements CommandArgumentParser {

    public TestParser() {}

    @Override
    public String[] parseArguments(String arguments) {
        return new String[0];
    }
}
