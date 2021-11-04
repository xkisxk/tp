package seedu.cardli;

import org.junit.jupiter.api.Test;
import seedu.cardli.commands.Command;
import seedu.cardli.commands.CommandResult;
import seedu.cardli.commands.deck.EditCardCommand;
import seedu.cardli.flashcard.Deck;
import seedu.cardli.parser.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EditCardCommandTest {

    @Test
    public void execute_emptyString_expectFieldEmptyErrorMessage() {
        Deck deck = new Deck();
        deck.addFlashCard("card", "card");
        String input = "edit ";
        String commandType = Parser.getCommandType(input);
        String arguments = Parser.getCommandArguments(commandType, input);
        Command test = new EditCardCommand(arguments, deck);
        CommandResult result = test.execute();
        String output = result.getResult();
        assertEquals("You cannot leave any field empty! "
                + "Format should be\nedit /c <card index> /s <side> /i <input>", output);
    }

    @Test
    public void execute_duplicateCorrectFlags_expectFlagArgumentErrorMessage() {
        Deck deck = new Deck();
        deck.addFlashCard("card", "card");
        String input = "edit /c /c /s /s /i /i";
        String commandType = Parser.getCommandType(input);
        String arguments = Parser.getCommandArguments(commandType, input);
        Command test = new EditCardCommand(arguments, deck);
        CommandResult result = test.execute();
        String output = result.getResult();
        assertEquals("You should not use this command's flag as your argument", output);
    }

    @Test
    public void execute_incorrectFlags_expectMissingFlagMessage() {
        Deck deck = new Deck();
        deck.addFlashCard("card", "card");
        String input = "edit /d 1 /j back /e noice";
        String commandType = Parser.getCommandType(input);
        String arguments = Parser.getCommandArguments(commandType, input);
        Command test = new EditCardCommand(arguments, deck);
        CommandResult result = test.execute();
        String output = result.getResult();
        assertEquals("You are missing the relevant flag/flags", output);
    }

    @Test
    public void execute_swappedFlagOrder_expectWrongOrderErrorMessage() {
        Deck deck = new Deck();
        deck.addFlashCard("card", "card");
        String input = "edit /c 1 /i name /s front";
        String commandType = Parser.getCommandType(input);
        String arguments = Parser.getCommandArguments(commandType, input);
        Command test = new EditCardCommand(arguments, deck);
        CommandResult result = test.execute();
        String output = result.getResult();
        assertEquals("/c should come before /s, which should come before /i!"
                + " Format should be\nedit /c <card index> /s <side> /i <input>", output);
    }

    @Test
    public void execute_emptyArgument_expectFieldEmptyErrorMessage() {
        Deck deck = new Deck();
        deck.addFlashCard("card", "card");
        String input = "edit /c /s /i";
        String commandType = Parser.getCommandType(input);
        String arguments = Parser.getCommandArguments(commandType, input);
        Command test = new EditCardCommand(arguments, deck);
        CommandResult result = test.execute();
        String output = result.getResult();
        assertEquals("You cannot leave any field empty! "
                + "Format should be\nedit /c <card index> /s <side> /i <input>", output);
    }

    @Test
    public void execute_incorrectFlagOrder_expectInvalidArgumentsMessage() {
        Deck deck = new Deck();
        deck.addFlashCard("card", "card");
        String input = "edit 1 back noice /c /s /i";
        String commandType = Parser.getCommandType(input);
        String arguments = Parser.getCommandArguments(commandType, input);
        Command test = new EditCardCommand(arguments, deck);
        CommandResult result = test.execute();
        String output = result.getResult();
        assertEquals("Please use the correct flags and in the correct order! "
                + "\nFormat should be edit /c <card index> /s <side> /i <input>", output);
    }

    @Test
    public void execute_incorrectSide_expectInvalidSideErrorMessage() {
        Deck deck = new Deck();
        deck.addFlashCard("card", "card");
        String input = "edit /c 1 /s middle /i name";
        String commandType = Parser.getCommandType(input);
        String arguments = Parser.getCommandArguments(commandType, input);
        Command test = new EditCardCommand(arguments, deck);
        CommandResult result = test.execute();
        String output = result.getResult();
        assertEquals("What side is this? It's only either front or back", output);
    }

    @Test
    public void execute_invalidIntegerIndex_expectInvalidIndexErrorMessage() {
        Deck deck = new Deck();
        deck.addFlashCard("card", "card");
        String input = "edit /c 2 /s front /i name";
        String commandType = Parser.getCommandType(input);
        String arguments = Parser.getCommandArguments(commandType, input);
        Command test = new EditCardCommand(arguments, deck);
        CommandResult result = test.execute();
        String output = result.getResult();
        assertEquals("Incorrect index for Card!", output);
    }

    @Test
    public void execute_negativeIntegerIndex_expectArgumentTypeErrorMessage() {
        Deck deck = new Deck();
        deck.addFlashCard("card", "card");
        String input = "edit /c -1 /s front /i name";
        String commandType = Parser.getCommandType(input);
        String arguments = Parser.getCommandArguments(commandType, input);
        Command test = new EditCardCommand(arguments, deck);
        CommandResult result = test.execute();
        String output = result.getResult();
        assertEquals("You can only input the index of the card, which is "
                + "a positive integer!", output);
    }

    @Test
    public void execute_stringCard_expectArgumentTypeErrorMessage() {
        Deck deck = new Deck();
        deck.addFlashCard("card", "card");
        String input = "edit /c abc /s front /i name";
        String commandType = Parser.getCommandType(input);
        String arguments = Parser.getCommandArguments(commandType, input);
        Command test = new EditCardCommand(arguments, deck);
        CommandResult result = test.execute();
        String output = result.getResult();
        assertEquals("You can only input the index of the card, which is "
                + "a positive integer!", output);
    }

    @Test
    public void execute_largeInteger_expectLargeIntegerErrorMessage() {
        Deck deck = new Deck();
        deck.addFlashCard("card", "card");
        String input = "edit /c 2147483648 /s front /i name";
        String commandType = Parser.getCommandType(input);
        String arguments = Parser.getCommandArguments(commandType, input);
        Command test = new EditCardCommand(arguments, deck);
        CommandResult result = test.execute();
        String output = result.getResult();
        assertEquals("Card index must be smaller than 2147483647.", output);
    }



}
