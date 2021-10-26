package seedu.duke.commands.deck;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.flashcard.Deck;

public class ViewCardsCommand extends Command {

    private Deck deck;

    public ViewCardsCommand(Deck deck) {
        super("ViewCardCommand");
        this.deck = deck;
    }

    @Override
    public CommandResult execute() {
        CommandResult result = new CommandResult(this.deck.returnAllFlashCards());
        return result;
    }
}
