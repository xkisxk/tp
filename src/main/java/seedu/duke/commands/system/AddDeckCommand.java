package seedu.duke.commands.system;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.flashcard.DeckManager;
import seedu.duke.parser.system.AddDeckParser;

public class AddDeckCommand extends Command {

    private AddDeckParser parser;
    private DeckManager deckManager;

    public AddDeckCommand(String arguments, DeckManager deckManager) {
        super("AddDeckCommand", arguments);
        this.parser = new AddDeckParser();
        this.deckManager = deckManager;
    }

    @Override
    public CommandResult execute() {
        String[] parameters = parser.parseArguments(super.arguments);
        String deckName = parameters[0];
        deckManager.prepareToAddDeck(deckName);
        return null; // TODO: change prepareToAddDeck so it returns a String or throws an error
    }
}
