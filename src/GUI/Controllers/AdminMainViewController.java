package GUI.Controllers;

import BE.User;
import GUI.Models.UserModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
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
            alert.setContentText("An error has occured, please try again!");
            alert.showAndWait();
        }
    }

    public void handleDeleteButton(ActionEvent actionEvent) {
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
}
