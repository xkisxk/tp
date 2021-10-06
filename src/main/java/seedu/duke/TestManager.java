package seedu.duke;

import seedu.duke.exceptions.FieldEmptyException;

import java.util.ArrayList;
import java.util.Scanner;

import static seedu.duke.FlashCardManager.cards;
import static seedu.duke.FlashCardManager.viewFlashCard;

public class TestManager {
    public static ArrayList<Answer> answersResponse = new ArrayList<Answer>();
    private static int answerCount = 0;

    //goes through all the cards and stores the response by the user into answersResponse arraylist
    public static void testAllCardsInOrder() {
        for (FlashCard question : cards) {
            int questionNumber = FlashCardManager.getCardIndex(question);
            System.out.println("--------------------------------------------------");
            System.out.println("Question " + String.valueOf(questionNumber + 1) + ":");
            //display front of card so that user can understand question
            viewFlashCard(questionNumber);
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
        System.out.println("--------------------------------------------------");
        //let user know testing is over
        System.out.println("Test Over");
    }

    //may seem useless right now but will be needed in the future
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

    //getter for index of an answer
    public static int getAnswerIndex(Answer answer) {
        return answersResponse.indexOf(answer);
    }

    //hopefully there is a ui class which i can put this in and access from this class
    public static String getInput() {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        return input;
    }

    public static void addAnswer(String answer, int questionIndex) {
        answersResponse.add(new Answer(answer, questionIndex));
        answerCount += 1;
    }

    //lets user see the answer
    public static void viewAnswer(int answerIndex) {
        System.out.println(answersResponse.get(answerIndex).getAnswer());
    }


    public static void viewAllAnswers() {
        for (Answer response : answersResponse) {
            int responseNumber = getAnswerIndex(response);
            //display front of card so that user can understand question
            System.out.println("--------------------------------------------------");
            System.out.println("Question " + String.valueOf(responseNumber + 1) + ":");
            viewFlashCard(responseNumber);
            System.out.println("Your answer:");
            viewAnswer(responseNumber);
        }
        System.out.println("--------------------------------------------------");
    }
}
