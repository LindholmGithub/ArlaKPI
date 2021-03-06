package GUI.Controllers;

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

public class CreateNewUserViewController implements Initializable {
    @FXML
    private CheckBox showHidePassword;
    @FXML
    private TextField fullNameField;
    @FXML
    private TextField loginNameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private CheckBox adminCheckBox;
    @FXML
    private Button createUserButton;
    @FXML
    private Button cancelCreateUserButton;
    private Tooltip tooltip;
    private SimpleBooleanProperty showPassword;
    private String typedFullName;
    private String typedLoginName;
    private String typedPassword;
    private Boolean isAdmin;
    private UserModel userModel;

    /**
     * This is the constructor for the CreateNewUserViewController.
     * @throws IOException
     */
    public CreateNewUserViewController() throws IOException {
        this.showPassword = new SimpleBooleanProperty();
        userModel = new UserModel();
        tooltip = new Tooltip();
    }

    /**
     * This method handles the create user button. It lets an admin create a new user.
     * @param actionEvent
     * @throws IOException
     */
    public void handleCreateUserButton(ActionEvent actionEvent) throws IOException {
        typedFullName = fullNameField.getText();
        typedLoginName = loginNameField.getText();
        typedPassword = passwordField.getText();
        isAdmin = adminCheckBox.isSelected();
        if (typedFullName != null && typedLoginName != null && typedPassword != null) {
            userModel.addUser(typedFullName, typedLoginName, typedPassword, isAdmin);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("User created");
            alert.setHeaderText("Success!");
            alert.setContentText("User " + typedFullName + " has been created!");
            alert.showAndWait();
            Stage stage = (Stage) createUserButton.getScene().getWindow();
            stage.close();
        }


    }

    /**
     * This method handles the cancel create user button. It lets the admin cancel their create user process.
     * @param actionEvent
     */
    public void handleCancelCreateUserButton(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelCreateUserButton.getScene().getWindow();
        stage.close();
    }

    /**
     * This is the initialize method.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
