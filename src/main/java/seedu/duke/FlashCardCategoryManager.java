package seedu.duke;

import seedu.duke.exceptions.DeckNotExistException;
import seedu.duke.exceptions.NoSlashException;

import java.util.ArrayList;

public class FlashCardCategoryManager {
    private static ArrayList<FlashCardCategory> decks = new ArrayList<>();

    public static void prepareToAddCategory(String categoryName) {
        if (!categoryExists(categoryName)) {
            addCategory(categoryName);
            printNewCategory(categoryName);
        } else {
            System.out.println("The category you are trying to create already exists.");
        }
    }

    private static void printNewCategory(String categoryName) {
        System.out.println("You have just made the category <<" + categoryName + ">>.");
    }

    private static boolean categoryExists(String categoryName) {
        for (FlashCardCategory fcc : decks) {
            if (fcc.getName().trim().equals(categoryName.trim())) {
                return true;
            }
        }
        return false;
    }

    private static void addCategory(String categoryName) {
        decks.add(new FlashCardCategory(categoryName, new FlashCardManager()));
    }

    public static void viewCategories() {
        int i = 1;
        System.out.println("These are your decks: ");
        for (FlashCardCategory fcc : decks) {
            System.out.println(i + ". " + fcc.getName());
            i += 1;
        }
    }

    public static void viewOneCategory(String input) {
        int deckIndex = Integer.parseInt(input) - 1;
        System.out.println("Viewing deck " + decks.get(deckIndex).getName() + " :");
        decks.get(deckIndex).getManager().viewAllFlashCards();
    }

    public static void prepareToAddCardToDeck(String input) {
        try {
            int deckNumber = findDeckIndex(input, "/car");
            if (deckNumber < decks.size() && deckNumber >= 0) {
                String addInput = trimToPass(input);
                System.out.println("Added to deck " + decks.get(deckNumber).getName() + " :");
                decks.get(deckNumber).getManager().prepareToAddFlashCard(addInput);
            } else {
                throw new DeckNotExistException();
            }
        } catch (NumberFormatException e) {
            System.out.println("That's not a number.");
        } catch (DeckNotExistException e) {
            System.out.println("That deck doesn't exist.");
        } catch (NoSlashException e) {
            System.out.println("Add command needs to contain the index of the deck you wish to "
                    + "add the card to, followed by \"/car\".");
        }
    }

    public static String trimToPass(String input) {
        int splitIndex = input.indexOf("/car");
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
