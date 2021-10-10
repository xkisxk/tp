package seedu.duke;

import seedu.duke.exceptions.CardLiException;
import seedu.duke.exceptions.FieldEmptyException;
import seedu.duke.exceptions.NoSlashException;

import java.util.ArrayList;

/**
 * Implements the list of added flashcards.
 */
public class FlashCardManager {
    public static ArrayList<FlashCard> cards = new ArrayList<FlashCard>();

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

    private static void printDoesNotExistError() {
        System.out.println("\tThe card you are trying to delete does not exist.");
    }

    private static void printEmptyDescriptionError() {
        System.out.println("\tCan't delete a card with no description!");
    }

    private static void printNewFlashCard(String front, String back) {
        System.out.println("\tAdded card:");
        printCardInfo(front, back);
    }

    private static void printCardInfo(String front, String back) {
        assert cards.size() > 0;
        System.out.println("\tFront: " + front);
        System.out.println("\tBack: " + back);
        if (cards.size() == 1) {
            System.out.println("\tYou have " + cards.size() + " card in your card deck.");
        } else {
            System.out.println("\tYou have " + cards.size() + " cards in your card deck.");
        }
    }

    private static void printDeletedFlashCardMessage(String front, String back) {
        System.out.println("\tDeleted card:");
        printCardInfo(front, back);
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

    /**
     * Deletes the flash card given by the user's input.
     * The card will only be deleted if the input matches
     * exactly with FlashCard.front.
     *
     * @param input user's input in its entirety
     */
    public static void prepareToDeleteFlashCard(String input) {
        try {
            String description = getDescription(input);
            deleteFlashCard(description);
        } catch (FieldEmptyException e) {
            printEmptyDescriptionError();
        } catch (CardLiException e) {
            printDoesNotExistError();
        }
    }


    // TODO find elegant implementation of delete using index

    /**
     * Returns all contents of the input after the command word.
     *
     * @param input user's input
     * @return description of card
     * @throws FieldEmptyException if description is empty
     */
    public static String getDescription(String input) throws FieldEmptyException {
        String line = input.substring(6).trim();
        if (line.isEmpty()) {
            throw new FieldEmptyException();
        }
        return line;
    }

    /**
     * Deletes the flashcard with the given input.
     *
     * @param input description of the card to delete
     * @throws CardLiException if card does not exist
     */
    public static void deleteFlashCard(String input) throws CardLiException {
        if (cards.size() == 0) {
            throw new CardLiException();
        }
        if (!isInteger(input)) {
            deleteFlashCardByDescription(input);
        } else {
            deleteFlashCardByIndex(input);
        }
    }

    /**
     * Deletes the flashcard with the given index.
     *
     * @param index user's input (index of the card to be deleted)
     * @throws CardLiException if the index of the card exceeds the number of flashcards in cards
     * or index of card is less than 1
     */
    private static void deleteFlashCardByIndex(String index) throws CardLiException {
        int indexToBeRemoved = Integer.parseInt(index) - 1;
        if (indexToBeRemoved >= 0 && indexToBeRemoved < cards.size()) {
            FlashCard card = cards.get(indexToBeRemoved);
            cards.remove(card);
            printDeletedFlashCardMessage(card.getFront(), card.getBack());
        } else {
            throw new CardLiException();
        }
    }

    /**
     * Deletes the flashcard with the given description.
     *
     * @param description user's input (front of the card to be deleted)
     * @throws CardLiException if none of the front of the cards match the description input by user
     */
    private static void deleteFlashCardByDescription(String description) throws CardLiException {
        for (int i = 0; i < cards.size(); i++) {
            FlashCard card = cards.get(i);
            if (hasExactCard(description, card)) {
                cards.remove(card);
                printDeletedFlashCardMessage(card.getFront(), card.getBack());
                return;
            }
        }
        throw new CardLiException();
    }

    private static boolean hasExactCard(String query, FlashCard card) {
        return card.getFront().equalsIgnoreCase(query);
    }

    /**
     * Checks if the given input is an integer or not.
     *
     * @param input input given by user
     * @return true if input is an integer, false otherwise
     */
    private static boolean isInteger(String input) {
        for (int i = 0; i < input.length(); i += 1) {
            if (!Character.isDigit(input.charAt(i))) {
                return false;
            }
        }
        return true;
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
    }

    //getter for index of a flashcard
    public static int getCardIndex(FlashCard card) {
        return cards.indexOf(card);
    }

    public static void viewAFlashCard(int cardIndex) {
        System.out.println("*================FRONT================* "
                + "*===============BACK==================*");
        System.out.println();

        String front = cards.get(cardIndex).getFront();
        String frontSpaces = "";
        for (int i = 0; i < (39 - front.length()) / 2; i++) {
            frontSpaces += " ";
        }

        String back = cards.get(cardIndex).getBack();
        String backSpaces = "";
        for (int i = 0; i < (39 - back.length()) / 2; i++) {
            backSpaces += " ";
        }

        System.out.println(frontSpaces + front + frontSpaces + backSpaces + back);
        System.out.println();
        System.out.println("*=====================================* "
                + "*=====================================*");
    }

    public static void viewAllFlashCards() {
        for (int i = 0; i < cards.size(); i++) {
            System.out.println("Card " + (i + 1) + ":");
            viewAFlashCard(i);
        }
    }

    /**
     * Returns the String on the front of the flashCard.
     */
    public static String getFrontOfCard(int cardIndex) {
        return cards.get(cardIndex).getFront();
    }

    /**
     * Returns the String on the back of the flashCard.
     */
    public static String getBackOfCard(int cardIndex) {
        return cards.get(cardIndex).getBack();
    }
}
