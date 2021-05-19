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

    /**
     * constructor to instanciate our connectionpool
     * @throws IOException
     */
    public AdminDAO() throws IOException {
        connectionPool = JDBCConnectionPool.getInstance();
    }

    /**
     * Adds a view to a user in the Database with the given attributes corresponding with the database schema
     * @param user
     * @param nameOfFile
     * @param formatType
     * @param fileURL
     */
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

    /**
     * deletes a specific view from a given user, using the ID of the user & the URL to the file to which the file is attached to by deleting
     * the entry in the database which corresponds with the given paramaters
     * @param user
     * @param fileURL
     */
    public void deleteViewFromUser(User user, String fileURL){
        int userID = user.getId();
        String sql = "DELETE FROM UserView WHERE UserID = " + userID + "AND FileURL =" + "'" + fileURL + "'" + ";";
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

/*

                             ;\
                            |' \
         _                  ; : ;
        / `-.              /: : |
       |  ,-.`-.          ,': : |
       \  :  `. `.       ,'-. : |
        \ ;    ;  `-.__,'    `-.|
         \ ;   ;  :::  ,::'`:.  `.
          \ `-. :  `    :.    `.  \
           \   \    ,   ;   ,:    (\
            \   :., :.    ,'o)): ` `-.
           ,/,' ;' ,::"'`.`---'   `.  `-._
         ,/  :  ; '"      `;'          ,--`.
        ;/   :; ;             ,:'     (   ,:)
          ,.,:.    ; ,:.,  ,-._ `.     \""'/
          '::'     `:'`  ,'(  \`._____.-'"'
             ;,   ;  `.  `. `._`-.  \\
             ;:.  ;:       `-._`-.\  \`. 
              '`:. :        |' `. `\  ) \
      -hrr-      ` ;:       |    `--\__,'
                   '`      ,'
                        ,-'

 */