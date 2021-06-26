package Controller;

import Common.Profile;
import Model.API;
import Model.ClientEXE;
import Model.PageLoader;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.awt.*;
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
        imageview.setImage(new Image(new ByteArrayInputStream(profile.getProfilePhoto())));
        return root;
    }
    public void followProfile(ActionEvent actionEvent) throws IOException {
        if(ClientEXE.profile.folowings.contains(ClientEXE.selectedProfile)){
            Boolean isfollow= API.follow(ClientEXE.profile,ClientEXE.selectedProfile, false);
            if (!isfollow) {
                ClientEXE.profile.folowings.remove(ClientEXE.selectedProfile);
                ClientEXE.profile.setFollowingsnum(ClientEXE.profile.getFollowingsnum().decrementAndGet());
                ClientEXE.selectedProfile.followers.remove(ClientEXE.profile);
                ClientEXE.selectedProfile.setFollowersnum(ClientEXE.selectedProfile.getFollowersnum().decrementAndGet());
                PageLoader.showalert("SBU GRAM","Unfollow "+ClientEXE.selectedProfile.getUsername(),null);
            }
        }else {
            Boolean isfollow=API.follow(ClientEXE.profile, ClientEXE.selectedProfile, true);
            if (isfollow) {
                ClientEXE.profile.folowings.add(ClientEXE.selectedProfile);
                ClientEXE.profile.setFollowingsnum(ClientEXE.profile.getFollowingsnum().incrementAndGet());
                ClientEXE.selectedProfile.followers.add(ClientEXE.profile);
                ClientEXE.selectedProfile.setFollowersnum(ClientEXE.selectedProfile.getFollowersnum().incrementAndGet());
                PageLoader.showalert("SBU GRAM","Follow "+ClientEXE.selectedProfile.getUsername(),null);
            }
        }
        API.updateprofile(ClientEXE.profile,ClientEXE.selectedProfile);
    }
}
