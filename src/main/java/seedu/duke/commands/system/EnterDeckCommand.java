package seedu.duke.commands.system;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.exceptions.DeckNotExistException;
import seedu.duke.flashcard.Deck;
import seedu.duke.flashcard.DeckManager;
import seedu.duke.parser.InnerParser;
import seedu.duke.parser.Parser;
import seedu.duke.parser.system.AddDeckParser;
import seedu.duke.parser.system.EnterDeckParser;

public class EnterDeckCommand extends Command {

    private EnterDeckParser parser;
    private DeckManager deckManager;
    private InnerParser innerParser;


    public EnterDeckCommand(String arguments, DeckManager deckManager, InnerParser innerParser) {
        super("EnterDeckCommand", arguments);
        this.parser = new EnterDeckParser();
        this.deckManager = deckManager;
        this.innerParser = innerParser;
    }

    @Override
    public CommandResult execute() {
        CommandResult result;
        try {
            String[] parameters = parser.parseArguments(super.arguments);
            String enterInput = parameters[0];

            if (!Parser.isInteger(enterInput)) {
                throw new NumberFormatException("That is not a number.");
            }

            int deckIndex = Integer.parseInt(enterInput) - 1;
            if (!(deckIndex >= 0 && deckIndex < deckManager.getDecks().size())) {
                throw new DeckNotExistException("That deck doesn't exist.");
            }

            Deck currDeck = deckManager.getDeck(deckIndex);
            this.innerParser.setCurrDeck(currDeck);
            this.innerParser.setDeckManager(deckManager);
            result = new CommandResult("You are now in deck " + enterInput
                    + ". Type \"help\" for more commands.", false, true);
        } catch (NumberFormatException | DeckNotExistException e) {
            result = new CommandResult(e.getMessage());
        }

        return result;
    }
}
