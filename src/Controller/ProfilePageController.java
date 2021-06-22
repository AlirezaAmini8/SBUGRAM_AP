package Controller;

import Model.*;
import Server.ServerEXE;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProfilePageController {
    public ImageView profile_image;
    public Label name_label;
    public Label username_label;
    public Label location_label;
    public Label birthdate_label;
    public Label followersnum_label;
    public Label followingsnum_label;
    public Button updateinfo_button;
    public Button deleteaccount_button;
    public ImageView ManuPage_button;
    public ListView<Post> listView;

    public void initialize() {
        profile_image.setImage(new Image(new ByteArrayInputStream(ClientEXE.profile.getProfilePhoto())));
        name_label.setText(ClientEXE.profile.getName()+" "+ClientEXE.profile.getLastName());
        username_label.setText(ClientEXE.profile.getUserName());
        location_label.setText(ClientEXE.profile.getLocation());
        birthdate_label.setText(String.valueOf(ClientEXE.profile.getBirthDate()));
        followersnum_label.setText(ClientEXE.profile.followersnum.toString());
        followingsnum_label.setText(ClientEXE.profile.followingsnum.toString());

        //show the post array in list view
        listView.setItems(FXCollections.observableArrayList(API.getmyposts(ClientEXE.getProfile())));

        //customize each cell of postList with new graphic object PostItem
        listView.setCellFactory(postList -> new PostItem());
    }

    public void updateinfo(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("ChangeInfo");
    }

    public void deleteaccount(ActionEvent actionEvent) throws IOException {
        API.deleteaccount(ClientEXE.profile);
        ClientNetworker.disconnectFromServer();
        ClientEXE.profile = null;
        new PageLoader().load("Login");
    }

    public void MenuPage(MouseEvent actionEvent) throws IOException {
        new PageLoader().load("MenuPage");
    }
}
