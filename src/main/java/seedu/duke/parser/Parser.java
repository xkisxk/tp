package seedu.duke.parser;


import seedu.duke.testing.TestHistory;
import seedu.duke.testing.TestManager;
import seedu.duke.exceptions.CardLiException;
import seedu.duke.exceptions.DeckNotExistException;
import seedu.duke.exceptions.FieldEmptyException;
import seedu.duke.exceptions.InvalidCommandFormatException;
import seedu.duke.ui.CardLiUi;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Deals with the parsing of user input at the command line.
 */
public class Parser {

    /**
     * Returns the command type of the user's input.
     * @param input
     */
    public static String getCommandType(String input) {
        return input.trim().split(" ")[0].toLowerCase();
    }

    /**
     * Returns the String containing the arguments to the command.
     */
    public static String getCommandArguments(String commandType, String input) { // TODO: throws FieldEmptyException
        assert input.length() > 0 : "input string should not be empty, at least have command word";
        return input.substring(commandType.length()).trim();
    }

    /**
     * Checks if the given input is an integer or not.
     *
     * @param input input given by user
     * @return true if input is an integer, false otherwise
     */
    public static boolean isInteger(String input) {
        for (int i = 0; i < input.length(); i += 1) {
            if (!Character.isDigit(input.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
