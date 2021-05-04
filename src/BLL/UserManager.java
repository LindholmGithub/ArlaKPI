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
    public UserManager() throws IOException {
        this.fillLists();
    }

    public void fillLists() throws IOException {
        UserDAO uDAO = new UserDAO();
        usersList = new ArrayList<>();
        adminsList = new ArrayList<>();
        allUsers = uDAO.getAllUsers();
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
