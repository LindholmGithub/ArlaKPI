package GUI.Controllers;
import BE.User;
import GUI.Models.FileModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserSelectViewController implements Initializable {

    @FXML
    private ListView listOfViews;
    @FXML
    private Button loadButton;
    @FXML
    private Button logoutButton;
    @FXML
    private ChoiceBox<Integer> updateChoiceBox;
    private User selectedUser;
    private FileModel fileModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            fileModel = new FileModel();
            updateChoiceBox.getItems().setAll(1,2,3,4,5);
            listOfViews.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            selectedUser = LoginViewController.getSelectedUser();
            listOfViews.getItems().setAll(fileModel.getAllViewsForUser(selectedUser));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void handleLoadButton(ActionEvent actionEvent) {
        System.out.println(listOfViews.getSelectionModel().getSelectedItems());
    }

    public void handleLogoutButton(ActionEvent actionEvent) throws IOException {
        Stage thisStage = (Stage) logoutButton.getScene().getWindow();
        URL userUrl = new File("src/GUI/Views/LoginView.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(userUrl);
        Scene scene = new Scene(root,1270,720);
        Stage stage = new Stage();
        stage.setTitle("Arla KPI");
        stage.getIcons().add(new Image("Resources/Images/ArlaLogo100x100.png"));
        stage.setMinWidth(350);
        stage.setMinHeight(470);
        stage.setScene(scene);
        thisStage.hide();
        stage.show();
    }
}
