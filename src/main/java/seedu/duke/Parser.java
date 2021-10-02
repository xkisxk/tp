package seedu.duke;

import java.util.Scanner;

public class Parser {

    static void programLogic() {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        String command = line.split(" ")[0];
        while (!command.equals("bye")) {
            switch(command) {
            case "add":
                FlashCardManager.prepareToAddFlashCard(line);
                break;
            case "delete":
                FlashCardManager.prepareToDeleteFlashCard(line);
                break;
            default:
                System.out.println("\tThat's not a command.");
            }
            line = in.nextLine();
            command = line.split(" ")[0];
        }

    }
}
