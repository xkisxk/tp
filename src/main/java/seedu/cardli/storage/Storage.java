//@@author xRossKoh

package seedu.cardli.storage;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import seedu.cardli.flashcard.Deck;
import seedu.cardli.flashcard.FlashCard;
import seedu.cardli.testing.AnswerList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.json.simple.JSONArray;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class containing methods to save and parse user data.
 */
public class Storage {

    private static final Logger logger = Logger.getLogger(Storage.class.getName());

    /**
     * Specified file path to save task list.
     */
    private static final String CARDS_FILEPATH = "data/Cards_CardLI.json";
    private static final String TESTS_FILEPATH = "data/Tests_CardLi.json";
    File cardsFile;
    File testsFile;

    public Storage() {
        logger.setLevel(Level.SEVERE);
        try {
            this.cardsFile = new File(CARDS_FILEPATH);
            this.testsFile = new File(TESTS_FILEPATH);

            // create new directory and file if they do not exist
            if (!cardsFile.exists()) {
                cardsFile.getParentFile().mkdirs();
                cardsFile.createNewFile();
                logger.log(Level.INFO, "data/Cards_CardLI.json created");
            }
            if (!testsFile.exists()) {
                testsFile.getParentFile().mkdirs();
                testsFile.createNewFile();
                logger.log(Level.INFO, "data/Tests_CardLI.json created");
            }
        } catch (IOException e) {
            System.out.println((e.getMessage()));
            logger.log(Level.WARNING, "IOException thrown at Storage()");
        }
    }

    /**
     * Saves user's current decks of flashcards to JSON file.
     *
     * @param decks     User's current decks of flashcards
     */
    public void writeCardsToFile(ArrayList<Deck> decks) {
        logger.setLevel(Level.SEVERE);
        try {
            // instantiate FileWriter object to overwrite specified text file
            FileWriter fileWriter = new FileWriter(CARDS_FILEPATH, false);

            JSONArray jsonDecks = new JSONArray();

            for (Deck deck : decks) {
                jsonDecks.add(deck.toJsonObject());
            }

            fileWriter.write(jsonDecks.toJSONString());
            fileWriter.close();
            logger.log(Level.INFO, "All decks of flashcards saved to Cards_CardLI.json");
        } catch (IOException e) {
            System.out.println("Something went wrong while saving to data/Cards_CardLI.json...");
            logger.log(Level.WARNING, "IOException thrown at writeCardsToFile()");
        }
    }

    /**
     * Saves user's current test history to JSON file.
     *
     * @param testHistory       User's current test history
     */
    public void writeTestsToFile(ArrayList<AnswerList> testHistory) {
        logger.setLevel(Level.SEVERE);
        try {
            // instantiate FileWriter object to overwrite specified text file
            FileWriter fileWriter = new FileWriter(TESTS_FILEPATH, false);

            JSONArray jsonTestHistory = new JSONArray();

            for (AnswerList answerList : testHistory) {
                jsonTestHistory.add(answerList.toJsonObject());
            }
            fileWriter.write(jsonTestHistory.toJSONString());
            fileWriter.close();
            logger.log(Level.INFO, "Test history saved to Cards_CardLI.json");
        } catch (IOException e) {
            System.out.println("Something went wrong while saving to data/Tests_CardLI.json...");
            logger.log(Level.WARNING, "IOException thrown at writeTestsToFile()");
        }
    }

