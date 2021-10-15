package seedu.duke.parser;

import seedu.duke.FlashCardManager;
import seedu.duke.TestManager;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Deals with the parsing of user input at the command line.
 */
public class Parser {
    private static final Logger logger = Logger.getLogger(Parser.class.getName());

    /**
     * Parses user input at the command line and invokes the necessary follow up actions.
     */
    public static void parseCommand(String line) {
        logger.log(Level.INFO, "new user input detected");
        String command = getCommand(line);
        switch (command) {
        case "add":
            FlashCardManager.prepareToAddFlashCard(line);
            logger.log(Level.INFO, "add command parsed and executed");
            break;
        case "delete":
            FlashCardManager.prepareToDeleteFlashCard(line);
            logger.log(Level.INFO, "delete command parsed and executed");
            break;
        case "view":
            FlashCardManager.viewAllFlashCards();
            logger.log(Level.INFO, "view command parsed and executed");
            break;
        case "test":
            TestManager.testAllCardsInOrder();
            logger.log(Level.INFO, "test command parsed and executed");
            break;
        case "bye":
            logger.log(Level.INFO, "bye command parsed and executed, program will terminate");
            break;
        default:
            System.out.println("\tThat's not a command.");
            logger.log(Level.INFO, "command was unrecognised and could not be parsed");
        }
    }

    public static String getCommand(String input) {
        return input.trim().split(" ")[0];
    }

    /**
     * Returns all contents of the input after the command word.
     *
     * @param input user's input
     * @return description of card
     * @throws ArrayIndexOutOfBoundsException if description is empty
     */
    public static String getDescription(String input) throws ArrayIndexOutOfBoundsException {
        return input.split(" ", 2)[1];
    }

    
}
