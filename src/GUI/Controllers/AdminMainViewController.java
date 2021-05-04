package GUI.Controllers;

import BE.User;
import GUI.Models.UserModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminMainViewController implements Initializable {
    @FXML
    private ListView<User> usersList;
    @FXML
    private ListView<User> adminsList;
    @FXML
    private Button createButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button editInfoButton;
    @FXML
    private Button editViewButton;

    private ObservableList<User> usersObservableList;
    private ObservableList<User> adminsObservableList;
    private UserModel userModel;

    public AdminMainViewController() throws IOException {
        try {
            userModel = new UserModel();
            usersList = new ListView();
        } catch (IOException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("An error occured, please try again!");
            alert.showAndWait();
        }
    }

    public void handleCreateButton(ActionEvent actionEvent) {
    }

    public void handleDeleteButton(ActionEvent actionEvent) {
    }

    public void handleEditInfoButton(ActionEvent actionEvent) {
    }

    public void handleEditViewButton(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usersObservableList = userModel.getUsersList();
        adminsObservableList = userModel.getUsersList();
        adminsList.setItems(adminsObservableList);
    }
}
