package Controller;


import Model.API;
import Model.ClientEXE;
import Model.PageLoader;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.*;
import java.nio.file.Paths;

public class ChangeInfoController {
    public String path;
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
    public ImageView changephotomark;
    public ImageView Changephoto_button;
    public Button ProfilePhoto;
    public ImageView profile_image;
    public byte[] photo;


    public void ChangeBirthDate(MouseEvent actionEvent) {
        ClientEXE.profile.setBirthDate(birthDate_field.getText());
        changebirthdatemark.setVisible(true);
        changelocationmark.setVisible(false);
        changelastnamemark.setVisible(false);
        changenamemark.setVisible(false);
        changephotomark.setVisible(false);
    }

    public void ChangeLocation(MouseEvent actionEvent) {
        ClientEXE.profile.setLocation(Location_field.getText());
        changelocationmark.setVisible(true);
        changelastnamemark.setVisible(false);
        changebirthdatemark.setVisible(false);
        changenamemark.setVisible(false);
        changephotomark.setVisible(false);
    }

    public void ChangeLastName(MouseEvent actionEvent) {
        ClientEXE.profile.setLastName(Lastname_field.getText());
        changelastnamemark.setVisible(true);
        changelocationmark.setVisible(false);
        changebirthdatemark.setVisible(false);
        changenamemark.setVisible(false);
        changephotomark.setVisible(false);
    }

    public void ChangeName(MouseEvent actionEvent) {
        ClientEXE.profile.setName(name_field.getText());
        changenamemark.setVisible(true);
        changebirthdatemark.setVisible(false);
        changelastnamemark.setVisible(false);
        changelocationmark.setVisible(false);
        changephotomark.setVisible(false);
    }

    public void goBack(MouseEvent mouseEvent) throws IOException {
        API.updateinfo(ClientEXE.profile,path);
        new PageLoader().load("ProfilePage");
    }

    public void AddProfilePhoto(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        profile_image.setImage(new Image(Paths.get(String.valueOf(selectedFile)).toUri().toString()));
        path=Paths.get(String.valueOf(selectedFile)).toUri().toString();
        InputStream input = new FileInputStream(selectedFile);
        DataInputStream dataInputStream=new DataInputStream(input);
        photo=dataInputStream.readAllBytes();
    }

    public void Changephoto_button(MouseEvent mouseEvent) {
        ClientEXE.profile.setProfilePhoto(photo);
        changephotomark.setVisible(true);
        changenamemark.setVisible(false);
        changebirthdatemark.setVisible(false);
        changelastnamemark.setVisible(false);
        changelocationmark.setVisible(false);
    }
}
