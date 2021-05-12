package GUI.Controllers;
import GUI.Models.FileModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class UserPDFViewController implements Initializable {
    @FXML
    private ImageView pdfImage;
    private FileModel fileModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fileModel = new FileModel();
        try {
            fileModel.getPDFData();
            File file = new File("src/Resources/Temp/temp.jpg");
            Image image = new Image(file.toURI().toString());
            pdfImage.setImage(image);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
