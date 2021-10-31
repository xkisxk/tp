package seedu.cardli.parser;

import seedu.cardli.exceptions.FieldEmptyException;


public class TestParser {
    public static String parseUserResponse(String userResponse) throws FieldEmptyException {
        if (userResponse.isEmpty()) {
            throw new FieldEmptyException();
        }
        return userResponse.trim();
    }

    public static int toInt(String input) throws NumberFormatException {
        if (input.equalsIgnoreCase("all")) {
            return -1;
        }
        return Integer.parseInt(input) - 1;
    }

}
