package seedu.duke;

import seedu.duke.exceptions.FieldEmptyException;

import java.util.ArrayList;
import java.util.Scanner;

import static seedu.duke.FlashCardManager.cards;
import static seedu.duke.FlashCardManager.getFrontOfCard;
import static seedu.duke.FlashCardManager.getBackOfCard;


/**
 * Implements the test function.
 */
public class TestManager {

    public static ArrayList<Answer> answersResponse = new ArrayList<Answer>();
    private static int answerCount = 0;

    /**
     * Goes through all the flashcards and stores the user's responses into answersResponse ArrayList.
     */
    public static void testAllCardsInOrder() {
        for (FlashCard question : cards) {
            int questionNumber = FlashCardManager.getCardIndex(question);
            printDividerLine();
            System.out.println("Question " + String.valueOf(questionNumber + 1) + ":");
            //display front of card so that user can understand question
            System.out.println(getFrontOfCard(questionNumber));
            System.out.println("Your answer?");
            //get user's answer to the card shown(currently assume user inputs only his/her answer)
            //later version to include question number and parsing to allow for randomised testing
            String userResponse = getInput();
            try {
                parseUserResponse(userResponse);
            } catch (FieldEmptyException e) {
                userResponse = "NO ANSWER GIVEN :(";
                printAnswerEmptyError();
            }
            addAnswer(userResponse, questionNumber);
        }
        printDividerLine();
        //let user know testing is over
        System.out.println("Test Over");
        viewTestResult();
    }

    public static String parseUserResponse(String userResponse) throws FieldEmptyException {
        String input = userResponse;
        if (userResponse.isEmpty()) {
            throw new FieldEmptyException();
        }
        return input;
    }

    public static void printAnswerEmptyError() {
        System.out.println("Remember to provide an answer next time! Don't give up!");
    }

    public static int getAnswerIndex(Answer answer) {
        return answersResponse.indexOf(answer);
    }

    //hopefully there is a ui class which i can put this in and access from this class
    public static String getInput() {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        return input;
    }

    /**
     * Saves a new user answer to the current list of user answers.
     *
     * @param answer            String representation of user's answer
     * @param questionIndex     Question number for the question that the answer answers
     */
    public static void addAnswer(String answer, int questionIndex) {
        answersResponse.add(new Answer(answer, questionIndex));
        answerCount += 1;
    }

    /**
     * Prints user's answer for a specified question to the system output.
     *
     * @param answerIndex   Specified question number
     */
    public static void viewAnswer(int answerIndex) {
        System.out.println(answersResponse.get(answerIndex).getAnswer());
    }

    /**
     * Prints results of test to system output.
     */
    private static void viewTestResult() {
        int score = 0;

        for (Answer response : answersResponse) {
            int responseNumber = getAnswerIndex(response);
            //display front of card so that user can understand question
            printDividerLine();
            System.out.println("Question "
                    + String.valueOf(responseNumber + 1)
                    + ": " + getFrontOfCard(responseNumber));
            System.out.println("Correct answer: " + getBackOfCard(responseNumber));
            System.out.print("Your answer: ");
            viewAnswer(responseNumber);

            if (getBackOfCard(responseNumber).equals(answersResponse.get(responseNumber).getAnswer())) {
                score++;
                printCorrectAnsMessage();
            } else {
                printWrongAnsMessage();
            }
        }
        printDividerLine();
        System.out.println("Your scored " + score + " out of " + answerCount + " for this test");
    }

    private static void printDividerLine() {
        System.out.println("--------------------------------------------------");
    }

    private static void printCorrectAnsMessage() {
        System.out.println("Well done! You got this question correct");
    }

    private static void printWrongAnsMessage() {
        System.out.println("You got this question wrong! Take note of the correct answer!");
    }
}
