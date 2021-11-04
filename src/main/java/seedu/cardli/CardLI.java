package seedu.cardli;

import org.fusesource.jansi.AnsiConsole;
import seedu.cardli.commands.Command;
import seedu.cardli.commands.CommandResult;
import seedu.cardli.flashcard.Deck;
import seedu.cardli.flashcard.DeckManager;
import seedu.cardli.parser.InnerParser;
import seedu.cardli.parser.OuterParser;
import seedu.cardli.storage.Storage;
import seedu.cardli.testing.TestHistory;
import seedu.cardli.testing.TestManager;
import seedu.cardli.ui.CardLiUi;

import java.util.ArrayList;

/**
 * Represents CardLI application.
 */
public class CardLI {
    private static final CardLiUi ui = new CardLiUi();

    private ArrayList<Deck> decks;
    private Storage storage;
    private DeckManager deckManager;
    private TestManager testManager;
    private InnerParser innerParser;
    private OuterParser outerParser;
    private TestHistory testHistory;

    private CardLI() {
        this.storage = new Storage();
        this.decks = storage.readCardsFromFile();
        this.deckManager = new DeckManager(decks);
        this.testHistory = new TestHistory(deckManager, storage.readTestsFromFile());
        this.testManager = new TestManager(testHistory, deckManager);
        this.innerParser = new InnerParser();
        this.outerParser = new OuterParser(deckManager, innerParser, testHistory, testManager);
        AnsiConsole.systemInstall();
    }

    /**
     * Main function that runs the java.duke.Duke application.
     */
    public void run() {

        ui.printGreetingMessage();
        boolean exitProgram = false;
        boolean inDeck;
        boolean inReview;


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
            storage.writeCardsToFile(deckManager.getDecks());
            storage.writeTestsToFile(testHistory.getTestHistory());
        }
        ui.printByeMessage();
    }

    /**
     * Entry point to the java.duke.Duke application.
     * @param args user's input
     */
    public static void main(String[] args) {
        new CardLI().run();
    }
}
