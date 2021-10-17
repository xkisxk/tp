package seedu.duke;

import seedu.duke.exceptions.FieldEmptyException;
import seedu.duke.parser.TestParser;
import seedu.duke.ui.TestUi;

import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Implements the test function.
 */
public class TestManager {

    public static ArrayList<Answer> answersResponse = new ArrayList<Answer>();
    private static int answersCount = 0;
    private static final TestUi ui = new TestUi();
    private static final Logger logger = Logger.getLogger(TestManager.class.getName());

    /**
     * Goes through all the flashcards and stores the user's responses into answersResponse ArrayList.
     */
    public static void testAllCardsInOrder(Deck deck) {
        logger.setLevel(Level.WARNING);
        logger.log(Level.INFO, "starting test");

        for (FlashCard question : deck.cards) {
            logger.log(Level.INFO, "starting to test a new card");
            int questionNumber = deck.getCardIndex(question);
            ui.printDividerLine();
            ui.printQuestion(question, questionNumber);
            //get user's answer to the card shown(currently assume user inputs only his/her answer)
            //later version to include question number and parsing to allow for randomised testing
            logger.log(Level.INFO, "getting user's answer to the question");
            String userResponse = ui.getUserMessage();
            try {
                userResponse = TestParser.parseUserResponse(userResponse);
            } catch (FieldEmptyException e) {
                logger.log(Level.WARNING, "No user input");
                userResponse = "NO ANSWER GIVEN :(";
                printAnswerEmptyError();
            }
            logger.log(Level.INFO, "Saving answer");
            addAnswer(userResponse, questionNumber);
            assert !answersResponse.isEmpty();
            assert answersCount > 0;
            logger.log(Level.INFO, "Finished this card's testing");
        }

        ui.printDividerLine();
        logger.log(Level.INFO, "Finished test");
        //let user know testing is over
        ui.printTestOver();
        viewTestResult(deck);
    }

    public static int getAnswerIndex(Answer answer) {
        return answersResponse.indexOf(answer);
    }

    /**
     * Saves a new user answer to the current list of user answers.
     *
     * @param answer        String representation of user's answer
     * @param questionIndex Question number for the question that the answer answers
     */
    public static void addAnswer(String answer, int questionIndex) {
        answersResponse.add(new Answer(answer, questionIndex));
        answersCount += 1;
    }

    /**
     * Prints results of test to system output.
     */
    private static void viewTestResult(Deck deck) {
        logger.setLevel(Level.WARNING);
        int score = 0;
        logger.log(Level.INFO, "starting test check");

        //there must be at least one response to start a test
        assert answersResponse.size() > 0;
        for (Answer response : answersResponse) {
            int responseNumber = getAnswerIndex(response);
            FlashCard question = deck.getCard(responseNumber);
            String userAnswer = getUserAnswer(responseNumber);
            ui.printDividerLine();
            //display front of card so that user can understand question
            ui.printQuestion(question, responseNumber);
            ui.printCorrectAnswer(question);
            ui.printUserAnswer(userAnswer);

            if (isUserAnswerCorrect(userAnswer, question)) {
                score++;
                printCorrectAnsMessage();
                logger.log(Level.INFO, "user answer is correct");
            } else {
                printWrongAnsMessage();
                logger.log(Level.INFO, "user answer is wrong");
            }
        }
        ui.printDividerLine();
        assert score <= answersCount;
        System.out.println("Your scored " + score + " out of " + answersCount + " for this test.\n" +
                "That is " + score / answersCount * 100 + "%!");
        logger.log(Level.INFO, "all answers checked, score printed to system output");
    }

    private static String getUserAnswer(int responseNumber) {
        return answersResponse.get(responseNumber).getAnswer();
    }

    private static boolean isUserAnswerCorrect(String userAnswer, FlashCard question) {
        return question.getBack().equals(userAnswer);
    }

    /**
     * View overall result statistics of all tests and individual flashcards
     * Invoked by the user command "stats"
     */
    public static void viewTestStatistics(CategoryList categories) {
        for (Category category : categories.getCategories()) {

            int userScore  = category.getUserScore();
            int totalScore = category.getTotalScore();
            System.out.println("Your score for " + category.getName() + " is " +
                    userScore + " out of " + totalScore);
            System.out.println("That is " + userScore / totalScore * 100 + "%!");
            ui.printDividerLine();
        }
    }

    private static void printCorrectAnsMessage() {
        System.out.println("Well done! You got this question correct");
    }

    private static void printWrongAnsMessage() {
        System.out.println("You got this question wrong! Take note of the correct answer!");
    }

    private static void printAnswerEmptyError() {
        System.out.println("Remember to provide an answer next time! Don't give up!");
    }
}
