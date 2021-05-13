package GUI.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            uri = new URI("http://youtube.com");
            uriToUrl = uri.toURL();
            webEngine = websiteViewer.getEngine();
            webEngine.load(String.valueOf(uriToUrl));
        } catch (URISyntaxException | MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
