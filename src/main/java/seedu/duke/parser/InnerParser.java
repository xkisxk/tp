package seedu.duke.parser;

import seedu.duke.commands.Command;
import seedu.duke.commands.InvalidCommand;
import seedu.duke.commands.deck.AddCardCommand;
import seedu.duke.commands.system.ExitProgrammeCommand;
import seedu.duke.commands.system.HelpCommand;
import seedu.duke.exceptions.CardLiException;
import seedu.duke.flashcard.Deck;
import seedu.duke.ui.CardLiUi;

import java.util.logging.Level;
import java.util.logging.Logger;

public class InnerParser {

    private static final Logger logger = Logger.getLogger(InnerParser.class.getName());

    private Deck currDeck;

    public InnerParser(Deck currDeck) {
        this.currDeck = currDeck;
    }

    public InnerParser() {
        this.currDeck = null;
    }

    public void parseCommandWithinDeck(String input) throws CardLiException {
        logger.setLevel(Level.WARNING);
        String commandType = getCommandType(input);
        logger.log(Level.INFO, "new user input detected");

        Command command;
        String arguments;

        switch (command) {
            case "add": //add /f <front> /b <back>
                String addInput = removeCommandWord(input, command.length());
                String[] frontAndBack = parseAddCardCommand(addInput);
                deckManager.getDecks().get(currDeck).prepareToAddFlashCard(frontAndBack);
                logger.log(Level.INFO, "add command parsed and executed");
                break;
            case "delete": //delete <index/front>
                String deleteInput = removeCommandWord(input, command.length());
                deckManager.getDecks().get(currDeck).prepareToDeleteFlashCard(deleteInput);
                logger.log(Level.INFO, "delete command parsed and executed");
                break;
            case "edit": //edit /card <card index> /side <side> /input <input>
                String editCardInput = removeCommandWord(input, command.length());
                String[] parsedEditCardArgs = parseEditCardCommand(editCardInput);
                deckManager.getDecks().get(currDeck).editCard(parsedEditCardArgs);
                logger.log(Level.INFO, "editcard command parsed and executed");
                break;
            case "view": //view
                deckManager.getDecks().get(currDeck).viewAllFlashCards();
                break;
            case "help": //help
                CardLiUi.helpInDeckMessage();
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

    public Command parseCommand(String input) {
        logger.setLevel(Level.WARNING);
        String commandType = getCommandType(input);
        logger.log(Level.INFO, "new user input detected");

        Command command;
        String arguments;

        switch (commandType) {
            case "add":
                arguments = getCommandArguments(commandType, input);
                command = new AddCardCommand(arguments, this.currDeck);
                break;
            case "edit": //edit /deck <cat index> /input <input>
                break;
            case "delete":
                break;
            case "help":
                command = new HelpCommand();
                break;
            case "exit":
                command = new ExitProgrammeCommand(); // TODO: change to proper exit deck view command
                break;
            default:
                command = new InvalidCommand();
        }
        return command;
    }

    public void setCurrDeck(Deck currDeck) {
        this.currDeck = currDeck;
    }

    // TODO: clean up code repeated in OuterParser, possibly using inheritance
    /**
     * Returns the command type of the user's input.
     * @param input
     */
    private String getCommandType(String input) {
        return input.trim().split(" ")[0].toLowerCase();
    }

    /**
     * Returns the String containing the arguments to the command.
     */
    private String getCommandArguments(String commandType, String input) { // TODO: throws FieldEmptyException
        assert input.length() > 0 : "input string should not be empty, at least have command word";
        return input.substring(commandType.length()).trim();
    }
}
