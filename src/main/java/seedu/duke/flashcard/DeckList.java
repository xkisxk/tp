package seedu.duke.flashcard;

import seedu.duke.exceptions.DeckNotExistException;

import java.util.ArrayList;

public class DeckList {
    public static ArrayList<Deck> decks = new ArrayList<>();

    public static void editCat(String[] args) {
        decks.get(Integer.parseInt(args[0]) - 1).setDeckName(args[1]);
        System.out.println("Changed deck " + args[0] + " to " + args[1]);
    }

    public static Deck getDeck(int index) {
        assert getDecksSize() > 0;
        assert (index >= 0 && index < getDecksSize());
        return decks.get(index);
    }

    public static int getDecksSize() {
        return decks.size();
    }

    public static void prepareToAddDeck(String deckName) {
        if (!hasDeck(deckName)) {
            addDeck(deckName);
            printNewDeck(deckName);
        } else {
            System.out.println("The category you are trying to create already exists.");
        }
    }

    private static void printNewDeck(String deckName) {
        System.out.println("You have just made the deck <<" + deckName + ">>.");
    }

    private static boolean hasDeck(String categoryName) {
        for (Deck deck : decks) {
            if (deck.getName().trim().equals(categoryName.trim())) {
                return true;
            }
        }
        return false;
    }

    private static void addDeck(String deckName) {
        decks.add(new Deck(deckName));
    }

    public static ArrayList<Deck> getDeckList() {
        return decks;
    }

    public static void viewDecks() {
        if (getDecksSize() > 0) {
            int i = 1;
            System.out.println("These are your decks: ");
            for (Deck deck : decks) {
                System.out.println(i + ". " + deck.getName());
                i += 1;
            }
        } else {
            System.out.println("You have no decks.");
        }
    }

    public static void viewOneDeck(String input) {
        try {
            int deckIndex = Integer.parseInt(input) - 1;
            if (deckIndex < getDecksSize() && deckIndex >= 0) {
                System.out.println("Viewing deck " + decks.get(deckIndex).getName() + " :");
                Deck deckToView = decks.get(deckIndex);
                deckToView.viewAllFlashCards();
            } else {
                throw new DeckNotExistException();
            }
        } catch (DeckNotExistException e) {
            System.out.println("This deck doesn't exist.");
        } catch (NumberFormatException e) {
            System.out.println("That's not a number.");
        }
    }

}