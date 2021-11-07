package seedu.cardli.parser;


import seedu.cardli.commands.Command;
import seedu.cardli.commands.InvalidCommand;
import seedu.cardli.commands.deck.AddCardCommand;
import seedu.cardli.commands.deck.DeleteCardCommand;
import seedu.cardli.commands.deck.EditCardCommand;
import seedu.cardli.commands.deck.ExitDeckCommand;
import seedu.cardli.commands.deck.HelpInDeckCommand;
import seedu.cardli.commands.deck.MoveCardCommand;
import seedu.cardli.commands.deck.ViewCardsCommand;
import seedu.cardli.flashcard.Deck;
import seedu.cardli.flashcard.DeckManager;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implements the OuterParser class, which identifies the command the
 * user has input at the deck level and returns a Command class with the
 * given arguments to be executed.
 */
public class InnerParser {

    private static final Logger logger = Logger.getLogger(InnerParser.class.getName());

    private Deck currDeck;
    private DeckManager deckManager;

    /**
     * Constructs a {@code InnerParser} with the specified field.
     * @param currDeck the current {@code Deck} the {@code InnerParser} should operate in
     */
    public InnerParser(Deck currDeck) {
        this.currDeck = currDeck;
    }

    /**
     * Constructs a {@code InnerParser} with an empty currDeck.
     */
    public InnerParser() {
        this.currDeck = null;
    }

    /**
     * Parses the user's input, identifies the command and creates a {@code Command}
     * with the arguments that were input.
     * @param input the user's raw String input
     * @return a {@code Command} object
     */
    public Command parseCommand(String input) {
        logger.setLevel(Level.WARNING);
        String commandType = Parser.getCommandType(input);
        logger.log(Level.INFO, "new user input detected");

        Command command;
        String arguments;

        switch (commandType) {
        case "add":
            arguments = Parser.getCommandArguments(commandType, input);
            command = new AddCardCommand(arguments, this.currDeck, this.deckManager);
            logger.log(Level.INFO, "add (card) command parsed and executed");
            break;
        case "edit":
            arguments = Parser.getCommandArguments(commandType, input);
            command = new EditCardCommand(arguments, this.currDeck, this.deckManager);
            logger.log(Level.INFO, "edit (card) command parsed and executed");
            break;
        case "delete":
            arguments = Parser.getCommandArguments(commandType, input);
            command = new DeleteCardCommand(arguments, this.currDeck);
            logger.log(Level.INFO, "delete (card) command parsed and executed");
            break;
        case "view":
            arguments = Parser.getCommandArguments(commandType, input);
            command = new ViewCardsCommand(this.currDeck, arguments);
            logger.log(Level.INFO, "view command parsed and executed");
            break;
        case "move":
            arguments = Parser.getCommandArguments(commandType, input);
            command = new MoveCardCommand(arguments, this.currDeck, this.deckManager);
            logger.log(Level.INFO, "move command parsed and executed");
            break;
        case "help":
            arguments = Parser.getCommandArguments(commandType, input);
            command = new HelpInDeckCommand(arguments);
            logger.log(Level.INFO, "help command parsed and executed");
            break;
        case "exit":
            arguments = Parser.getCommandArguments(commandType, input);
            command = new ExitDeckCommand(arguments);
            logger.log(Level.INFO, "exit command parsed and executed");
            break;
        default:
            command = new InvalidCommand();
            logger.log(Level.INFO, "command was unrecognised and could not be parsed");
        }
        return command;
    }

    public void setCurrDeck(Deck currDeck) {
        this.currDeck = currDeck;
    }

    public void setDeckManager(DeckManager deckList) {
        this.deckManager = deckList;
    }
}
