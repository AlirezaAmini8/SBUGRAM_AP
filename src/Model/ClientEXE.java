package Model;

import Common.Profile;
import javafx.stage.*;
import javafx.application.*;
import java.util.*;

/**
 it is mail application of clientside
 it extends application because it is a javafx program
 it has exe in name because the executable class that user should run
 **/
public class ClientEXE extends Application {

    public static Profile getProfile() {
        return profile;
    }

    public static Profile profile;
    public static Map<String,Profile> profiles=new HashMap<>();
    public static List<Post> getAllpostsList() {
        return allpostsList;
    }

    public static List<Post> allpostsList = new ArrayList<>();

    public static List<Post> getMypostlist() {
        return mypostlist;
    }

    public static List<Post> mypostlist = new ArrayList<>();

    public static void setProfile(Profile profile){
        ClientEXE.profile = profile;
    }


    public static void main(String[] args) {
        launch( args );
    }

    @Override
    @SuppressWarnings("deprecation")
    public void start(Stage primaryStage) throws Exception{
        PageLoader.initStage(primaryStage);
        ClientNetworker.connectToServer();
        new PageLoader().load("Login");

    }

}
