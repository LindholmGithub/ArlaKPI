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
import java.util.Optional;
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

    /**
     * This is the initialize method.
     * @param url
     * @param resourceBundle
     */
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

    /**
     * This method handles the log out button. It let's the user log out of the current account.
     * @param actionEvent
     */
    public void handleLogoutButton(ActionEvent actionEvent) {
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.close();
    }

    /**
     * This method handles the delete button. It lets the user delete a selected View from their list.
     * @param actionEvent
     */
    public void handleDeleteButton(ActionEvent actionEvent) {
        selectedFile = listOfViews.getSelectionModel().getSelectedItem();
        if (selectedFile != null) {
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setTitle("View deletion");
            confirm.setContentText("Are you sure you want to delete the selected view?");
            Optional<ButtonType> result = confirm.showAndWait();
            ButtonType button = result.orElse(ButtonType.CANCEL);
            if (button == ButtonType.OK) {
                fileModel.deleteViewFromUser(selectedUser,selectedFile.getFilePath());
            } else {
                confirm.close();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select something from the list.");
            alert.showAndWait();
        }
    }

    /**
     * This method handles the create button. This lets the user create a new view.
     * @param actionEvent
     * @throws IOException
     */
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

    /**
     * This method clears the list of views.
     */
    public static void clearStaticList(){
        observableListOfViews.clear();
    }
}
