package GUI.Models;

import BE.User;
import BLL.LoginManager;
import BLL.UserManager;

import java.io.IOException;
import java.sql.SQLException;

public class LoginModel {
    private LoginManager loginManager;

    /**
     * This is the constructor for the LoginModel.
     * @throws IOException
     */
    public LoginModel() throws IOException {
        loginManager = new LoginManager();
    }

    /**
     * This method retrieves a user from a given login. Essentially validating user login info.
     * @param login
     * @param password
     * @return
     * @throws SQLException
     */
    public User getUserFromLogin(String login, String password) throws SQLException {
        return loginManager.getUserFromLogin(login,password);
    }
}
