package GUI.Controllers;
import BE.FileInfo;
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
    private String filePath;
    private String fileName;
    private FileInfo selectedFileInfo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            fileModel = new FileModel();
            selectedFileInfo = UserSelectViewController.getSelectedFileInfo();
            filePath = selectedFileInfo.getFilePath();
            fileName = "src/Resources/Temp/" + filePath.substring(filePath.lastIndexOf("\\")+1,filePath.lastIndexOf(".")) + ".jpg";
            fileModel.getPDFData(filePath);
            File file = new File(fileName);
            Image image = new Image(file.toURI().toString());
            pdfImage.setImage(image);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
