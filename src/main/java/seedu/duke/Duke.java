package seedu.duke;

import seedu.duke.exceptions.CardLiException;
import seedu.duke.flashcard.DeckManager;
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
        DeckManager.readFromFile();
        boolean exitProgram = false;
        boolean inDeck;

        while (!exitProgram) {
            try {
                String input = ui.getUserMessage();
                Parser.parseCommand(input);
                inDeck = checkEnter(input);
                while (inDeck) {
                    input = ui.getUserMessage();
                    Parser.parseCommandWithinDeck(input);
                    inDeck = checkExit(input);
                }

                exitProgram = checkBye(input);
            } catch (CardLiException e) {
                ui.showMessage(e.getMessage());
            }
        }
        ui.printByeMessage();
    }

    private static boolean checkBye(String input) {
        if (Parser.getCommand(input).equals("bye")) {
            return true;
        }
        return false;
    }

    private static boolean checkExit(String input) {
        if (Parser.getCommand(input).equals("exit")) {
            return false;
        }
        return true;
    }

    private static boolean checkEnter(String input) {
        if (Parser.getCommand(input).equals("enter")) {
            if (Parser.isInteger(input.substring(5).trim())) {
                return true;
            }
        }
        return false;
    }
}
