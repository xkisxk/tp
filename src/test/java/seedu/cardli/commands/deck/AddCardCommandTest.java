package seedu.cardli.commands.deck;

import org.junit.jupiter.api.Test;
import seedu.cardli.commands.Command;
import seedu.cardli.commands.CommandResult;
import seedu.cardli.flashcard.DeckManager;
import seedu.cardli.parser.Parser;

import static org.junit.jupiter.api.Assertions.*;

class AddCardCommandTest {

    @Test
    public void execute_addingCardWithSameFront_expectError() {
        DeckManager deckManager = new DeckManager();
        deckManager.prepareToAddDeck("deck1");
        deckManager.getDeck(0).addFlashCard("card", "card");
        String input = "add /f card /b back";
        String commandType = Parser.getCommandType(input);
        String arguments = Parser.getCommandArguments(commandType, input);
        Command test = new AddCardCommand(arguments, deckManager.getDeck(0), deckManager);
        CommandResult result = test.execute();
        String output = result.getResult();
        assertEquals("There is already a card with card on the front in deck deck1.", output);
    }

}