    /**
     * Reads JSON file to return user's saved decks of flashcards.
     *
     * @return  User's saved decks of flashcards
     */
    public ArrayList<Deck> readCardsFromFile() {
        logger.setLevel(Level.SEVERE);
        ArrayList<Deck> decks = new ArrayList<>();

        try {
            // instantiate scanner to read file contents
            Scanner s = new Scanner(this.cardsFile);
            JSONParser parser = new JSONParser();
            JSONArray jsonDecks = (JSONArray) parser.parse(s.nextLine());

            for (Object o : jsonDecks) {
                decks.add(parseDeck((JSONObject) o));
            }
        } catch (ParseException e) {
            System.out.println("Something went wrong parsing data/Cards_CardLI.json...");
            System.out.println("If you directly edited the JSON file, please revert all changes made to it.");
            logger.log(Level.WARNING, "ParseException thrown at readCardsFromFile()");
        } catch (FileNotFoundException e) {
            logger.log(Level.WARNING, "NoFileFoundException thrown at readCardsFromFile()");
        } catch (NoSuchElementException e) {
            logger.log(Level.WARNING, "NoSuchElementException thrown at readCardsFromFile()");
        }
        return decks;
    }

    /**
     * Reads JSON file to return user's saved test history.
     *
     * @return      User's saved test history
     */
    public ArrayList<AnswerList> readTestsFromFile() {
        logger.setLevel(Level.SEVERE);
        ArrayList<AnswerList> testHistory = new ArrayList<>();

        try {
            // instantiate scanner to read file contents
            Scanner s = new Scanner(this.testsFile);
            JSONParser parser = new JSONParser();
            JSONArray jsonTestHistory = (JSONArray) parser.parse(s.nextLine());

            for (Object o : jsonTestHistory) {
                testHistory.add(parseAnswerList((JSONObject) o));
            }
        } catch (ParseException e) {
            System.out.println("Something went wrong parsing data/Tests_CardLI.json...");
            System.out.println("If you directly edited the JSON file, please revert all changes made to it.");
            logger.log(Level.WARNING, "ParseException thrown at readTestsFromFile()");
        } catch (FileNotFoundException e) {
            logger.log(Level.WARNING, "NoFileFoundException thrown at readTestsFromFile()");
        } catch (NoSuchElementException e) {
            logger.log(Level.WARNING, "NoSuchElementException thrown at readTestsFromFile()");
        }
        return testHistory;
    }

    /**
     * Converts an AnswerList as JSONObject instance to an AnswerList instance.
     *
     * @param jsonAnswerList    AnswerList as a JSONObject instance
     * @return                  AnswerList instance
     */
    private AnswerList parseAnswerList(JSONObject jsonAnswerList) {
        logger.setLevel(Level.WARNING);
        JSONObject jsonDeck = (JSONObject) jsonAnswerList.get("deck");
        AnswerList newAnswerList = new AnswerList(parseDeck(jsonDeck));
        JSONArray jsonAnswers = (JSONArray) jsonAnswerList.get("answerList");

        for (Object o : jsonAnswers) {
            JSONObject jsonAnswer = (JSONObject) o;
            newAnswerList.addAnswer((String) jsonAnswer.get("answer"),
                    (int) (long) jsonAnswer.get("questionIndex"));
        }
        newAnswerList.setUserScore((int) (long) jsonAnswerList.get("userScore"));
        logger.log(Level.INFO, "Successfully parsed 1 AnswerList instance");
        return newAnswerList;
    }

    /**
     * Converts a Deck as a JSONObject instance to a Deck instance.
     *
     * @param jsonDeck      Deck as a JSONObject instance
     * @return              Deck instance
     */
    private Deck parseDeck(JSONObject jsonDeck) {
        logger.setLevel(Level.WARNING);
        Deck newDeck = new Deck((String) jsonDeck.get("deckName"));
        JSONArray jsonCards = (JSONArray) jsonDeck.get("cards");

        for (Object o : jsonCards) {
            JSONObject jsonCard = (JSONObject) o;
            newDeck.addFlashCard(new FlashCard((String) jsonCard.get("front"),
                    (String) jsonCard.get("back"),
                    (int) (long) jsonCard.get("userScore"),
                    (int) (long) jsonCard.get("totalScore")));
        }
        logger.log(Level.INFO, "Successfully parsed 1 Deck instance");
        return newDeck;
    }
}
