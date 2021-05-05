package GUI.Models;

import BE.User;
import BLL.UserManager;
import GUI.Controllers.AdminMainViewController;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UserModel {
    private UserManager userManager;
    private ObservableList<User> usersList = FXCollections.observableArrayList();
    private ObservableList<User> adminsList = FXCollections.observableArrayList();


    public UserModel() throws IOException {
        userManager = new UserManager();
        usersList.addListener(new ListChangeListener<User>() {
            @Override
            public void onChanged(Change<? extends User> change) {

            }
        });
        adminsList.addListener(new ListChangeListener<User>() {
            @Override
            public void onChanged(Change<? extends User> change) {

            }
        });
    }

    public ObservableList<User> getUsersList() {
        usersList.addAll(userManager.getUsersList());
        return usersList;
    }

    public ObservableList<User> getAdminsList() {
        adminsList.addAll(userManager.getAdminsList());
        return adminsList;
    }
    public void addUser(String fullName,String loginName,String password,boolean isAdmin) throws IOException {
        userManager.addUser(fullName, loginName, password, isAdmin);
        userManager.emptyLists();
        userManager.fillLists();
        AdminMainViewController.emptyStaticLists();
        AdminMainViewController.adminsObservableList.addAll(userManager.getAdminsList());
        AdminMainViewController.usersObservableList.addAll(userManager.getUsersList());
    }

    public void deleteUser(User selectedUser) throws SQLException, IOException {
        userManager.deleteUser(selectedUser);
        userManager.emptyLists();
        userManager.fillLists();
        AdminMainViewController.emptyStaticLists();
        AdminMainViewController.adminsObservableList.addAll(userManager.getAdminsList());
        AdminMainViewController.usersObservableList.addAll(userManager.getUsersList());
    }

    public void emptyLists(){
        usersList.clear();
        adminsList.clear();
    }
}
