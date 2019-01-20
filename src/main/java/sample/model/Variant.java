package main.java.sample.model;

public class Variant {
    private int number;
    private String question;
    private String answer;

    public Variant(int number, String question, String answer) {
        this.number = number;
        this.question = question;
        this.answer = answer;
    }
    //
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
