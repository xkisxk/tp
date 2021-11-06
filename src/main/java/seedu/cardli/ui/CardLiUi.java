package seedu.cardli.ui;

import seedu.cardli.commands.CommandResult;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class CardLiUi {
    private final Scanner in;
    private final PrintStream out;

    public CardLiUi() {
        this(System.in, System.out);
    }

    public CardLiUi(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    //@@author JWweiyin
    public void printByeMessage() {
        System.out.println("\tYou did well today! Goodbye!");
    }

    public static String returnHelpMessage() {
        String help = "\n"
                + "................................................................................"
                + "....................................... \n"
                + "Here is the list of commands! \n"
                + "1. add \n"
                + "Description: Adds a flashcard deck \n"
                + "Format: add <name of deck> \n\n"
                + "2. delete \n"
                + "Description: Deletes a flashcard deck \n"
                + "Format: delete <index of deck> \n\n"
                + "3. edit \n"
                + "Description: Edits the name of a flashcard deck \n"
                + "Format: edit /d <index of deck> /n <new name of deck> \n\n"
                + "4. view \n"
                + "Description: Lists flashcard decks \n"
                + "Format: view \n\n"
                + "5. enter \n"
                + "Description: Enters a flashcard deck (further actions can be taken)\n"
                + "Format: enter <index of deck> \n\n"
                + "6. test \n"
                + "Description: Tests flashcards \n"
                + "Format: test \n\n"
                + "7. viewfc \n"
                + "Description: View overall results for flashcards \n"
                + "Format: viewfc \n\n"
                + "8. viewtest \n"
                + "Description: View result of a test \n"
                + "Format: viewtest <index of test/all> \n\n"
                + "9. review \n"
                + "Description: Enters review mode, which is same as test mode but tests cards "
                + "that the user got wrong more often \n"
                + "Format: review \n\n"
                + "10. find \n"
                + "Description: Finds card using word/phrase of the query \n"
                + "Format: find <word/phrase> \n\n"
                + "11. bye \n"
                + "Description: Exits the program \n"
                + "Format: exit \n\n"
                + "................................................................................"
                + "....................................... \n";
        return help;
    }

    public static String returnHelpInDeckMessage() {
        String help = "\n"
                + "................................................................................"
                + "....................................... \n"
                + "Here is the list of commands! \n"
                + "1. add \n"
                + "Description: Adds a flashcard to a deck \n"
                + "Format: add /f <word/phrase on front of flashcard> /b"
                + " <word/phrase on back of flashcard> \n\n"
                + "2. delete \n"
                + "Description: Deletes a flashcard \n"
                + "Format: delete <index of card> \n\n"
                + "3. edit \n"
                + "Description: Edits the front or back of a flashcard \n"
                + "Format: edit /c <index of card> /s <front"
                + " or back of card> /i <word/phrase> \n\n"
                + "4. move \n"
                + "Description: Moves a flashcard from current deck to another deck \n"
                + "Format: move /c <index of card>"
                + " /d <index of deck> \n\n"
                + "5. view \n"
                + "Description: Lists flashcards in the current deck \n"
                + "Format: view\n\n"
                + "6. exit \n"
                + "Description: Returns the program to main menu. \n"
                + "Format: exit \n\n"
                + "................................................................................"
                + "....................................... \n";
        return help;
    }

    //@@author JWweiyin
    public void printGreetingMessage() {
        String logo = "\n"
                + " .----------------.  .----------------.  .----------------.  .----------------. "
                + " .----------------.  .----------------.\n"
                + "| .--------------. || .--------------. || .--------------. || .--------------. |"
                + "| .--------------. || .--------------. |\n"
                + "| |     ______   | || |      __      | || |  _______     | || |  ________    | ||"
                + " |   _____      | || |     _____    | |\n"
                + "| |   .' ___  |  | || |     /  \\     | || | |_   __ \\    | || | |_   ___ `.  | ||"
                + " |  |_   _|     | || |    |_   _|   | |\n"
                + "| |  / .'   \\_|  | || |    / /\\ \\    | || |   | |__) |   | || |   | |   `. \\ |"
                + " || |    | |       | || |      | |     | |\n"
                + "| |  | |         | || |   / ____ \\   | || |   |  __ /    | || |   | |    | | | ||"
                + " |    | |   _   | || |      | |     | |\n"
                + "| |  \\ `.___.'\\  | || | _/ /    \\ \\_ | || |  _| |  \\ \\_  | || |  _| |___.' "
                + "/ | || |   _| |__/ |  | || |     _| |_    | |\n"
                + "| |   `._____.'  | || ||____|  |____|| || | |____| |___| | || | |________.'  | || "
                + "|  |________|  | || |    |_____|   | |\n"
                + "| |              | || |              | || |              | || |              | || "
                + "|              | || |              | |\n"
                + "| '--------------' || '--------------' || '--------------' || '--------------' |"
                + "| '--------------' || '--------------' |\n"
                + " '----------------'  '----------------'  '----------------'  '----------------' "
                + " '----------------'  '----------------'\n";
        System.out.println("Welcome to\n" + logo);
        System.out.println("Let's get started!");
        System.out.println("Type in \"help\" for more details.");
    }

    public static void printResult(CommandResult result) {
        System.out.println(result.getResult());
    }

    //@@author xkisxk
    public String getUserMessage() {
        return in.nextLine();
    }
}
