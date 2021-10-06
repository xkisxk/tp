package seedu.duke;

import seedu.duke.exceptions.FieldEmptyException;
import seedu.duke.exceptions.NoSlashException;

import java.util.ArrayList;

/**
 * Implements the list of added flashcards.
 */
public class FlashCardManager {
    public static ArrayList<FlashCard> cards = new ArrayList<FlashCard>();
    private static int cardCount = 0;

    public static void printNoSlashFoundError() {
        System.out.println("\tRemember that a command must contain \"/def\"!");
    }

    public static void printFieldEmptyError() {
        System.out.println("\tRemember that both sides of the flashcard must be filled in!");
    }

    private static void printInvalidAddFormat() {
        System.out.println("\tHey, the command you printed is invalid.");
        System.out.println("\tThe correct command format to add a flash card is as follows:");
        System.out.println("\tadd <word on front> /def <word on back>");
    }

    public static void prepareToAddFlashCard(String input) {
        try {
            String[] flashCardWords = trimStrings(input);
            addFlashCard(flashCardWords[0], flashCardWords[1]);
            printNewFlashCard(flashCardWords[0], flashCardWords[1]);
        } catch (NoSlashException e) {
            printInvalidAddFormat();
            printNoSlashFoundError();
        } catch (FieldEmptyException e) {
            printInvalidAddFormat();
            printFieldEmptyError();
        }
    }

    public static String[] trimStrings(String input) throws FieldEmptyException, NoSlashException {
        int slashIndex = input.indexOf("/def");
        String[] flashCardWords = new String[2];
        if (slashIndex < 3) {
            throw new NoSlashException();
        }
        flashCardWords[0] = input.substring(3, slashIndex - 1).trim();
        flashCardWords[1] = input.substring(slashIndex + 4).trim();
        if (flashCardWords[0].isEmpty() || flashCardWords[1].isEmpty()) {
            throw new FieldEmptyException();
        }
        return flashCardWords;
    }

    public static void addFlashCard(String front, String back) {
        cards.add(new FlashCard(front, back));
        cardCount += 1;
    }

    private static void printNewFlashCard(String front, String back) {
        System.out.println("\tAdded card:");
        System.out.println("\tFront: " + front);
        System.out.println("\tBack: " + back);
        if (cardCount == 1) {
            System.out.println("\tYou have " + cardCount + " card in your card deck.");
        } else {
            System.out.println("\tYou have " + cardCount + " cards in your card deck.");
        }
    }

    //getter for index of a flashcard
    public static int getCardIndex(FlashCard card) {
        return cards.indexOf(card);
    }

    /**
     * Returns the String on the front of the flashCard
     */
    public static String getFrontOfCard(int cardIndex) {
        return cards.get(cardIndex).getFront();
    }

    /**
     * Returns the String on the back of the flashCard
     */
    public static String getBackOfCard(int cardIndex) {
        return cards.get(cardIndex).getBack();
    }

}
