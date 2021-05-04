package DAL.DAO;

import BE.User;
import BLL.UserManager;
import DAL.DBConnection.JDBCConnectionPool;

import java.io.IOException;
import java.sql.*;
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

    public void addUser(String fullName, String loginName, String password, boolean isAdmin) {
        // Mangler implementering
        String sql1 = "INSERT INTO Account (FullName, IsAdmin) VALUES (?,?);";
        Connection con = connectionPool.checkOut();
        try (PreparedStatement st = con.prepareStatement(sql1,Statement.RETURN_GENERATED_KEYS)){
            st.setString(1,fullName);
            st.setBoolean(2,isAdmin);
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            int id = 0;
            if (rs.next()){
                id = rs.getInt(1);
                addLogin(id,loginName,password);
            }

        } catch (SQLException | IOException exception) {
            exception.printStackTrace();
        } finally {
            connectionPool.checkIn(con);
        }

    }

    public void addLogin(int id,String loginName, String password){
        String sql = "Insert Into UserLogin (UsID, LoginName, LoginPassword) VALUES (?,?,?);";
        Connection con = connectionPool.checkOut();
        try(PreparedStatement st = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
            st.setInt(1,id);
            st.setString(2,loginName);
            st.setString(3,password);
            st.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            connectionPool.checkIn(con);
        }
    }
}
