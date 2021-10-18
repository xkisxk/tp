package seedu.duke;

import seedu.duke.exceptions.CardLiException;
import seedu.duke.parser.Parser;
import seedu.duke.ui.CardLiUi;

/**
 * Represents CardLI application.
 */
public class Duke {
    private static final CardLiUi ui = new CardLiUi();

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        ui.printGreetingMessage();
        boolean exitProgram = false;

        while (!exitProgram) {
            try {
                String input = ui.getUserMessage();
                Parser.parseCommand(input);
                if (Parser.getCommand(input).equals("bye")) {
                    exitProgram = true;
                }
            } catch (CardLiException e) {
                ui.showMessage(e.getMessage());
            }
        }
        ui.printByeMessage();
    }
}
