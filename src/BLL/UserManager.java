package BLL;

import BE.User;
import DAL.DAO.UserDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private List<User> allUsers;
    private List<User> usersList;
    private List<User> adminsList;
    private UserDAO userDAO;


    /**
     * constructor for the UserManager
     * @throws IOException
     */
    public UserManager() throws IOException {
        this.userDAO = new UserDAO();
        this.fillLists();
    }

    /**
     * method uses the iterator pattern which looks through our list with all users and sorts them into two new lists
     * depending on weather they are admin or not.
     * @throws IOException
     */
    public void fillLists() throws IOException {
        usersList = new ArrayList<>();
        adminsList = new ArrayList<>();
        allUsers = userDAO.getAllUsers();
        for (User u: allUsers) {
            if(u.isAdmin()){
                adminsList.add(u);
            }
            else if(!u.isAdmin()){
                usersList.add(u);
            }
        }
    }

    /**
     * adds a user to the database from given information
     * @param fullName
     * @param loginName
     * @param password
     * @param isAdmin
     */
    public void addUser(String fullName,String loginName,String password,boolean isAdmin){
        userDAO.addUser(fullName,loginName,password,isAdmin);
    }

    /**
     * gets list of non admin users
     * @return
     */
    public List<User> getUsersList() {
        return usersList;
    }

    /**
     * gets a list of admin users
     * @return
     */
    public List<User> getAdminsList() {
        return adminsList;
    }

    /**
     * deletes a selected user
     * @param selectedUser
     * @throws SQLException
     */
    public void deleteUser(User selectedUser) throws SQLException {
        userDAO.deleteUser(selectedUser);
    }

    /**
     * empties all of our lists
     */
    public void emptyLists() {
        allUsers.clear();
        adminsList.clear();
        usersList.clear();
    }

    /**
     * edits a user in the database overriding the information with the given parameters
     * @param selectedUser
     * @param fullName
     * @param loginName
     * @param password
     */
    public void editUser(User selectedUser,String fullName, String loginName, String password) {
        userDAO.editUser(selectedUser,fullName,loginName,password);
    }
}
