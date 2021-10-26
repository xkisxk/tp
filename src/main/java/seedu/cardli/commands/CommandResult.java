package seedu.cardli.commands;

public class CommandResult {

    private final String result;
    private final boolean exit;
    private final boolean enter;
    private final boolean isTest;
    private final boolean isReview;

    public CommandResult(String result) {
        this.result = result;
        this.exit = false;
        this.enter = false;
        this.isTest = false;
        this.isReview = false;
    }

    public CommandResult(String result, boolean exit, boolean enter) {
        this.result = result;
        this.exit = exit;
        this.enter = enter;
        this.isTest = false;
        this.isReview = false;
    }

    public CommandResult(String result, boolean exit, boolean enter, boolean isTest, boolean isReview) {
        this.result = result;
        this.exit = exit;
        this.enter = enter;
        this.isTest = isTest;
        this.isReview = isReview;
    }

    public String getResult() {
        return this.result;
    }

    public boolean isExit() {
        return this.exit;
    }

    public boolean isEnter() {
        return this.enter;
    }

    public boolean isTest() {
        return this.isTest;
    }

    public boolean isReview() {
        return this.isReview;
    }
}
