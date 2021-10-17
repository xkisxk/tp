package seedu.duke;

import java.util.Scanner;
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
    static void programLogic() {
        logger.setLevel(Level.WARNING);
        Scanner in = new Scanner(System.in);
        boolean exitProgram = false;

        while (!exitProgram) {
            String line = in.nextLine();
            String command = line.trim().split(" ")[0];

            logger.log(Level.INFO, "new user input detected");

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
                exitProgram = true;
                logger.log(Level.INFO, "bye command parsed and executed, program will terminate");
                break;
            default:
                System.out.println("\tThat's not a command.");
                logger.log(Level.INFO, "command was unrecognised and could not be parsed");
            }
        }
    }
}
