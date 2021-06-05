package SBU.Client.Controller;

import SBU.Client.Model.API;
import SBU.Client.Model.PageLoader;
import SBU.Client.Model.Post;
import SBU.Server.ServerEXE;
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
    public static Integer followersnum;
    public static Integer followingsnum;
    public void initialize() {
        profile_image.setImage(new Image(new ByteArrayInputStream(SignUpPageController.profile.getProfilePhoto())));
        name_label.setText(SignUpPageController.profile.getName()+" "+SignUpPageController.profile.getLastName());
        username_label.setText(SignUpPageController.profile.getUserName());
        location_label.setText(SignUpPageController.profile.getLocation());
        birthdate_label.setText(String.valueOf(SignUpPageController.profile.getBirthDate()));
        List<Post> mypostslist=new ArrayList<>();
        for(Post post: ServerEXE.posts){
            if(post.getUsername().equals(SignUpPageController.profile.getUserName())){
                mypostslist.add(post);
            }
        }
        /*followersnum_label.setText(followersnum.toString());
        followingsnum_label.setText(followingsnum.toString());*/

        //show the post array in list view
        listView.setItems(FXCollections.observableArrayList(ServerEXE.posts));

        //customize each cell of postList with new graphic object PostItem
        listView.setCellFactory(postList -> new PostItem());
    }

    public void updateinfo(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("ChangeInfo");
    }

    public void deleteaccount(ActionEvent actionEvent) throws IOException {
        if(API.deleteaccount(SignUpPageController.profile)) {
            new PageLoader().load("Login");
        }
    }

    public void MenuPage(MouseEvent actionEvent) throws IOException {
        new PageLoader().load("MenuPage");
    }
}
