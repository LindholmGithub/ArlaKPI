package GUI.Controllers;
import BE.FileInfo;
import BE.User;
import GUI.Models.FileModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserSelectViewController implements Initializable {

    @FXML
    private ListView<FileInfo> listOfViews;
    @FXML
    private Button loadButton;
    @FXML
    private Button logoutButton;

    private User selectedUser;
    private FileModel fileModel;
    private static FileInfo selectedFileInfo;

    /**
     * This is the initialize method.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            fileModel = new FileModel();
            listOfViews.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            selectedUser = LoginViewController.getSelectedUser();
            listOfViews.getItems().setAll(fileModel.getAllViewsForUser(selectedUser));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    /**
     * This method handles the load button. It lets the user load the selected views.
     * It loads different views based on the file extensions through switch cases.
     * @param actionEvent
     */
    public void handleLoadButton(ActionEvent actionEvent) {
        selectedFileInfo = listOfViews.getSelectionModel().getSelectedItem();
        String fileType = selectedFileInfo.getFileType();
        try {
            switch (fileType){
                case "pdf":
                    URL pdfUrl = new File("src/GUI/Views/UserPDFView.fxml").toURI().toURL();
                    Parent pdfRoot = FXMLLoader.load(pdfUrl);
                    Scene pdfScene = new Scene(pdfRoot,800,600);
                    Stage pdfStage = new Stage();
                    pdfStage.setScene(pdfScene);
                    pdfStage.show();
                    break;
                case "csv":
                    URL csvUrl = new File("src/GUI/Views/UserCSVView.fxml").toURI().toURL();
                    Parent csvRoot = FXMLLoader.load(csvUrl);
                    Scene csvScene = new Scene(csvRoot,800,600);
                    Stage csvStage = new Stage();
                    csvStage.setScene(csvScene);
                    csvStage.show();
                    break;
                case "html":
                    URL htmlUrl = new File("src/GUI/Views/UserHTMLView.fxml").toURI().toURL();
                    Parent htmlRoot = FXMLLoader.load(htmlUrl);
                    Scene htmlScene = new Scene(htmlRoot,800,600);
                    Stage htmlStage = new Stage();
                    htmlStage.setScene(htmlScene);
                    htmlStage.show();
                    break;
                case "xlsx":
                    URL xlsxUrl = new File("src/GUI/Views/UserXLSXView.fxml").toURI().toURL();
                    Parent xlsxRoot = FXMLLoader.load(xlsxUrl);
                    Scene xlsxScene = new Scene(xlsxRoot,800,600);
                    Stage xlsxStage = new Stage();
                    xlsxStage.setScene(xlsxScene);
                    xlsxStage.show();
                    break;
                default:
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Error, please select an item on the list");
                    alert.showAndWait();
            }
        } catch (IOException e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("No file was found");
            alert.showAndWait();
        }
    }

    /**
     * Returns the selected FileInfo object from a list when called.
     * @return selectedFileInfo
     */
    public static FileInfo getSelectedFileInfo(){
        return selectedFileInfo;
    }

    /**
     * This method handles the logout button. It lets the user log out to the Login View.
     * @param actionEvent
     * @throws IOException
     */
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
