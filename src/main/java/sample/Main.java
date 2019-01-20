package main.java.sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.sample.model.Repository;
import main.java.sample.model.Task;
import main.java.sample.model.Variant;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);

        Repository repository = new Repository();
        for (Task task : repository.getTasks()) {
            System.out.println("numberTask: " + task.getNumber());
            for (Variant variant : task.getVariants()) {
                System.out.println("    numberVariant: " + variant.getNumber());
                System.out.println("    Question: " + variant.getQuestion());
                System.out.println("    Answer: " + variant.getAnswer());
                System.out.println();
            }
            //
            System.out.println("------------------------------------------------------------------------------");
        }
    }
}
