package seedu.duke.flashcard;

import seedu.duke.exceptions.DeckNotExistException;

import seedu.duke.exceptions.NoSlashException;


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

    private static ArrayList<Deck> decks = new ArrayList<>();

    public static void editCard(String[] args) {
        if (args[2].equalsIgnoreCase("front")) {
            decks.get(Integer.parseInt(args[0]) - 1).getCard(Integer.parseInt(args[1]) - 1).setFront(args[3]);
        } else {
            decks.get(Integer.parseInt(args[0]) - 1).getCard(Integer.parseInt(args[1]) - 1).setBack(args[3]);
        }
        System.out.println("Changed " + args[2] + " of card " + args[1] + " of deck " + args[0] + " to " + args[3]);
    }


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

    public static ArrayList<Deck> getDecks() {
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


    public static void saveToFile() {
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

    public static void readFromFile() {
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