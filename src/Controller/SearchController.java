package Controller;

import Common.Profile;
import Model.API;
import Model.ClientEXE;
import Model.PageLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.TextField;

public class SearchController implements Initializable {
    public ImageView back_button;
    public TextField username_field;
    @FXML
    private TableView<Profile> tableView;
    @FXML
    private TableColumn<Profile,String> username;
    @FXML
    private TableColumn<Profile,String> name;
    @FXML
    private TableColumn<Profile,String> lastname;
    @FXML
    private TableColumn<Profile,String> location;

    private final ObservableList<Profile> dataList=FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ClientEXE.profile.setWasWhere("Search");
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        lastname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        location.setCellValueFactory(new PropertyValueFactory<>("location"));


        dataList.addAll(API.getprofiles());

        FilteredList<Profile> filteredData = new FilteredList<>(dataList, b -> true);

        username_field.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(user -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();
                if (user.getUsername().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (user.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (user.getLastName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if(user.getLocation().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }else {
                    return false;
                }
            });
        });

        SortedList<Profile> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(tableView.comparatorProperty());

        tableView.setItems(sortedData);
    }
    public void clickItem(MouseEvent event) throws IOException {
        if (event.getClickCount() == 2)
        {
            ClientEXE.othersprofile = tableView.getSelectionModel().getSelectedItem();
            new PageLoader().load("OthersProfilePage");
        }
    }

    public void goBack(MouseEvent actionEvent) throws IOException {
        new PageLoader().load("TimeLine");
    }
}
