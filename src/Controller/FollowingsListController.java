package Controller;

import Common.Profile;
import Model.API;
import Model.ClientEXE;
import Model.PageLoader;
import javafx.collections.FXCollections;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class FollowingsListController {

    public ImageView back_button;
    public ListView<Profile> listview;
    public void initialize() {
        //show the post array in list view
        listview.setItems(FXCollections.observableArrayList(API.followingslist(ClientEXE.selectedProfile)));
        //customize each cell of postList with new graphic object PostItem
        listview.setCellFactory( postList -> new ProfileItem());
    }
    public void goBack(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load(ClientEXE.profile.getWasWhere());
    }
}
