package seedu.cardli;

import org.junit.jupiter.api.Test;
import seedu.cardli.commands.Command;
import seedu.cardli.commands.CommandResult;
import seedu.cardli.commands.system.EditDeckCommand;
import seedu.cardli.flashcard.DeckManager;
import seedu.cardli.parser.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EditDeckCommandTest {

    @Test
    public void execute_emptyString_expectFieldEmptyErrorMessage() {
        DeckManager deckManager = new DeckManager();
        deckManager.prepareToAddDeck("yeet");
        String input = "edit";
        String commandType = Parser.getCommandType(input);
        String arguments = Parser.getCommandArguments(commandType, input);
        Command test = new EditDeckCommand(arguments, deckManager);
        CommandResult result = test.execute();
        String output = result.getResult();
        assertEquals("You cannot leave any field empty! "
                + "Format should be\n edit /d <deck index/name of deck> /n <new name of deck>", output);
    }

    @Test
    public void execute_duplicateCorrectFlags_expectInvalidArgumentsMessage() {
        DeckManager deckManager = new DeckManager();
        deckManager.prepareToAddDeck("yeet");
        String input = "edit /d /d /n /n";
        String commandType = Parser.getCommandType(input);
        String arguments = Parser.getCommandArguments(commandType, input);
        Command test = new EditDeckCommand(arguments, deckManager);
        CommandResult result = test.execute();
        String output = result.getResult();
        assertEquals("Please use the correct flags and in the correct order! "
                + "\nFormat + should be edit /d <deck index/name of deck> /n <new name of deck>", output);
    }

    @Test
    public void execute_incorrectFlag_expectInvalidArgumentsMessage() {
        DeckManager deckManager = new DeckManager();
        deckManager.prepareToAddDeck("yeet");
        String input = "edit /b 1 /e name";
        String commandType = Parser.getCommandType(input);
        String arguments = Parser.getCommandArguments(commandType, input);
        Command test = new EditDeckCommand(arguments, deckManager);
        CommandResult result = test.execute();
        String output = result.getResult();
        assertEquals("Please use the correct flags and in the correct order! "
                + "\nFormat + should be edit /d <deck index/name of deck> /n <new name of deck>", output);
    }

    @Test
    public void execute_swappedFlagOrder_expectWrongOrderErrorMessage() {
        DeckManager deckManager = new DeckManager();
        deckManager.prepareToAddDeck("yeet");
        String input = "edit /n 1 /d name";
        String commandType = Parser.getCommandType(input);
        String arguments = Parser.getCommandArguments(commandType, input);
        Command test = new EditDeckCommand(arguments, deckManager);
        CommandResult result = test.execute();
        String output = result.getResult();
        assertEquals("Incorrect edit command! /n should come after /d. Format "
                + "should be\n edit /d <deck index/name of deck> /n <new name of deck>", output);
    }

    @Test
    public void execute_emptyArguments_expectFieldEmptyErrorMessage() {
        DeckManager deckManager = new DeckManager();
        deckManager.prepareToAddDeck("yeet");
        String input = "edit /d  /n";
        String commandType = Parser.getCommandType(input);
        String arguments = Parser.getCommandArguments(commandType, input);
        Command test = new EditDeckCommand(arguments, deckManager);
        CommandResult result = test.execute();
        String output = result.getResult();
        assertEquals("You cannot leave any field empty! "
                + "Format should be\n edit /d <deck index/name of deck> /n <new name of deck>", output);
    }

    @Test
    public void execute_incorrectFlagOrder_expectInvalidArgumentsMessage() {
        DeckManager deckManager = new DeckManager();
        deckManager.prepareToAddDeck("yeet");
        String input = "edit a b /d /n";
        String commandType = Parser.getCommandType(input);
        String arguments = Parser.getCommandArguments(commandType, input);
        Command test = new EditDeckCommand(arguments, deckManager);
        CommandResult result = test.execute();
        String output = result.getResult();
        assertEquals("Please use the correct flags and in the correct order! "
                + "\nFormat + should be edit /d <deck index/name of deck> /n <new name of deck>", output);
    }

    @Test
    public void execute_invalidIntegerIndex_expectInvalidIndexErrorMessage() {
        DeckManager deckManager = new DeckManager();
        deckManager.prepareToAddDeck("yeet");
        String input = "edit /d 2 /n name";
        String commandType = Parser.getCommandType(input);
        String arguments = Parser.getCommandArguments(commandType, input);
        Command test = new EditDeckCommand(arguments, deckManager);
        CommandResult result = test.execute();
        String output = result.getResult();
        assertEquals("Incorrect index for deck!", output);
    }

    @Test
    public void execute_NegativeIntegerIndex_expectArgumentTypeErrorMessage() {
        DeckManager deckManager = new DeckManager();
        deckManager.prepareToAddDeck("yeet");
        String input = "edit /d -1 /n name";
        String commandType = Parser.getCommandType(input);
        String arguments = Parser.getCommandArguments(commandType, input);
        Command test = new EditDeckCommand(arguments, deckManager);
        CommandResult result = test.execute();
        String output = result.getResult();
        assertEquals("You can only input the index of the deck, which is "
                + "a positive integer!", output);
    }

    @Test
    public void execute_stringDeck_expectArgumentTypeErrorMessage() {
        DeckManager deckManager = new DeckManager();
        deckManager.prepareToAddDeck("yeet");
        String input = "edit /d abc /n name";
        String commandType = Parser.getCommandType(input);
        String arguments = Parser.getCommandArguments(commandType, input);
        Command test = new EditDeckCommand(arguments, deckManager);
        CommandResult result = test.execute();
        String output = result.getResult();
        assertEquals("You can only input the index of the deck, which is "
                + "a positive integer!", output);
    }
}
