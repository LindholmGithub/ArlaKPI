package BLL;

import BE.User;
import DAL.DAO.UserDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private List<User> allUsers;
    private List<User> usersList;
    private List<User> adminsList;
    private UserDAO userDAO;
    public UserManager() throws IOException {
        this.fillLists();
        userDAO = new UserDAO();
    }

    public void addUser(String fullName,String loginName,String password,boolean isAdmin){
        userDAO.addUser(fullName,loginName,password,isAdmin);
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

    public List<User> getUsersList() {
        return usersList;
    }

    public List<User> getAdminsList() {
        return adminsList;
    }
}
