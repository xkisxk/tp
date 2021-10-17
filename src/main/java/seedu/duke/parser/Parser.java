package seedu.duke.parser;

import seedu.duke.DeckList;

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
    public static void parseCommand(String input) {
        logger.log(Level.INFO, "new user input detected");
        logger.setLevel(Level.WARNING);
        String command = getCommand(input);

        logger.log(Level.INFO, "new user input detected");

        switch (command) {
        case "add":
            String addInput = removeCommandWord(input, command.length());
            DeckList.prepareToAddCardToDeck(addInput);
            logger.log(Level.INFO, "add command parsed and executed");
            break;
        case "adddeck":
            String addDeckInput = removeCommandWord(input, command.length());
            DeckList.prepareToAddDeck(addDeckInput);
            break;
        case "viewdeck":
            DeckList.viewDecks();
            break;
        case "delete":
            String deleteInput = removeCommandWord(input, command.length());
            DeckList.prepareToDeleteCardFromDeck(deleteInput);
            logger.log(Level.INFO, "delete command parsed and executed");
            break;
        case "view":
            String viewInput = removeCommandWord(input, command.length());
            DeckList.viewOneDeck(viewInput);
            logger.log(Level.INFO, "view command parsed and executed");
            break;
        case "test":
            String testInput = removeCommandWord(input, command.length());
            DeckList.testDeck(testInput);
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

    public static String getCommand(String line) {
        return line.trim().split(" ")[0].toLowerCase();
    }

    /**
     * Returns all contents of the input after the command word.
     *
     * @param input user's input
     * @return description of card
     */
    public static String removeCommandWord(String input, int index) {
        assert input.length() > 0 : "input string should not be empty, at least have command word";
        return input.substring(index).trim();
    }
}
