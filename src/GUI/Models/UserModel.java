package GUI.Models;

import BE.User;
import BLL.UserManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.List;

public class UserModel {
    private UserManager userManager;


    public UserModel() throws IOException {
        userManager = new UserManager();
    }

    public ObservableList<User> getUsersList() {
        ObservableList<User> usersList = FXCollections.observableArrayList();
        usersList.addAll(userManager.getUsersList());
        return usersList;
    }

    public ObservableList<User> getAdminsList() {
        ObservableList<User> adminsList = FXCollections.observableArrayList();
        adminsList.addAll(userManager.getAdminsList());
        return adminsList;
    }
}
