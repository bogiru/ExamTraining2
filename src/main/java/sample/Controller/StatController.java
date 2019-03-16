package main.java.sample.Controller;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.Background;
import main.java.sample.Main;
import main.java.sample.model.Repository;

import java.util.prefs.Preferences;


public class StatController {

    private Main mainApp;

    private Repository repository;

    @FXML
    private ProgressIndicator progressIndicator;

    @FXML
    private Label allAnswerText;

    @FXML
    private Label trueAnswerText;

    public StatController() {
        repository = Repository.getInstance();
    }

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }


    public void statDraw() {
        Preferences prefs = Preferences.userRoot().node("ExamApp").node("tasks");
        int current = Integer.parseInt(prefs.get("current", null));

        int currentNumberOfVariant = Integer.parseInt(prefs.node(String.valueOf(current)).get("variant", null));
        int score = Integer.parseInt(prefs.node(String.valueOf(current)).get("score", null));

        draw(currentNumberOfVariant, score, (double) score / currentNumberOfVariant);
    }

    private void draw(int currentNumberOfVariant, int score, double progress) {
        allAnswerText.setText(String.format("Количество решённых вариантов: %d", currentNumberOfVariant));
        trueAnswerText.setText(String.format("Количество правильных ответов: %d", score));
        System.out.println(progress);
        progressIndicator.setProgress(progress);
        progressIndicator.setStyle(String.format("-fx-accent: %s;", colorSelection(progress)));

    }

    private String colorSelection(double progress) {
        if (progress <= 0.25) {
            return "darkred";
        }

        if (progress <= 0.5) {
            return "chocolate";
        }

        if (progress <= 0.75) {
            return "orange";
        }

        if (progress >= 0.9) {
            return "green";
        }

        return null;
    }

}