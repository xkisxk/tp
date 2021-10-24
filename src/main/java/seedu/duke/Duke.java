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
        this.testManager = new TestManager(this.deckManager);
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
            exitProgram = result.isExit();
            inDeck = result.isEnter();
            while (inDeck) {
                input = ui.getUserMessage();
                command = innerParser.parseCommand(input);
                result = command.execute();
                ui.printResult(result);
                inDeck = !result.isExit();
            }
        }
        ui.printByeMessage();
    }

    /**
     * Entry point to the java.duke.Duke application.
     * @param args
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
