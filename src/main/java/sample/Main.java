package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.Controller.MainController;
import sample.Controller.RootController;
import sample.Controller.StatController;
import sample.Controller.TheoryController;

import java.io.IOException;
import java.util.Optional;
import java.util.prefs.Preferences;

public class Main extends Application {

    private BorderPane rootLayout;
    private Stage primaryStage;

    private MainController mainController;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("ExamTraining");
        this.primaryStage.getIcons().add(new Image(getClass().getClassLoader().getResource("images/favicon.png").toString()));

        this.primaryStage.setResizable(false);

        initRootLayout();

        initMainLayout();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>(){

            @Override

            public void handle(WindowEvent event) {
                exit(event);
            }

        });


    }

    private void exit(WindowEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Выход");
        alert.setHeaderText("Вы точно хотите выйти?");

        Optional<ButtonType> type = alert.showAndWait();
        if (type.get() == ButtonType.CANCEL) {
            event.consume();
        }else {
            saveState();
        }
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

            mainController = loader.getController();
            mainController.setMainApp(this);
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
            stage.getIcons().add(new Image(getClass().getClassLoader().getResource("images/favicon.png").toString()));
            stage.setResizable(false);

            Scene scene = new Scene(pane);
            stage.setScene(scene);

            StatController controller = loader.getController();

            saveState();
            controller.setMainApp(this);


            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initTheoryLayout() {
        try {
            saveState();
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/theory_layout.fxml"));
            AnchorPane pane = loader.load();

            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setTitle("Теория");
            stage.initOwner(primaryStage);
            stage.setResizable(false);
            stage.getIcons().add(new Image(getClass().getClassLoader().getResource("images/favicon.png").toString()));

            Scene scene = new Scene(pane);
            stage.setScene(scene);


            TheoryController controller = loader.getController();
            controller.setMainApp(this);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reset() {
        initMainLayout();
        mainController.reset();
    }

    private void saveState() {
        Preferences preferences = Preferences.userRoot().node("ExamApp").node("tasks");
        preferences.put("current", String.valueOf(mainController.getCurrentNumberOfTask()));
        preferences.node(String.valueOf(mainController.getCurrentNumberOfTask())).put("variant", String.valueOf(mainController.getCurrentNumberOfVariant()));
        preferences.node(String.valueOf(mainController.getCurrentNumberOfTask())).put("score", String.valueOf(mainController.getScore()));
    }

    public static void main(String[] args) {
        launch(args);

    }
}
