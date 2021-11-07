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

public class Storage {

    /**
     * Specified file path to save task list.
     */
    private static final String CARDS_FILEPATH = "data/Cards_CardLI.json";
    private static final String TESTS_FILEPATH = "data/Tests_CardLi.json";
    File cardsFile;
    File testsFile;

    public Storage() {
        try {
            this.cardsFile = new File(CARDS_FILEPATH);
            this.testsFile = new File(TESTS_FILEPATH);

            // create new directory and file if they do not exist
            if (!cardsFile.exists()) {
                cardsFile.getParentFile().mkdirs();
                cardsFile.createNewFile();
            }
            if (!testsFile.exists()) {
                testsFile.getParentFile().mkdirs();
                testsFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println((e.getMessage()));
        }
    }

    public void writeCardsToFile(ArrayList<Deck> decks) {
        try {
            // instantiate FileWriter object to overwrite specified text file
            FileWriter fileWriter = new FileWriter(CARDS_FILEPATH, false);

            JSONArray jsonDecks = new JSONArray();

            for (Deck deck: decks) {
                jsonDecks.add(deck.toJsonObject());
            }

            fileWriter.write(jsonDecks.toJSONString());
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Something went wrong while saving to data/Cards_CardLI.json...");
        }
    }

    public void writeTestsToFile(ArrayList<AnswerList> testHistory) {
        try {
            // instantiate FileWriter object to overwrite specified text file
            FileWriter fileWriter = new FileWriter(TESTS_FILEPATH, false);

            JSONArray jsonTestHistory = new JSONArray();

            for (AnswerList answerList: testHistory) {
                jsonTestHistory.add(answerList.toJsonObject());
            }
            fileWriter.write(jsonTestHistory.toJSONString());
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Something went wrong while saving to data/Tests_CardLI.json...");
        }
    }

    public ArrayList<Deck> readCardsFromFile() {
        ArrayList<Deck> decks = new ArrayList<>();

        try {
            // instantiate scanner to read file contents
            Scanner s = new Scanner(this.cardsFile);
            JSONParser parser = new JSONParser();
            JSONArray jsonDecks = (JSONArray) parser.parse(s.nextLine());

            for (Object o: jsonDecks) {
                decks.add(parseDeck((JSONObject) o));
            }
        } catch (ParseException e) {
            System.out.println("Something went wrong parsing data/Cards_CardLI.json...");
            System.out.println("If you directly edited the JSON file, please revert all changes made to it.");
        } catch (FileNotFoundException | NoSuchElementException e){}
        return decks;
    }

    public ArrayList<AnswerList> readTestsFromFile() {
        ArrayList<AnswerList> testHistory = new ArrayList<>();

        try {
            // instantiate scanner to read file contents
            Scanner s = new Scanner(this.testsFile);
            JSONParser parser = new JSONParser();
            JSONArray jsonTestHistory = (JSONArray) parser.parse(s.nextLine());

            for (Object o: jsonTestHistory) {
                testHistory.add(parseAnswerList((JSONObject) o));
            }
        } catch (ParseException e) {
            System.out.println("Something went wrong parsing data/Tests_CardLI.json...");
            System.out.println("If you directly edited the JSON file, please revert all changes made to it.");
        } catch (FileNotFoundException | NoSuchElementException e){}
        return testHistory;
    }

    private AnswerList parseAnswerList(JSONObject jsonAnswerList) {
        JSONObject jsonDeck = (JSONObject) jsonAnswerList.get("deck");
        AnswerList newAnswerList = new AnswerList(parseDeck(jsonDeck));
        JSONArray jsonAnswers = (JSONArray) jsonAnswerList.get("answerList");

        for (Object o: jsonAnswers) {
            JSONObject jsonAnswer = (JSONObject) o;
            newAnswerList.addAnswer((String) jsonAnswer.get("answer"),
                    (int) (long) jsonAnswer.get("questionIndex"));
        }
        newAnswerList.setUserScore((int) (long) jsonAnswerList.get("userScore"));
        return newAnswerList;
    }

    private Deck parseDeck(JSONObject jsonDeck) {
        Deck newDeck = new Deck((String) jsonDeck.get("deckName"));
        JSONArray jsonCards = (JSONArray) jsonDeck.get("cards");

        for (Object o: jsonCards) {
            JSONObject jsonCard = (JSONObject) o;
            newDeck.addFlashCard(new FlashCard((String) jsonCard.get("front"),
                    (String) jsonCard.get("back"),
                    (int) (long) jsonCard.get("userScore"),
                    (int) (long) jsonCard.get("totalScore")));
        }
        return newDeck;
    }
}
