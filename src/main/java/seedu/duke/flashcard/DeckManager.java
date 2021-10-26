package seedu.duke.flashcard;

import seedu.duke.exceptions.DeckNotExistException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeckManager {

    /**
     * Specified file path to save task list.
     */
    static final String FILEPATH = "data/CardLI.txt";

    private final ArrayList<Deck> decks;
    private static final Logger logger = Logger.getLogger(Deck.class.getName());

    public DeckManager() {
        this.decks = new ArrayList<>();
    }

    public DeckManager(ArrayList<Deck> decks) {
        this.decks = decks;
    }

    public void editCard(String[] args) {
        if (args[2].equalsIgnoreCase("front")) {
            decks.get(Integer.parseInt(args[0]) - 1).getCard(Integer.parseInt(args[1]) - 1).setFront(args[3]);
        } else {
            decks.get(Integer.parseInt(args[0]) - 1).getCard(Integer.parseInt(args[1]) - 1).setBack(args[3]);
        }
        System.out.println("Changed " + args[2] + " of card " + args[1] + " of deck " + args[0] + " to " + args[3]);
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
        throw new IndexOutOfBoundsException("This deck does not exist.");
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