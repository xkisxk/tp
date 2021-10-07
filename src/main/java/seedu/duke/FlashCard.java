package seedu.duke;

public class FlashCard {
    private String front;
    private String back;

    public FlashCard(String front, String back) {
        this.front = front;
        this.back = back;
    }

    //getter for front
    public String getFront() {
        return this.front;
    }

    public String getBack() {
        return this.back;
    }
}
