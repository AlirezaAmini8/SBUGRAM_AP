package Controller;

import Model.API;
import Model.ClientEXE;
import Model.PageLoader;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ForgotPasswordController {
    public Button showPassword_button;
    public TextField favoriteColor_field;
    public TextField newPassword_field;
    public Label invalidPassword_label;
    public Button Login_button;
    public Button newpassword_button;
    public Label successful_label;
    public TextField username_field;

    public void Login(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("Login");
    }

    public void ShowPassword(ActionEvent actionEvent) {
        if(username_field.getText().equals("")){
            PageLoader.showalert("SBU GRAM","Enter your username.",null);
        }else {
            if(API.isUserNameExists(username_field.getText())) {
                if (API.forgotpassword(username_field.getText()).equals(favoriteColor_field.getText())) {
                    PageLoader.showalert("SBU GRAM", "your password is :", API.getpassword(username_field.getText()));
                } else {
                    PageLoader.showalert("SBU GRAM","Wrong color.",null);
                }
            }else {
                PageLoader.showalert("SBU GRAM","username doesn't exist.",null);
            }
        }
    }

    public void setPassword(ActionEvent actionEvent) {
        if(username_field.getText().equals("")){
            PageLoader.showalert("SBU GRAM","Enter your username.",null);
        }else {
            if(API.isUserNameExists(username_field.getText())) {
                if (isValid(newPassword_field.getText())) {
                    if (API.setpassword(username_field.getText(), newPassword_field.getText())) {
                        ClientEXE.getProfile().setPassword(newPassword_field.getText());
                        invalidPassword_label.setVisible(false);
                        successful_label.setVisible(true);
                    }
                } else {
                    successful_label.setVisible(false);
                    invalidPassword_label.setVisible(true);
                }
            }else{
                PageLoader.showalert("SBU GRAM","username doesn't exist.",null);
            }
        }
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
}
