package GUI.Controllers;

import BE.User;
import GUI.Models.UserModel;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class  EditUserInfoViewController implements Initializable {

    @FXML
    private Button cancelEditUserButton;
    @FXML
    private Button editUserButton;
    @FXML
    private CheckBox showHidePassword;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField loginNameField;
    @FXML
    private TextField fullNameField;

    private SimpleBooleanProperty showPassword;
    private Tooltip tooltip;
    private String typedFullName;
    private String typedLoginName;
    private String typedPassword;
    private User selectedUser;
    private UserModel userModel;

    /**
     * This is the EditUserInfoViewController constructor.
     * @throws IOException
     */
    public EditUserInfoViewController() throws IOException {
        this.showPassword = new SimpleBooleanProperty();
        userModel = new UserModel();
        tooltip = new Tooltip();
    }

    /**
     * This method handles the edit user button. It lets the admin edit the user info.
     * @param actionEvent
     * @throws IOException
     */
    public void handleEditUserButton(ActionEvent actionEvent) throws IOException {
        typedFullName = fullNameField.getText();
        typedLoginName = loginNameField.getText();
        typedPassword = passwordField.getText();
        if (typedFullName != null && typedLoginName != null && typedPassword != null){
            userModel.editUser(selectedUser,typedFullName,typedLoginName,typedPassword);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("User " + typedFullName + " has been edited!");
            alert.showAndWait();
            Stage stage = (Stage) editUserButton.getScene().getWindow();
            stage.close();
        }

    }

    /**
     * This method lets and admin cancel the edit user process.
     * @param actionEvent
     */
    public void handleCancelEditUserButton(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelEditUserButton.getScene().getWindow();
        stage.close();
    }

    /**
     * This is the initialize method.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectedUser = AdminMainViewController.getSelectedUser();
        fullNameField.setText(selectedUser.getFullName());
        showPassword.addListener((ChangeListener<Boolean>)(observable, oldValue, newValue) -> {
            if(newValue){
                showPassword();
            } else {
                hidePassword();
            }
        });
        tooltip.setShowDelay(Duration.ZERO);
        tooltip.setAutoHide(false);
        tooltip.setMinWidth(50);
        passwordField.setOnKeyTyped(e -> {
            if (showPassword.get()){
                showPassword();
            }
        });
        showPassword.bind(showHidePassword.selectedProperty());
    }

    /**
     * This method lets the user show a given password.
     */
    private void showPassword() {
        Stage stage = (Stage) passwordField.getScene().getWindow();
        Point2D p = passwordField.localToScene(passwordField.getBoundsInLocal().getMaxX(), passwordField.getBoundsInLocal().getMaxY());
        tooltip.setText(passwordField.getText());
        tooltip.show(passwordField,
                p.getX() + stage.getScene().getX() + stage.getX(),
                p.getY() + stage.getScene().getY() + stage.getY());
    }

    /**
     * This method lets the user hide a given password.
     */
    private void hidePassword() {
        tooltip.setText("");
        tooltip.hide();
    }
}
