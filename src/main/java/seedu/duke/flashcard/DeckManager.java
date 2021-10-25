package seedu.duke.flashcard;

import seedu.duke.exceptions.DeckNotExistException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DeckManager {

    /**
     * Specified file path to save task list.
     */
    static final String FILEPATH = "data/CardLI.txt";

    private final ArrayList<Deck> decks;

    public DeckManager() {
        this.decks = new ArrayList<>();
    }

    public DeckManager(ArrayList<Deck> decks) {
        this.decks = decks;
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

    public void saveToFile() {
        try {
            File file = new File(FILEPATH);

            // create new directory and file if they do not exist
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            // instantiate FileWriter object to overwrite specified text file
            FileWriter fileWriter = new FileWriter(FILEPATH, false);

            int decksCount = decks.size();
            fileWriter.write(Integer.toString(decksCount) + '\n');

            for (int i = 0; i < decksCount; i++) {
                fileWriter.write(decks.get(i).toString());
            }

            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Something went wrong while saving the flashcards to file...");
        }
    }

    public void readFromFile() {
        try {
            File file = new File(FILEPATH);

            // instantiate scanner to read file contents
            Scanner s = new Scanner(file);

            int decksCount = Integer.parseInt(s.nextLine());

            for (int i = 0; i < decksCount; i++) {
                String deckName = s.nextLine();
                Deck newDeck = new Deck(deckName);

                int cardsCount = Integer.parseInt(s.nextLine());

                for (int j = 0; j < cardsCount; j++) {
                    String newLine = s.nextLine();
                    String[] newLineArgs = newLine.split(" \\| ");
                    newDeck.addFlashCard(newLineArgs[0], newLineArgs[1],
                            Integer.parseInt(newLineArgs[2]),
                            Integer.parseInt(newLineArgs[3]));
                }

                decks.add(newDeck);
            }
        } catch (FileNotFoundException e) { // file does not exist on first boot
            return;
        }
    }

}