package seedu.duke;

/**
 * Represents CardLI application.
 */
public class Duke {
    private static final CardLiUi ui = new CardLiUi();

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        ui.printGreetingMessage();
        boolean exitProgram = false;
        while (!exitProgram) {
            String input = ui.getUserMessage();
            Parser.parseCommand(input);
            if (Parser.getCommand(input).contains("bye")) {
                exitProgram = true;
            }
        }
        ui.printByeMessage();
    }
}
