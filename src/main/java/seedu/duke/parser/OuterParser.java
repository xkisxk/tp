package seedu.duke.parser;

import seedu.duke.commands.Command;
import seedu.duke.commands.InvalidCommand;
import seedu.duke.commands.system.*;
import seedu.duke.flashcard.DeckManager;

import java.util.logging.Level;
import java.util.logging.Logger;

public class OuterParser {

    private static final Logger logger = Logger.getLogger(OuterParser.class.getName());

    private DeckManager deckManager;
    private InnerParser innerParser;

    public OuterParser(DeckManager deckManager, InnerParser innerParser) {
        logger.setLevel(Level.WARNING);
        this.deckManager = deckManager;
        this.innerParser = innerParser;
    }
    public Command parseCommand(String input) {
        // create a new Command that has `type` and `arguments`
        // the command should be of type `AddDeckCommand`, `DeleteDeckCommand`, etc.
        // anyhow, `Command` can't be instantiated as it is abstract
        logger.setLevel(Level.WARNING);
        String commandType = getCommandType(input);
        logger.log(Level.INFO, "new user input detected");

        Command command;
        String arguments;

        switch (commandType) {
        case "enter":
            arguments = getCommandArguments(commandType, input);
            command = new EnterDeckCommand(arguments, this.deckManager, this.innerParser);
            logger.log(Level.INFO, "enter (deck) command parsed and executed");
        case "add":
            arguments = getCommandArguments(commandType, input);
            command = new AddDeckCommand(arguments, this.deckManager);
            logger.log(Level.INFO, "add (deck) command parsed and executed");
            break;
        case "edit": //edit /deck <cat index> /input <input>
            arguments = getCommandArguments(commandType, input);
            command = new EditDeckCommand(arguments, this.deckManager)
            logger.log(Level.INFO, "edit (deck) command parsed and executed");
            break;
        case "delete":
            // TODO
            logger.log(Level.INFO, "delete (deck) command parsed and executed");
            break;
        case "help":
            command = new HelpCommand();
            logger.log(Level.INFO, "help (deck) command parsed and executed");
            break;
        case "bye":
            command = new ExitProgrammeCommand();
            logger.log(Level.INFO, "current list of decks and flashcards saved to text file");
            logger.log(Level.INFO, "bye command parsed and executed, program will terminate");
            break;
        default:
            command = new InvalidCommand();
            logger.log(Level.INFO, "command was unrecognised and could not be parsed");
        }
        return command;
    }

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

    /**
     * Checks if the given input is an integer or not.
     *
     * @param input input given by user
     * @return true if input is an integer, false otherwise
     */
    public boolean isInteger(String input) {
        for (int i = 0; i < input.length(); i += 1) {
            if (!Character.isDigit(input.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
