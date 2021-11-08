package seedu.cardli.parser;

import seedu.cardli.exceptions.FieldEmptyException;

/**
 * TestParser checks to make sure the user's input is valid.
 */
public class TestParser {

    /**
     * Make sure the user's input is valid.
     *
     * @param userResponse         raw user's input
     * @return                     trimmed user's response
     * @throws FieldEmptyException if user's response is empty
     */
    public static String parseUserResponse(String userResponse) throws FieldEmptyException {
        if (userResponse.isEmpty()) {
            throw new FieldEmptyException();
        }
        return userResponse.trim();
    }

    /**
     * Make sure that the user's input is a number or "all".
     *
     * @param input                  user's input
     * @return                       user's input as integer
     * @throws NumberFormatException if the input is not a number or "all"
     */
    public static int toInt(String input) throws NumberFormatException {
        if (input.equalsIgnoreCase("all")) {
            return -1;
        }
        return Integer.parseInt(input) - 1;
    }

}
