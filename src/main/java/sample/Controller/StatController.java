package main.java.sample.Controller;


import main.java.sample.Main;
import main.java.sample.model.Repository;

public class StatController {

    private Main mainApp;

    private Repository repository;

    public StatController() {
        repository = Repository.getInstance();
    }

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }


}