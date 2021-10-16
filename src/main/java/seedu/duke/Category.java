package seedu.duke;

public class Category {
    private String name;
    private Deck manager;

    public Category(String name, Deck manager) {
        this.name = name;
        this.manager = manager;
    }

    public String getName() {
        return name;
    }

    public Deck getManager() {
        return manager;
    }
}
