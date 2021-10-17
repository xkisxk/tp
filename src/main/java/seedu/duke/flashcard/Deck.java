package seedu.duke.flashcard;

import seedu.duke.testing.TestManager;
import seedu.duke.exceptions.CardLiException;
import seedu.duke.exceptions.DeckNotExistException;
import seedu.duke.exceptions.FieldEmptyException;
import seedu.duke.exceptions.NoSlashException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implements the list of added flashcards.
 */
public class Deck {

    public ArrayList<FlashCard> cards = new ArrayList<FlashCard>();
    private String name;
    private static final Logger logger = Logger.getLogger(Deck.class.getName());

    public Deck(String name) {
        this.name = name;
    }

    public Deck() {
        this.name = "Untitled";
    }

    public String getName() {
        return name;
    }

    public void printNoSlashFoundError() {
        System.out.println("\tRemember that a command must contain \"/bac\"!");
    }

    public void printFieldEmptyError() {
        System.out.println("\tRemember that both sides of the flashcard must be filled in!");
    }

    private void printInvalidAddFormat() {
        System.out.println("\tHey, the command you printed is invalid.");
        System.out.println("\tThe correct command format to add a flash card is as follows:");
        System.out.println("\tadd <deck index> /fro <word on front> /bac <word on back>");
    }

    private void printDoesNotExistError() {
        System.out.println("\tThe card you are trying to delete does not exist.");
    }

    private void printEmptyDescriptionError() {
        System.out.println("\tCan't delete a card with no description!");
    }

    private void printNewFlashCard(String front, String back) {
        //System.out.println("\tAdded card:");
        printCardInfo(front, back);
    }

    private void printCardInfo(String front, String back) {
        System.out.println("\tFront: " + front);
        System.out.println("\tBack: " + back);
        if (cards.size() == 1) {
            System.out.println("\tYou have " + cards.size() + " card in your card deck.");
        } else {
            System.out.println("\tYou have " + cards.size() + " cards in your card deck.");
        }
    }

    private void printDeletedFlashCardMessage(String front, String back) {
        //System.out.println("\tDeleted card:");
        printCardInfo(front, back);
    }

