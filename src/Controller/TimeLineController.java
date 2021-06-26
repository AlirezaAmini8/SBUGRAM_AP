package Controller;

import Model.API;
import Model.ClientEXE;
import Model.PageLoader;
import Model.Post;
import Server.ServerEXE;
import javafx.collections.FXCollections;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class TimeLineController {
    public ImageView Menu_button;
    public ImageView refresh_button;
    public ListView<Post> Listview;
    public ImageView search_button;


    public void initialize() {
        ClientEXE.profile.setWasWhere("TimeLine");
        //show the post array in list view
        Listview.setItems(FXCollections.observableArrayList(API.timeLine(ClientEXE.profile)));
        //customize each cell of postList with new graphic object PostItem
        Listview.setCellFactory( postList -> new PostItem());
    }

    public void Menu(MouseEvent actionEvent) throws IOException {
        new PageLoader().load("MenuPage");
    }

    public void refreshPosts(MouseEvent actionEvent) throws IOException {
        new PageLoader().load("TimeLine");
    }

    public void Search(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("Search");
    }
}
