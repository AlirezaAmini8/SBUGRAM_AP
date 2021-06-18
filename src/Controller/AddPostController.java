package Controller;

import Common.Profile;
import Model.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.*;
import java.nio.file.Paths;

public class AddPostController {
    public static byte[] image;
    public ImageView goback_button;
    public TextField title_field;
    public TextField description_field;
    public ImageView post_image;
    public Button Addpost_image;
    public Button publish_button;
    public Label successful_label;
    public Label Repetitious;
    Profile profile=ClientEXE.getProfile();
    Post currentpost=new Post();

    public void goback(MouseEvent actionEvent) throws IOException {
        new PageLoader().load("MenuPage");
    }

    public void Addpost_image(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser=new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        post_image.setImage(new Image(Paths.get(String.valueOf(selectedFile)).toUri().toString()));
        InputStream input = new FileInputStream(selectedFile);
        DataInputStream dataInputStream=new DataInputStream(input);
        image=dataInputStream.readAllBytes();
    }

    public void Publish(ActionEvent actionEvent) {
        for (int i = 0; i < ClientEXE.allpostsList.size(); i++) {
            if (currentpost.equals(ClientEXE.allpostsList.get(i))) {
                successful_label.setVisible(false);
                Repetitious.setVisible(true);
            }
        }
        if (!Repetitious.isVisible()) {
            currentpost.setUsername(profile.getUserName());
            currentpost.setDescription(description_field.getText());
            currentpost.setPostimage(image);
            currentpost.setDate(java.time.LocalDate.now());
            currentpost.setTime(java.time.LocalTime.now());
            currentpost.setTitle(title_field.getText());
            if(API.addpost(SignUpPageController.profile,currentpost)){
                successful_label.setVisible(true);
            }else{
                successful_label.setVisible(false);
            }
            currentpost = new Post();

            //empty fields
            currentpost.setTitle("");
            currentpost.setUsername("");
            currentpost.setDate(null);
            currentpost.setTime(null);
            currentpost.setDescription("");
            currentpost.setPostimage(null);
        }
        Repetitious.setVisible(false);
    }
}
