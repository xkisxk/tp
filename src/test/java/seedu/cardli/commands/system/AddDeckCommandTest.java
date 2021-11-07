package seedu.cardli.commands.system;

import org.junit.jupiter.api.Test;
import seedu.cardli.commands.Command;
import seedu.cardli.commands.CommandResult;
import seedu.cardli.flashcard.DeckManager;
import seedu.cardli.parser.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddDeckCommandTest {

    public static final String DECK_ALREADY_EXISTS_MESSAGE = "The deck you are trying to create already exists.";

    @Test
    public void execute_deckWithSameNameDoesNotExist_expectSuccess() {
        DeckManager deckManager = new DeckManager();
        deckManager.prepareToAddDeck("deck1");
        String input = "add deck1";
        String commandType = Parser.getCommandType(input);
        String arguments = Parser.getCommandArguments(commandType, input);
        Command test = new AddDeckCommand(arguments, deckManager);
        CommandResult result = test.execute();
        String output = result.getResult();
        assertEquals(DECK_ALREADY_EXISTS_MESSAGE, output);
    }

    @Test
    public void execute_sameSpellingDifferentCase_expectSuccess() {
        DeckManager deckManager = new DeckManager();
        deckManager.prepareToAddDeck("deck1");
        String input = "add Deck1";
        String commandType = Parser.getCommandType(input);
        String arguments = Parser.getCommandArguments(commandType, input);
        Command test = new AddDeckCommand(arguments, deckManager);
        CommandResult result = test.execute();
        String output = result.getResult();
        assertEquals("You have just made the deck <<Deck1>>.", output);
    }

}