package seedu.duke.flashcard;

import seedu.duke.exceptions.DeckNotExistException;

import java.util.ArrayList;

public class DeckManager {

    private final ArrayList<Deck> decks;

    public DeckManager() {
        this.decks = new ArrayList<>();
    }

    public DeckManager(ArrayList<Deck> decks) {
        this.decks = decks;
    }


    public String editDeck(String[] args) {
        decks.get(Integer.parseInt(args[0]) - 1).setDeckName(args[1]);
        return ("Changed deck " + args[0] + " to " + args[1]);
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
        throw new IndexOutOfBoundsException("This deck does not exist.");
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
            if (deck.getName().trim().equals(categoryName.trim())) {
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
            throw new DeckNotExistException("This deck does not exist");
        }
        return message;
    }

    public String deleteDeck(int deckIndex) {
        String message = returnDeletedDeckMessage(decks.get(deckIndex));
        decks.remove(deckIndex);
        return message;
    }

    /**
     * Deletes the deck given by the deck name.
     * The deck will only be deleted if the name matches
     * exactly with the name of the deck. If there are
     * multiple decks with the same name, only the first matching
     * one will be deleted.
     *
     * @param deckName name of the deck to delete
     * @return delete message
     */
    public String deleteDeck(String deckName) throws DeckNotExistException {
        for (Deck deck : decks) {
            if (deck.getName().equals(deckName)) {
                String message = returnDeletedDeckMessage(deck);
                decks.remove(deck);
                return message;
            }
        }
        throw new DeckNotExistException("This deck does not exist");
    }

    private String returnDeletedDeckMessage(Deck deck) {
        String result = "\tDeleted deck:";
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
            result = "There are no decks." + System.lineSeparator();
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
}