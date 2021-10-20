package seedu.duke.testing;

import seedu.duke.exceptions.FieldEmptyException;
import seedu.duke.flashcard.DeckManager;
import seedu.duke.parser.TestParser;
import seedu.duke.ui.TestUi;
import seedu.duke.flashcard.Deck;
import seedu.duke.flashcard.FlashCard;

import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Implements the test function.
 */
public class TestManager {
    private static final TestUi ui = new TestUi();
    private static final Logger logger = Logger.getLogger(TestManager.class.getName());

    /**
     * Enters test mode and requires user to input the index of the deck that they want to be tested.
     */
    public static void startTest() {
        logger.setLevel(Level.WARNING);
        logger.log(Level.INFO, "starting test");
        ui.printStartTest();
        String input = ui.getUserMessage();
        try {
            logger.log(Level.INFO, "choosing deck to test");
            int deckIndex = TestParser.toInt(input);

            Deck deck = DeckManager.getDeckList().get(deckIndex);
            AnswerList answersResponse = new AnswerList(deck);

            testAllCardsInOrder(answersResponse, deck);
            viewTestResult(answersResponse, deck);
        } catch (NumberFormatException e) {
            System.out.println("Incorrect input format, make sure the description is a numeric.");
            logger.log(Level.WARNING, "Incorrect format causing NumberFormatException");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("This deck doesn't exist.");
            logger.log(Level.WARNING, "Incorrect format causing IndexOutOfBoundsException");
        }
    }

    /**
     * Goes through all the flashcards and stores the user's responses into answersResponse ArrayList.
     */
    public static void testAllCardsInOrder(AnswerList answersResponse, Deck deck) {
        logger.setLevel(Level.WARNING);

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
                ui.printAnswerEmptyError();
            }
            logger.log(Level.INFO, "Saving answer");
            answersResponse.addAnswer(userResponse, questionNumber);
            assert !answersResponse.isEmpty();
            assert answersResponse.getSize() > 0;
            logger.log(Level.INFO, "Finished this card's testing");
        }

        ui.printDividerLine();
        logger.log(Level.INFO, "Finished test");
        //let user know testing is over
        ui.printTestOver();
        TestHistory.addAnswerList(answersResponse);
    }

    /**
     * Prints results of test to system output.
     */
    public static void viewTestResult(AnswerList answersResponse, Deck deck) {
        logger.setLevel(Level.WARNING);
        int score = 0;
        logger.log(Level.INFO, "starting test check");

        //there must be at least one response to start a test
        assert answersResponse.getSize() > 0;
        for (Answer response : answersResponse.getAnswerList()) {
            int responseNumber = answersResponse.getAnswerIndex(response);
            FlashCard question = deck.getCard(responseNumber);
            String userAnswer = response.getAnswer();
            ui.printDividerLine();
            //display front of card so that user can understand question
            ui.printQuestion(question, responseNumber);
            ui.printCorrectAnswer(question);
            ui.printUserAnswer(userAnswer);

            if (response.isCorrect(userAnswer, question)) {
                score++;
                question.incrementUserScore();
                System.out.println(question.getUserScore());
                ui.printCorrectAnsMessage();
                logger.log(Level.INFO, "user answer is correct");
            } else {
                ui.printWrongAnsMessage();
                logger.log(Level.INFO, "user answer is wrong");
            }
            question.incrementTotalScore();
        }
        ui.printDividerLine();
        int answersCount = answersResponse.getSize();
        assert score <= answersCount;
        System.out.println("You scored " + score + " out of " + answersCount + " for this test.");
        System.out.println("That is " + score / answersCount * 100 + "%!");
        logger.log(Level.INFO, "all answers checked, score printed to system output");
    }
}
