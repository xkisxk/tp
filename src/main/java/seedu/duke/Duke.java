package seedu.duke;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.exceptions.CardLiException;
import seedu.duke.flashcard.Deck;
import seedu.duke.flashcard.DeckManager;
import seedu.duke.parser.InnerParser;
import seedu.duke.parser.OuterParser;
import seedu.duke.parser.Parser;
import seedu.duke.storage.Storage;
import seedu.duke.testing.TestManager;
import seedu.duke.ui.CardLiUi;

import java.util.ArrayList;

/**
 * Represents CardLI application.
 */
public class Duke {
    private static final CardLiUi ui = new CardLiUi();

    private ArrayList<Deck> decks;
    private Storage storage;
    private DeckManager deckManager;
    private TestManager testManager;
    private InnerParser innerParser;
    private OuterParser outerParser;

    private Duke() {
        this.storage = new Storage();
        this.decks = storage.load();
        this.deckManager = new DeckManager(decks);
        this.testManager = new TestManager(decks);
        this.innerParser = new InnerParser();
        this.outerParser = new OuterParser(deckManager, innerParser);
    }

    /**
     * Main function that runs the java.duke.Duke application.
     */
    public void run() {

        ui.printGreetingMessage();
        boolean exitProgram = false;
        boolean inDeck;


        while (!exitProgram) {
            String input = ui.getUserMessage();
            Command command = outerParser.parseCommand(input);
            CommandResult result = command.execute();
            ui.printResult(result);
            inDeck = result.isEnter();
            while (inDeck) {
                input = ui.getUserMessage();
                command = innerParser.parseCommand(input);
                result = command.execute();
                ui.printResult(result);
                inDeck = result.isExit();
            }
            exitProgram = result.isExit();
        }
        ui.printByeMessage();


/*
        while (!exitProgram) {
            try {
                String input = ui.getUserMessage();
                Parser.parseCommand(input);
                inDeck = checkEnter(input);
                while (inDeck) {
                    input = ui.getUserMessage();
                    Parser.parseCommandWithinDeck(input);
                    inDeck = checkExit(input);
                }

                exitProgram = checkBye(input);
            } catch (CardLiException e) {
                ui.showMessage(e.getMessage());
            }
        }
        ui.printByeMessage();

 */
    }

    /**
     * Entry point to the java.duke.Duke application.
     * @param args
     */
    public static void main(String[] args) {
        new Duke().run();
    }
    /*
    private static boolean checkBye(String input) {
        if (Parser.getCommand(input).equals("bye")) {
            return true;
        }
        return false;
    }

    private static boolean checkExit(String input) {
        if (Parser.getCommand(input).equals("exit")) {
            return false;
        }
        return true;
    }

    private static boolean checkEnter(String input) {
        if (Parser.getCommand(input).equals("enter")) {
            if (Parser.isInteger(input.substring(5).trim())) {
                return true;
            }
        }
        return false;
    }
    */
}
