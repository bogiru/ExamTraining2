package sample.Controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import main.java.sample.model.Task;
import sample.Main;
import sample.model.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;


public class StatController {

    private Main mainApp;
    private Repository repository;

    private int currentNumberOfVariant;
    private int score;

    @FXML
    private ChoiceBox choiceBox;

    @FXML
    private Label allAnswerText;

    @FXML
    private Label trueAnswerText;

    @FXML
    private PieChart chart;

    public StatController() {
        repository = Repository.getInstance();
    }

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void initialize() {
        initChoiceBox();
    }

    public void initChoiceBox() {
        choiceBox.setItems(FXCollections.observableArrayList(getTitlesTask()));
        choiceBox.getSelectionModel().selectFirst();

        choiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue.equals("Выберите задание")) {
                resetUI();
            }else {
                initUI(getNumberTask(String.valueOf(newValue)));

                float correctPercent = score * 100 / currentNumberOfVariant;
                float uncorrectPercent = 100 - correctPercent;

                ObservableList<PieChart.Data> chartData = FXCollections.observableArrayList(
                        new PieChart.Data("Правильные", correctPercent),
                        new PieChart.Data("Неправильные", uncorrectPercent)
                );
                chart.setData(chartData);
            }

        });

    }

    private void resetUI() {
        draw(0, 0);
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

    private void initUI(int numberTask) {
        Preferences prefs = Preferences.userRoot().node("ExamApp").node("tasks");

        currentNumberOfVariant = Integer.parseInt(prefs.node(String.valueOf(numberTask)).get("variant", null));
        score = Integer.parseInt(prefs.node(String.valueOf(numberTask)).get("score", null));


        draw(currentNumberOfVariant, score);
    }

    private void draw(int currentNumberOfVariant, int score) {
        allAnswerText.setText(String.valueOf(currentNumberOfVariant));
        trueAnswerText.setText(String.valueOf(score));
    }

}