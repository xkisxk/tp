package seedu.cardli.ui;

import seedu.cardli.flashcard.FlashCard;
import seedu.cardli.testing.AnswerList;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * TestUi class handles the input and output during a test or a review.
 */
public class TestUi {

    /** The message to be displayed when the timer has expired. */
    public static final String TIMES_UP_MESSAGE = "TIME'S UP! You can still input an answer, but it won't be counted.";
    public static final String INCORRECT_INPUT_FORMAT_MESSAGE = "Incorrect input format, "
            + "make sure the description is either a numeric or all.";
    public static final String NO_CARDS_TO_REVIEW_MESSAGE = "Congratulations you don't have any low scoring cards!";
    public static final String DECK_NOT_EXIST_MESSAGE = "This deck does not exist";
    public static final String NO_CARDS_TO_TEST_MESSAGE = "There are no cards to test.";
    public static final String END_TEST_MESSAGE = "End of test. Returning to main menu...";
    public static final String END_REVIEW_MESSAGE = "End of review. Returning to main menu...";

    private final Scanner in;
    private final PrintStream out;

    public TestUi() {
        this(System.in, System.out);
    }

    public TestUi(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void showMessage(String input) {
        out.println(input);
    }

    public String getUserMessage() {
        return in.nextLine();
    }

    public void printDividerLine() {
        System.out.println("--------------------------------------------------");
    }

    public void printQuestion(FlashCard question, int questionNumber) {
        out.println("Question " + (questionNumber + 1) + ":");
        //display front of card so that user can understand question
        out.println(question.getFront());
        out.println("Your answer?");
    }

    public void printCorrectAnswer(FlashCard question) {
        out.println("Correct answer: " + question.getBack());
    }

    /**
     * Prints user's answer for a specified question to the system output.
     *
     * @param userAnswer the user's answer for the question
     */
    public void printUserAnswer(String userAnswer) {
        out.println("Your answer: " + userAnswer);
    }

    public void printTestOver() {
        out.println("Test Over");
    }

    public void printStartTest() {
        out.println("Which deck do you want to test?");
        out.print("Input deck index (0 or \"all\" to test all decks): ");
    }

    public void printStartReview() {
        out.println("Which deck do you want to review?");
        out.print("Input deck index (0 or \"all\" to review all decks): ");
    }

    public void printReviewCard() {
        out.println("Reviewing all low scoring cards");
    }

    public void printCorrectAnsMessage() {
        out.println("Well done! You got this question correct");
    }

    public void printWrongAnsMessage() {
        out.println("You got this question wrong! Take note of the correct answer!");
    }

    public void printAnswerEmptyError() {
        out.println("Remember to provide an answer next time! Don't give up!");
    }
}
