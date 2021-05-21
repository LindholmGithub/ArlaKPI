package GUI.Controllers;
import BE.User;
import GUI.Models.UserModel;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdminMainViewController implements Initializable {
    @FXML
    private Button adminLogoutButton;
    @FXML
    private ListView<User> usersList;
    @FXML
    private ListView<User> adminsList;
    @FXML
    private Button createButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button editInfoButton;
    @FXML
    private Button editViewButton;

    private static User selectedUser;
    private static User selectedAdmin;
    public static ObservableList<User> usersObservableList;
    public static ObservableList<User> adminsObservableList;
    private UserModel userModel;

    /**
     * This is the constructor for the AdminMainViewController.
     * @throws IOException
     */
    public AdminMainViewController() throws IOException {
        try {
            userModel = new UserModel();
            usersObservableList = FXCollections.observableArrayList();
            adminsObservableList = FXCollections.observableArrayList();
        } catch (IOException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("An error occurred, please try again!");
            alert.showAndWait();
        }
    }

    /**
     * This method handles the create button in the admin view.
     * @param actionEvent
     * @throws IOException
     */
    public void handleCreateButton(ActionEvent actionEvent) throws IOException {
        try {
            URL urlMoreInfo = new File("src/GUI/Views/CreateNewUserView.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(urlMoreInfo);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("An error has occurred, please try again!");
            alert.showAndWait();
        }
    }

    /**
     * This method handles the delete button. It lets an admin delete a user.
     * @param actionEvent
     * @throws SQLException
     */
    public void handleDeleteButton(ActionEvent actionEvent) throws SQLException {
        selectedUser = usersList.getSelectionModel().getSelectedItem();
        selectedAdmin = adminsList.getSelectionModel().getSelectedItem();
        try {
            if (selectedUser != null || selectedAdmin != null) {
                Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
                confirm.setTitle("User deletion");
                confirm.setContentText("Are you sure you want to delete the selected user?");
                Optional<ButtonType> result = confirm.showAndWait();
                ButtonType button = result.orElse(ButtonType.CANCEL);
                if (button == ButtonType.OK) {
                    if (selectedUser != null) {
                        userModel.deleteUser(selectedUser);
                    }
                    else if (selectedAdmin != null) {
                        userModel.deleteUser(selectedAdmin);
                    }
                } else {
                    confirm.close();
                }
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please select a user first!");
                alert.showAndWait();
            }
        } catch (SQLException | IOException sqlException) {
            sqlException.printStackTrace();
        }
    }

    /**
     * This method handles the edit info button. It lets an Admin edit user info (login.. etc.)
     * @param actionEvent
     */
    public void handleEditInfoButton(ActionEvent actionEvent) {
        selectedUser = usersList.getSelectionModel().getSelectedItem();
        selectedAdmin = adminsList.getSelectionModel().getSelectedItem();
        try {
            if (selectedUser != null || selectedAdmin != null) {
                URL urlMoreInfo = new File("src/GUI/Views/EditUserInfoView.fxml").toURI().toURL();
                Parent root = FXMLLoader.load(urlMoreInfo);
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(scene);
                stage.show();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please select a user first!");
                alert.showAndWait();
            }
        } catch (IOException sqlException) {
            sqlException.printStackTrace();
        }
    }

    /**
     * This method handles the edit view button. It lets an admin set the views for a specific user.
     * @param actionEvent
     */
    public void handleEditViewButton(ActionEvent actionEvent) {
        selectedUser = usersList.getSelectionModel().getSelectedItem();
        selectedAdmin = adminsList.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        try {
            if (selectedUser != null) {
                URL urlMoreInfo = new File("src/GUI/Views/AdminEditUserView.fxml").toURI().toURL();
                Parent root = FXMLLoader.load(urlMoreInfo);
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.show();
            } else if (selectedAdmin != null){
                alert.setContentText("Please select a User, admins doesn't have views.");
                alert.showAndWait();
            }
            else {

                alert.setContentText("Please select a user first!");
                alert.showAndWait();
            }
        } catch (IOException sqlException) {
            sqlException.printStackTrace();
        }
    }

    /**
     * This is the initialize method.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        adminsList.setItems(userModel.getAdminsList());
        usersList.setItems(userModel.getUsersList());
        usersObservableList.addListener(new ListChangeListener<User>() {
            @Override
            public void onChanged(Change<? extends User> change) {
                usersList.setItems(usersObservableList);
            }
        });
        adminsObservableList.addListener(new ListChangeListener<User>() {
            @Override
            public void onChanged(Change<? extends User> change) {
                adminsList.setItems(adminsObservableList);
            }
        });
    }

    /**
     * This method blocks the user from making multiple selections from different lists.
     * @param mouseEvent
     */
    public void handleAdminFocus(MouseEvent mouseEvent) {
        usersList.getSelectionModel().clearSelection();
    }

    /**
     * This method has the same job as the above. It just works the other way around. Blocking selection from both admin and user list simultaneously.
     * @param mouseEvent
     */
    public void handleUserFocus(MouseEvent mouseEvent) {
        adminsList.getSelectionModel().clearSelection();
    }

    /**
     * This clears the static lists.
     */
    public static void emptyStaticLists() {
        usersObservableList.clear();
        adminsObservableList.clear();
    }

    /**
     * This method returns the selected user or admin object, from the list it is selected from, this is to use it in other controllers.
     * @return
     */
    public static User getSelectedUser() {
        if (selectedUser != null){
            return selectedUser;
        } else if (selectedAdmin != null){
            return selectedAdmin;
        }
        return null;
    }

    /**
     * This method handles the admin logout button. It lets admins log out to the Login View..
     * @param actionEvent
     * @throws IOException
     */
    public void handleAdminLogoutButton(ActionEvent actionEvent) throws IOException {
        Stage thisStage = (Stage) adminLogoutButton.getScene().getWindow();
        URL userUrl = new File("src/GUI/Views/LoginView.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(userUrl);
        Scene scene = new Scene(root,1270,720);
        Stage stage = new Stage();
        stage.setTitle("Arla KPI");
        stage.getIcons().add(new Image("Resources/Images/ArlaLogo100x100.png"));
        stage.setMinWidth(350);
        stage.setMinHeight(470);
        stage.setScene(scene);
        thisStage.hide();
        stage.show();
    }
}
