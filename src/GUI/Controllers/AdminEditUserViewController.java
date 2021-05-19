package GUI.Controllers;

import BE.FileInfo;
import BE.User;
import GUI.Models.FileModel;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminEditUserViewController implements Initializable {

    @FXML
    private ListView<FileInfo> listOfViews;
    @FXML
    private Button loadButton;
    @FXML
    private Button logoutButton;
    public static ObservableList<FileInfo> observableListOfViews;
    private FileModel fileModel;
    private User selectedUser;
    private FileInfo selectedFile;
    private String selectedFileName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            fileModel = new FileModel();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        observableListOfViews = FXCollections.observableArrayList();
        listOfViews.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        selectedUser = AdminMainViewController.getSelectedUser();
        listOfViews.setItems(fileModel.getAllViewsForUser(selectedUser));
        observableListOfViews.addListener(new ListChangeListener<FileInfo>() {
            @Override
            public void onChanged(Change<? extends FileInfo> change) {
                listOfViews.setItems(observableListOfViews);
            }
        });
    }

    public void handleLogoutButton(ActionEvent actionEvent) {
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.close();
    }

    public void handleDeleteButton(ActionEvent actionEvent) {
        selectedFile = listOfViews.getSelectionModel().getSelectedItem();
        if (selectedFile != null) {
            fileModel.deleteViewFromUser(selectedUser,selectedFile.getFilePath());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select something from the list.");
            alert.showAndWait();
        }
    }

    public void handleCreateButton(ActionEvent actionEvent) throws IOException {
        URL userUrl = new File("src/GUI/Views/AdminCreateView.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(userUrl);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Create User View");
        stage.getIcons().add(new Image("Resources/Images/ArlaLogo100x100.png"));
        stage.setMinWidth(350);
        stage.setMinHeight(470);
        stage.setScene(scene);
        stage.show();
    }
    public static void clearStaticList(){
        observableListOfViews.clear();
    }
}
