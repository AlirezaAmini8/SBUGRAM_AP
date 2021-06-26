package Controller;

import Common.Profile;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class ProfileItem extends ListCell<Profile> {
    @Override
    public void updateItem(Profile profile, boolean empty) {
        super.updateItem(profile, empty);
        if (profile != null) {
            try {
                setGraphic(new ProfileItemController(profile).init());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
