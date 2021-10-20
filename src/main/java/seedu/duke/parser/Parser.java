package seedu.duke.parser;


import seedu.duke.flashcard.DeckManager;
import seedu.duke.flashcard.Deck;

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

    public static int getCurrDeck() {
        return currDeck;
    }

    private static int currDeck;

    /**
     * Parses user input at the command line and invokes the necessary follow up actions.
     */
    public static void parseCommand(String input) throws CardLiException {
        logger.setLevel(Level.WARNING);
        logger.log(Level.INFO, "new user input detected");
        String command = getCommand(input);
        logger.log(Level.INFO, "new user input detected");

        switch (command) {
        case "enter":
            String enterInput = removeCommandWord(input, command.length());
            setCurrentDeck(enterInput);
            System.out.println("You are now in deck " + enterInput + ". Type \"help\"for more commands.");
            break;
        case "adddeck":
            String addDeckInput = removeCommandWord(input, command.length());
            DeckManager.prepareToAddDeck(addDeckInput);
            break;
        case "viewdecks":
            DeckManager.viewDecks();
            break;
        case "viewdeck":
            String viewInput = removeCommandWord(input, command.length());
            DeckManager.viewOneDeck(viewInput);
            logger.log(Level.INFO, "view command parsed and executed");
            break;
        case "test": //TODO: restructure into deck level
            //String testInput = removeCommandWord(input, command.length());
            TestManager.startTest();
            logger.log(Level.INFO, "test command parsed and executed");
            break;
        case "viewtest": //TODO: percentage wrong, change name
            String viewTestInput = removeCommandWord(input, command.length());
            TestHistory.prepareToViewTest(viewTestInput);
            logger.log(Level.INFO, "viewtest command parsed and executed");
            break;
        case "viewtests": //TODO: save the results
            TestHistory.viewTests();
            logger.log(Level.INFO, "viewtests command parsed and executed");
            break;
        case "viewfc": //TODO: make it part of view flashcards in deck instead
            TestHistory.viewOverallFlashcardStats();
            logger.log(Level.INFO, "viewfc command parsed and executed");
            break;

        case "review": //TODO: restructure into deck level
            TestManager.startReview();
            logger.log(Level.INFO, "review command parsed and executed");
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
            DeckManager.saveToFile(); //TODO: maybe implement other autosaves
            logger.log(Level.INFO, "current list of decks and flashcards saved to text file");
            logger.log(Level.INFO, "bye command parsed and executed, program will terminate");
            break;
        default:
            System.out.println("\tThat's not a command.");
            logger.log(Level.INFO, "command was unrecognised and could not be parsed");
        }
    }

    public static void setCurrentDeck(String input) {
        try {
            int inputString = Integer.parseInt(input) - 1;
            if (inputString >= 0 && inputString < DeckManager.getDecks().size()) {
                currDeck = inputString;
            } else {
                throw new DeckNotExistException();
            }
        } catch (NumberFormatException e) {
            System.out.println("The deck index has to be an integer");
        } catch (DeckNotExistException e) {
            System.out.println("That deck doesn't exist.");
        }
    }

    //TODO: make the rest of the functions work
    public static void parseCommandWithinDeck(String input) throws CardLiException {
        String command = getCommand(input);
        switch (command) {
        case "add": //add /fro <front> /bac <back>
            String addInput = removeCommandWord(input, command.length());
            String[] frontAndBack = parseAddCardCommand(addInput);
            DeckManager.getDecks().get(currDeck).prepareToAddFlashCard(frontAndBack);
            logger.log(Level.INFO, "add command parsed and executed");
            break;
        case "delete": //delete <index/front>
            String deleteInput = removeCommandWord(input, command.length());
            DeckManager.getDecks().get(currDeck).prepareToDeleteFlashCard(deleteInput);
            logger.log(Level.INFO, "delete command parsed and executed");
            break;
        case "edit": //edit /card <card index> /side <side> /input <input>
            String editCardInput = removeCommandWord(input, command.length());
            String[] parsedEditCardArgs = parseEditCardCommand(editCardInput);
            DeckManager.getDecks().get(currDeck).editCard(parsedEditCardArgs);
            logger.log(Level.INFO, "editcard command parsed and executed");
            break;
        case "view": //view
            DeckManager.getDecks().get(currDeck).viewAllFlashCards();
            break;
        case "help": //help
            CardLiUi.helpInDeck();
            break;
        case "exit":
            System.out.println("Exiting to main menu.");
            break;
        default:
            System.out.println("\tThat's not a command.");
            logger.log(Level.INFO, "command was unrecognised and could not be parsed");
            break;
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
     * Returns the parsed contents after the command word for the edit function.
     *
     * @param input user's input
     * @return a String array containing the most important information (Card index, side to change, what to change)
     * @throw FieldEmptyException, InvalidCommandFormatException, DeckNotExistException, CardLiException
     */
    public static String[] parseEditCardCommand(String input) throws CardLiException {
        logger.setLevel(Level.WARNING);
        if (input.isEmpty()) {
            throw new FieldEmptyException("You cannot leave any field empty! Format should be\n"
                    + "editcard /card <card index> /side <side> /input <input>");
        }
        logger.log(Level.INFO, "splitting input");
        String trimmedInput = input.trim();
        String[] args = trimmedInput.split(" ", 6);
        logger.log(Level.INFO, "checking if there are enough arguments");
        if (args.length != 6) {
            throw new FieldEmptyException("You cannot leave the entire field empty! Format should be\n"
                    + "editcard /card <card index> /side <side> /input <input>");
        }
        logger.log(Level.INFO, "checking if command keywords used are correct");
        if (!args[0].equalsIgnoreCase("/card")
                | !args[2].equalsIgnoreCase("/side") | !args[4].equalsIgnoreCase("/input")) {
            throw new InvalidCommandFormatException("Incorrect editcard command! Format should be\n"
                    + "editcard /card <card index> /side <side> /input <input>");
        }
        int cardIndex = Integer.parseInt(args[1]) - 1;
        logger.log(Level.INFO, "checking if deck index and card index are not out of bounds");
        //TODO: make sure this works
        if (!(cardIndex >= 0 && cardIndex <= DeckManager.getDeck(currDeck).cards.size())) {

            throw new CardLiException("Incorrect index for Card!");
        }
        logger.log(Level.INFO, "checking if user inputted a correct side");
        if (!(args[3].equalsIgnoreCase("front") | args[3].equalsIgnoreCase("back"))) {
            throw new CardLiException("What side is this? Its only either front or back");
        }
        String[] editArgs = {args[1], args[3], args[5]};
        return editArgs;
    }

    public static String[] parseAddCardCommand(String input) throws CardLiException {
        logger.setLevel(Level.WARNING);
        if (input.isEmpty()) {
            throw new FieldEmptyException("You cannot leave any field empty! Format should be\n"
                    + "add /fro <words on front> /bac <words on back>");
        }
        logger.log(Level.INFO, "splitting input");

        int froIndex = input.indexOf("/fro");
        int bacIndex = input.indexOf("/bac");
        if (bacIndex - froIndex < 5) {
            throw new FieldEmptyException("You cannot leave the entire field empty! Format should be\n"
                    + "add /fro <words on front> /bac <words on back>");
        }
        String[] args = new String[2];
        args[0] = input.substring(froIndex + 4, bacIndex).trim();
        args[1] = input.substring(bacIndex + 4).trim();
        logger.log(Level.INFO, "checking if there are enough arguments");
        if (args[0].isEmpty() || args[1].isEmpty()) {
            throw new FieldEmptyException("You cannot leave the entire field empty! Format should be\n"
                    + "add /fro <words on front> /bac <words on back>");
        }
        return args;
    }

    /**
     * Returns the parsed contents after the command word.
     *
     * @param input user's input
     * @return a String array containing the most important information (Deck index, what to change)
     * @throw FieldEmptyException, InvalidCommandFormatException, DeckNotExistException
     */
    public static String[] parseEditDeckCommand(String input) throws CardLiException {
        logger.setLevel(Level.WARNING);
        if (input.isEmpty()) {
            throw new FieldEmptyException("You cannot leave the entire field empty! Format should be\n"
                    + "editdeck /deck <deck index> /input <input>");
        }
        String trimmedInput = input.trim();
        String[] args = trimmedInput.split(" ", 4);
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

    /**
     * Checks if the given input is an integer or not.
     *
     * @param input input given by user
     * @return true if input is an integer, false otherwise
     */
    public static boolean isInteger(String input) {
        for (int i = 0; i < input.length(); i += 1) {
            if (!Character.isDigit(input.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
