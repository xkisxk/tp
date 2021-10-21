package seedu.duke.testing;

import seedu.duke.exceptions.EmptyDeckException;
import seedu.duke.exceptions.FieldEmptyException;
import seedu.duke.flashcard.DeckManager;
import seedu.duke.parser.TestParser;
import seedu.duke.ui.TestUi;
import seedu.duke.flashcard.Deck;
import seedu.duke.flashcard.FlashCard;

import java.util.ArrayList;
import java.util.Collections;
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
     * If the input is "all", all decks will be tested. If the input is an integer, the deck at
     * that index will be tested.
     */
    public static void startTest() {
        logger.setLevel(Level.WARNING);
        logger.log(Level.INFO, "starting test");
        ui.printStartTest();
        String input = ui.getUserMessage();
        try {
            logger.log(Level.INFO, "choosing deck to test");
            int deckIndex = TestParser.toInt(input);

            Deck deckToTest = DeckManager.getTestDeck(deckIndex);
            AnswerList userAnswers = new AnswerList(deckToTest);

            testAllCardsShuffled(userAnswers);
            TestHistory.addAnswerList(userAnswers);
            markTest(userAnswers);
        } catch (NumberFormatException e) {
            System.out.println("Incorrect input format, make sure the description is a numeric.");
            logger.log(Level.WARNING, "Incorrect format causing NumberFormatException");
        } catch (IndexOutOfBoundsException e) {
            ui.showMessage(e.getMessage());
            logger.log(Level.WARNING, "Deck does not exist causing IndexOutOfBoundsException");
        } catch (EmptyDeckException e) {
            ui.showMessage(e.getMessage());
            logger.log(Level.WARNING, "Empty deck");
        }
    }

    /**
     * Enters review mode and requires user to input the index of the deck that they want to be reviewed.
     * If the input is "all", the cards will come from all decks. If the input is an integer, only cards from
     * the deck at that index will be tested.
     */
    public static void startReview() {
        logger.setLevel(Level.WARNING);
        logger.log(Level.INFO, "starting review");
        ui.printStartReview();
        String input = ui.getUserMessage();
        try {
            logger.log(Level.INFO, "choosing deck to test");
            int deckIndex = TestParser.toInt(input);
            Deck deckToReview = TestHistory.getLowScoringCards(deckIndex);
            reviewCards(deckToReview);
        } catch (NumberFormatException e) {
            System.out.println("Incorrect input format, make sure the description is a numeric.");
            logger.log(Level.WARNING, "Incorrect format causing NumberFormatException");
        } catch (IndexOutOfBoundsException e) {
            ui.showMessage(e.getMessage());
            logger.log(Level.WARNING, "Incorrect format causing IndexOutOfBoundsException");
        } catch (EmptyDeckException e) {
            ui.showMessage("Congratulations you don't have any low scoring cards!");
        }
    }

    /**
     * Reviews the lowest scoring deck of all tests.
     */
    public static void reviewCards(Deck deckToReview) throws EmptyDeckException {
        logger.log(Level.INFO, "Reviewing low scoring cards");
        ui.printReviewCard();
        AnswerList answerList = new AnswerList(deckToReview);
        testAllCardsShuffled(answerList);
        TestHistory.addAnswerList(answerList);
        markTest(answerList);
    }

    /**
     * Goes through all the flashcards and stores the user's responses into userAnswer ArrayList.
     */
    public static void testAllCardsShuffled(AnswerList userAnswer) throws EmptyDeckException {
        logger.setLevel(Level.WARNING);
        ArrayList<FlashCard> deckReplicate = userAnswer.getDeck().getCards();
        if (deckReplicate.isEmpty()) {
            throw new EmptyDeckException("There are no cards to test.");
        }
        Collections.shuffle(deckReplicate);
        logger.log(Level.INFO, "replicated and shuffled flashcard list");
        for (FlashCard question : deckReplicate) {
            testCard(userAnswer, question);
        }
        ui.printDividerLine();
        logger.log(Level.INFO, "Finished test");
        //let user know testing is over
        ui.printTestOver();
    }

    private static void testCard(AnswerList userAnswer, FlashCard question) {
        logger.log(Level.INFO, "starting to test a new card");
        int questionNumber = userAnswer.getDeck().getCardIndex(question);
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
        userAnswer.addAnswer(userResponse, questionNumber);
        assert !userAnswer.isEmpty();
        assert userAnswer.getSize() > 0;
        logger.log(Level.INFO, "Finished this card's testing");
    }

    /**
     * Marks the user's answers then print their results of test to system output.
     */
    public static void markTest(AnswerList userAnswers) {
        logger.setLevel(Level.WARNING);
        logger.log(Level.INFO, "starting test check");

        //there must be at least one response to start a test
        assert userAnswers.getSize() > 0;
        for (Answer response : userAnswers.getAnswerList()) {
            markAnswer(userAnswers, response);
        }
        ui.printDividerLine();
        int answersCount = userAnswers.getSize();
        int score = userAnswers.getUserScore();
        assert score <= answersCount;
        System.out.println("You scored " + score + " out of " + answersCount + " for this test.");
        System.out.println("That is " + Double.valueOf(score) / answersCount * 100 + "%!");
        logger.log(Level.INFO, "all answers checked, score printed to system output");
    }

    // Marks the user's answer
    private static void markAnswer(AnswerList userAnswers, Answer response) {
        int responseNumber = userAnswers.getAnswerIndex(response);
        FlashCard question = userAnswers.getDeck().getCard(responseNumber);
        String userAnswer = response.getAnswer();

        ui.printDividerLine();
        //display front of card so that user can understand question
        ui.printQuestion(question, responseNumber);
        ui.printCorrectAnswer(question);
        ui.printUserAnswer(userAnswer);

        if (response.isCorrect(userAnswer, question)) {
            userAnswers.incrementUserScore();
            question.incrementUserScore();
            ui.printCorrectAnsMessage();
            logger.log(Level.INFO, "user answer is correct");
        } else {
            ui.printWrongAnsMessage();
            logger.log(Level.INFO, "user answer is wrong");
        }
        question.incrementTotalScore();
    }
}
