package seedu.duke;

import java.util.ArrayList;

public class Category {
    private String name;
    private ArrayList<Deck> decks;

    public Category(String name, ArrayList<Deck> decks) {
        this.name = name;
        this.decks = decks;
    }

    public String getName() {
        return name;
    }

    public Deck getDeck() {
        return decks;
    }
}
