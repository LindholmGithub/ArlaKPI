package GUI.Controllers;

import BE.User;
import GUI.Models.UserModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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

    private User selectedUser;
    private User selectedAdmin;
    private ObservableList<User> usersObservableList;
    private ObservableList<User> adminsObservableList;
    private UserModel userModel;

    public AdminMainViewController() throws IOException {
        try {
            userModel = new UserModel();
            usersList = new ListView();
        } catch (IOException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("An error occurred, please try again!");
            alert.showAndWait();
        }
    }

    public void handleCreateButton(ActionEvent actionEvent) throws IOException {
        try {
            URL urlMoreInfo = new File("src/GUI/Views/CreateNewUserView.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(urlMoreInfo);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("An error has occurred, please try again!");
            alert.showAndWait();
        }
    }

    public void handleDeleteButton(ActionEvent actionEvent) throws SQLException {
        selectedUser = usersList.getSelectionModel().getSelectedItem();
        selectedAdmin = adminsList.getSelectionModel().getSelectedItem();
        try {
            if (selectedUser != null && !selectedUser.isAdmin()) {
                userModel.deleteUser(selectedUser);
            } else if (selectedAdmin != null && selectedAdmin.isAdmin()){
                userModel.deleteUser(selectedAdmin);
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please select a user first!");
                alert.showAndWait();
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void handleEditInfoButton(ActionEvent actionEvent) {
    }

    public void handleEditViewButton(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        adminsList.setItems(userModel.getAdminsList());
        usersList.setItems(userModel.getUsersList());
    }

    public void handleAdminFocus(MouseEvent mouseEvent) {
        usersList.getSelectionModel().clearSelection();
    }

    public void handleUserFocus(MouseEvent mouseEvent) {
        adminsList.getSelectionModel().clearSelection();
    }
}
