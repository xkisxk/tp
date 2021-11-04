package seedu.cardli.commands.deck;

import seedu.cardli.commands.Command;
import seedu.cardli.commands.CommandResult;
import seedu.cardli.flashcard.Deck;

public class ViewCardsCommand extends Command {

    private Deck deck;

    public ViewCardsCommand(Deck deck, String arguments) {
        super("ViewCardCommand", arguments);
        this.deck = deck;
    }

    @Override
    public CommandResult execute() {
        if (arguments.length() > 0) {
            return new CommandResult("There should not be any arguments.");
        }
        CommandResult result = new CommandResult(this.deck.returnAllFlashCards());
        return result;
    }
}
