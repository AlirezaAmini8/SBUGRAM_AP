package Controller;

import Common.Profile;
import Common.Time;
import Model.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class AddCommentController {
    public ImageView goback_button;
    public TextField Description_field;
    public Button publish_button;
    Profile profile=ClientEXE.getProfile();
    Message currentcomment=new Message();

    public void goback(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load(ClientEXE.profile.getWasWhere());
    }

    public void Publish(ActionEvent actionEvent) {
        currentcomment.setWriter(profile.getUserName());
        currentcomment.setDescription(Description_field.getText());
        currentcomment.setTime(Time.getTime());
        API.addcomment(ClientEXE.toCommentPost, currentcomment);
        PageLoader.showalert("SBU GRAM", "comment added successfully.", null);
        currentcomment = new Message();
        currentcomment.setWriter("");
        currentcomment.setDescription("");
        currentcomment.setTime(null);
        Description_field.setText("");
    }
}
