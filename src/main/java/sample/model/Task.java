package main.java.sample.model;

import java.util.List;

public class Task {
    private int number;
    private List<Variant> variants;

    public Task(int number, List<Variant> variants) {
        this.number = number;
        this.variants = variants;
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
