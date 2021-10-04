package seedu.duke;

public class Answer {
    private String answer;
    private int questionIndex;

    public Answer(String answer, int questionIndex) {
        this.answer = answer;
        this.questionIndex = questionIndex;
    }

    //getter for front
    public String getAnswer() {
        return this.answer;
    }


}
