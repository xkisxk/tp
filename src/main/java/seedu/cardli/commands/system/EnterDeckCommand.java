package seedu.cardli.commands.system;

import seedu.cardli.commands.Command;
import seedu.cardli.commands.CommandResult;
import seedu.cardli.exceptions.CardLiException;
import seedu.cardli.exceptions.DeckNotExistException;
import seedu.cardli.flashcard.Deck;
import seedu.cardli.flashcard.DeckManager;
import seedu.cardli.parser.InnerParser;
import seedu.cardli.parser.Parser;
import seedu.cardli.parser.system.EnterDeckParser;

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

            if (enterInput.isEmpty()) {
                throw new CardLiException("Invalid input. Please input deck index after \"enter\".");
            }

            if (!Parser.isInteger(enterInput)) {
                throw new NumberFormatException("That is not a number.");
            }

            int deckIndex = Integer.parseInt(enterInput) - 1;

            if (deckIndex < 0) {
                throw new DeckNotExistException("Invalid deck index. Please input a positive integer.");
            }

            if (deckIndex >= deckManager.getDecks().size()) {
                throw new DeckNotExistException("That deck doesn't exist. Please input a valid deck index.");
            }



            Deck currDeck = deckManager.getDeck(deckIndex);
            this.innerParser.setCurrDeck(currDeck);
            this.innerParser.setDeckManager(deckManager);
            result = new CommandResult("You are now in deck " + enterInput
                    + ". Type \"help\" for more commands.", false, true);
        } catch (NumberFormatException | CardLiException e) {
            result = new CommandResult(e.getMessage());
        }

        return result;
    }
}
