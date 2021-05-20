package GUI.Controllers;

import BE.FileInfo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;

public class UserHTMLViewController implements Initializable {

    @FXML
    private WebView websiteViewer;
    private WebEngine webEngine;
    private URI uri;
    private FileInfo selectedFile;
    private String filePath;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectedFile = UserSelectViewController.getSelectedFileInfo();
        filePath = selectedFile.getFilePath();
        File f = new File(filePath);
        uri = f.toURI();
        webEngine = websiteViewer.getEngine();
        webEngine.load(String.valueOf(uri));
    }
}

