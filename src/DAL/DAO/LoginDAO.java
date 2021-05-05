package DAL.DAO;

import BE.User;
import DAL.DBConnection.JDBCConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

    private int id;
    private String fullName;
    private boolean isAdmin;


    private final JDBCConnectionPool connectionPool;

    /**
     * Constuctor for the JDBCConnectionPool.
     * @param connectionPool
     */
    public LoginDAO(JDBCConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    /**
     * This method returns a User-object from two given Strings, essentially a Username and a Password.
     * @param login
     * @param password
     * @return
     * @throws SQLException
     */

    public User getUserFromLogin(String login, String password) throws SQLException {
        Connection connection = connectionPool.checkOut();
        String sql = "Select * from Account INNER JOIN UserLogin ON Account.UserID = Userlogin.UsID WHERE UserLogin.LoginName = ? AND UserLogin.LoginPassword = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, login);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();
        User user = null;
        if (resultSet.next()) {
            id = resultSet.getInt("UserId");
            fullName = resultSet.getString("FullName");
            isAdmin = resultSet.getBoolean("IsAdmin");
            user = new User(id,fullName,isAdmin);
        }
        return user;
    }
}
