package main.java.sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import main.java.sample.model.Repository;
import main.java.sample.model.Task;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    @FXML
    private ChoiceBox<String> chbTask;

    @FXML
    private Label currentNum;

    @FXML
    private Label allNum;

    @FXML
    private TextArea textQuestion;

    @FXML
    private TextField textAnswer;

    @FXML
    private Button btnAnswer;

    @FXML
    private Button btnSkip;

    private Repository repository;
    private int numberTask;

    public Controller() {
        this.repository = new Repository();
    }

    @FXML
    private void initialize() {
        chbTask.setItems(FXCollections.observableArrayList(getTitleTask()));

        chbTask.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                numberTask = getNumberTask(newValue);
                Task task = repository.getTaskByNumber(numberTask);

                textQuestion.setText(task.getVariants().get(0).getQuestion());

            }
        });
    }

    private int getNumberTask(String title) {
        return Integer.parseInt(title.split("№")[1]);
    }

    private List<String> getTitleTask() {
        List<String> list = new ArrayList<>();

        for (Task task : repository.getTasks()) {
            list.add(String.format("Задание №%d", task.getNumber()));
        }

        return list;
    }
}
