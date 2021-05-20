package GUI.Controllers;

import BE.FileInfo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserHTMLViewController implements Initializable {

    @FXML
    private WebView websiteViewer;
    private WebEngine webEngine;
    private URI uri;
    private URL uriToUrl;
    private FileInfo selectedFile;
    private String filePath;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectedFile = UserSelectViewController.getSelectedFileInfo();
        filePath = selectedFile.getFilePath();
        File f = new File(filePath);
        try {
            uri = f.toURI();
            uriToUrl = uri.toURL();
            webEngine = websiteViewer.getEngine();
            webEngine.load(String.valueOf(uri));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}

