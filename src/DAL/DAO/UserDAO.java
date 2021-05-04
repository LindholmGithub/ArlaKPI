package DAL.DAO;

import BE.User;
import DAL.DBConnection.JDBCConnectionPool;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private final JDBCConnectionPool connectionPool;

    public UserDAO() throws IOException {
         connectionPool = JDBCConnectionPool.getInstance();
    }

    public List<User> getAllUsers(){
        ArrayList<User> allUsers = new ArrayList<>(){};

        try (Connection con = connectionPool.checkOut()) {
            String sql = "SELECT * FROM Account;";
            Statement statement = con.createStatement();
            if(statement.execute(sql)){
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()){
                    int id = resultSet.getInt("UserID");
                    String name = resultSet.getString("FullName");
                    boolean isAdmin = resultSet.getBoolean("IsAdmin");
                    User user = new User(id,name,isAdmin);
                    allUsers.add(user);
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return allUsers;
    }

}
