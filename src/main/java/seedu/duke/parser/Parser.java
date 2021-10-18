package seedu.duke.parser;

import seedu.duke.flashcard.DeckManager;
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

    private static final Logger logger = Logger.getLogger(Parser.class.getName());

    /**
     * Parses user input at the command line and invokes the necessary follow up actions.
     */
    public static void parseCommand(String input) throws CardLiException {
        logger.setLevel(Level.WARNING);
        logger.log(Level.INFO, "new user input detected");
        String command = getCommand(input);

        logger.log(Level.INFO, "new user input detected");

        switch (command) {
        case "add":
            String addInput = removeCommandWord(input, command.length());
            DeckManager.prepareToAddCardToDeck(addInput);
            logger.log(Level.INFO, "add command parsed and executed");
            break;
        case "adddeck":
            String addDeckInput = removeCommandWord(input, command.length());
            DeckManager.prepareToAddDeck(addDeckInput);
            break;
        case "viewdecks":
            DeckManager.viewDecks();
            break;
        case "delete":
            String deleteInput = removeCommandWord(input, command.length());
            DeckManager.prepareToDeleteCardFromDeck(deleteInput);
            logger.log(Level.INFO, "delete command parsed and executed");
            break;
        case "viewdeck":
            String viewInput = removeCommandWord(input, command.length());
            DeckManager.viewOneDeck(viewInput);
            logger.log(Level.INFO, "view command parsed and executed");
            break;
        case "test":
            TestManager.startTest();
            logger.log(Level.INFO, "test command parsed and executed");
            break;
        case "viewtest":
            String viewTestInput = removeCommandWord(input, command.length());
            TestHistory.prepareToViewTest(viewTestInput);
            logger.log(Level.INFO, "viewtest command parsed and executed");
            break;
        case "viewtests":
            TestHistory.viewTests();
            logger.log(Level.INFO, "viewtests command parsed and executed");
            break;
        case "viewfc":
            TestHistory.viewOverallFlashcardStats();
            logger.log(Level.INFO, "viewfc command parsed and executed");
            break;
        case "editcard": //editcard /deck <cat index> /card <card index> /side <side> /input <input>
            String editCardInput = removeCommandWord(input, command.length());
            String[] parsedEditCardArgs = parseEditCardCommand(editCardInput);
            DeckManager.editCard(parsedEditCardArgs);
            logger.log(Level.INFO, "editcard command parsed and executed");
            break;
        case "editdeck": //editdeck /deck <cat index> /input <input>
            String editCatInput = removeCommandWord(input, command.length());
            String[] parsedEditCatArgs = parseEditDeckCommand(editCatInput);
            DeckManager.editCat(parsedEditCatArgs);
            logger.log(Level.INFO, "editdeck command parsed and executed");
            break;
        case "help":
            CardLiUi.helpMessage();
            logger.log(Level.INFO, "editdeck command parsed and executed");
            break;
        case "bye":
            DeckManager.saveToFile();
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

    /**
     * Returns the parsed contents after the command word.
     *
     * @param input user's input
     * @throw FieldEmptyException, InvalidCommandFormatException, DeckNotExistException, CardLiException
     * @return a String array containing the most important information (Deck index, Card index, side to change
     *      and what to change)
     */
    public static String[] parseEditCardCommand(String input) throws CardLiException {
        logger.setLevel(Level.WARNING);
        if (input.isEmpty()) {
            throw new FieldEmptyException("You cannot leave any field empty! Format should be\n"
                    + "editcard /deck <deck index> /card <card index> /side <side> /input <input>");
        }
        logger.log(Level.INFO, "splitting input");
        String trimmedInput = input.trim();
        String[] args = trimmedInput.split(" ",8);
        logger.log(Level.INFO, "checking if there are enough arguments");
        if (args.length != 8) {
            throw new FieldEmptyException("You cannot leave the entire field empty! Format should be\n"
                    + "editcard /deck <deck index> /card <card index> /side <side> /input <input>");
        }
        logger.log(Level.INFO, "checking if command keywords used are correct");
        if (!args[0].equalsIgnoreCase("/deck") | !args[2].equalsIgnoreCase("/card")
                | !args[4].equalsIgnoreCase("/side") | ! args[6].equalsIgnoreCase("/input")) {
            throw new InvalidCommandFormatException("Incorrect editcard command! Format should be\n"
                    + "editcard /deck <deck index> /card <card index> /side <side> /input <input>");
        }
        int catIndex = Integer.parseInt(args[1]);
        int cardIndex = Integer.parseInt(args[3]);
        logger.log(Level.INFO, "checking if deck index and card index are not out of bounds");
        if (!(catIndex > 0 && catIndex <= DeckManager.getDecksSize())) {
            throw new DeckNotExistException("Incorrect index for Deck!");
        }
        if (!(cardIndex > 0 && cardIndex <= DeckManager.getDeck(catIndex - 1).getDeckSize())) {
            throw new CardLiException("Incorrect index for Card!");
        }
        logger.log(Level.INFO, "checking if user inputted a correct side");
        if (!(args[5].equalsIgnoreCase("front") | args[5].equalsIgnoreCase("back"))) {
            throw new CardLiException("What side is this? Its only either front or back");
        }
        String[] editArgs = { args[1], args[3], args[5], args[7]};
        return editArgs;
    }

    /**
     * Returns the parsed contents after the command word.
     *
     * @param input user's input
     * @throw FieldEmptyException, InvalidCommandFormatException, DeckNotExistException
     * @return a String array containing the most important information (Deck index, what to change)
     */
    public static String[] parseEditDeckCommand(String input) throws CardLiException {
        logger.setLevel(Level.WARNING);
        if (input.isEmpty()) {
            throw new FieldEmptyException("You cannot leave the entire field empty! Format should be\n"
                    + "editdeck /deck <deck index> /input <input>");
        }
        String trimmedInput = input.trim();
        String[] args = trimmedInput.split(" ",4);
        logger.log(Level.INFO, "checking if there are enough arguments");
        if (args.length != 4) {
            throw new FieldEmptyException("You cannot leave any field empty! Format should be\n"
                    + "editdeck /deck <deck index> /input <input>");
        }
        logger.log(Level.INFO, "checking if command keywords used are correct");
        if (!args[0].equalsIgnoreCase("/deck") | !args[2].equalsIgnoreCase("/input")) {
            throw new InvalidCommandFormatException("Incorrect editdeck command! Format should be\n"
                    + "editdeck /deck <deck index> /input <input>");
        }
        int catIndex = Integer.parseInt(args[1]);
        logger.log(Level.INFO, "checking if deck index and card index are not out of bounds");
        if (!(catIndex > 0 && catIndex <= DeckManager.getDecksSize())) {
            throw new DeckNotExistException("Incorrect index for Deck!");
        }
        String[] editArgs = {args[1], args[3]};
        return editArgs;
    }
}
