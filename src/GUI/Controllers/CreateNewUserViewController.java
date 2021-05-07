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

    public CreateNewUserViewController() throws IOException {
        this.showPassword = new SimpleBooleanProperty();
        userModel = new UserModel();
        tooltip = new Tooltip();
    }

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

    public void handleCancelCreateUserButton(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelCreateUserButton.getScene().getWindow();
        stage.close();
    }

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

    private void showPassword() {
        Stage stage = (Stage) passwordField.getScene().getWindow();
        Point2D p = passwordField.localToScene(passwordField.getBoundsInLocal().getMaxX(), passwordField.getBoundsInLocal().getMaxY());
        tooltip.setText(passwordField.getText());
        tooltip.show(passwordField,
                p.getX() + stage.getScene().getX() + stage.getX(),
                p.getY() + stage.getScene().getY() + stage.getY());
    }

    private void hidePassword() {
        tooltip.setText("");
        tooltip.hide();
    }
}
