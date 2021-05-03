package GUI.Controllers;

import BE.User;
import GUI.Models.LoginModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class LoginViewController {
    @FXML
    private Button loginButton;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField loginField;

    private User selectedUser;
    private LoginModel loginModel;


    public LoginViewController() throws IOException {
        loginModel = new LoginModel();
    }

    public void handleLoginButton(ActionEvent actionEvent) throws SQLException {
        String writtenLogin = loginField.getText();
        String writtenPassword = passwordField.getText();
        selectedUser = loginModel.getUserFromLogin(writtenLogin,writtenPassword);
        if(selectedUser != null && selectedUser.isAdmin()){
            System.out.println("Admin!");
        } else if (selectedUser != null && !selectedUser.isAdmin()) {
            System.out.println("User!");
        } else {
            System.out.println("Failure!");
        }
    }
}
