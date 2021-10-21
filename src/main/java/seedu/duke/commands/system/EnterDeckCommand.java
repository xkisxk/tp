package seedu.duke.commands.system;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.exceptions.DeckNotExistException;
import seedu.duke.flashcard.Deck;
import seedu.duke.flashcard.DeckManager;
import seedu.duke.parser.InnerParser;
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
        String[] parameters = parser.parseArguments(super.arguments);
        String enterInput = parameters[0];
        int deckIndex = Integer.parseInt(enterInput) - 1;
        Deck currDeck;
        CommandResult result;

        try {
            if (!(deckIndex >= 0 && deckIndex < deckManager.getDecks().size())) {
                throw new DeckNotExistException("That deck doesn't exist.");
            }
            currDeck = deckManager.getDeck(deckIndex);
            this.innerParser.setCurrDeck(currDeck);
            result = new CommandResult("You are now in deck " + enterInput + ". Type \"help\"for more commands.",
                    false, true);
        } catch (NumberFormatException | DeckNotExistException e) {
            result = new CommandResult(e.getMessage());
        }

        return result;
    }
}
