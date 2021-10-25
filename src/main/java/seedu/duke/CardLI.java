package seedu.duke;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.flashcard.Deck;
import seedu.duke.flashcard.DeckManager;
import seedu.duke.parser.InnerParser;
import seedu.duke.parser.OuterParser;
import seedu.duke.storage.Storage;
import seedu.duke.testing.TestHistory;
import seedu.duke.testing.TestManager;
import seedu.duke.ui.CardLiUi;

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
        this.outerParser = new OuterParser(deckManager, innerParser, testHistory);
    }

    /**
     * Main function that runs the java.duke.Duke application.
     */
    public void run() {

        ui.printGreetingMessage();
        boolean exitProgram = false;
        boolean inDeck;
        boolean inTest;
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
            inTest = result.isTest();
            if (inTest) {
                testManager.startTest();
            }
            inReview = result.isReview();
            if (inReview) {
                testManager.startReview();
            }
        }
        storage.writeToFile(deckManager.getDecks(), "cards");
        storage.writeToFile(testHistory.getTestHistory(), "tests");
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
