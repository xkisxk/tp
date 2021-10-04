package seedu.duke;

import seedu.duke.exceptions.CardLiException;
import seedu.duke.exceptions.FieldEmptyException;
import seedu.duke.exceptions.NoSlashException;

import java.util.ArrayList;

public class FlashCardManager {
    private static ArrayList<FlashCard> cards = new ArrayList<FlashCard>();
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

    private static void printDoesNotExistError() {
        System.out.println("\tThe card you are trying to delete does not exist.");
    }

    private static void printEmptyDescriptionError() {
        System.out.println("\tCan't delete a card with no description!");
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

    private static void printDeletedFlashCardMessage(String front, String back) {
        System.out.println("\tDeleted card:");
        System.out.println("\tFront: " + front);
        System.out.println("\tBack: " + back);
        if (cardCount == 1) {
            System.out.println("\tYou have " + cardCount + " card in your card deck.");
        } else {
            System.out.println("\tYou have " + cardCount + " cards in your card deck.");
        }
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

    /**
     * Returns the description, which is anything after the command word.
     *
     * @param input                user's input
     * @return                     description of card
     * @throws FieldEmptyException if description is empty
     */
    private static String getDescription(String input) throws FieldEmptyException {
        String[] line = input.split(" ");
        if (line.length < 2) {
            throw new FieldEmptyException();
        }
        return line[1];
    }

    /**
     * Deletes the flashcard with the given description.
     *
     * @param description       description of the card to delete
     * @throws CardLiException  if card does not exist
     */
    public static void deleteFlashCard(String description) throws CardLiException {
        if (cards.size() == 0) {
            throw new CardLiException();
        }
        for (int i = 0; i < cards.size(); i++) {
            FlashCard card = cards.get(i);
            if (hasExactCard(description, card)) {
                cards.remove(card);
                cardCount--;
                printDeletedFlashCardMessage(card.getFront(), card.getBack());
                return;
            }
        }
        throw new CardLiException();
    }

    private static boolean hasExactCard(String query, FlashCard card) {
        return card.getFront().equalsIgnoreCase(query);
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

}
