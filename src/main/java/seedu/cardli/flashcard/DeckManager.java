package seedu.cardli.flashcard;


import seedu.cardli.exceptions.CardLiException;
import seedu.cardli.exceptions.DeckNotExistException;

import static seedu.cardli.ui.TestUi.DECK_NOT_EXIST_MESSAGE;


import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;


public class DeckManager {
    private final ArrayList<Deck> decks;
    private static final Logger logger = Logger.getLogger(Deck.class.getName());

    public DeckManager() {
        this.decks = new ArrayList<>();
    }

    public DeckManager(ArrayList<Deck> decks) {
        this.decks = decks;
    }


    public String moveCard(String[] parameters) throws CardLiException {
        String enteredCurrentDeckIndex = parameters[0];
        int currentDeckIndex = Integer.parseInt(enteredCurrentDeckIndex);
        String enteredCardIndex = parameters[1];
        int cardIndex = Integer.parseInt(enteredCardIndex) - 1;
        String enteredDeckIndex = parameters[2];
        int deckIndex = Integer.parseInt(enteredDeckIndex) - 1;

        //get card from current deck
        FlashCard cardCopy = decks.get(currentDeckIndex).getCard(cardIndex);
        //add card to destination deck
        decks.get(deckIndex).addFlashCard(cardCopy);
        //delete card from current deck
        decks.get(currentDeckIndex).deleteFlashCard(enteredCardIndex);

        return ("Moved card " + enteredCardIndex + " to " + "deck " + enteredDeckIndex);
    }


    public String editDeck(String[] args) {
        String enteredDeckIndex = args[0];
        int deckIndex = Integer.parseInt(enteredDeckIndex) - 1;
        String deckName = args[1];
        decks.get(deckIndex).setDeckName(deckName);
        return ("Changed deck " + enteredDeckIndex + " to " + deckName);
    }

    public int getDeckIndex(Deck deck) {
        return decks.indexOf(deck);
    }

    public Deck getDeck(int index) {
        assert getDecksSize() > 0;
        assert (index >= 0 && index < getDecksSize());
        return decks.get(index);
    }

    public Deck getTestDeck(int index) {
        if (index == -1) {
            Deck deckToTest = new Deck("Test");
            for (Deck deck : getDecks()) {
                for (FlashCard card : deck.getCards()) {
                    deckToTest.addFlashCard(card);
                }
            }
            return deckToTest;
        }
        if (hasDeck(index)) {
            return decks.get(index);
        }
        throw new IndexOutOfBoundsException(DECK_NOT_EXIST_MESSAGE);
    }

    public int getDecksSize() {
        return decks.size();
    }

    public String prepareToAddDeck(String deckName) {
        if (!hasDeck(deckName)) {
            addDeck(deckName);
            return printNewDeck(deckName);
        } else {
            return ("The category you are trying to create already exists.");
        }
    }

    private String printNewDeck(String deckName) {
        return ("You have just made the deck <<" + deckName + ">>.");
    }

    private boolean hasDeck(String categoryName) {
        for (Deck deck : decks) {
            if (deck.hasSameName(categoryName)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasDeck(int deckIndex) {
        return deckIndex >= 0 && deckIndex < getDecksSize();
    }

    private void addDeck(String deckName) {
        decks.add(new Deck(deckName));
    }

    public String deleteDeck(Deck deck) throws DeckNotExistException {
        String message = returnDeletedDeckMessage(deck);
        boolean isRemoved = decks.remove(deck);
        if (!isRemoved) {
            throw new DeckNotExistException(DECK_NOT_EXIST_MESSAGE);
        }
        return message;
    }

    public String deleteDeck(int deckIndex) {
        String message = returnDeletedDeckMessage(decks.get(deckIndex));
        decks.remove(deckIndex);
        return message;
    }

    private String returnDeletedDeckMessage(Deck deck) {
        String result = "\tDeleted deck: ";
        result = result.concat(deck.getName());
        return result;
    }

    public ArrayList<Deck> getDecks() {
        return decks;
    }

    public String findCards(String searchInput) {
        String result = "";
        if (decks.size() > 0) {
            for (int i = 0; i < decks.size(); i += 1) {
                result = result.concat(getDeck(i).returnMatchingFlashCards(searchInput));
            }
        } else {
            result = "There are no decks.";
        }

        if (result.isEmpty()) {
            result = "There are no cards matching the search term.";
        }
        return result;
    }

    public String viewDecks() {
        String result = "";
        if (getDecksSize() > 0) {
            int i = 1;
            result = result.concat("These are your decks: " + System.lineSeparator());
            for (Deck deck : decks) {
                result = result.concat("\t" + i + ". " + deck.getName()
                        + System.lineSeparator());
                i += 1;
            }
        } else {
            result = result.concat("You have no decks.");
        }
        return result;
    }


    /**
     * Gets all the low scoring cards and put them into a deck.
     *
     * @return deck of low scoring cards
     */
    private Deck getLowScoringCardsFromAllDecks() {
        logger.setLevel(Level.WARNING);
        logger.log(Level.INFO, "Collecting low scoring cards");
        Deck reviewDeck = new Deck("Review");
        for (Deck deck : getDecks()) {
            for (FlashCard card : deck.getCards()) {
                if (isLowScoring(card)) {
                    reviewDeck.addFlashCard(card);
                    logger.log(Level.INFO, "Added a low scoring card");
                }
            }
        }
        return reviewDeck;
    }

    /**
     * Gets all the low scoring cards from a deck and put them into a deck.
     *
     * @return deck of low scoring cards
     */
    private Deck getLowScoringCardsFromADeck(Deck deck) {
        logger.setLevel(Level.WARNING);
        logger.log(Level.INFO, "Collecting low scoring cards");
        Deck reviewDeck = new Deck("Review");
        for (FlashCard card : deck.getCards()) {
            if (isLowScoring(card)) {
                reviewDeck.addFlashCard(card);
                logger.log(Level.INFO, "Added a low scoring card");
            }
        }
        return reviewDeck;
    }

    /**
     * Gets all the low scoring cards and put them into a deck.
     * If index is -1, get low scaring cards from all decks.
     * Else get low scoring cards from the deck from that index.
     * The cards that are put into the deck are the same cards objects, in other
     * words they are not new FlashCard objects.
     *
     * @return deck of low scoring cards
     */
    public Deck getLowScoringCards(int index) {
        if (index == -1) {
            return getLowScoringCardsFromAllDecks();
        }
        if (hasDeck(index)) {
            return getLowScoringCardsFromADeck(getDeck(index));
        }
        throw new IndexOutOfBoundsException(DECK_NOT_EXIST_MESSAGE);
    }

    /**
     * A card is low scoring if its accumulated user score is less than 50% of
     * the total score.
     *
     * @return true if card is low scoring, false otherwise
     */
    private boolean isLowScoring(FlashCard card) {
        return (double) card.getUserScore() * 100 / card.getTotalScore() < 50;
    }
}
