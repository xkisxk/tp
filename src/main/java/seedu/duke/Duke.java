package seedu.duke;

public class Duke {
    private static final CardLiUi ui = new CardLiUi();

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        ui.printGreetingMessage();
        Parser.programLogic();
        ui.printByeMessage();
    }
}
