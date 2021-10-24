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
        String commandType = Parser.getCommandType(input);
        logger.log(Level.INFO, "new user input detected");

        Command command;
        String arguments;

        switch (commandType) {
        case "enter":
            arguments = Parser.getCommandArguments(commandType, input);
            command = new EnterDeckCommand(arguments, this.deckManager, this.innerParser);
            logger.log(Level.INFO, "enter (deck) command parsed and executed");
            break;
        case "view":
            command = new ViewDecksCommand(this.deckManager);
            logger.log(Level.INFO, "view (all decks) command parsed and executed");
            break;
        case "add":
            arguments = Parser.getCommandArguments(commandType, input);
            command = new AddDeckCommand(arguments, this.deckManager);
            logger.log(Level.INFO, "add (deck) command parsed and executed");
            break;
        case "edit": //edit /deck <cat index> /input <input>
            arguments = Parser.getCommandArguments(commandType, input);
            command = new EditDeckCommand(arguments, this.deckManager);
            logger.log(Level.INFO, "edit (deck) command parsed and executed");
            break;
        case "delete":
            // TODO
            command = new InvalidCommand();
            logger.log(Level.INFO, "delete (deck) command parsed and executed");
            break;
        case "find":
            arguments = Parser.getCommandArguments(commandType, input);
            command = new FindCardsCommand(arguments, this.deckManager);
            logger.log(Level.INFO, "find (card) command parsed and executed");
            break;
        case "test":
            command = new TestCommand();
            logger.log(Level.INFO, "test command parsed and executed");
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


}
