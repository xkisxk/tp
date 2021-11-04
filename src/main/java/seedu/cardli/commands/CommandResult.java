package seedu.cardli.commands;

public class CommandResult {

    private final String result;
    private final boolean exit;
    private final boolean enter;

    public CommandResult(String result) {
        this.result = result;
        this.exit = false;
        this.enter = false;
    }

    public CommandResult(String result, boolean exit, boolean enter) {
        this.result = result;
        this.exit = exit;
        this.enter = enter;
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
}
