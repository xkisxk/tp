package seedu.duke.flashcard;

import seedu.duke.exceptions.DeckNotExistException;
import seedu.duke.exceptions.NoSlashException;
import seedu.duke.testing.TestManager;

import java.util.ArrayList;

public class DeckList {
    private static ArrayList<Deck> decks = new ArrayList<>();

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
        if (decks.size() > 0) {
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
            if (deckIndex < decks.size() && deckIndex >= 0) {
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

    public static void prepareToAddCardToDeck(String input) {
        try {
            int deckIndex = findDeckIndex(input, "/fro");
            if (deckIndex < decks.size() && deckIndex >= 0) {
                String addInput = trimToPass(input, "/fro");
                System.out.println("Added to deck " + decks.get(deckIndex).getName() + ":");
                Deck deckToAdd = decks.get(deckIndex);
                deckToAdd.prepareToAddFlashCard(addInput);
            } else {
                throw new DeckNotExistException();
            }
        } catch (NumberFormatException e) {
            System.out.println("That's not a number.");
        } catch (DeckNotExistException e) {
            System.out.println("That deck doesn't exist.");
        } catch (NoSlashException e) {
            System.out.println("Incorrect format. The correct format is:");
            System.out.println("add <deckIndex> /fro <frontOfCard> /bac <backOfCard>");
        }
    }

    public static void prepareToDeleteCardFromDeck(String input) {
        try {
            int deckIndex = findDeckIndex(input, "/car");
            if (deckIndex < decks.size() && deckIndex >= 0) {
                String addInput = trimToPass(input, "/car");
                System.out.println("Deleted from deck " + decks.get(deckIndex).getName() + " :");
                decks.get(deckIndex).prepareToDeleteFlashCard(addInput);
            } else {
                throw new DeckNotExistException();
            }
        } catch (NumberFormatException e) {
            System.out.println("That's not a number.");
        } catch (DeckNotExistException e) {
            System.out.println("That deck doesn't exist.");
        } catch (NoSlashException e) {
            System.out.println("Wrong format. The correct format is:");
            System.out.println("delete <deckIndex> /car <indexOfCard/frontOfCard>");
        }
    }

    public static String trimToPass(String input, String toSplit) {
        int splitIndex = input.indexOf(toSplit);
        return input.substring(splitIndex + 4).trim();
    }

    private static int findDeckIndex(String input, String lookFor) throws NoSlashException {
        int splitIndex = input.indexOf(lookFor);
        if (splitIndex >= 2) {
            String intAsString = input.substring(0, splitIndex).trim();
            return Integer.parseInt(intAsString) - 1;
        } else {
            throw new NoSlashException();
        }
    }
}