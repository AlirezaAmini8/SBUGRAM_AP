package Controller;

import Model.API;
import Model.ClientEXE;
import Model.ClientNetworker;
import Model.PageLoader;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class MenuPageController {
    public Button Profile_button;
    public Button newPost_button;
    public Button Logout_button;
    public ImageView goBack_button;

    public void ShowProfile(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("ProfilePage");
    }


    public void newPost(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("AddPost");
    }

    public void Logout(ActionEvent actionEvent) throws IOException {
        if(API.logout()) {
            ClientNetworker.disconnectFromServer();
            ClientEXE.profile = null;
            new PageLoader().load("Login");
        }
    }

    public void goBack(MouseEvent actionEvent) throws IOException {
        new PageLoader().load("TimeLine");
    }
}
