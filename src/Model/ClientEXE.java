package Model;

import Common.Profile;
import javafx.stage.*;
import javafx.application.*;
import java.util.*;

public class ClientEXE extends Application {

    public static Profile getProfile() {
        return profile;
    }
    public static Profile profile;
    public static Post toCommentPost;
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
    public void start(Stage primaryStage) throws Exception{
        PageLoader.initStage(primaryStage);
        ClientNetworker.connectToServer();
        new PageLoader().load("Login");

    }

}
