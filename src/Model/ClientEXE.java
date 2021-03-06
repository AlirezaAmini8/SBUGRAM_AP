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
    public static Profile othersprofile;
    public static Profile selectedProfile;

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
