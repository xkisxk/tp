package seedu.cardli.flashcard;


import org.json.simple.JSONObject;


import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents information within a single flashcard.
 */
public class FlashCard {

    private static final String FLASHCARD_TOP_FRONT = "*================FRONT================*";
    private static final String FLASHCARD_TOP_BACK = "*================BACK=================*";
    private static final String FLASHCARD_BOTTOM = "*=====================================*";
    private static final String FLASHCARD_WHITESPACE = " ";
    private static final String FLASHCARD_TOP_LINE = FLASHCARD_TOP_FRONT
            + FLASHCARD_WHITESPACE + FLASHCARD_TOP_BACK;
    private static final String FLASHCARD_BOTTOM_LINE = FLASHCARD_BOTTOM
            + FLASHCARD_WHITESPACE + FLASHCARD_BOTTOM;
    private static final int FLASHCARD_MAX_LINE_LENGTH = 37;
    static final String SEPARATOR = " | ";

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
     * Splits the given word/phrase into a String array so that it
     * can fit nicely into the flashcard to be printed to the
     * standard output.
     * @param word The word/phrase to be split
     * @return A String array containing the split input
     */
    private String[] splitFlashcardWord(String word) {
        ArrayList<String> splitWords = new ArrayList<>();

        int index = 0;
        while (index < word.length()) {
            String currentLine;
            if (index + FLASHCARD_MAX_LINE_LENGTH - 1 >= word.length()) {
                // probably the characters of the word
                currentLine = word.substring(index);
                index += FLASHCARD_MAX_LINE_LENGTH;
            } else {
                assert index + FLASHCARD_MAX_LINE_LENGTH - 1 < word.length();
                char terminalChar = word.charAt(index + FLASHCARD_MAX_LINE_LENGTH - 1);
                if (terminalChar == ' ' || terminalChar == '-') {
                    currentLine = word.substring(index, index + FLASHCARD_MAX_LINE_LENGTH);
                    index += FLASHCARD_MAX_LINE_LENGTH;
                } else {
                    currentLine = word
                            .substring(index, index + FLASHCARD_MAX_LINE_LENGTH - 1)
                            .concat("-");
                    index += FLASHCARD_MAX_LINE_LENGTH - 1;
                }
                assert currentLine.length() == 37;
            }
            splitWords.add(currentLine);
        }

        String[] splitWordsArray = new String[splitWords.size()];
        splitWordsArray = splitWords.toArray(splitWordsArray);
        return splitWordsArray;
    }

    private String returnWhitespaceLine() {
        String result = "";
        for (int i = 0; i < FLASHCARD_MAX_LINE_LENGTH; i++) {
            result = result.concat(FLASHCARD_WHITESPACE);
        }
        return result;
    }

    private String padLineWithWhitespace(String line) {
        // pads line with whitespaces so that it is centred
        // and has 35 characters
        String result = "";
        int numOfExcessChars = FLASHCARD_MAX_LINE_LENGTH - line.length();
        for (int i = 0; i < numOfExcessChars / 2; i++) {
            result = result.concat(FLASHCARD_WHITESPACE);
        }
        result = result.concat(line);
        for (int i = 0; i < (numOfExcessChars + 1) / 2; i++) {
            result = result.concat(FLASHCARD_WHITESPACE);
        }
        return result;
    }

    private String[] padLinesWithEmptyLines(String[] lines, String emptyLine, int numOfLines) {
        // creates and returns a String[] so that the number of lines
        // equals to the given numOfLines
        String[] newLines = new String[numOfLines];
        int numOfExcessLines = numOfLines - lines.length;
        for (int i = 0; i < numOfExcessLines / 2; i++) {
            newLines[i] = emptyLine;
        }
        for (int i = 0; i < lines.length; i++) {
            newLines[numOfExcessLines / 2 + i] = lines[i];
        }
        for (int i = 0; i < (numOfExcessLines + 1) / 2; i++) {
            newLines[numOfExcessLines / 2 + lines.length + i] = emptyLine;
        }
        return newLines;
    }

