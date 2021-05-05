package GUI.Models;

import BE.User;
import BLL.LoginManager;
import BLL.UserManager;

import java.io.IOException;
import java.sql.SQLException;

public class LoginModel {
    private LoginManager loginManager;

    public LoginModel() throws IOException {
        loginManager = new LoginManager();
    }
    public User getUserFromLogin(String login, String password) throws SQLException {
        return loginManager.getUserFromLogin(login,password);
    }
}
