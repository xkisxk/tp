package seedu.duke.commands.system;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.exceptions.FieldEmptyException;
import seedu.duke.flashcard.DeckManager;
import seedu.duke.parser.OuterParser;
import seedu.duke.parser.system.FindCardsParser;

public class FindCardsCommand extends Command {

    private FindCardsParser parser;
    private DeckManager deckManager;


    public FindCardsCommand(String arguments, DeckManager deckManager) {
        super("FindCardsCommand", arguments);
        this.deckManager = deckManager;
        this.parser = new FindCardsParser();
    }

    @Override
    public CommandResult execute() {
        CommandResult result;
        try {
            String[] parameters = parser.parseArguments(super.arguments);
            String findInput = parameters[0];
            if (findInput.isEmpty()) {
                throw new FieldEmptyException("You did not input a search term.");
            }
            result = new CommandResult(deckManager.findCards(findInput));
        } catch (FieldEmptyException e) {
            result = new CommandResult(e.getMessage());
        }
        return result;
    }

}