    private String joinFrontBackLines(String[] frontLines, String[] backLines) {
        int numOfLines = 0;

        // pad last line of frontLines and backLines with
        // whitespaces so that it makes up to 35 characters
        String frontLinesLast = frontLines[frontLines.length - 1];
        frontLines[frontLines.length - 1] = padLineWithWhitespace(frontLinesLast);
        String backLinesLast = backLines[backLines.length - 1];
        backLines[backLines.length - 1] = padLineWithWhitespace(backLinesLast);

        String[] newFrontLines;
        String[] newBackLines;
        // check if front and back have the same number of lines
        if (frontLines.length == backLines.length) {
            // join the same line of front and back
            numOfLines = frontLines.length;
            newFrontLines = frontLines;
            newBackLines = backLines;
        } else {
            String emptyLine = returnWhitespaceLine();
            if (frontLines.length < backLines.length) {
                // front has fewer lines than back
                numOfLines = backLines.length;
                // pad frontLines with empty lines to match numOfLines
                newFrontLines = padLinesWithEmptyLines(frontLines, emptyLine, numOfLines);
                newBackLines = backLines;
            } else {
                assert backLines.length < frontLines.length;
                // back has fewer lines than front
                numOfLines = frontLines.length;
                // pad frontLines with empty lines to match numOfLines
                newBackLines = padLinesWithEmptyLines(backLines, emptyLine, numOfLines);
                newFrontLines = frontLines;
            }
        }
        String result = "";
        for (int i = 0; i < numOfLines; i++) {
            String line = FLASHCARD_WHITESPACE + newFrontLines[i] + FLASHCARD_WHITESPACE
                    + FLASHCARD_WHITESPACE
                    + FLASHCARD_WHITESPACE + newBackLines[i];
            result = result.concat(line + System.lineSeparator());
        }
        return result;
    }

    private String joinFrontBack(String front, String back) {
        String[] frontLines = splitFlashcardWord(front);
        String[] backLines = splitFlashcardWord(back);
        String result = joinFrontBackLines(frontLines, backLines);
        return result;
    }

    /**
     * Formats the flashcard to be printed to the standard output
     * as a String.
     * @return A String containing the formatted flashcard
     */
    public String returnFlashCard() {
        String top = FLASHCARD_TOP_LINE + System.lineSeparator();
        String middle = joinFrontBack(this.front, this.back);
        String bottom = FLASHCARD_BOTTOM_LINE + System.lineSeparator();
        String result = top + middle + bottom;
        return result;
    }

    /**
     * Prints the formatted flashcard to the standard output.
     */
    public void printFlashCard() {
        String result = returnFlashCard();
        System.out.println(result);
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

    /**
     * Getter for userScore.
     * @return An integer representing userScore
     */
    public int getUserScore() {
        return this.userScore;
    }

    /**
     * Getter for totalScore.
     * @return An integer representing totalScore
     */
    public int getTotalScore() {
        return this.totalScore;
    }

    /**
     * Setter for front.
     * @param input The input to replace front.
     */
    public void setFront(String input) {
        logger.setLevel(Level.WARNING);
        assert this.front != null;
        logger.log(Level.INFO, "Getting card front: " + front);
        this.front = input;
    }

    /**
     * Setter for back.
     * @param input The input to replace back.
     */
    public void setBack(String input) {
        logger.setLevel(Level.WARNING);
        assert this.back != null;
        logger.log(Level.INFO, "Getting card back: " + back);
        this.back = input;
    }

    @Override
    public String toString() {
        return getFront() + SEPARATOR
                + getBack() + SEPARATOR
                + getUserScore() + SEPARATOR
                + getTotalScore() + '\n';
    }

    public JSONObject toJsonObject() {
        JSONObject jsonCard = new JSONObject();

        jsonCard.put("front", getFront());
        jsonCard.put("back", getBack());
        jsonCard.put("userScore", getUserScore());
        jsonCard.put("totalScore", getTotalScore());

        return jsonCard;
    }
}
