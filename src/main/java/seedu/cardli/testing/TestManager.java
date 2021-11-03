package seedu.cardli.testing;

import seedu.cardli.exceptions.EmptyDeckException;
import seedu.cardli.exceptions.FieldEmptyException;
import seedu.cardli.flashcard.DeckManager;
import seedu.cardli.parser.TestParser;
import seedu.cardli.ui.TestUi;
import seedu.cardli.flashcard.Deck;
import seedu.cardli.flashcard.FlashCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Logger;
import java.util.logging.Level;

import static seedu.cardli.ui.TestUi.END_REVIEW_MESSAGE;
import static seedu.cardli.ui.TestUi.END_TEST_MESSAGE;
import static seedu.cardli.ui.TestUi.INCORRECT_INPUT_FORMAT_MESSAGE;
import static seedu.cardli.ui.TestUi.NO_CARDS_TO_REVIEW_MESSAGE;
import static seedu.cardli.ui.TestUi.NO_CARDS_TO_TEST_MESSAGE;
import static seedu.cardli.ui.TestUi.TIMES_UP_MESSAGE;

/**
 * Implements the test function.
 */
public class TestManager {
    private final TestUi ui;
    private final Logger logger = Logger.getLogger(TestManager.class.getName());
    private final TestHistory testHistory;
    private final DeckManager deckManager;

    public TestManager(TestHistory testHistory, DeckManager deckManager) {
        this.logger.setLevel(Level.WARNING);
        this.testHistory = testHistory;
        this.deckManager = deckManager;
        this.ui = new TestUi();
    }

    public TestManager(TestHistory testHistory, DeckManager deckManager, TestUi ui) {
        this.logger.setLevel(Level.WARNING);
        this.testHistory = testHistory;
        this.deckManager = deckManager;
        this.ui = ui;
    }

