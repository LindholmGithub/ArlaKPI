package BLL;

import BE.User;
import DAL.DAO.LoginDAO;

import java.io.IOException;
import java.sql.SQLException;

public class LoginManager {
    private LoginDAO loginDAO;

    /**
     * constructor for the LoginManager
     * @throws IOException
     */
    public LoginManager() throws IOException {
        loginDAO = new LoginDAO();
    }

    /**
     * gets a user from given login information
     * @param login
     * @param password
     * @return
     * @throws SQLException
     */
    public User getUserFromLogin(String login, String password) throws SQLException {
        return loginDAO.getUserFromLogin(login,password);
    }
}
