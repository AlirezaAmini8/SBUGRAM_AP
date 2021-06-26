package Controller;

import Common.Profile;
import Model.API;
import Model.ClientEXE;
import Model.PageLoader;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class ProfileItemController {
    public Label username_label;
    public ImageView imageview;
    public AnchorPane root;
    Profile profile;

    public ProfileItemController(Profile profile) throws IOException {
        new PageLoader().load("ProfileItem", this);
        this.profile = profile;
    }
    public AnchorPane init() {
        username_label.setText(profile.getUsername());
        if(profile.getProfilePhoto()!=null) {
            imageview.setImage(new Image(new ByteArrayInputStream(profile.getProfilePhoto())));
        }
        return root;
    }
    public void followProfile(ActionEvent actionEvent) throws IOException {
        if(!ClientEXE.profile.equals(profile)) {
            if (ClientEXE.profile.folowings.contains(profile)) {
                Boolean isfollow = API.follow(ClientEXE.profile, profile, false);
                if (!isfollow) {
                    ClientEXE.profile.folowings.remove(profile);
                    ClientEXE.profile.setFollowingsnum(ClientEXE.profile.getFollowingsnum().decrementAndGet());
                    profile.followers.remove(ClientEXE.profile);
                    profile.setFollowersnum(profile.getFollowersnum().decrementAndGet());
                    API.updateprofile(ClientEXE.profile,profile);
                    PageLoader.showalert("SBU GRAM", "Unfollow " + profile.getUsername(), null);
                }
            } else {
                Boolean isfollow = API.follow(ClientEXE.profile, profile, true);
                if (isfollow) {
                    ClientEXE.profile.folowings.add(profile);
                    ClientEXE.profile.setFollowingsnum(ClientEXE.profile.getFollowingsnum().incrementAndGet());
                    profile.followers.add(ClientEXE.profile);
                    profile.setFollowersnum(profile.getFollowersnum().incrementAndGet());
                    API.updateprof(ClientEXE.profile,profile);
                    PageLoader.showalert("SBU GRAM", "Follow " + profile.getUsername(), null);
                }
            }
            API.updateprofile(ClientEXE.profile, profile);
        }else {
            PageLoader.showalert("SBU GRAM", "You can't follow yourself:)" , null);
        }
    }
}