    public void prepareToAddFlashCard(String input) {
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
    public void prepareToDeleteFlashCard(String input) {
        logger.entering(Deck.class.getName(), "prepareToDeleteFlashCard");
        logger.setLevel(Level.WARNING);
        logger.log(Level.INFO, "Starting delete process");
        try {
            deleteFlashCard(input);
        } catch (ArrayIndexOutOfBoundsException e) {
            printEmptyDescriptionError();
            logger.log(Level.SEVERE, "Empty field error, no description found after command term");
        } catch (CardLiException e) {
            printDoesNotExistError();
            logger.log(Level.SEVERE, "CardLi error, query card does not exist");
        }
        logger.log(Level.INFO, "End of delete process");
        logger.exiting(Deck.class.getName(), "prepareToDeleteFlashCard");
    }

    /**
     * Deletes the flashcard with the given input.
     *
     * @param input description of the card to delete
     * @throws CardLiException if card does not exist
     */
    public void deleteFlashCard(String input) throws CardLiException {
        logger.setLevel(Level.WARNING);
        if (cards.isEmpty()) {
            throw new CardLiException();
        }
        assert cards.size() > 0 : "cards.size() should be greater than 0";
        logger.log(Level.INFO, "Detecting the type of input, ie word/phrase or index");
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
     *              or index of card is less than 1
     */
    private void deleteFlashCardByIndex(String index) throws CardLiException {
        logger.setLevel(Level.WARNING);
        int indexToBeRemoved = Integer.parseInt(index) - 1;
        if (!((indexToBeRemoved < cards.size()) && (indexToBeRemoved >= 0))) {
            throw new CardLiException();
        }
        assert cards.size() > 0 : "cards.size() should be greater than 0";
        logger.log(Level.INFO, "Detecting the type of input, ie word/phrase or index");

        FlashCard card = cards.get(indexToBeRemoved);
        cards.remove(card);
        printDeletedFlashCardMessage(card.getFront(), card.getBack());
    }

    /**
     * Deletes the flashcard with the given description.
     *
     * @param description user's input (front of the card to be deleted)
     * @throws CardLiException if none of the front of the cards match the description input by user
     */
    private void deleteFlashCardByDescription(String description) throws CardLiException {
        assert cards.size() > 0 : "cards.size() should be greater than 0";
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

    private boolean hasExactCard(String query, FlashCard card) {
        return card.getFront().equalsIgnoreCase(query);
    }

    /**
     * Checks if the given input is an integer or not.
     *
     * @param input input given by user
     * @return true if input is an integer, false otherwise
     */
    private boolean isInteger(String input) {
        for (int i = 0; i < input.length(); i += 1) {
            if (!Character.isDigit(input.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public String[] trimStrings(String input) throws FieldEmptyException, NoSlashException {
        int slashIndex = input.indexOf("/bac");
        String[] flashCardWords = new String[2];
        if (slashIndex < 3) {
            throw new NoSlashException();
        }
        flashCardWords[0] = input.substring(0, slashIndex - 1).trim();
        flashCardWords[1] = input.substring(slashIndex + 4).trim();
        if (flashCardWords[0].isEmpty() || flashCardWords[1].isEmpty()) {
            throw new FieldEmptyException();
        }
        return flashCardWords;
    }

    public void addFlashCard(String front, String back) {
        cards.add(new FlashCard(front, back));
    }

    //getter for index of a flashcard
    public int getCardIndex(FlashCard card) {
        return cards.indexOf(card);
    }

    public void viewAFlashCard(int cardIndex) {
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

    public void viewAllFlashCards() {
        if (cards.size() > 0) {
            for (int i = 0; i < cards.size(); i++) {
                System.out.println("Card " + (i + 1) + ":");
                viewAFlashCard(i);
            }
        } else {
            System.out.println("This deck has no cards.");
        }
    }

    /**
     * Returns the String on the front of the flashCard.
     */
    public String getFrontOfCard(int cardIndex) {
        return cards.get(cardIndex).getFront();
    }

    /**
     * Returns the String on the back of the flashCard.
     */
    public String getBackOfCard(int cardIndex) {
        return cards.get(cardIndex).getBack();
    }

    public static class DeckList {
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
                    Deck fcmToView = decks.get(deckIndex);
                    fcmToView.viewAllFlashCards();
                } else {
                    throw new DeckNotExistException();
                }
            } catch (DeckNotExistException e) {
                System.out.println("This deck doesn't exist.");
            }
        }

        public static void testDeck(String input) {
            try {
                int deckIndex = Integer.parseInt(input) - 1;
                if (deckIndex < decks.size() && deckIndex >= 0) {
                    Deck fcm = decks.get(deckIndex);
                    if (fcm.cards.size() > 0) {
                        System.out.println("Testing deck " + decks.get(deckIndex).getName() + ":");
                        TestManager.testAllCardsInOrder(fcm);
                    } else {
                        System.out.println("This deck has no cards and cannot be tested.");
                    }
                } else {
                    throw new DeckNotExistException();
                }
            } catch (DeckNotExistException e) {
                System.out.println("This deck doesn't exist.");
            }
        }

        public static void prepareToAddCardToDeck(String input) {
            try {
                int deckIndex = findDeckIndex(input, "/fro");
                if (deckIndex < decks.size() && deckIndex >= 0) {
                    String addInput = trimToPass(input, "/fro");
                    System.out.println("Added to deck " + decks.get(deckIndex).getName() + ":");
                    Deck fcmToAdd = decks.get(deckIndex);
                    fcmToAdd.prepareToAddFlashCard(addInput);
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
}
