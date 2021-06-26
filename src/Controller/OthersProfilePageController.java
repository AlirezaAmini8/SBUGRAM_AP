package Controller;

import Model.API;
import Model.ClientEXE;
import Model.PageLoader;
import Model.Post;
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

public class OthersProfilePageController {
    public ImageView back_button;
    public ImageView profile_image;
    public Label name_label;
    public Label username_label;
    public Label location_label;
    public Label birthdate_label;
    public Label followersnum_label;
    public Label followingsnum_label;
    public ListView<Post> listview;
    public Button follow_button;
    public Label followers;
    public Label followings;

    public void initialize() {
        ClientEXE.profile.setWasWhere("OthersProfilePage");
        profile_image.setImage(new Image(new ByteArrayInputStream(ClientEXE.othersprofile.getProfilePhoto())));
        name_label.setText(ClientEXE.othersprofile.getName()+" "+ClientEXE.othersprofile.getLastName());
        username_label.setText(ClientEXE.othersprofile.getUsername());
        location_label.setText(ClientEXE.othersprofile.getLocation());
        birthdate_label.setText(String.valueOf(ClientEXE.othersprofile.getBirthDate()));
        followersnum_label.setText(ClientEXE.othersprofile.followersnum.toString());
        followingsnum_label.setText(ClientEXE.othersprofile.followingsnum.toString());
        API.getinfo(ClientEXE.profile,ClientEXE.othersprofile,ClientEXE.othersprofile.getPath());

        //show the post array in list view
        listview.setItems(FXCollections.observableArrayList(API.getmyposts(ClientEXE.othersprofile)));

        //customize each cell of postList with new graphic object PostItem
        listview.setCellFactory(postList -> new PostItem());
    }
    public void goBack(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("Search");
    }

    public void Follow(ActionEvent actionEvent) {
        if(ClientEXE.profile.folowings.contains(ClientEXE.othersprofile)){
            Boolean isfollow=API.follow(ClientEXE.profile,ClientEXE.othersprofile, false);
            if (!isfollow) {
                ClientEXE.profile.folowings.remove(ClientEXE.othersprofile);
                ClientEXE.profile.setFollowingsnum(ClientEXE.profile.getFollowingsnum().decrementAndGet());
                ClientEXE.othersprofile.followers.remove(ClientEXE.profile);
                ClientEXE.othersprofile.setFollowersnum(ClientEXE.othersprofile.getFollowersnum().decrementAndGet());
                PageLoader.showalert("SBU GRAM","Unfollow "+ClientEXE.othersprofile.getUsername(),null);
                followingsnum_label.setText(ClientEXE.othersprofile.followersnum.toString());
            }
        }else {
            Boolean isfollow=API.follow(ClientEXE.profile, ClientEXE.othersprofile, true);
            if (isfollow) {
                ClientEXE.profile.folowings.add(ClientEXE.othersprofile);
                ClientEXE.profile.setFollowingsnum(ClientEXE.profile.getFollowingsnum().incrementAndGet());
                ClientEXE.othersprofile.followers.add(ClientEXE.profile);
                ClientEXE.othersprofile.setFollowersnum(ClientEXE.othersprofile.getFollowersnum().incrementAndGet());
                PageLoader.showalert("SBU GRAM","Follow "+ClientEXE.othersprofile.getUsername(),null);
                followingsnum_label.setText(ClientEXE.othersprofile.followersnum.toString());
            }
        }
        API.updateprofile(ClientEXE.profile,ClientEXE.othersprofile);
    }

    public void followersList(MouseEvent mouseEvent) throws IOException {
        ClientEXE.selectedProfile=ClientEXE.othersprofile;
        new PageLoader().load("FollowersList");
    }

    public void followingsList(MouseEvent mouseEvent) throws IOException {
        ClientEXE.selectedProfile=ClientEXE.othersprofile;
        new PageLoader().load("FollowingsList");
    }
}
