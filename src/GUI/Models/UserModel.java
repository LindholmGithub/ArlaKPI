package GUI.Models;

import BE.User;
import BLL.UserManager;
import GUI.Controllers.AdminMainViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.IOException;
import java.sql.SQLException;

public class UserModel {
    private UserManager userManager;
    private ObservableList<User> usersList = FXCollections.observableArrayList();
    private ObservableList<User> adminsList = FXCollections.observableArrayList();

    /**
     * This is the constructor for the UserModel.
     * @throws IOException
     */
    public UserModel() throws IOException {
        userManager = new UserManager();
    }

    /**
     * This retrieves the list of users.
     * @return
     */
    public ObservableList<User> getUsersList() {
        usersList.addAll(userManager.getUsersList());
        return usersList;
    }

    /**
     * This retrieves the list of admins.
     * @return
     */
    public ObservableList<User> getAdminsList() {
        adminsList.addAll(userManager.getAdminsList());
        return adminsList;
    }

    /**
     * This method adds a user/admin to their respective lists.
     * Then empties and fills the static lists again to trigger the listener, to refresh the lists in the GUI.
     *
     * @param fullName
     * @param loginName
     * @param password
     * @param isAdmin
     * @throws IOException
     */
    public void addUser(String fullName,String loginName,String password,boolean isAdmin) throws IOException {
        userManager.addUser(fullName, loginName, password, isAdmin);
        userManager.emptyLists();
        userManager.fillLists();
        AdminMainViewController.emptyStaticLists();
        AdminMainViewController.adminsObservableList.addAll(userManager.getAdminsList());
        AdminMainViewController.usersObservableList.addAll(userManager.getUsersList());
    }

    /**
     * This method makes it possible to edit a user.
     * Then empties and fills the static lists again to trigger the listener, to refresh the lists in the GUI.
     * @param selectedUser
     * @param fullName
     * @param loginName
     * @param password
     * @throws IOException
     */
    public void editUser(User selectedUser,String fullName, String loginName, String password) throws IOException {
        userManager.editUser(selectedUser,fullName,loginName,password);
        userManager.emptyLists();
        userManager.fillLists();
        AdminMainViewController.emptyStaticLists();
        AdminMainViewController.adminsObservableList.addAll(userManager.getAdminsList());
        AdminMainViewController.usersObservableList.addAll(userManager.getUsersList());
    }

    /**
     * This method makes it possible to delete a user.
     * Then empties and fills the static lists again to trigger the listener, to refresh the lists in the GUI.
     * @param selectedUser
     * @throws SQLException
     * @throws IOException
     */
    public void deleteUser(User selectedUser) throws SQLException, IOException {
        userManager.deleteUser(selectedUser);
        userManager.emptyLists();
        userManager.fillLists();
        AdminMainViewController.emptyStaticLists();
        AdminMainViewController.adminsObservableList.addAll(userManager.getAdminsList());
        AdminMainViewController.usersObservableList.addAll(userManager.getUsersList());
    }

}
