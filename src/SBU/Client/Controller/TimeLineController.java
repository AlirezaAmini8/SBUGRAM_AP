package SBU.Client.Controller;

import SBU.Client.Model.PageLoader;
import SBU.Client.Model.Post;
import SBU.Server.ServerEXE;
import javafx.collections.FXCollections;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class TimeLineController {
    public ImageView Menu_button;
    public ImageView refresh_button;
    public ListView<Post> Listview;


    public void initialize() {
        //show the post array in list view
        Listview.setItems(FXCollections.observableArrayList(ServerEXE.posts));

        //customize each cell of postList with new graphic object PostItem
        Listview.setCellFactory(postList -> new PostItem());
    }

    public void Menu(MouseEvent actionEvent) throws IOException {
        new PageLoader().load("MenuPage");
    }

    public void refreshPosts(MouseEvent actionEvent) {

        initialize();    }
}
