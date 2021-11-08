package seedu.cardli.commands.deck;

import org.junit.jupiter.api.Test;
import seedu.cardli.commands.Command;
import seedu.cardli.commands.CommandResult;
import seedu.cardli.commands.deck.MoveCardCommand;
import seedu.cardli.flashcard.DeckManager;
import seedu.cardli.parser.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoveCardCommandTest {
    @Test
    public void execute_emptyString_expectFieldEmptyErrorMessage() {
        DeckManager deckManager = new DeckManager();
        deckManager.prepareToAddDeck("yeet");
        deckManager.prepareToAddDeck("yeeter");
        deckManager.getDeck(0).addFlashCard("card", "card");
        String input = "move ";
        String commandType = Parser.getCommandType(input);
        String arguments = Parser.getCommandArguments(commandType, input);
        Command test = new MoveCardCommand(arguments, deckManager.getDeck(0), deckManager);
        CommandResult result = test.execute();
        String output = result.getResult();
        assertEquals("You cannot leave any field empty! "
                + "Format should be\nmove /c <card index> /d <deck index>", output);
    }

    @Test
    public void execute_duplicateCorrectFlags_expectFlagArgumentErrorMessage() {
        DeckManager deckManager = new DeckManager();
        deckManager.prepareToAddDeck("yeet");
        deckManager.prepareToAddDeck("yeeter");
        deckManager.getDeck(0).addFlashCard("card", "card");
        String input = "move /c /c /d /d";
        String commandType = Parser.getCommandType(input);
        String arguments = Parser.getCommandArguments(commandType, input);
        Command test = new MoveCardCommand(arguments, deckManager.getDeck(0), deckManager);
        CommandResult result = test.execute();
        String output = result.getResult();
        assertEquals("You should not use this command's flag as your argument", output);
    }

    @Test
    public void execute_incorrectFlags_expectMissingFlagMessage() {
        DeckManager deckManager = new DeckManager();
        deckManager.prepareToAddDeck("yeet");
        deckManager.prepareToAddDeck("yeeter");
        deckManager.getDeck(0).addFlashCard("card", "card");
        String input = "move /a 1 /b /1";
        String commandType = Parser.getCommandType(input);
        String arguments = Parser.getCommandArguments(commandType, input);
        Command test = new MoveCardCommand(arguments, deckManager.getDeck(0), deckManager);
        CommandResult result = test.execute();
        String output = result.getResult();
        assertEquals("You are missing the relevant flag/flags", output);
    }

    @Test
    public void execute_swappedFlagOrder_expectWrongOrderErrorMessage() {
        DeckManager deckManager = new DeckManager();
        deckManager.prepareToAddDeck("yeet");
        deckManager.prepareToAddDeck("yeeter");
        deckManager.getDeck(0).addFlashCard("card", "card");
        String input = "move /d 1 /c /1";
        String commandType = Parser.getCommandType(input);
        String arguments = Parser.getCommandArguments(commandType, input);
        Command test = new MoveCardCommand(arguments, deckManager.getDeck(0), deckManager);
        CommandResult result = test.execute();
        String output = result.getResult();
        assertEquals("/c should come before /d! Format should be\n"
                + "move /c <card index> /d <deck index>", output);
    }

    @Test
    public void execute_emptyArgument_expectFieldEmptyErrorMessage() {
        DeckManager deckManager = new DeckManager();
        deckManager.prepareToAddDeck("yeet");
        deckManager.prepareToAddDeck("yeeter");
        deckManager.getDeck(0).addFlashCard("card", "card");
        String input = "move /c /d ";
        String commandType = Parser.getCommandType(input);
        String arguments = Parser.getCommandArguments(commandType, input);
        Command test = new MoveCardCommand(arguments, deckManager.getDeck(0), deckManager);
        CommandResult result = test.execute();
        String output = result.getResult();
        assertEquals("You cannot leave any field empty! "
                + "Format should be\nmove /c <card index> /d <deck index>", output);
    }

    @Test
    public void execute_incorrectFlagOrder_expectInvalidArgumentsMessage() {
        DeckManager deckManager = new DeckManager();
        deckManager.prepareToAddDeck("yeet");
        deckManager.prepareToAddDeck("yeeter");
        deckManager.getDeck(0).addFlashCard("card", "card");
        String input = "move 1 /c 1 /d ";
        String commandType = Parser.getCommandType(input);
        String arguments = Parser.getCommandArguments(commandType, input);
        Command test = new MoveCardCommand(arguments, deckManager.getDeck(0), deckManager);
        CommandResult result = test.execute();
        String output = result.getResult();
        assertEquals("Please use the correct flags and in the correct order! "
                + "\nFormat should be move /c <card index> /d <deck index>", output);
    }

    @Test
    public void execute_invalidCardIntegerIndex_expectInvalidIndexErrorMessage() {
        DeckManager deckManager = new DeckManager();
        deckManager.prepareToAddDeck("yeet");
        deckManager.prepareToAddDeck("yeeter");
        deckManager.getDeck(0).addFlashCard("card", "card");
        String input = "move /c 2 /d 1";
        String commandType = Parser.getCommandType(input);
        String arguments = Parser.getCommandArguments(commandType, input);
        Command test = new MoveCardCommand(arguments, deckManager.getDeck(0), deckManager);
        CommandResult result = test.execute();
        String output = result.getResult();
        assertEquals("Incorrect index for Card!", output);
    }

    @Test
    public void execute_invalidDeckIntegerIndex_expectInvalidIndexErrorMessage() {
        DeckManager deckManager = new DeckManager();
        deckManager.prepareToAddDeck("yeet");
        deckManager.prepareToAddDeck("yeeter");
        deckManager.getDeck(0).addFlashCard("card", "card");
        String input = "move /c 1 /d 3";
        String commandType = Parser.getCommandType(input);
        String arguments = Parser.getCommandArguments(commandType, input);
        Command test = new MoveCardCommand(arguments, deckManager.getDeck(0), deckManager);
        CommandResult result = test.execute();
        String output = result.getResult();
        assertEquals("Incorrect index for deck!", output);
    }

    @Test
    public void execute_negativeCardIntegerIndex_expectArgumentTypeErrorMessage() {
        DeckManager deckManager = new DeckManager();
        deckManager.prepareToAddDeck("yeet");
        deckManager.prepareToAddDeck("yeeter");
        deckManager.getDeck(0).addFlashCard("card", "card");
        String input = "move /c -1 /d 2";
        String commandType = Parser.getCommandType(input);
        String arguments = Parser.getCommandArguments(commandType, input);
        Command test = new MoveCardCommand(arguments, deckManager.getDeck(0), deckManager);
        CommandResult result = test.execute();
        String output = result.getResult();
        assertEquals("You can only input the index of the card, "
                + "which is a positive integer!", output);
    }

    @Test
    public void execute_negativeDeckIntegerIndex_expectArgumentTypeErrorMessage() {
        DeckManager deckManager = new DeckManager();
        deckManager.prepareToAddDeck("yeet");
        deckManager.prepareToAddDeck("yeeter");
        deckManager.getDeck(0).addFlashCard("card", "card");
        String input = "move /c 1 /d -1";
        String commandType = Parser.getCommandType(input);
        String arguments = Parser.getCommandArguments(commandType, input);
        Command test = new MoveCardCommand(arguments, deckManager.getDeck(0), deckManager);
        CommandResult result = test.execute();
        String output = result.getResult();
        assertEquals("You can only input the index of the deck, "
                + "which is a positive integer!", output);
    }

    @Test
    public void execute_stringAsCardIndex_expectArgumentTypeErrorMessage() {
        DeckManager deckManager = new DeckManager();
        deckManager.prepareToAddDeck("yeet");
        deckManager.prepareToAddDeck("yeeter");
        deckManager.getDeck(0).addFlashCard("card", "card");
        String input = "move /c abc /d 2";
        String commandType = Parser.getCommandType(input);
        String arguments = Parser.getCommandArguments(commandType, input);
        Command test = new MoveCardCommand(arguments, deckManager.getDeck(0), deckManager);
        CommandResult result = test.execute();
        String output = result.getResult();
        assertEquals("You can only input the index of the card, "
                + "which is a positive integer!", output);
    }

    @Test
    public void execute_stringAsDeckIndex_expectArgumentTypeErrorMessage() {
        DeckManager deckManager = new DeckManager();
        deckManager.prepareToAddDeck("yeet");
        deckManager.prepareToAddDeck("yeeter");
        deckManager.getDeck(0).addFlashCard("card", "card");
        String input = "move /c 1 /d abc";
        String commandType = Parser.getCommandType(input);
        String arguments = Parser.getCommandArguments(commandType, input);
        Command test = new MoveCardCommand(arguments, deckManager.getDeck(0), deckManager);
        CommandResult result = test.execute();
        String output = result.getResult();
        assertEquals("You can only input the index of the deck, "
                + "which is a positive integer!", output);
    }

    @Test
    public void execute_largeIntegerCardIndex_expectLargeIntgerErrorMessage() {
        DeckManager deckManager = new DeckManager();
        deckManager.prepareToAddDeck("yeet");
        deckManager.prepareToAddDeck("yeeter");
        deckManager.getDeck(0).addFlashCard("card", "card");
        String input = "move /c 2147483648 /d 2";
        String commandType = Parser.getCommandType(input);
        String arguments = Parser.getCommandArguments(commandType, input);
        Command test = new MoveCardCommand(arguments, deckManager.getDeck(0), deckManager);
        CommandResult result = test.execute();
        String output = result.getResult();
        assertEquals("Card and Deck index must be both "
                + "smaller than 2147483647.", output);
    }

    @Test
    public void execute_largeIntegerDeckIndex_expectLargeIntgerErrorMessage() {
        DeckManager deckManager = new DeckManager();
        deckManager.prepareToAddDeck("yeet");
        deckManager.prepareToAddDeck("yeeter");
        deckManager.getDeck(0).addFlashCard("card", "card");
        String input = "move /c 1 /d 2147483648";
        String commandType = Parser.getCommandType(input);
        String arguments = Parser.getCommandArguments(commandType, input);
        Command test = new MoveCardCommand(arguments, deckManager.getDeck(0), deckManager);
        CommandResult result = test.execute();
        String output = result.getResult();
        assertEquals("Card and Deck index must be both "
                + "smaller than 2147483647.", output);
    }

    @Test
    public void execute_sameDeckDestination_expectSameDestinationErrorMessage() {
        DeckManager deckManager = new DeckManager();
        deckManager.prepareToAddDeck("yeet");
        deckManager.prepareToAddDeck("yeeter");
        deckManager.getDeck(0).addFlashCard("card", "card");
        String input = "move /c 1 /d 1";
        String commandType = Parser.getCommandType(input);
        String arguments = Parser.getCommandArguments(commandType, input);
        Command test = new MoveCardCommand(arguments, deckManager.getDeck(0), deckManager);
        CommandResult result = test.execute();
        String output = result.getResult();
        assertEquals("Your card is already in the deck specified!", output);
    }


}
