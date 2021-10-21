package seedu.duke.parser;

import seedu.duke.commands.Command;
import seedu.duke.commands.InvalidCommand;
import seedu.duke.commands.system.AddDeckCommand;
import seedu.duke.commands.system.EnterDeckCommand;
import seedu.duke.commands.system.ExitProgrammeCommand;
import seedu.duke.commands.system.HelpCommand;
import seedu.duke.flashcard.DeckManager;

import java.util.logging.Level;
import java.util.logging.Logger;

public class OuterParser {

    private static final Logger logger = Logger.getLogger(OuterParser.class.getName());

    private DeckManager deckManager;
    private InnerParser innerParser;

    public OuterParser(DeckManager deckManager, InnerParser innerParser) {
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
        case "add":
            arguments = getCommandArguments(commandType, input);
            command = new AddDeckCommand(arguments, this.deckManager);
            break;
        case "edit": //edit /deck <cat index> /input <input>
            break;
        case "delete":
            break;
        case "help":
            command = new HelpCommand();
            break;
        case "bye":
            command = new ExitProgrammeCommand();
            break;
        default:
            command = new InvalidCommand();
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
}
