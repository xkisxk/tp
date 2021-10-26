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
        System.out.println(input);
    }

    public String getUserMessage() {
        return in.nextLine();
    }

    public void printDividerLine() {
        System.out.println("--------------------------------------------------");
    }

    public void printQuestion(FlashCard question, int questionNumber) {
        System.out.println("Question " + (questionNumber + 1) + ":");
        //display front of card so that user can understand question
        System.out.println(question.getFront());
        System.out.println("Your answer?");
    }

    public void printCorrectAnswer(FlashCard question) {
        System.out.println("Correct answer: " + question.getBack());
    }

    /**
     * Prints user's answer for a specified question to the system output.
     *
     * @param userAnswer the user's answer for the question
     */
    public void printUserAnswer(String userAnswer) {
        System.out.println("Your answer: " + userAnswer);
    }

    public void printTestOver() {
        System.out.println("Test Over");
    }

    public void printStartTest() {
        System.out.println("Which deck do you want to test?");
        System.out.print("Input deck index (0 or \"all\" to test all decks): ");
    }

    public void printEndTest() {
        System.out.println("End of test. Returning to main menu...");
    }

    public void printStartReview() {
        System.out.println("Which deck do you want to review?");
        System.out.print("Input deck index (0 or \"all\" to review all decks): ");
    }

    public void printReviewCard() {
        System.out.println("Reviewing all low scoring cards");
    }

    public void printTest(int index, AnswerList answerList) {
        int score = answerList.getUserScore();
        int totalScore = answerList.getSize();
        System.out.println(
                "Score for test " + index + " " + answerList.getDeck().getName()
                        + " " + score + "/" + totalScore
                        + " " + (double) score / totalScore * 100 + "%");
    }

    public void printScore(int index, int score, int totalScore) {
        System.out.println("You scored " + score + " out of " + totalScore + " for test " + (index + 1));
        System.out.println("That is " + (double) score / totalScore * 100 + "%!");
    }

    public void printScoreWithCard(FlashCard card) {
        card.viewFlashCard();
        System.out.println("Score: " + card.getUserScore() + " out of " + card.getTotalScore());
    }

    public void printCorrectAnsMessage() {
        System.out.println("Well done! You got this question correct");
    }

    public void printWrongAnsMessage() {
        System.out.println("You got this question wrong! Take note of the correct answer!");
    }

    public void printAnswerEmptyError() {
        System.out.println("Remember to provide an answer next time! Don't give up!");
    }
}
