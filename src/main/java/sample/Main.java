package main.java.sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.java.sample.Controller.MainController;
import main.java.sample.Controller.RootController;
import main.java.sample.Controller.StatController;
import main.java.sample.model.Repository;
import main.java.sample.model.Task;
import main.java.sample.model.Variant;

import java.io.IOException;

public class Main extends Application {

    private BorderPane rootLayout;
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("ExamTraining");
        this.primaryStage.getIcons().add(new Image(getClass().getClassLoader().getResource("images/favicon.png").toString()));

        initRootLayout();

        initMainLayout();

    }

    private void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("fxml/root_layout.fxml"));
            rootLayout = loader.load();

            RootController controller = loader.getController();
            controller.setMainApp(this);

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initMainLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("fxml/main_layout.fxml"));
            AnchorPane pane = loader.load();

            rootLayout.setCenter(pane);

            MainController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initStatLayout() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/stat_layout.fxml"));
            AnchorPane pane = loader.load();

            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setTitle("Статистика");
            stage.initOwner(primaryStage);

            Scene scene = new Scene(pane);
            stage.setScene(scene);

            StatController controller = loader.getController();
            controller.setMainApp(this);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);

    }
}
