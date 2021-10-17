package seedu.duke.parser;

import seedu.duke.exceptions.FieldEmptyException;

import java.util.logging.Logger;

public class TestParser {
    private static final Logger logger = Logger.getLogger(Parser.class.getName());

    public static String parseUserResponse(String userResponse) throws FieldEmptyException {
        if (userResponse.isEmpty()) {
            throw new FieldEmptyException();
        }
        return userResponse;
    }

}
