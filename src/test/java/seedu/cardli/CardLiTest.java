package seedu.cardli;

import org.junit.jupiter.api.Test;
import seedu.cardli.exceptions.CardLiException;
import seedu.cardli.exceptions.FieldEmptyException;
import seedu.cardli.exceptions.NoSlashException;
import seedu.cardli.flashcard.Deck;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CardLiTest {


    @Test
    public void deleteFlashCard_cardNotExist_expectCardLiException() {
        Deck fcm = new Deck();
        String input = "abcdef";
        assertThrows(CardLiException.class, () -> fcm.deleteFlashCard(input));
    }



    @Test
    public void deleteFlashCard_provideIndex_expectDelete() throws CardLiException {
        Deck fcm = new Deck();
        String[] firstCard = {"illness", "byouki"};
        String[] secondCard = {"to lose", "nakushimasu"};
        fcm.prepareToAddFlashCard(firstCard);
        fcm.prepareToAddFlashCard(secondCard);
        fcm.deleteFlashCard("1");
        fcm.viewAllFlashCards();
        assertEquals(1, fcm.getCards().size());
        fcm.deleteFlashCard("1");
    }

    @Test
    public void deleteFlashCard_provideInvalidIndex_expectCardLiException() throws CardLiException {
        Deck fcm = new Deck();
        String[] firstCard = {"illness", "byouki"};
        String[] secondCard = {"to lose", "nakushimasu"};
        fcm.prepareToAddFlashCard(firstCard);
        fcm.prepareToAddFlashCard(secondCard);
        fcm.viewAllFlashCards();
        assertThrows(CardLiException.class, () -> fcm.deleteFlashCard("6"));
        fcm.deleteFlashCard("1");
        fcm.deleteFlashCard("1");
    }

    @Test
    public void deleteFlashCard_provideNegativeIndex_expectCardLiException() throws CardLiException {
        Deck fcm = new Deck();
        String[] firstCard = {"illness", "byouki"};
        String[] secondCard = {"to lose", "nakushimasu"};
        fcm.prepareToAddFlashCard(firstCard);
        fcm.prepareToAddFlashCard(secondCard);
        fcm.viewAllFlashCards();
        assertThrows(CardLiException.class, () -> fcm.deleteFlashCard("0"));
        fcm.deleteFlashCard("1");
        fcm.deleteFlashCard("1");
    }
}