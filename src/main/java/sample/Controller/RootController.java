package main.java.sample.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import main.java.sample.Main;
import main.java.sample.model.Repository;
import main.java.sample.model.Task;

import java.util.List;
import java.util.Optional;
import java.util.prefs.Preferences;


public class RootController {

    @FXML
    private Label menuStat;

    private Main mainApp;

    private Repository repository;

    public RootController(){
        repository = Repository.getInstance();
    }

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void handleStat() {
        mainApp.initStatLayout();
    }

    @FXML
    private void handleTheory() {
        mainApp.initTheoryLayout();
    }

    @FXML
    private void handleExit() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Выход");
        alert.setHeaderText("Вы точно хотите выйти?");

        Optional<ButtonType> type = alert.showAndWait();
        if (type.get() == ButtonType.OK) {
            System.exit(0);
        }

    }

    @FXML
    private void handleReset() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Сбросить прогресс");
        alert.setHeaderText("Вы уверены, что хотите сбросить прогресс?");

        Optional<ButtonType> type = alert.showAndWait();
        if (type.get() == ButtonType.OK) {
            reset();
        }
    }

    private void reset() {
        Preferences preferences = Preferences.userRoot().node("ExamApp");
        Preferences prefs = preferences.node("tasks");

        List<Task> tasks = repository.getTasks();
        for (Task task : tasks) {
            Preferences node = prefs.node(String.valueOf(task.getNumber()));
            node.put("variant", "1");
            node.put("score", "0");
        }

        prefs.put("current", "0");

        mainApp.reset();

    }

    @FXML
    private void handleAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Exam Training");
        alert.setHeaderText("Программа для подготовки к ГИА");
        alert.setContentText("Автор: Барболин Семен\nс. Кичменгский Городок");
        alert.showAndWait();
    }


}