package seedu.duke;

import java.util.Scanner;

/**
 * Deals with the parsing of user input at the command line.
 */
public class Parser {

    /**
     * Parses user input at the command line and invokes the necessary follow up actions.
     */
    static void programLogic() {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        String command = line.split(" ")[0];
        while (!command.equals("bye")) {
            switch (command) {
            case "add":
                FlashCardManager.prepareToAddFlashCard(line);
                break;
            case "delete":
                FlashCardManager.prepareToDeleteFlashCard(line);
                break;
            case "view":
                FlashCardManager.viewAllFlashCards();
                break;
            case "test":
                TestManager.testAllCardsInOrder();
                break;
            default:
                System.out.println("\tThat's not a command.");
            }
            line = in.nextLine();
            command = line.split(" ")[0];
        }
    }
}
