package GUI.Controllers;

import BE.User;
import DAL.DAO.CSVDAO;
import GUI.Models.LoginModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginViewController implements Initializable {
    @FXML
    private Button loginButton;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField loginField;

    private User selectedUser;
    private LoginModel loginModel;
    private CSVDAO csvdao;

    public LoginViewController() throws IOException {
        loginModel = new LoginModel();
        csvdao = new CSVDAO();
    }

    public void handleLoginButton(ActionEvent actionEvent) throws SQLException, IOException {
        Stage thisStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        String writtenLogin = loginField.getText();
        String writtenPassword = passwordField.getText();
        selectedUser = loginModel.getUserFromLogin(writtenLogin,writtenPassword);
        try {
            if(selectedUser != null && selectedUser.isAdmin()){
                URL adminUrl = new File("src/GUI/Views/AdminMainView.fxml").toURI().toURL();
                Parent root = FXMLLoader.load(adminUrl);
                Scene adminViewScene = new Scene(root,1270,720);
                thisStage.setScene(adminViewScene);
                thisStage.hide();
                thisStage.show();
            }
            else if (selectedUser != null && !selectedUser.isAdmin()) {
                URL userUrl = new File("src/GUI/Views/UserMainView.fxml").toURI().toURL();
                Parent root = FXMLLoader.load(userUrl);
                Scene adminViewScene = new Scene(root,1270,720);
                thisStage.setScene(adminViewScene);
                thisStage.hide();
                thisStage.show();
            }
        } catch (IOException ioException){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Wrong login, please try again!");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                loginButton.requestFocus();
            }
        });
    }
}
