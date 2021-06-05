package SBU.Client.Controller;

import SBU.Client.Model.API;
import SBU.Client.Model.PageLoader;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class ChangeInfoController {
    public TextField name_field;
    public TextField Location_field;
    public TextField Lastname_field;
    public TextField birthDate_field;
    public ImageView ChangeBirthDate_button;
    public ImageView ChangeName_button;
    public ImageView ChangeLastName_button;
    public ImageView ChangeLocation_button;
    public ImageView changebirthdatemark;
    public ImageView changelocationmark;
    public ImageView changelastnamemark;
    public ImageView changenamemark;
    public ImageView backbutton;


    public void ChangeBirthDate(MouseEvent actionEvent) {
        SignUpPageController.profile.setBirthDate(birthDate_field.getText());
        changebirthdatemark.setVisible(true);
        changelocationmark.setVisible(false);
        changelastnamemark.setVisible(false);
        changenamemark.setVisible(false);
    }

    public void ChangeLocation(MouseEvent actionEvent) {
        SignUpPageController.profile.setLocation(Location_field.getText());
        changelocationmark.setVisible(true);
        changelastnamemark.setVisible(false);
        changebirthdatemark.setVisible(false);
        changenamemark.setVisible(false);
    }

    public void ChangeLastName(MouseEvent actionEvent) {
        SignUpPageController.profile.setLastName(Lastname_field.getText());
        changelastnamemark.setVisible(true);
        changelocationmark.setVisible(false);
        changebirthdatemark.setVisible(false);
        changenamemark.setVisible(false);
    }

    public void ChangeName(MouseEvent actionEvent) {
        SignUpPageController.profile.setName(name_field.getText());
        changenamemark.setVisible(true);
        changebirthdatemark.setVisible(false);
        changelastnamemark.setVisible(false);
        changelocationmark.setVisible(false);
    }

    public void goBack(MouseEvent mouseEvent) throws IOException {
        if(API.updateinfo(SignUpPageController.profile)) {
            new PageLoader().load("ProfilePage");
        }
    }
}
