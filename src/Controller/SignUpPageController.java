package Controller;

import Common.Profile;
import Model.API;
import Model.ClientEXE;
import Model.ClientNetworker;
import Model.PageLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.*;
import java.nio.file.Paths;

public class SignUpPageController {
    public Label failedusername_label;
    public Label unsuccessful_label;
    public Profile profile;
    public byte[] photo;
    public String path;
    @FXML
    public Button Loginbutton;
    public Button sign_up_button;
    public TextField username_field;
    public Button ProfilePhoto;
    public TextField Location;
    public ImageView profile_image;
    public TextField BirthDate;
    public TextField LastName;
    public TextField Name;
    public TextField password_field;
    public TextField Color_field;
    public Button forgotpass_field;
    public Label successful_label;

    public void login(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("Login");
    }

    public void SignUp(ActionEvent actionEvent) throws IOException {
        if (!ClientNetworker.isConnected()){
            PageLoader.showalert("SBU GRAM","Not connected to server","you are not connected to server yet, please use connection panel!");
            return;
        }
        if (isValid(password_field.getText())) {
            if (!API.isUserNameExists(username_field.getText())) {
                failedusername_label.setVisible(false);
                profile = new Profile(username_field.getText());
                profile.setFavoriteColor(Color_field.getText());
                profile.setLocation(Location.getText());
                profile.setBirthDate(BirthDate.getText());
                profile.setName(Name.getText());
                profile.setLastName(LastName.getText());
                if(photo!=null) {
                    profile.setProfilePhoto(photo);
                    profile.setPath(path);
                }
                profile.setPassword(password_field.getText());
                if (API.signUp(profile,profile.getPath())) {
                    ClientEXE.setProfile(profile);
                    photo=null;
                    unsuccessful_label.setVisible(false);
                    failedusername_label.setVisible(false);
                    successful_label.setVisible(true);
                } else {
                    successful_label.setVisible(false);
                    failedusername_label.setVisible(false);
                    unsuccessful_label.setVisible(true);
                }
            } else {
                failedusername_label.setVisible(true);
                successful_label.setVisible(false);
                unsuccessful_label.setVisible(false);
            }
        }else{
            PageLoader.showalert("SBU GRAM","Invalid password","Password should be at least 8 characters and contains letters and numbers.It shouldn't have any space between words.");
        }
    }

    public void AddProfilePhoto(ActionEvent actionEvent) throws IOException {

        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        profile_image.setImage(new Image(Paths.get(String.valueOf(selectedFile)).toUri().toString()));
        path=Paths.get(String.valueOf(selectedFile)).toUri().toString();
        InputStream input = new FileInputStream(selectedFile);
        DataInputStream dataInputStream=new DataInputStream(input);
        photo = dataInputStream.readAllBytes();
    }

    public boolean isValid(String password)
    {

        // for checking if password length
        // is lower than 8.
        if (password.length() < 8) {
            return false;
        }

        // to check space
        if (password.contains(" ")) {
            return false;
        }

        if (true) {
            int count = 0;

            // check digits from 0 to 9
            for (int i = 0; i <= 9; i++) {

                // to convert int to string
                String str1 = Integer.toString(i);

                if (password.contains(str1)) {
                    count = 1;
                }
            }
            if (count == 0) {
                return false;
            }
        }

        if (true) {
            int count = 0;

            // checking capital letters
            for (int i = 65; i <= 90; i++) {

                // type casting
                char c = (char)i;

                String str1 = Character.toString(c);
                if (password.contains(str1)) {
                    count = 1;
                }
            }
            for (int i = 90; i <= 122; i++) {

                // type casting
                char c = (char)i;
                String str1 = Character.toString(c);

                if (password.contains(str1)) {
                    count = 1;
                }
            }
            if (count == 0) {
                return false;
            }
        }

        //checking unvalid chars.
        char[] chars=password.toCharArray();
        for(int i=0;i<chars.length;i++){
            if(!(( chars[i]<=57 && chars[i]>=48 ) || ( chars[i]<=90 && chars[i]>=65 ) || ( chars[i]<=122 && chars[i]>=97 ))){
                return false;
            }
        }

        // if all conditions fails
        return true;
    }

    public void forgotPassword(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("ForgotPassword");
    }
}
