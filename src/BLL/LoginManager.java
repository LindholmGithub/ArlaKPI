package BLL;

import BE.User;
import DAL.DAO.LoginDAO;

import java.io.IOException;
import java.sql.SQLException;

public class LoginManager {
    private LoginDAO loginDAO;

    public LoginManager() throws IOException {
        loginDAO = new LoginDAO();
    }
    public User getUserFromLogin(String login, String password) throws SQLException {
        return loginDAO.getUserFromLogin(login,password);
    }
}
