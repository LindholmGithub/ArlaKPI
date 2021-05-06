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

    public UserManager() throws IOException {
        this.userDAO = new UserDAO();
        this.fillLists();
    }

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
    public void addUser(String fullName,String loginName,String password,boolean isAdmin){
        userDAO.addUser(fullName,loginName,password,isAdmin);
    }

    public List<User> getUsersList() {
        return usersList;
    }

    public List<User> getAdminsList() {
        return adminsList;
    }

    public void deleteUser(User selectedUser) throws SQLException {
        userDAO.deleteUser(selectedUser);
    }
    public void emptyLists() {
        allUsers.clear();
        adminsList.clear();
        usersList.clear();
    }

    public void editUser(User selectedUser,String fullName, String loginName, String password) {
        userDAO.editUser(selectedUser,fullName,loginName,password);
    }
}
