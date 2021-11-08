package seedu.cardli.commands.system;

import org.junit.jupiter.api.Test;
import seedu.cardli.commands.Command;
import seedu.cardli.commands.CommandResult;
import seedu.cardli.flashcard.DeckManager;
import seedu.cardli.parser.Parser;
import seedu.cardli.testing.TestHistory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ViewFlashCardStatsCommandTest {
    @Test
    public void executeArgument_expectExtraArgumentMessage() {
        DeckManager deckManager = new DeckManager();
        TestHistory testHistory = new TestHistory(deckManager);
        String input = "viewfc 100";
        String commandType = Parser.getCommandType(input);
        String arguments = Parser.getCommandArguments(commandType, input);
        Command test = new ViewFlashCardStatsCommand(arguments, testHistory);
        CommandResult result = test.execute();
        String output = result.getResult();
        assertEquals("There should not be any arguments.", output);
    }
}
