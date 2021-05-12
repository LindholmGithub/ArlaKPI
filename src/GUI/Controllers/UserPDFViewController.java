package GUI.Controllers;

import DAL.DAO.FileDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserPDFViewController implements Initializable {
    @FXML
    private ImageView pdfImage;
    private FileDAO fileDAO;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fileDAO = new FileDAO();
        try {
            fileDAO.getPDFData();
            File file = new File("src/Resources/Temp/temp.jpg");
            Image image = new Image(file.toURI().toString());
            pdfImage.setImage(image);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
