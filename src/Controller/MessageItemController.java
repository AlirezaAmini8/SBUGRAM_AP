package Controller;

import Model.Message;
import Model.PageLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class MessageItemController {
    public AnchorPane root;
    public TextField Description_field;
    public Label writer_label;
    public Label commentTime;

    Message message;

    public MessageItemController(Message message) throws IOException {
        new PageLoader().load("MessageItem", this);
        this.message = message;
    }
    public AnchorPane init() {
        commentTime.setText(message.getTime());
        writer_label.setText(message.getWriter());
        Description_field.setText(message.getDescription());
        return root;
    }
}
