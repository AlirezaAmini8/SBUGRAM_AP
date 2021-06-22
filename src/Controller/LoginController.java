package Controller;

import Common.Profile;
import Model.API;
import Model.ClientEXE;
import Model.ClientNetworker;
import Model.PageLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class LoginController {
    @FXML
    public javafx.scene.control.Label incorrect_label;
    public PasswordField password_field;
    public javafx.scene.control.Button login_button;
    public TextField username_field;
    public TextField password_visible;
    public Button sign_up_button;
    public Button forgot_password;
    public Label forgotpass_label;
    public ImageView serverconnect_image;
    public Label server_Label;

    public void login(ActionEvent actionEvent) throws IOException {
        if (!ClientNetworker.isConnected()){
            PageLoader.showalert("SBU GRAM","Not connected to server","you are not connected to server yet, please use connection panel!");
            return;
        }
        String username= username_field.getText();
        String password;
        if(password_field.isVisible()) {
            password = password_field.getText();
        }else{
            password=password_visible.getText();
        }
        Profile profile = API.login(username,password);
        if(profile == null){
            incorrect_label.setVisible(true);
            return;
        }
        ClientEXE.setProfile(profile);
        incorrect_label.setVisible(false);
        new PageLoader().load("TimeLine");
    }

    public void show_password(ActionEvent actionEvent) {
        if(!password_visible.isVisible()){
            password_field.setVisible(false);
            password_visible.setVisible(true);
            password_visible.setText(password_field.getText());
        }else{
            password_visible.setVisible(false);
            password_field.setVisible(true);
            password_field.setText(password_visible.getText());
        }
    }

    public void sign_up(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("SignUpPage");
    }

    public void forgotPassword(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("ForgotPassword");
    }

    public void connecttoserver(MouseEvent mouseEvent) {
        ClientNetworker.connectToServer();
        PageLoader.showalert("SBU GRAM","Connected successfully :)",null);
    }
}
