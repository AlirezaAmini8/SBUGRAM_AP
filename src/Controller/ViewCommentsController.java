package Controller;

import Model.*;
import javafx.collections.FXCollections;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class ViewCommentsController {
    public ListView<Message> commentlist;
    public ImageView back_button;
    public ImageView refreshbutton;

    public void initialize() throws IOException {
        //show the comment array in list view
        commentlist.setItems(FXCollections.observableArrayList(API.viewcomments(ClientEXE.toCommentPost)));
        //customize each cell of messageList with new graphic object PostItem
        commentlist.setCellFactory(messageListView-> new MessageItem());
    }
    public void goback(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load(ClientEXE.profile.getWasWhere());
    }

    public void refresh(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("ViewComments");
    }
}
