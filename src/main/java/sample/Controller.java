package main.java.sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import main.java.sample.model.Repository;
import main.java.sample.model.Task;

import javax.swing.*;
import java.sql.SQLOutput;
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
    private Label message;

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
    private int numberVariant = 0;


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
                allNum.setText(String.valueOf(task.getVariants().size() - 1));
                textQuestion.setText(task.getVariants().get(numberVariant).getQuestion());

            }
        });

        btnAnswer.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            String userAnswer = textAnswer.getText();

            nextMove(userAnswer);

        });

        btnSkip.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            nextMove("");
        });
    }

    private void nextMove(String userAnser) {
        Task task = repository.getTaskByNumber(numberTask);
        String absoluteAnswer = task.getVariants().get(numberVariant).getAnswer();

        if (userAnser.equals(absoluteAnswer)) {
            message.setText("Молодец! Так держать!");
        } else {
            message.setText("Ошибка! Правильный ответ: " + absoluteAnswer);
        }

        if (numberVariant < task.getVariants().size() - 1) {
            numberVariant++;
        }
        else {
            message.setText("К сожалению, варианты закончились");
        }
        currentNum.setText(String.valueOf(numberVariant));
        textQuestion.setText(task.getVariants().get(numberVariant).getQuestion());
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

    private void listener () {



    }

}
