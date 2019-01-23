package main.java.sample.model;


import java.util.List;

public class Task {
    private int numberPastQustion;
    private int number;
    private List<Variant> variants;

    public Task(int number, int numberPastQustion, List<Variant> variants) {
        this.number = number;
        this.numberPastQustion = numberPastQustion;
        this.variants = variants;
    }

    public int getNumberPastQustion() {
        return numberPastQustion;
    }

    public void setNumberPastQustion(int numberPastQustion) {
        this.numberPastQustion = numberPastQustion;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<Variant> getVariants() {
        return variants;
    }

    public void setVariants(List<Variant> variants) {
        this.variants = variants;
    }
}
