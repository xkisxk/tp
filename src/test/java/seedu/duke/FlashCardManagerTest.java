package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.CardLiException;
import seedu.duke.exceptions.FieldEmptyException;
import seedu.duke.exceptions.NoSlashException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FlashCardManagerTest {

    @Test
    public void trimStrings_hasFrontHasBack_expectTwoParts() throws NoSlashException, FieldEmptyException {
        String input = "add good morning /def ohayou";
        assertEquals(2, FlashCardManager.trimStrings(input).length);
    }

    @Test
    public void trimStrings_hasNoSlash_expectNoSlashException() {
        String input = "add good morning ohayou";
        assertThrows(NoSlashException.class, () -> FlashCardManager.trimStrings(input));
    }

    @Test
    public void trimStrings_emptyFront_expectFieldEmptyException() {
        String input = "add /def ohayou";
        assertThrows(FieldEmptyException.class, () -> FlashCardManager.trimStrings(input));
    }

    @Test
    public void trimStrings_emptyBack_expectFieldEmptyException() {
        String input = "add good morning /def";
        assertThrows(FieldEmptyException.class, () -> FlashCardManager.trimStrings(input));
    }

    @Test
    public void getDescription_noDescription_ArrayIndexOutOfBoundsException() {
        String input = "delete";
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> Parser.getDescription(input));
    }

    @Test
    public void deleteFlashCard_cardNotExist_expectCardLiException() {
        String input = "delete abcdef";
        assertThrows(CardLiException.class, () -> FlashCardManager.deleteFlashCard(input));
    }

    @Test
    public void deleteFlashCard_provideDescription_expectDelete() throws CardLiException {
        String firstCard = "add illness /def byouki";
        String secondCard = "add to lose /def nakushimasu";
        FlashCardManager.prepareToAddFlashCard(firstCard);
        FlashCardManager.prepareToAddFlashCard(secondCard);
        FlashCardManager.deleteFlashCard("illness");
        FlashCardManager.viewAllFlashCards();
        assertEquals(1, FlashCardManager.cards.size());
        FlashCardManager.deleteFlashCard("to lose");
    }

    @Test
    public void deleteFlashCard_provideIndex_expectDelete() throws CardLiException {
        String firstCard = "add illness /def byouki";
        String secondCard = "add to lose /def nakushimasu";
        FlashCardManager.prepareToAddFlashCard(firstCard);
        FlashCardManager.prepareToAddFlashCard(secondCard);
        FlashCardManager.deleteFlashCard("1");
        FlashCardManager.viewAllFlashCards();
        assertEquals(1, FlashCardManager.cards.size());
        FlashCardManager.deleteFlashCard("1");
    }

    @Test
    public void deleteFlashCard_provideInvalidIndex_expectCardLiException() throws CardLiException {
        String firstCard = "add illness /def byouki";
        String secondCard = "add to lose /def nakushimasu";
        FlashCardManager.prepareToAddFlashCard(firstCard);
        FlashCardManager.prepareToAddFlashCard(secondCard);
        FlashCardManager.viewAllFlashCards();
        assertThrows(CardLiException.class, () -> FlashCardManager.deleteFlashCard("6"));
        FlashCardManager.deleteFlashCard("1");
        FlashCardManager.deleteFlashCard("1");
    }

    @Test
    public void deleteFlashCard_provideNegativeIndex_expectCardLiException() throws CardLiException {
        String firstCard = "add illness /def byouki";
        String secondCard = "add to lose /def nakushimasu";
        FlashCardManager.prepareToAddFlashCard(firstCard);
        FlashCardManager.prepareToAddFlashCard(secondCard);
        FlashCardManager.viewAllFlashCards();
        assertThrows(CardLiException.class, () -> FlashCardManager.deleteFlashCard("0"));
        FlashCardManager.deleteFlashCard("1");
        FlashCardManager.deleteFlashCard("1");
    }
}