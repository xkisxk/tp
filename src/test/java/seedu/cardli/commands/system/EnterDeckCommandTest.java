package seedu.cardli.commands.system;

import org.junit.jupiter.api.Test;
import seedu.cardli.commands.Command;
import seedu.cardli.commands.CommandResult;
import seedu.cardli.flashcard.DeckManager;
import seedu.cardli.parser.InnerParser;
import seedu.cardli.parser.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnterDeckCommandTest {
    @Test
    public void execute_emptyString_expectInvalidInputMessage() {
        DeckManager deckManager = new DeckManager();
        InnerParser innerParser = new InnerParser();
        deckManager.prepareToAddDeck("yeet");
        String input = "enter";
        String commandType = Parser.getCommandType(input);
        String arguments = Parser.getCommandArguments(commandType, input);
        Command test = new EnterDeckCommand(arguments, deckManager, innerParser);
        CommandResult result = test.execute();
        String output = result.getResult();
        assertEquals("Invalid input. Please input deck index after \"enter\".", output);
    }

    @Test
    public void execute_invalidIndex_expectDeckDoesNotExistMessage() {
        DeckManager deckManager = new DeckManager();
        InnerParser innerParser = new InnerParser();
        deckManager.prepareToAddDeck("yeet");
        String input = "enter 2";
        String commandType = Parser.getCommandType(input);
        String arguments = Parser.getCommandArguments(commandType, input);
        Command test = new EnterDeckCommand(arguments, deckManager, innerParser);
        CommandResult result = test.execute();
        String output = result.getResult();
        assertEquals("That deck doesn't exist. Please input a valid deck index.", output);
    }

}