    /**
     * Enters test mode and requires user to input the index of the deck that they want to be tested.
     * If the input is "all", all decks will be tested. If the input is an integer, the deck at
     * that index will be tested.
     */
    public void startTest() {
        logger.setLevel(Level.SEVERE);
        logger.log(Level.INFO, "starting test");
        ui.printStartTest();
        String input = ui.getUserMessage();
        try {
            logger.log(Level.INFO, "choosing deck to test");
            int deckIndex = TestParser.toInt(input);

            Deck deckToTest = deckManager.getTestDeck(deckIndex);
            AnswerList userAnswers = new AnswerList(deckToTest);

            testAllCardsShuffled(userAnswers);
            markTest(userAnswers);
            testHistory.addAnswerList(userAnswers);
            ui.showMessage(END_TEST_MESSAGE);
        } catch (NumberFormatException e) {
            ui.showMessage(INCORRECT_INPUT_FORMAT_MESSAGE);
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
    public void startReview() {
        logger.setLevel(Level.SEVERE);
        logger.log(Level.INFO, "starting review");
        ui.printStartReview();
        String input = ui.getUserMessage();
        try {
            logger.log(Level.INFO, "choosing deck to test");
            int deckIndex = TestParser.toInt(input);
            Deck deckToReview = deckManager.getLowScoringCards(deckIndex);
            reviewCards(deckToReview);
            ui.showMessage(END_REVIEW_MESSAGE);
        } catch (NumberFormatException e) {
            ui.showMessage(INCORRECT_INPUT_FORMAT_MESSAGE);
            logger.log(Level.WARNING, "Incorrect format causing NumberFormatException");
        } catch (IndexOutOfBoundsException e) {
            ui.showMessage(e.getMessage());
            logger.log(Level.WARNING, "Incorrect format causing IndexOutOfBoundsException");
        } catch (EmptyDeckException e) {
            ui.showMessage(NO_CARDS_TO_REVIEW_MESSAGE);
        }
    }

    /**
     * Reviews the lowest scoring deck of all tests.
     */
    private void reviewCards(Deck deckToReview) throws EmptyDeckException {
        logger.log(Level.INFO, "Reviewing low scoring cards");
        ui.printReviewCard();
        AnswerList answerList = new AnswerList(deckToReview);
        testAllCardsShuffled(answerList);
        testHistory.addAnswerList(answerList);
        markTest(answerList);
    }

    /**
     * Goes through all the flashcards and stores the user's responses into userAnswer ArrayList.
     */
    public void testAllCardsShuffled(AnswerList userAnswer) throws EmptyDeckException {
        ArrayList<FlashCard> deckReplicate = userAnswer.getDeck().getCards();
        if (deckReplicate.isEmpty()) {
            throw new EmptyDeckException(NO_CARDS_TO_TEST_MESSAGE);
        }
        Collections.shuffle(deckReplicate);
        logger.log(Level.INFO, "replicated and shuffled flashcard list");
        //populate userAnswer
        for (FlashCard question : deckReplicate) {
            int questionNumber = userAnswer.getDeck().getCardIndex(question);
            userAnswer.addAnswer("NIL", questionNumber);
        }
        logger.log(Level.INFO, "starting test");
        testInProgress(deckReplicate, userAnswer);

        ui.printDividerLine();
        logger.log(Level.INFO, "Finished test");
        //let user know testing is over
        ui.printTestOver();
    }

    private void testInProgress(ArrayList<FlashCard> deckReplicate,AnswerList userAnswer) {
        boolean allQuestionsAnswered = false;
        int currentQuestion = 0;
        int nextQuestionFlag = 0;
        logger.log(Level.INFO, "starting test proper");
        while (!allQuestionsAnswered) {
            logger.log(Level.INFO, "currentQuestion is out of index. Either test finished or user scroll too far");
            while (currentQuestion >= 0 && currentQuestion < deckReplicate.size()) {
                //question is not answered yet
                if (!userAnswer.isQuestionAnswered(currentQuestion)) {
                    logger.log(Level.INFO, "question not answered yet");
                    nextQuestionFlag = testCard(userAnswer, deckReplicate.get(currentQuestion));
                }
                logger.log(Level.INFO, "setting next question to test");
                //next question to be tested is currentQuestion - 1
                if (nextQuestionFlag == 1) {
                    currentQuestion--;
                } else {
                    //next question to be tested is currentQuestion + 1
                    currentQuestion++;
                }
            }
            logger.log(Level.INFO, "Wraparound for edge case");
            //wraparound from end of deckReplicate to start of deckReplicate
            if (currentQuestion == deckReplicate.size()) {
                currentQuestion = 0;
            }
            //wraparound from start of deckReplicate to end of deckReplicate
            if (currentQuestion == -1) {
                currentQuestion = deckReplicate.size() - 1;
            }
            logger.log(Level.INFO, "checking isAllAnswered");
            if (userAnswer.isAllAnswered()) {
                allQuestionsAnswered = true;
            }
        }
    }

    private int testCard(AnswerList userAnswer, FlashCard question) {
        logger.log(Level.INFO, "starting to test a new card");
        int timer = 20;
        Countdown countdown = new Countdown(timer, TIMES_UP_MESSAGE);

        //0 means proceed to next question in userAnswer;1 means go back 1 question
        int nextQuestionFlag = 0;

        int questionNumber = userAnswer.getDeck().getCardIndex(question);

        ui.printDividerLine();
        ui.printQuestion(question, questionNumber);
        countdown.start();

        //get user's answer to the card shown(currently assume user inputs only his/her answer)
        //later version to include question number and parsing to allow for randomised testing
        logger.log(Level.INFO, "getting user's answer to the question");

        String userResponse = ui.getUserMessage();
        if (countdown.isRunning()) { // timer has not expired yet
            countdown.stop();
        } else {
            userResponse = "";
        }
        countdown.stop();

        try {
            userResponse = TestParser.parseUserResponse(userResponse);
        } catch (FieldEmptyException e) {
            logger.log(Level.INFO, "No user input");
            userResponse = "NO ANSWER GIVEN :(";
            ui.printAnswerEmptyError();
        }

        //set question as answered with the new user response
        if (!(userResponse.trim().equalsIgnoreCase("/NEXT") || userResponse.trim().equalsIgnoreCase("/BACK"))) {
            logger.log(Level.INFO, "Saving answer");
            userAnswer.setQuestionAnswer(questionNumber,userResponse);
            userAnswer.getAnswerList().get(questionNumber).setIsAnswered();
        }
        //signalling to test previous question next
        if (userResponse.trim().equalsIgnoreCase("/BACK")) {
            nextQuestionFlag = 1;
        }

        assert !userAnswer.isEmpty();
        assert userAnswer.getSize() > 0;
        logger.log(Level.INFO, "Finished this card's testing");

        return nextQuestionFlag;
    }

    /**
     * Marks the user's answers then print their results of test to system output.
     */
    public void markTest(AnswerList userAnswers) {
        logger.log(Level.INFO, "starting test check");

        //there must be at least one response to start a test
        assert userAnswers.getSize() > 0;
        for (Answer response : userAnswers.getAnswerList()) {
            markQuestion(userAnswers, response);
        }
        ui.printDividerLine();
        int answersCount = userAnswers.getSize();
        int score = userAnswers.getUserScore();
        assert score <= answersCount;
        System.out.println("You scored " + score + " out of " + answersCount + " for this test.");
        System.out.println("That is " + Math.round(((double) score / answersCount) * 10000) / 100  + "%!");
        logger.log(Level.INFO, "all answers checked, score printed to system output");
    }

    // Marks the user's answer
    private void markQuestion(AnswerList userAnswers, Answer response) {
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