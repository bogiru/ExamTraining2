package main.java.sample.Controller;


import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.Background;
import main.java.sample.Main;
import main.java.sample.model.Repository;
import main.java.sample.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;


public class StatController {

    private Main mainApp;

    private Repository repository;

    @FXML
    private ProgressIndicator progressIndicator;

    @FXML
    private ChoiceBox choiceBox;

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


    public void initChoiceBox() {
        choiceBox.setItems(FXCollections.observableArrayList(getTitlesTask()));
        choiceBox.getSelectionModel().selectFirst();

        choiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            statDraw(getNumberTask(String.valueOf(newValue)));
        });

    }

    private List<String> getTitlesTask() {
        List<String> list = new ArrayList<String>();
        list.add("Выберите задание");
        for (Task task : repository.getTasks()) {
            list.add(String.format("Задание №%d", task.getNumber()));
        }
        return list;
    }

    private int getNumberTask(String titleTask) {
        return Integer.parseInt(titleTask.split("№")[1]);
    }

    private void statDraw(int numberTask) {
        Preferences prefs = Preferences.userRoot().node("ExamApp").node("tasks");
        //int current = Integer.parseInt(prefs.get("current", null));

        int currentNumberOfVariant = Integer.parseInt(prefs.node(String.valueOf(numberTask)).get("variant", null)) - 1;
        int score = Integer.parseInt(prefs.node(String.valueOf(numberTask)).get("score", null));


        draw(numberTask, currentNumberOfVariant, score, (double) score / currentNumberOfVariant);
    }

    private void draw(int numberTask, int currentNumberOfVariant, int score, double progress) {
       // numberTaskText.setText(String.format("Задание №%d", numberTask));
        allAnswerText.setText(String.format(String.valueOf(currentNumberOfVariant)));
        trueAnswerText.setText(String.valueOf(score));
        System.out.println(progress);
        progressIndicator.setProgress(progress);
        progressIndicator.setStyle(String.format("-fx-accent: %s;", colorSelection(progress)));


    }

    private String colorSelection(double progress) {
        if (progress <= 0.25) {
            return "Maroon";
        }

        if (progress <= 0.5) {
            return "e20104";
        }

        if (progress <= 0.75) {
            return "#d76e00";
        }

        if (progress < 0.9) {
            return "#fdd524";
        }

        if (progress >= 0.9) {
            return "DarkGreen";
        }

        return null;
    }

}