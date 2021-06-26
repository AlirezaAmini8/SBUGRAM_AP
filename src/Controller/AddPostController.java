package Controller;

import Common.Profile;
import Common.Time;
import Model.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.*;
import java.nio.file.Paths;

public class AddPostController {
    public byte[] image;
    public ImageView goback_button;
    public TextField title_field;
    public TextField description_field;
    public ImageView post_image;
    public Button Addpost_image;
    public Button publish_button;
    public String photopath;
    Post currentpost=new Post();

    public void goback(MouseEvent actionEvent) throws IOException {
        new PageLoader().load("MenuPage");
    }

    public void Addpost_image(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser=new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        post_image.setImage(new Image(Paths.get(String.valueOf(selectedFile)).toUri().toString()));
        photopath=Paths.get(String.valueOf(selectedFile)).toUri().toString();
        InputStream input = new FileInputStream(selectedFile);
        DataInputStream dataInputStream=new DataInputStream(input);
        image=dataInputStream.readAllBytes();
    }

    public void Publish(ActionEvent actionEvent) throws IOException {
        currentpost.setUsername(ClientEXE.profile.getUsername());
        currentpost.setDescription(description_field.getText());
        if(image!=null){
            currentpost.setPostimage(image);
        }
        currentpost.setTime(Time.getTime());
        currentpost.setTitle(title_field.getText());
        currentpost.setWriter(ClientEXE.profile.getUsername());
        API.addpost(ClientEXE.profile,currentpost,photopath);
        PageLoader.showalert("SBU GRAM", "post added successfully.",null);
        currentpost = new Post();

        //empty fields
        currentpost.setTitle("");
        currentpost.setUsername("");
        currentpost.setTime(null);
        currentpost.setDescription("");
        currentpost.setPostimage(null);
        currentpost.setWriter("");
        description_field.setText("");
        title_field.setText("");
        post_image.setImage(null);
        image=null;
    }
}
