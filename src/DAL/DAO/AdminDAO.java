package DAL.DAO;

import BE.User;
import DAL.DBConnection.JDBCConnectionPool;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminDAO {

    private final JDBCConnectionPool connectionPool;

    public AdminDAO() throws IOException {
        connectionPool = JDBCConnectionPool.getInstance();
    }


    public void addViewToUser(User user, String nameOfFile, String formatType, String fileURL){
        int userID = user.getId();
        String sql = "INSERT INTO UserView (UserID, NameOfFile, FormatType, FileURL) VALUES (?,?,?,?);";
        Connection con = connectionPool.checkOut();
        try(PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            st.setInt(1,userID);
            st.setString(2,nameOfFile);
            st.setString(3,formatType);
            st.setString(4,fileURL);
            st.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            connectionPool.checkIn(con);
        }
    }

    public void deleteViewFromUser(User user, String fileURL){
        int userID = user.getId();
        String sql = "DELETE FROM UserView WHERE UserID = " + userID + "AND fileURL =" + fileURL + ";";
        Connection con = connectionPool.checkOut();
        try(PreparedStatement st = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
            st.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            connectionPool.checkIn(con);
        }
    }
}