package seedu.cardli.commands.deck;

import seedu.cardli.commands.Command;
import seedu.cardli.commands.CommandResult;
import seedu.cardli.flashcard.Deck;

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
