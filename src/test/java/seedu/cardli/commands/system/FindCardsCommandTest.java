package seedu.cardli.commands.system;

import org.junit.jupiter.api.Test;
import seedu.cardli.commands.Command;
import seedu.cardli.commands.CommandResult;
import seedu.cardli.flashcard.DeckManager;
import seedu.cardli.parser.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindCardsCommandTest {
    @Test
    public void execute_emptyString_expectNoSearchTermErrorMessage() {
        DeckManager deckManager = new DeckManager();
        deckManager.prepareToAddDeck("yeet");
        String input = "find ";
        String commandType = Parser.getCommandType(input);
        String arguments = Parser.getCommandArguments(commandType, input);
        Command test = new FindCardsCommand(arguments, deckManager);
        CommandResult result = test.execute();
        String output = result.getResult();
        assertEquals("You did not input a search term after \"find\".", output);
    }
}
