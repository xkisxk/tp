package seedu.duke.flashcard;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents information within a single flashcard.
 */
public class FlashCard {

    final String SEPARATOR = " | ";

    private static final Logger logger = Logger.getLogger("Card");
    private String front;
    private String back;
    private int userScore;
    private int totalScore;

    public FlashCard(String front, String back) {
        logger.setLevel(Level.WARNING);
        assert front != null;
        assert back != null;
        this.front = front;
        this.back = back;
        this.userScore = 0;
        this.totalScore = 0;
        logger.log(Level.INFO, "Card front: " + front);
        logger.log(Level.INFO, "Card back: " + back);
    }

    public FlashCard(String front, String back, int userScore, int totalScore) {
        logger.setLevel(Level.WARNING);
        assert front != null;
        assert back != null;
        this.front = front;
        this.back = back;
        this.userScore = userScore;
        this.totalScore = totalScore;
        logger.log(Level.INFO, "Card front: " + front);
        logger.log(Level.INFO, "Card back: " + back);
    }

    /**
     * Getter for String on front of flashcard.
     *
     * @return String on front of flashcard
     */
    public String getFront() {
        logger.setLevel(Level.WARNING);
        assert this.front != null;
        logger.log(Level.INFO, "Getting card front: " + front);
        return this.front;
    }

    /**
     * Getter for String on back of flashcard.
     *
     * @return String on back of flashcard
     */
    public String getBack() {
        logger.setLevel(Level.WARNING);
        assert this.back != null;
        logger.log(Level.INFO, "Getting card back: " + back);
        return this.back;
    }

    public int getUserScore() {
        return this.userScore;
    }

    public int getTotalScore() {
        return this.totalScore;
    }

    public void incrementUserScore() {
        logger.setLevel(Level.WARNING);
        logger.log(Level.INFO, "Incrementing flashcard user score");
        userScore++;
    }

    public void incrementTotalScore() {
        logger.setLevel(Level.WARNING);
        logger.log(Level.INFO, "Incrementing flashcard total score");
        totalScore++;
    }

    public void viewFlashCard() {
        System.out.println("*================FRONT================* "
                + "*===============BACK==================*");
        System.out.println();

        String front = this.front;
        String frontSpaces = "";
        for (int i = 0; i < (39 - front.length()) / 2; i++) {
            frontSpaces += " ";
        }

        String back = this.back;
        String backSpaces = "";
        for (int i = 0; i < (39 - back.length()) / 2; i++) {
            backSpaces += " ";
        }

        System.out.println(frontSpaces + front + frontSpaces + backSpaces + back);
        System.out.println();
        System.out.println("*=====================================* "
                + "*=====================================*");
    }
      
    public void setBack(String input) {
        logger.setLevel(Level.WARNING);
        assert this.back != null;
        logger.log(Level.INFO, "Getting card back: " + back);
        this.back = input;
    }

    public void setFront(String input) {
        logger.setLevel(Level.WARNING);
        assert this.front != null;
        logger.log(Level.INFO, "Getting card front: " + front);
        this.front = input;

    }

    @Override
    public String toString(){
        return getFront() + SEPARATOR
                + getBack() + SEPARATOR
                + getUserScore() + SEPARATOR
                + getTotalScore() + '\n';
    }
}
