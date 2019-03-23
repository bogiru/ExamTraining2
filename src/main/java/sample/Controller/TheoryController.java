package sample.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import sample.Main;
import sample.model.Repository;

import java.util.prefs.Preferences;

public class TheoryController {
    private Main mainApp;

    private Repository repository;

    @FXML
    private Label textTheory;

    public TheoryController() {
        repository = Repository.getInstance();
    }

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void initialize() {
        Preferences prefs = Preferences.userRoot().node("ExamApp").node("tasks");
        int current = Integer.parseInt(prefs.get("current", "0"));

        if (current > 0) {
            textTheory.setText(repository.getTaskByNumber(current).getTheory().trim());
        }else {
            textTheory.setText("Для начала, выберите задание!");
        }
    }

}
