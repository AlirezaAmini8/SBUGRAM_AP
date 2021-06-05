package SBU.Client.Model;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
    int Height=650 ,Width=450;
    @Override
    public void start(Stage primaryStage) throws Exception{
        PageLoader.initStage(primaryStage);
        new PageLoader().load("Login");
    }


    public static void main(String[] args) {
        launch(args);
    }
}