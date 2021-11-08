package seedu.cardli.commands.system;

import org.junit.jupiter.api.Test;
import seedu.cardli.commands.Command;
import seedu.cardli.commands.CommandResult;
import seedu.cardli.flashcard.DeckManager;
import seedu.cardli.parser.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ViewDecksCommandTest {
    @Test
    public void executeArgument_expectExtraArgumentMessage() {
        DeckManager deckManager = new DeckManager();
        String input = "view abc";
        String commandType = Parser.getCommandType(input);
        String arguments = Parser.getCommandArguments(commandType, input);
        Command test = new ViewDecksCommand(deckManager, arguments);
        CommandResult result = test.execute();
        String output = result.getResult();
        assertEquals("There should not be any arguments.", output);
    }
}
