package seedu.duke.parser;

import seedu.duke.exceptions.FieldEmptyException;


public class TestParser {
    public static String parseUserResponse(String userResponse) throws FieldEmptyException {
        if (userResponse.isEmpty()) {
            throw new FieldEmptyException();
        }
        return userResponse;
    }

    public static int toInt(String input) throws NumberFormatException {
        if (input.toLowerCase().contains("all")) {
            return -1;
        }
        return Integer.parseInt(input) - 1;
    }

}
