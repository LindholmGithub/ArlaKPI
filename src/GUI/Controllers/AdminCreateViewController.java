package GUI.Controllers;

import BE.FileInfo;
import BE.User;
import GUI.Models.FileModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminCreateViewController implements Initializable {

    @FXML
    private TextField fileDisplayName;
    @FXML
    private TextField filePathField;
    @FXML
    private Button browseFilesButton;
    @FXML
    private Button createViewButton;
    @FXML
    private Button cancelCreateViewButton;
    private String selectedName;
    private String selectedFilePath;
    private String selectedFileType;
    private User selectedUser;
    private FileModel fileModel;


    public void handleBrowseFilesButton(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select data files");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Data Files",
                "*.pdf", "*.xlsx", "*.html", "*.csv"));
        List<File> file = fileChooser.showOpenMultipleDialog(new Stage());
        if (!file.isEmpty()){
            filePathField.setText(file.get(0).getPath());
        }
    }

    public void handleCreateViewButton(ActionEvent actionEvent) {
        selectedName = fileDisplayName.getText();
        selectedFilePath = filePathField.getText();
        selectedFileType = filePathField.getText().substring(selectedFilePath.lastIndexOf(".")+1);
        fileModel.addViewToUser(selectedUser,selectedName,selectedFileType,selectedFilePath);
        Stage stage = (Stage) createViewButton.getScene().getWindow();
        stage.close();
    }

    public void handleCancelCreateViewButton(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelCreateViewButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectedUser = AdminMainViewController.getSelectedUser();
        try {
            fileModel = new FileModel();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
