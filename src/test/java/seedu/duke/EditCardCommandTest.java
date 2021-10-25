package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.commands.deck.EditCardCommand;
import seedu.duke.flashcard.Deck;
import seedu.duke.parser.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EditCardCommandTest {

    @Test
    public void execute_emptyArgument_expectFieldEmptyErrorMessage() {
        Deck deck = new Deck();
        deck.addFlashCard("card", "card");
        String input = "edit ";
        String commandType = Parser.getCommandType(input);
        String arguments = Parser.getCommandArguments(commandType, input);
        Command test = new EditCardCommand(arguments, deck);
        CommandResult result = test.execute();
        String output = result.getResult();
        assertEquals("You cannot leave any field empty! "
                + "Format should be\n edit /c <card index> /s <side> /i <input>", output);
    }

}
